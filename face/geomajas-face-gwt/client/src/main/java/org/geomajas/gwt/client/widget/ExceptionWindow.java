/*
 * This is part of Geomajas, a GIS framework, http://www.geomajas.org/.
 *
 * Copyright 2008-2011 Geosparc nv, http://www.geosparc.com/, Belgium.
 *
 * The program is available in open source according to the GNU Affero
 * General Public License. All contributions in this program are covered
 * by the Geomajas Contributors License Agreement. For full licensing
 * details, see LICENSE.txt in the project root.
 */

package org.geomajas.gwt.client.widget;

import org.geomajas.global.ExceptionDto;
import org.geomajas.gwt.client.i18n.I18nProvider;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.HTMLFlow;
import com.smartgwt.client.widgets.HTMLPane;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import org.geomajas.gwt.client.util.WidgetLayout;

/**
 * <p>
 * Modal error messaging window that displays an exception. This window is used in case the server throws up an
 * exception during command execution. This window shows the general exception message, and adds an option to view more
 * details about the error. The details will then display the Java class name of the exception and the entire stack
 * trace.
 * </p>
 * 
 * @author Pieter De Graef
 * @since 1.8.0
 */
public class ExceptionWindow extends Window {

	/** The list of exceptions this window should display. */
	private ExceptionDto error;

	/** The button that displays the details about the exception upon clicking. */
	private Button expandButton;

	/** The actual layout that contains the details. Invisible by default. */
	private VLayout detailsLayout;

	// ------------------------------------------------------------------------
	// Constructors:
	// ------------------------------------------------------------------------

	/**
	 * Create a new error messaging window displaying a single exception.
	 * 
	 * @param error
	 *            The exception to display.
	 */
	public ExceptionWindow(ExceptionDto error) {
		super();
		this.error = error;

		buildGui();
		setDetailsVisible(false);
	}

	// ------------------------------------------------------------------------
	// Private methods:
	// ------------------------------------------------------------------------

	/** Build the entire GUI for this widget. */
	private void buildGui() {
		setTitle(I18nProvider.getGlobal().commandError());
		setHeaderIcon(WidgetLayout.iconError);
		setIsModal(true);
		setShowModalMask(true);
		setModalMaskOpacity(50);
		setWidth(450);
		setHeight(132);
		setCanDragResize(true);
		centerInPage();
		setAutoSize(true);

		addItem(createErrorLayout(error));
	}

	/** Create the GUI for a single exception. */
	private VLayout createErrorLayout(ExceptionDto error) {
		VLayout layout = new VLayout();
		layout.setWidth100();
		layout.setHeight100();
		layout.setPadding(10);

		HLayout topLayout = new HLayout(20);
		topLayout.setWidth100();
		Img icon = new Img(WidgetLayout.iconError, 64, 64);
		topLayout.addMember(icon);
		HTMLFlow message = new HTMLFlow();
		message.setWidth100();
		message.setHeight100();
		message.setLayoutAlign(VerticalAlignment.TOP);
		message.setContents("<div style='font-size:12px; font-weight:bold;'>" + error.getMessage() + "</div>");
		topLayout.addMember(message);
		layout.addMember(topLayout);

		if (error.getStackTrace() != null && error.getStackTrace().length > 0) {
			expandButton = new Button("View details");
			expandButton.setWidth(100);
			expandButton.setLayoutAlign(Alignment.RIGHT);
			expandButton.addClickHandler(new ClickHandler() {

				public void onClick(ClickEvent event) {
					setDetailsVisible(!detailsLayout.isVisible());
				}
			});
			layout.addMember(expandButton);

			String content = "<div><b>" + error.getClassName() + ":</b></div><div style='padding-left:10px;'>";
			for (StackTraceElement el : error.getStackTrace()) {
				content += el.toString() + "<br/>";
			}
			content += "</div>";
			HTMLPane detailPane = new HTMLPane();
			detailPane.setContents(content);
			detailPane.setWidth100();
			detailPane.setHeight100();
			detailsLayout = new VLayout();
			detailsLayout.setWidth100();
			detailsLayout.setHeight100();
			detailsLayout.addMember(detailPane);
			detailsLayout.setBorder("1px solid #A0A0A0;");
			layout.addMember(detailsLayout);
		}
		return layout;
	}

	/** Toggle the visibility of the exception details */
	private void setDetailsVisible(boolean detailsVisible) {
		detailsLayout.setVisible(detailsVisible);
		if (detailsVisible) {
			setAutoSize(false);
			expandButton.setTitle("Hide details");
			setHeight(350);
		} else {
			expandButton.setTitle("View details");
			setHeight(132);
		}
	}
}