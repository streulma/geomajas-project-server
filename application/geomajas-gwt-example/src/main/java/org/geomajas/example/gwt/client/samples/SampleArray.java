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

package org.geomajas.example.gwt.client.samples;

import org.geomajas.example.gwt.client.samples.attribute.AttributeSearchSample;
import org.geomajas.example.gwt.client.samples.attribute.FeatureListGridSample;
import org.geomajas.example.gwt.client.samples.attribute.SearchSample;
import org.geomajas.example.gwt.client.samples.base.SampleTreeNode;
import org.geomajas.example.gwt.client.samples.controller.CircleControllerSample;
import org.geomajas.example.gwt.client.samples.controller.ControllerOnElementSample;
import org.geomajas.example.gwt.client.samples.controller.CustomControllerSample;
import org.geomajas.example.gwt.client.samples.controller.FallbackControllerSample;
import org.geomajas.example.gwt.client.samples.controller.RectangleControllerSample;
import org.geomajas.example.gwt.client.samples.editing.EditLineLayerSample;
import org.geomajas.example.gwt.client.samples.editing.EditMultiLineLayerSample;
import org.geomajas.example.gwt.client.samples.editing.EditMultiPointLayerSample;
import org.geomajas.example.gwt.client.samples.editing.EditMultiPolygonLayerSample;
import org.geomajas.example.gwt.client.samples.editing.EditPointLayerSample;
import org.geomajas.example.gwt.client.samples.editing.EditPolygonLayerSample;
import org.geomajas.example.gwt.client.samples.i18n.I18nProvider;
import org.geomajas.example.gwt.client.samples.layer.GeoToolsSample;
import org.geomajas.example.gwt.client.samples.layer.GoogleSample;
import org.geomajas.example.gwt.client.samples.layer.OpenStreetMapSample;
import org.geomajas.example.gwt.client.samples.layer.WmsSample;
import org.geomajas.example.gwt.client.samples.layertree.LayertreeSample;
import org.geomajas.example.gwt.client.samples.layertree.LegendSample;
import org.geomajas.example.gwt.client.samples.mapwidget.CrsSample;
import org.geomajas.example.gwt.client.samples.mapwidget.MaxBoundsToggleSample;
import org.geomajas.example.gwt.client.samples.mapwidget.NavigationSample;
import org.geomajas.example.gwt.client.samples.mapwidget.OverviewMapSample;
import org.geomajas.example.gwt.client.samples.mapwidget.PanScaleToggleSample;
import org.geomajas.example.gwt.client.samples.mapwidget.RenderingSample;
import org.geomajas.example.gwt.client.samples.mapwidget.UnitTypesSample;
import org.geomajas.example.gwt.client.samples.mapwidget.WorldScreenSample;
import org.geomajas.example.gwt.client.samples.security.AttributeSecuritySample;
import org.geomajas.example.gwt.client.samples.security.CommandSecuritySample;
import org.geomajas.example.gwt.client.samples.security.FilterSecuritySample;
import org.geomajas.example.gwt.client.samples.security.LayerSecuritySample;
import org.geomajas.example.gwt.client.samples.security.LoginSample;
import org.geomajas.example.gwt.client.samples.security.ToolSecuritySample;
import org.geomajas.example.gwt.client.samples.toolbar.CustomToolbarSample;
import org.geomajas.example.gwt.client.samples.toolbar.CustomToolbarToolsSample;
import org.geomajas.example.gwt.client.samples.toolbar.ScaleSelectCustomSample;
import org.geomajas.example.gwt.client.samples.toolbar.ScaleSelectDefaultSample;
import org.geomajas.example.gwt.client.samples.toolbar.ToolbarFeatureInfoSample;
import org.geomajas.example.gwt.client.samples.toolbar.ToolbarMeasureSample;
import org.geomajas.example.gwt.client.samples.toolbar.ToolbarNavigationSample;
import org.geomajas.example.gwt.client.samples.toolbar.ToolbarSelectionSample;

/**
 * <p>
 * List of all test samples available in the <code>TreeGrid</code> on the left side.
 * </p>
 * 
 * @author Pieter De Graef
 */
public final class SampleArray {

	private SampleArray() {
	}

