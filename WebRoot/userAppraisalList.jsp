<%@ page contentType="text/html; charset=Cp1252"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/customTag.tld" prefix="ct"%>

<html:xhtml />

<div class="stdContainer">

	<h1>
		<bean:message key="user.appraisallist.title" />
	</h1>
	<br />
	
	<html:form action="userPrepareAppraisal">

		<html:select name="userAppraisalInfoForm" size="1" property="statusId" style="width: 300px;">
			<html:options collection="appraisalList" property="statusId" labelProperty="appraisalInfo" />
		</html:select>

		<html:submit>
			<bean:message key="common.button.submit" />
		</html:submit>
	</html:form>

</div>