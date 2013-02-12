<%@ page contentType="text/html; charset=Cp1252" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<html:xhtml/>

<div id="adminlogon">
	<div class="errors"><html:errors /><br /></div>

	<html:form method="post" action="checkAdminLogon">
		<div class="label">
			<bean:message key="login.admin.user" />
		</div>
		<html:text property="username" size="30" maxlength="45" />
		<br />
		<br />
		<div class="label">
			<bean:message key="login.admin.password" />
		</div>
		<html:password property="password" size="30" maxlength="45" />
		<br />
		<br />
		<html:submit><bean:message key="common.button.login" /></html:submit>
	</html:form>
</div>