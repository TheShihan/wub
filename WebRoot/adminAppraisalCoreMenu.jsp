<%@ page contentType="text/html; charset=Cp1252" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<html:xhtml/>

<div id="adminmenu">
 
	<html:link action="appraisalCoreBasicList"><bean:message key="admin.appraisalmanager.core.basic" /></html:link> |
	<html:link action="appraisalClassList"><bean:message key="admin.appraisalmanager.core.custom" /></html:link> | 
	<html:link action="openAppraisalManager"><bean:message key="admin.menu.appraisalmanager" /></html:link> |
	<html:link forward="doLogout"><bean:message key="admin.menu.logout" /></html:link>
	
</div>