/*
 * This is part of Geomajas, a GIS framework, http://www.geomajas.org/.
 *
 * Copyright 2008-2012 Geosparc nv, http://www.geosparc.com/, Belgium.
 *
 * The program is available in open source according to the GNU Affero
 * General Public License. All contributions in this program are covered
 * by the Geomajas Contributors License Agreement. For full licensing
 * details, see LICENSE.txt in the project root.
 */
package org.geomajas.plugin.deskmanager.client.gwt.manager.beheer.loketten;

import org.geomajas.plugin.deskmanager.client.gwt.manager.beheer.common.SaveButtonBar;
import org.geomajas.plugin.deskmanager.client.gwt.manager.beheer.common.SaveButtonBar.WoaEventHandler;
import org.geomajas.plugin.deskmanager.client.gwt.manager.beheer.common.themeconfig.ThemeConfigurationPanel;
import org.geomajas.plugin.deskmanager.client.gwt.manager.beheer.service.CommService;
import org.geomajas.plugin.deskmanager.client.gwt.manager.beheer.service.DataCallback;
import org.geomajas.plugin.deskmanager.client.gwt.manager.events.GeodeskEvent;
import org.geomajas.plugin.deskmanager.client.gwt.manager.events.Whiteboard;
import org.geomajas.plugin.deskmanager.client.gwt.manager.util.GeodeskDtoUtil;
import org.geomajas.plugin.deskmanager.command.manager.dto.SaveGeodeskRequest;
import org.geomajas.plugin.deskmanager.domain.dto.GeodeskDto;
import org.geomajas.widget.advancedviews.configuration.client.ThemesInfo;

import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * Panel to allow theme configuration on the geodesk.
 * 
 * @author Oliver May
 * 
 */
public class GeodeskThemeConfig extends VLayout implements WoaEventHandler {

	private GeodeskDto geodesk;

	private ThemeConfigurationPanel themePanel;

	public GeodeskThemeConfig() {
		super(5);

		SaveButtonBar buttonBar = new SaveButtonBar(this);
		addMember(buttonBar);

		// -------------------------------------------------

		themePanel = new ThemeConfigurationPanel();
		// themePanel.setDisabled(true);
		themePanel.setWidth100();
		themePanel.setHeight100();
		// themePanel.setOverflow(Overflow.AUTO);
		themePanel.setDisabled(true);

		addMember(themePanel);
	}

	public void setGeodesk(GeodeskDto geodesk) {
		this.geodesk = geodesk;
		if (geodesk != null) {
			themePanel.setMainMap(GeodeskDtoUtil.getMainMap(geodesk));

			if (GeodeskDtoUtil.getMainMapClientWidgetInfo(geodesk).get(ThemesInfo.IDENTIFIER) != null) {
				themePanel.setThemeConfig((ThemesInfo) GeodeskDtoUtil.getMainMapClientWidgetInfo(geodesk).get(
						ThemesInfo.IDENTIFIER));
			}
		}
	}

	// -- SaveButtonBar events --------------------------------------------------------

	public boolean onEditClick(ClickEvent event) {
		themePanel.setDisabled(false);
		return true;
	}

	public boolean onSaveClick(ClickEvent event) {
		// If we save and the clientwidgetinfo was not yet defined on the geodesk, overwrite it.
		geodesk.getMainMapClientWidgetInfos().put(ThemesInfo.IDENTIFIER,
				GeodeskDtoUtil.getMainMapClientWidgetInfo(geodesk).get(ThemesInfo.IDENTIFIER));

		// geodesk.setLayout(themePanel.getLoketLayout());
		CommService.saveGeodesk(geodesk, SaveGeodeskRequest.SAVE_CLIENTWIDGETINFO);
		themePanel.setDisabled(true);
		return true;
	}

	public boolean onCancelClick(ClickEvent event) {
		setGeodesk(geodesk);
		themePanel.setDisabled(true);
		// Reload the geodesk
		CommService.getGeodesk(geodesk.getId(), new DataCallback<GeodeskDto>() {

			public void execute(GeodeskDto result) {
				Whiteboard.fireEvent(new GeodeskEvent(result));
			}
		});
		return true;
	}
}