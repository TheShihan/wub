<%@ page contentType="text/html; charset=Cp1252" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<html:xhtml/>

<div class="stdContainer">

	<div class="errors"><html:errors /><br /></div>

	<html:form method="post" action="userLogon">
	
		<div class="label">
			<bean:message key="user.login.name" />
		</div>
		
		<html:text property="useremail" size="30" maxlength="45" />
		<br />
		<br />
		
		<div class="label">
			<bean:message key="user.login.token" />
		</div>
		<%--
		Wir verwenden ein Textfeld und kein Passwortfeld, da so die Eingabe
		der kryptischen Token leichter kontrolliert werden kann, ohne Copy&Paste
		 --%>
		<html:text property="token" size="30" maxlength="45" />
		<br />
		<br />
		
		<html:submit><bean:message key="common.button.login" /></html:submit>
		
	</html:form>
</div>