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
package org.geomajas.plugin.printing.document;

import java.io.OutputStream;
import java.text.DateFormat;
import java.util.Date;

import com.lowagie.text.DocumentException;

/**
 * <p>
 * Wrapper for a pdf document generated by iText, the wrapper contains
 * the document in memory as a ByteArrayOutputStream and attributes how
 * the file is downloaded.
 * </p>
 *
 * @author Oliver May
 */
public abstract class AbstractDocument implements Document {

	protected String fileName;

	/**
	 * @return the filename we want to give this document, this is generated
	 *         using the current date/time if no explicit filename is set.
	 */
	public String getFilename() {
		if (fileName != null) {
			return fileName;
		} else {
			return "geomajas_" + DateFormat.getInstance().format(new Date()) + ".pdf";
		}
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public abstract void render(OutputStream os) throws DocumentException;

}
