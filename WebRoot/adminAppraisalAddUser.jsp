<%@ page contentType="text/html; charset=Cp1252"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>

<html:xhtml />

<div class="stdContainer">
	<h1>
		<bean:message key="admin.appraisalmanager.user.add" />
	</h1>
	<br />
	
	<logic:notEmpty name="userEditForm" property="messageText">
		<p class="infomessage">	
			<bean:write name="userEditForm" property="messageText" />
		</p>
		<br />
	</logic:notEmpty>
	
	<html:form action="userEdit">
		<div class="label">
			<bean:message key="admin.appraisalmanager.user" />
		</div>
		<html:text maxlength="45" size="40" property="name" />
		<br />
		<br />
		<div class="label">
			<bean:message key="admin.appraisalmanager.user.mainclass" />
		</div>
		<html:text maxlength="10" size="40" property="mainClass" />
		<br />
		<br />
		<div class="label">
			<bean:message key="common.email" />
		</div>
		<html:text maxlength="255" size="40" property="email" />
		<br />
		<br />
		<html:hidden property="do" value="saveUser" />
		<html:submit>
			<bean:message key="common.button.add" />
		</html:submit>
	</html:form>


</div>