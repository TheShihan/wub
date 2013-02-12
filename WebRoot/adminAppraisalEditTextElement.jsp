<%@ page contentType="text/html; charset=Cp1252"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>

<html:xhtml />

<div class="stdContainer">
	<h1>
		<bean:message key="admin.appraisalmanager.textelements.edit" />
	</h1>
	<br />
	
	<div class="errors"><html:errors /><br /></div>
	
	<html:form action="textElementEdit">
		<html:hidden property="textId" />
		
		<div class="label">
			<bean:message key="common.title" />
		</div>
		<html:text maxlength="45" size="40" property="title" />
		<br />
		<br />
		<div class="label">
			<bean:message key="common.text" />
		</div>
		<html:textarea rows="10" cols="35" property="text" />
		<br />
		<br />
		<html:hidden property="do" value="saveTextElement" />
		<html:submit>
			<bean:message key="common.edit" />
		</html:submit>
	</html:form>


</div>