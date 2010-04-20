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
package org.geomajas.configuration;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.geomajas.global.Api;
import org.geomajas.layer.LayerType;

/**
 * Information about a raster layer.
 *
 * @author Joachim Van der Auwera
 * @since 1.6.0
 */
@Api(allMethods = true)
public class RasterLayerInfo extends LayerInfo implements Serializable {

	private static final long serialVersionUID = 151L;

	private String dataSourceName;
	@NotNull
	private int tileWidth;
	@NotNull
	private int tileHeight;
	private List<Double> resolutions;

	/**
	 * Create raster layer.
	 */
	public RasterLayerInfo() {
		setLayerType(LayerType.RASTER);
	}

	/**
	 * Get the data source name. This is used by the layer to know which data source to contact.
	 *
	 * @return data source name
	 */
	public String getDataSourceName() {
		return dataSourceName;
	}

	/**
	 * Set the data source name. This is used by the layer to know which data source to contact.
	 *
	 * @param dataSourceName data source name
	 */
	public void setDataSourceName(String dataSourceName) {
		this.dataSourceName = dataSourceName;
	}

	/**
	 * Get tile width in pixels.
	 * <p/>
	 * Raster layers often use fixed tile sizes which need to be combined to get the full picture.
	 * This allows you to get the width of these tiles (when applicable).
	 *
	 * @return tile width in pixels
	 */
	public int getTileWidth() {
		return tileWidth;
	}

	/**
	 * Set tile width in pixels.
	 * <p/>
	 * Raster layers often use fixed tile sizes which need to be combined to get the full picture.
	 * This allows you to get the width of these tiles (when applicable).
	 *
	 * @param tileWidth tile width
	 */
	public void setTileWidth(int tileWidth) {
		this.tileWidth = tileWidth;
	}

	/**
	 * Get tile height in pixels.
	 * <p/>
	 * Raster layers often use fixed tile sizes which need to be combined to get the full picture.
	 * This allows you to get the height of these tiles (when applicable).
	 *
	 * @return tile height
	 */
	public int getTileHeight() {
		return tileHeight;
	}

	/**
	 * Set tile height in pixels.
	 * <p/>
	 * Raster layers often use fixed tile sizes which need to be combined to get the full picture.
	 * This allows you to get the height of these tiles (when applicable).
	 *
	 * @param tileHeight tile height
	 */
	public void setTileHeight(int tileHeight) {
		this.tileHeight = tileHeight;
	}

	/**
	 * Get the list of supported resolutions for the layer.
	 *
	 * @return list of supported resolutions
	 */
	public List<Double> getResolutions() {
		return resolutions;
	}

	/**
	 * Set the list of supported resolutions.
	 *
	 * @param resolutions resolutions 
	 */
	public void setResolutions(List<Double> resolutions) {
		this.resolutions = resolutions;
	}

}
