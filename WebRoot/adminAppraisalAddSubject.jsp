<%@ page contentType="text/html; charset=Cp1252"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>

<html:xhtml />

<div class="stdContainer">
	<h1>
		<bean:message key="admin.appraisalmanager.subject.add" />
	</h1>
	<br />
	
	<div class="errors"><html:errors /><br /></div>
	
	<html:form action="subjectEdit">
		<div class="label">
			<bean:message key="admin.appraisalmanager.subject" />
		</div>
		<html:text maxlength="45" size="40" property="name" />
		<br />
		<br />
		<html:hidden property="do" value="saveSubject" />
		<html:submit>
			<bean:message key="common.button.add" />
		</html:submit>
	</html:form>


</div>