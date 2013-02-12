<%@ page contentType="text/html; charset=Cp1252" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<html:xhtml/>

<div id="adminmenu">
 
	<html:link action="openAppraisalManagerCore"><bean:message key="admin.appraisalmanager.menu.manageappraisals" /></html:link> |
	<html:link action="userList"><bean:message key="admin.appraisalmanager.menu.managestudents" /></html:link> | 
	<html:link action="subjectList"><bean:message key="admin.appraisalmanager.subject.title" /></html:link> | 
	<html:link action="textElementList"><bean:message key="admin.appraisalmanager.menu.manageelements" /></html:link> | 
	<html:link action="openAdminMainMenu"><bean:message key="admin.appraisalmanager.menu.mainmenu" /></html:link> |
	<html:link forward="doLogout"><bean:message key="admin.menu.logout" /></html:link>
	
</div>