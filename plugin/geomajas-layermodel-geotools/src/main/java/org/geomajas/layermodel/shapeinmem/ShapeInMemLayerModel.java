/*
 * This file is part of Geomajas, a component framework for building
 * rich Internet applications (RIA) with sophisticated capabilities for the
 * display, analysis and management of geographic information.
 * It is a building block that allows developers to add maps
 * and other geographic data capabilities to their web applications.
 *
 * Copyright 2008-2010 Geosparc, http://www.geosparc.com, Belgium
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.geomajas.layermodel.shapeinmem;

import org.geomajas.configuration.VectorLayerInfo;
import org.geomajas.geometry.Bbox;
import org.geomajas.global.ExceptionCode;
import org.geomajas.global.GeomajasException;
import org.geomajas.layer.LayerException;
import org.geomajas.layer.LayerModel;
import org.geomajas.layer.feature.FeatureModel;
import org.geomajas.service.BboxService;
import org.geomajas.service.FilterService;
import org.geomajas.service.GeoService;
import org.geotools.data.DataStore;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.filter.Filter;
import org.opengis.filter.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Layer model for handling shape files in memory.
 *
 * @author check subversion
 */
@Component
@Scope("prototype")
public class ShapeInMemLayerModel extends FeatureSourceRetriever implements LayerModel {

	private static final String CLASSPATH_URL_PROTOCOL = "classpath:";

	private Map<String, SimpleFeature> features = new HashMap<String, SimpleFeature>();

	private FeatureModel featureModel;

	private VectorLayerInfo layerInfo;

	private Filter defaultFilter;

	@Autowired
	private BboxService bboxService;

	@Autowired
	private FilterService filterCreator;

	@Autowired
	private GeoService geoService;

	public void setLayerInfo(VectorLayerInfo layerInfo) throws LayerException {
		this.layerInfo = layerInfo;
		setFeatureSourceName(layerInfo.getFeatureInfo().getDataSourceName());
		initFeatures();
	}


	public void setUrl(URL url) throws LayerException {
		try {
			InputStream in = url.openStream();
			if (in == null) {
				throw new IOException("File not found: " + url);
			}
			in.close();
			setDataStore(new ShapefileDataStore(url));
		} catch (MalformedURLException e) {
			throw new LayerException(ExceptionCode.INVALID_SHAPE_FILE_URL, url);
		} catch (IOException ioe) {
			throw new LayerException(ExceptionCode.CANNOT_CREATE_LAYER_MODEL, ioe, url);
		} catch (GeomajasException ge) {
			throw new LayerException(ExceptionCode.CANNOT_CREATE_LAYER_MODEL, ge, url);
		}
	}

	public void setUrl(String url) throws LayerException {
		try {
			URL realUrl = null;
			if (url.startsWith(CLASSPATH_URL_PROTOCOL)) {
				ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
				if (null == classLoader) {
					classLoader = this.getClass().getClassLoader();
				}
				realUrl = classLoader.getResource(url.substring(CLASSPATH_URL_PROTOCOL.length()));
			}
			if (null == realUrl) {
				realUrl = new URL(url);
			}
			setUrl(realUrl);
		} catch (MalformedURLException mue) {
			throw new LayerException(ExceptionCode.INVALID_SHAPE_FILE_URL, url);
		}
	}

	@Override
	protected void setDataStore(DataStore dataStore) throws LayerException {
		super.setDataStore(dataStore);
		initFeatures();
	}

	public Iterator<?> getElements(Filter queryFilter) throws LayerException {
		Filter filter = queryFilter;
		if (defaultFilter != null) {
			filter = filterCreator.createLogicFilter(filter, "AND", defaultFilter);
		}
		Filter realFilter = filter;
		List<SimpleFeature> filteredList = new ArrayList<SimpleFeature>();
		if (filter instanceof Id) {
			Iterator<?> iterator = ((Id) filter).getIdentifiers().iterator();
			List<String> identifiers = new ArrayList<String>();
			while (iterator.hasNext()) {
				identifiers.add(getFeatureSourceName() + "." + iterator.next());
			}
			realFilter = filterCreator.createFidFilter(identifiers.toArray(new String[identifiers.size()]));
		}
		for (SimpleFeature feature : features.values()) {
			if (realFilter.evaluate(feature)) {
				filteredList.add(feature);
			}
		}
		return filteredList.iterator();
	}

	public Bbox getBounds() throws LayerException {
		return getBounds(Filter.INCLUDE);
	}

	/**
	 * Retrieve the bounds of the specified features.
	 *
	 * @return the bounds of the specified features
	 */
	public Bbox getBounds(Filter queryFilter) throws LayerException {
		Filter filter = queryFilter;
		if (defaultFilter != null) {
			filter = filterCreator.createLogicFilter(filter, "AND", defaultFilter);
		}
		try {
			FeatureCollection<SimpleFeatureType, SimpleFeature> fc = getFeatureSource().getFeatures(filter);
			return bboxService.fromEnvelope(fc.getBounds());
		} catch (IOException ioe) {
			throw new LayerException(ExceptionCode.FEATURE_MODEL_PROBLEM, ioe);
		}
	}

	public FeatureModel getFeatureModel() {
		return featureModel;
	}

	public Object create(Object feature) throws LayerException {
		String id = featureModel.getId(feature);
		if (id != null && !features.containsKey(id)) {
			SimpleFeature realFeature = asFeature(feature);
			features.put(id, realFeature);
			return realFeature;
		}
		return null;
	}

	public Object read(String featureId) throws LayerException {
		return features.get(featureId);
	}

	public Object saveOrUpdate(Object feature) throws LayerException {
		if (read(getFeatureModel().getId(feature)) == null) {
			return create(feature);
		} else {
			// Nothing to do
			return feature;
		}
	}

	public void delete(String featureId) throws LayerException {
		features.remove(featureId);
	}

	public Iterator<?> getObjects(String attributeName, Filter filter) throws LayerException {
		return Collections.EMPTY_LIST.iterator();
	}

	// Private functions:

	private void initFeatures() throws LayerException {
		if (null == layerInfo || null == getDataStore()) {
			return;
		}
		try {
			featureModel = new ShapeInMemFeatureModel(getDataStore(), layerInfo.getFeatureInfo().getDataSourceName(),
					geoService.getSridFromCrs(layerInfo.getCrs()));
			FeatureCollection<SimpleFeatureType, SimpleFeature> col = getFeatureSource().getFeatures();
			FeatureIterator<SimpleFeature> iterator = col.features();
			while (iterator.hasNext()) {
				SimpleFeature feature = iterator.next();
				String id = featureModel.getId(feature);
				features.put(id, feature);
				int intId = Integer.parseInt(id);
				if (intId > nextId) {
					nextId = intId;
				}
			}
			col.close(iterator);
			//		getNextId();
			nextId++;
		} catch (NumberFormatException nfe) {
			throw new LayerException(ExceptionCode.FEATURE_MODEL_PROBLEM, nfe);
		} catch (IOException ioe) {
			throw new LayerException(ExceptionCode.FEATURE_MODEL_PROBLEM, ioe);
		}
	}

	public Filter getDefaultFilter() {
		return defaultFilter;
	}

	public void setDefaultFilter(Filter defaultFilter) {
		this.defaultFilter = defaultFilter;
	}

}