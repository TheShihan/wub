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
		<bean:message key="admin.statistics.title" />
	</h1>
	<br />
	<bean:message key="admin.statistics.info" />
	<br />
	<br />
	
	<!-- Statistiken könnte noch irgendwann sinnvoll erweitert werden:
	<b>
		<bean:message key="admin.statistics.subject" />
	</b>
	<br />
	<html:form action="statisticSelection">
		<html:select size="1" property="subjectId" style="width: 300px;">
			<html:options collection="subjectList" property="subjectId" labelProperty="name" />
		</html:select>

		<html:submit>
			<bean:message key="common.button.submit" />
		</html:submit>
	</html:form>
	<br />
	
	<b>
		<bean:message key="admin.statistics.teacher" />
	</b>
	<br />
	<html:form action="statisticSelection">
		<html:select size="1" property="teacherId" style="width: 300px;">
			<html:options collection="teacherList" property="adminId" labelProperty="name" />
		</html:select>

		<html:submit>
			<bean:message key="common.button.submit" />
		</html:submit>
	</html:form>
	<br />
	-->
	
	<b>
		<bean:message key="admin.statistics.class" />
	</b>
	<br />
	<html:form action="statisticSelection">
		<html:select size="1" property="classId" style="width: 300px;">
			<html:options collection="classList" property="classId" labelProperty="name" />
		</html:select>

		<html:submit>
			<bean:message key="common.button.submit" />
		</html:submit>
	</html:form>
	
</div>