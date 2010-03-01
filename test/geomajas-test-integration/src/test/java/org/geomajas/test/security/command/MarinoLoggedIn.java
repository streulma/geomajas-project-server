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

package org.geomajas.test.security.command;

import org.geomajas.command.Command;
import org.geomajas.command.CommandResponse;
import org.geomajas.command.EmptyCommandRequest;
import org.geomajas.global.ExceptionCode;
import org.geomajas.global.GeomajasSecurityException;
import org.geomajas.security.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Command which verifies that the correct user is logged in (checking security context setup).
 *
 * @author Joachim Van der Auwera
 */
@Component
public class MarinoLoggedIn implements Command<EmptyCommandRequest, CommandResponse> {

	@Autowired
	private SecurityContext securityContext;

	public CommandResponse getEmptyCommandResponse() {
		return new CommandResponse();
	}

	public void execute(EmptyCommandRequest emptyCommandRequest, CommandResponse commandResponse) throws Exception {
		if(securityContext.getToken() == null){
			throw new GeomajasSecurityException(ExceptionCode.TEST);
		}
		if(!"marino".equals(securityContext.getUserId())){
			throw new GeomajasSecurityException(ExceptionCode.TEST);
		}
	}
}