	public static SampleTreeNode[] getSampleArray() {
		return new SampleTreeNode[] {
				// MapWidget samples:
				new SampleTreeNode(I18nProvider.getSampleMessages().treeGroupLayers(),
						"[ISOMORPHIC]/geomajas/osgeo/layer.png", "Layers", "topLevel"),
				new SampleTreeNode(I18nProvider.getSampleMessages().osmTitle(),
						"[ISOMORPHIC]/geomajas/osgeo/layer-raster.png", OpenStreetMapSample.OSM_TITLE, "Layers",
						OpenStreetMapSample.FACTORY),
				new SampleTreeNode(I18nProvider.getSampleMessages().googleTitle(),
						"[ISOMORPHIC]/geomajas/osgeo/layer-raster.png", GoogleSample.TITLE, "Layers",
						GoogleSample.FACTORY),
				new SampleTreeNode(I18nProvider.getSampleMessages().wmsTitle(),
						"[ISOMORPHIC]/geomajas/osgeo/layer-wms.png", WmsSample.WMS_TITLE, "Layers", WmsSample.FACTORY),
				new SampleTreeNode(I18nProvider.getSampleMessages().geoTitle(),
						"[ISOMORPHIC]/geomajas/osgeo/layer-vector.png", GeoToolsSample.TITLE, "Layers",
						GeoToolsSample.FACTORY),

				new SampleTreeNode(I18nProvider.getSampleMessages().treeGroupMap(),
						"[ISOMORPHIC]/geomajas/example/images/silk/world.png", "MapWidget", "topLevel"),
				new SampleTreeNode(I18nProvider.getSampleMessages().navigationTitle(),
						"[ISOMORPHIC]/geomajas/example/images/silk/world.png", NavigationSample.TITLE, "MapWidget",
						NavigationSample.FACTORY),
				new SampleTreeNode(I18nProvider.getSampleMessages().crsTitle(),
						"[ISOMORPHIC]/geomajas/example/images/silk/world.png", CrsSample.TITLE, "MapWidget",
						CrsSample.FACTORY),
				new SampleTreeNode(I18nProvider.getSampleMessages().unitTypesTitle(),
						"[ISOMORPHIC]/geomajas/example/images/silk/world.png", UnitTypesSample.TITLE, "MapWidget",
						UnitTypesSample.FACTORY),
				new SampleTreeNode(I18nProvider.getSampleMessages().maxBoundsToggleTitle(),
						"[ISOMORPHIC]/geomajas/example/images/silk/world.png", MaxBoundsToggleSample.TITLE,
						"MapWidget", MaxBoundsToggleSample.FACTORY),
				new SampleTreeNode(I18nProvider.getSampleMessages().panScaleToggleTitle(),
						"[ISOMORPHIC]/geomajas/example/images/silk/world.png", PanScaleToggleSample.TITLE, "MapWidget",
						PanScaleToggleSample.FACTORY),
				new SampleTreeNode(I18nProvider.getSampleMessages().renderingTitle(),
						"[ISOMORPHIC]/geomajas/osgeo/edit.png", RenderingSample.TITLE, "MapWidget",
						RenderingSample.FACTORY),
				new SampleTreeNode(I18nProvider.getSampleMessages().screenWorldTitle(),
						"[ISOMORPHIC]/geomajas/osgeo/edit.png", WorldScreenSample.TITLE, "MapWidget",
						WorldScreenSample.FACTORY),
				new SampleTreeNode(I18nProvider.getSampleMessages().overviewMapTitle(),
						"[ISOMORPHIC]/geomajas/osgeo/region.png", OverviewMapSample.TITLE, "MapWidget",
						OverviewMapSample.FACTORY),

				// Editing:
				new SampleTreeNode(I18nProvider.getSampleMessages().treeGroupEditing(),
						"[ISOMORPHIC]/geomajas/osgeo/edit.png", "GeoGraphicEditing", "topLevel"),
				new SampleTreeNode(I18nProvider.getSampleMessages().editPointLayerTitle(),
						"[ISOMORPHIC]/geomajas/osgeo/edit.png", EditPointLayerSample.TITLE, "GeoGraphicEditing",
						EditPointLayerSample.FACTORY),
				new SampleTreeNode(I18nProvider.getSampleMessages().editLineLayerTitle(),
						"[ISOMORPHIC]/geomajas/osgeo/edit.png", EditLineLayerSample.TITLE, "GeoGraphicEditing",
						EditLineLayerSample.FACTORY),
				new SampleTreeNode(I18nProvider.getSampleMessages().editPolygonLayerTitle(),
						"[ISOMORPHIC]/geomajas/osgeo/edit.png", EditPolygonLayerSample.TITLE, "GeoGraphicEditing",
						EditPolygonLayerSample.FACTORY),
				new SampleTreeNode(I18nProvider.getSampleMessages().editMultiPointLayerTitle(),
						"[ISOMORPHIC]/geomajas/osgeo/edit.png", EditMultiPointLayerSample.TITLE, "GeoGraphicEditing",
						EditMultiPointLayerSample.FACTORY),
				new SampleTreeNode(I18nProvider.getSampleMessages().editMultiLineLayerTitle(),
						"[ISOMORPHIC]/geomajas/osgeo/edit.png", EditMultiLineLayerSample.TITLE, "GeoGraphicEditing",
						EditMultiLineLayerSample.FACTORY),
				new SampleTreeNode(I18nProvider.getSampleMessages().editMultiPolygonLayerTitle(),
						"[ISOMORPHIC]/geomajas/osgeo/edit.png", EditMultiPolygonLayerSample.TITLE, "GeoGraphicEditing",
						EditMultiPolygonLayerSample.FACTORY),

				// LayerTree & Legend samples:
				new SampleTreeNode(I18nProvider.getSampleMessages().treeGroupLayerTree(),
						"[ISOMORPHIC]/geomajas/osgeo/mapset.png", "Layertree", "topLevel"),
				new SampleTreeNode(I18nProvider.getSampleMessages().layertreeTitle(),
						"[ISOMORPHIC]/geomajas/osgeo/mapset.png", LayertreeSample.TITLE, "Layertree",
						LayertreeSample.FACTORY),
				new SampleTreeNode(I18nProvider.getSampleMessages().legendTitle(),
						"[ISOMORPHIC]/geomajas/osgeo/legend-add.png", LegendSample.TITLE, "Layertree",
						LegendSample.FACTORY),

				// Attribute samples:
				new SampleTreeNode(I18nProvider.getSampleMessages().treeGroupAttributes(),
						"[ISOMORPHIC]/geomajas/osgeo/table.png", "FeatureListGridGroup", "topLevel"),
				new SampleTreeNode(I18nProvider.getSampleMessages().fltTitle(),
						"[ISOMORPHIC]/geomajas/osgeo/table.png", FeatureListGridSample.TITLE, "FeatureListGridGroup",
						FeatureListGridSample.FACTORY),
				new SampleTreeNode(I18nProvider.getSampleMessages().searchTitle(),
						"[ISOMORPHIC]/geomajas/osgeo/table.png", SearchSample.TITLE, "FeatureListGridGroup",
						SearchSample.FACTORY),
				new SampleTreeNode(I18nProvider.getSampleMessages().search2Title(),
						"[ISOMORPHIC]/geomajas/osgeo/table.png", AttributeSearchSample.TITLE, "FeatureListGridGroup",
						AttributeSearchSample.FACTORY),

				// Map controller:
				new SampleTreeNode(I18nProvider.getSampleMessages().treeGroupMapController(),
						"[ISOMORPHIC]/geomajas/osgeo/tools.png", "MapController", "topLevel"),

				new SampleTreeNode(I18nProvider.getSampleMessages().customControllerTitle(),
						"[ISOMORPHIC]/geomajas/osgeo/tools.png", CustomControllerSample.TITLE, "MapController",
						CustomControllerSample.FACTORY),
				new SampleTreeNode(I18nProvider.getSampleMessages().controllerOnElementTitle(),
						"[ISOMORPHIC]/geomajas/osgeo/tools.png", ControllerOnElementSample.TITLE, "MapController",
						ControllerOnElementSample.FACTORY),
				new SampleTreeNode(I18nProvider.getSampleMessages().rectangleControllerTitle(),
						"[ISOMORPHIC]/geomajas/osgeo/tools.png", RectangleControllerSample.TITLE, "MapController",
						RectangleControllerSample.FACTORY),
				new SampleTreeNode(I18nProvider.getSampleMessages().circleControllerTitle(),
						"[ISOMORPHIC]/geomajas/osgeo/tools.png", CircleControllerSample.TITLE, "MapController",
						CircleControllerSample.FACTORY),
				new SampleTreeNode(I18nProvider.getSampleMessages().fallbackControllerTitle(),
						"[ISOMORPHIC]/geomajas/osgeo/tools.png", FallbackControllerSample.TITLE, "MapController",
						FallbackControllerSample.FACTORY),

				// Toolbar and controllers:
				new SampleTreeNode(I18nProvider.getSampleMessages().treeGroupToolbarAndControllers(),
						"[ISOMORPHIC]/geomajas/osgeo/zoom-in.png", "ToolbarAndControllers", "topLevel"),

				new SampleTreeNode(I18nProvider.getSampleMessages().toolbarNavigationTitle(),
						"[ISOMORPHIC]/geomajas/osgeo/pan.png", ToolbarNavigationSample.TITLE, "ToolbarAndControllers",
						ToolbarNavigationSample.FACTORY),

				new SampleTreeNode(I18nProvider.getSampleMessages().toolbarSelectionTitle(),
						"[ISOMORPHIC]/geomajas/osgeo/select.png", ToolbarSelectionSample.TITLE,
						"ToolbarAndControllers", ToolbarSelectionSample.FACTORY),

				new SampleTreeNode(I18nProvider.getSampleMessages().toolbarMeasureTitle(),
						"[ISOMORPHIC]/geomajas/osgeo/length-measure.png", ToolbarMeasureSample.TITLE,
						"ToolbarAndControllers", ToolbarMeasureSample.FACTORY),

				new SampleTreeNode(I18nProvider.getSampleMessages().toolbarFeatureInfoTitle(),
						"[ISOMORPHIC]/geomajas/osgeo/info.png", ToolbarFeatureInfoSample.TITLE,
						"ToolbarAndControllers", ToolbarFeatureInfoSample.FACTORY),

				new SampleTreeNode(I18nProvider.getSampleMessages().scaleSelectDefaultTitle(),
						"[ISOMORPHIC]/geomajas/osgeo/tools.png", ScaleSelectDefaultSample.TITLE,
						"ToolbarAndControllers", ScaleSelectDefaultSample.FACTORY),

				new SampleTreeNode(I18nProvider.getSampleMessages().scaleSelectCustomTitle(),
						"[ISOMORPHIC]/geomajas/osgeo/tools.png", ScaleSelectCustomSample.TITLE,
						"ToolbarAndControllers", ScaleSelectCustomSample.FACTORY),

				new SampleTreeNode(I18nProvider.getSampleMessages().customToolbarToolsTitle(),
						"[ISOMORPHIC]/geomajas/osgeo/tools.png", CustomToolbarToolsSample.TITLE,
						"ToolbarAndControllers", CustomToolbarToolsSample.FACTORY),

				new SampleTreeNode(I18nProvider.getSampleMessages().customToolbarTitle(),
						"[ISOMORPHIC]/geomajas/osgeo/tools.png", CustomToolbarSample.TITLE, "ToolbarAndControllers",
						CustomToolbarSample.FACTORY),

				// Security samples:
				new SampleTreeNode(I18nProvider.getSampleMessages().treeGroupSecurity(),
						"[ISOMORPHIC]/geomajas/silk/key.png", "Security", "topLevel"),
				new SampleTreeNode(I18nProvider.getSampleMessages().loginTitle(),
						"[ISOMORPHIC]/geomajas/springsecurity/key_go.png", LoginSample.LOGIN_TITLE, "Security",
						LoginSample.FACTORY),
				new SampleTreeNode(I18nProvider.getSampleMessages().layerSecurityTitle(),
						"[ISOMORPHIC]/geomajas/springsecurity/key_go.png", LayerSecuritySample.LAYER_SECUTIRY_TITLE,
						"Security", LayerSecuritySample.FACTORY),
				new SampleTreeNode(I18nProvider.getSampleMessages().filterSecurityTitle(),
						"[ISOMORPHIC]/geomajas/springsecurity/key_go.png", FilterSecuritySample.TITLE, "Security",
						FilterSecuritySample.FACTORY),
				new SampleTreeNode(I18nProvider.getSampleMessages().attributeSecurityTitle(),
						"[ISOMORPHIC]/geomajas/springsecurity/key_go.png", AttributeSecuritySample.TITLE, "Security",
						AttributeSecuritySample.FACTORY),
				new SampleTreeNode(I18nProvider.getSampleMessages().commandSecurityTitle(),
						"[ISOMORPHIC]/geomajas/springsecurity/key_go.png", CommandSecuritySample.TITLE, "Security",
						CommandSecuritySample.FACTORY),
				new SampleTreeNode(I18nProvider.getSampleMessages().toolSecurityTitle(),
						"[ISOMORPHIC]/geomajas/springsecurity/key_go.png", ToolSecuritySample.TITLE, "Security",
						ToolSecuritySample.FACTORY) };
	}
}
