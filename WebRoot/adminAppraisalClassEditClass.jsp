<%@ page contentType="text/html; charset=Cp1252"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>

<html:xhtml />

<div class="stdContainer">
	<h1>
		<bean:message key="admin.appraisalmanager.class.edit" />
	</h1>
	<br />
	
	
	<html:form action="classEdit">
	
		<%-- Klassen Id --%>
		<html:hidden name="appClassForm" property="classId" />
	
		<%-- Beschreibung/Name --%>
		<div class="label">
			<bean:message key="admin.appraisalmanager.class.name" />
		</div>
		<html:text maxlength="45" size="40" name="appClassForm" property="name" />
		<br />
		<br />
		
		<%-- Beurteilungen (Basis/Gerüste) --%>
		<div class="label">
			<bean:message key="admin.appraisalmanager.core.appraisal" />
		</div>
		<html:select name="appClassForm" size="1" property="appraisalId" style="width: 150px;">
			<html:options collection="appraisalBasicList" property="appraisalId"
				labelProperty="name" />
		</html:select>
		<br />
		<br />
		
		<%-- Administratoren (eigentlich: Lehrer) --%>
		<div class="label">
			<bean:message key="admin.appraisalmanager.class.teacher" />
		</div>
		<html:select name="appClassForm" size="1" property="adminId" style="width: 150px;">
			<html:options collection="teacherList" property="adminId"
				labelProperty="name" />
		</html:select>
		<br />
		<br />
		
		<%-- Subjekte (Fächer) --%>
		<div class="label">
			<bean:message key="admin.appraisalmanager.subject" />
		</div>
		<html:select name="appClassForm" size="1" property="subjectId" style="width: 150px;">
			<html:options collection="subjectList" property="subjectId"
				labelProperty="name" />
		</html:select>
		<br />
		<br />
		
		<%-- Startdatum --%>
		<div class="label">
			<bean:message key="admin.appraisalmanager.class.startdate2" />
		</div>
		<html:text name="appClassForm" property="startDate" size="20" maxlength="10" />
		<br />
		<br />
		
		<%-- Enddatum --%>
		<div class="label">
			<bean:message key="admin.appraisalmanager.class.enddate2" />
		</div>
		<html:text name="appClassForm" property="endDate" size="20" maxlength="10" />
		<br />
		<br />
		
		<%-- Jahr in welchem das Fach stattgefunden hat (hauptsächlich) --%>
		<div class="label">
			<bean:message key="admin.appraisalmanager.class.subjectyear" />
		</div>
		<html:text name="appClassForm" property="subjectYear" size="6" maxlength="4" />
		<br />
		<br />
		
		<%-- Schüler dieser Beurteilung zuordnen --%>
		<div class="label">
			<bean:message key="admin.appraisalmanager.class.students" />
		</div>
		<br />
		<br />

		<logic:iterate name="appClassForm" property="studentList" id="studentForm" >
			<!-- START ITEM -->
			<div class="left">
				<div class="label">
					<html:checkbox name="studentForm" property="active" indexed="true">
						&nbsp;<bean:message key="common.select" />
					</html:checkbox>
				</div>
				<bean:write name="studentForm" property="description" />
				<html:hidden name="studentForm" property="studentId" indexed="true" />
			</div>
			<br />
			<!-- END ITEM -->
		</logic:iterate>
		
		<br />
		<br />
		<html:hidden property="do" value="saveClass" />
		<html:submit>
			<bean:message key="common.edit" />
		</html:submit>
	</html:form>


</div>