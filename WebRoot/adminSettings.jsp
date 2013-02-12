<%@ page contentType="text/html; charset=Cp1252" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<html:xhtml/>

<div class="stdContainer">

	<h1>
		<bean:message key="admin.settings.title" />
	</h1>
	
	<div class="errors"><html:errors /><br /></div>

	<html:form action="/settingsManager">
	
		<h2><bean:message key="admin.settings.key" /> / <bean:message key="admin.settings.value" /></h2>
		<br />
		
		<logic:iterate name="settingsListForm" property="settingList" id="settings" >
			<!-- START ITEM -->
			<html:hidden name="settings" property="settingId" indexed="true" />
			<div class="label">
				<bean:write name="settings" property="settingName" />
			</div>
			<html:text size="40" maxlength="255" name="settings" property="settingValue" indexed="true" />
			<br />
			<br />
			<!-- END ITEM -->
		</logic:iterate>
		
		<html:submit>
			<bean:message key="common.button.submit" />
		</html:submit>

	</html:form>
</div>