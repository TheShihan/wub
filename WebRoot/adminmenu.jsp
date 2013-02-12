<%@ page contentType="text/html; charset=Cp1252" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<html:xhtml/>

<div id="adminmenu"> 
	<html:link action="openChangePassword"><bean:message key="admin.menu.changepassword" /></html:link> |
	<html:link action="adminList"><bean:message key="admin.menu.usermanager" /></html:link> | 
	<html:link action="openAppraisalManager"><bean:message key="admin.menu.appraisalmanager" /></html:link> |
	<html:link action="openSettings"><bean:message key="admin.menu.settings" /></html:link> |
	<html:link action="openStatistics"><bean:message key="admin.menu.statistics" /></html:link> | 
	<html:link forward="doLogout"><bean:message key="admin.menu.logout" /></html:link>
	
</div>