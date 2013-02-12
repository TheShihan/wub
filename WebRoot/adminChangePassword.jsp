<%@ page contentType="text/html; charset=Cp1252" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<html:xhtml/>

<div class="stdContainer">
	<div class="errors"><html:errors /><br /></div>

	<html:form action="/changePassword">
		<div class="label">
			<bean:message key="admin.changepassword.oldpassword"/>
		</div>
		<html:password property="oldPassword" maxlength="45"/>
		<br />
		<br />
		<div class="label">
			<bean:message key="admin.changepassword.newpassword"/>
		</div>
		<html:password property="newPassword" maxlength="45"/>
		<br />
		<br/>
		<div class="label">
			<bean:message key="admin.changepassword.newpasswordcheck"/>
		</div>
		<html:password property="newPasswordCheck" maxlength="45"/>
		<br/>
		<br/>
		<html:submit><bean:message key="common.button.submit"/></html:submit>
	</html:form>
</div>