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

package org.geomajas.internal.security;

import junit.framework.Assert;
import org.geomajas.security.Authentication;
import org.geomajas.security.BaseAuthorization;
import org.geomajas.security.SavedAuthorization;
import org.geomajas.security.SecurityContext;
import org.geomajas.security.SecurityManager;
import org.geomajas.security.allowall.AllowAllAuthorization;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Test to verify the save and restore of the security context.
 *
 * @author Joachim Van der Auwera
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/org/geomajas/spring/geomajasContext.xml",
		"/org/geomajas/layer/bean/beanContext.xml", "/org/geomajas/layer/bean/layerBeans.xml"})
public class SecurityContextSaveRestoreTest {

	@Autowired
	private SecurityContext securityContext;

	@Autowired
	private SecurityManager securityManager;

	private static final String SECURITY_SERVICE_ID = "ss";
	private static final String USER_ID = "auth";
	private static final String USER_NAME = "Full name";
	private static final Locale USER_LOCALE = Locale.CHINESE;
	private static final String USER_ORGANIZATION = "Geosparc";
	private static final String USER_DIVISION = "Development";

	@Test
	public void testSameUser() {
		SecurityContextImpl context = new SecurityContextImpl();
		List<Authentication> authentications = new ArrayList<Authentication>();
		Authentication auth1 = getAuthentication();
		Authentication auth2 = getAuthentication();
		auth2.setSecurityServiceId("ss2");
		authentications.add(auth1);
		authentications.add(auth2);
		context.setAuthentications("token", authentications);
		Assert.assertEquals("AllowAll@ss|AllowAll@ss2",context.getId());
		Assert.assertEquals(USER_ID,context.getUserId());
		Assert.assertEquals(USER_NAME,context.getUserName());
		Assert.assertEquals(USER_LOCALE,context.getUserLocale());
		Assert.assertEquals(USER_ORGANIZATION,context.getUserOrganization());
		Assert.assertEquals(USER_DIVISION,context.getUserDivision());
		SavedAuthorization sa = context.getSavedAuthorization();
		Assert.assertNotNull(sa);
		securityManager.restoreSecurityContext(sa);
		Assert.assertEquals(context.getId(), securityContext.getId());
		Assert.assertEquals(null, securityContext.getUserId());
		Assert.assertEquals(null, securityContext.getUserName());
		Assert.assertEquals(null, securityContext.getUserLocale());
		Assert.assertEquals(null, securityContext.getUserOrganization());
		Assert.assertEquals(null, securityContext.getUserDivision());

		context.restoreSecurityContext(sa);
		Assert.assertEquals(securityContext.getId(), context.getId());
		Assert.assertEquals(null, context.getUserId());
		Assert.assertEquals(null, context.getUserName());
		Assert.assertEquals(null, context.getUserLocale());
		Assert.assertEquals(null, context.getUserOrganization());
		Assert.assertEquals(null, context.getUserDivision());
	}

	private Authentication getAuthentication() {
		Authentication auth = new Authentication();
		auth.setAuthorizations(new BaseAuthorization[]{new AllowAllAuthorization()});
		auth.setSecurityServiceId(SECURITY_SERVICE_ID);
		auth.setUserId(USER_ID);
		auth.setUserName(USER_NAME);
		auth.setUserLocale(USER_LOCALE);
		auth.setUserOrganization(USER_ORGANIZATION);
		auth.setUserDivision(USER_DIVISION);
		return auth;
	}

}
