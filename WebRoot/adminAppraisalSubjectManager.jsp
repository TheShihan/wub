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
		<bean:message key="admin.appraisalmanager.subject.title" />
	</h1>
	<br />
	<br />
	<html:link action="subjectEdit.do?do=addSubject"><bean:message key="admin.appraisalmanager.subject.add" /></html:link>
	<br />
	<br />
	<table border="1">
		<tbody>
			<tr>
				<th>
					<bean:message key="common.id" />
				</th>
				<th>
					<bean:message key="admin.appraisalmanager.subject" />
				</th>
				<th>
					<bean:message key="common.edit" />
				</th>
				<th>
					<bean:message key="common.delete" />
				</th>
			</tr>
			<logic:empty name="subjectListForm" property="subjectList">
				<tr>
					<td colspan="4">
						<bean:message key="admin.appraisalmanager.subject.empty" />
					</td>
				</tr>
			</logic:empty>
			<logic:notEmpty name="subjectListForm" property="subjectList">
				<logic:iterate name="subjectListForm" property="subjectList" id="subjects">
					<tr>
						<td>
							<bean:write name="subjects" property="subjectId" />
						</td>
						<td>
							<bean:write name="subjects" property="name" />
						</td>
						<td>
							<html:link action="subjectEdit.do?do=editSubject"
								paramName="subjects" paramProperty="subjectId"
								paramId="subjectId">
								X
							</html:link>
						</td>
						<td>
							<html:link action="subjectEdit.do?do=deleteSubject"
								paramName="subjects" paramProperty="subjectId"
								paramId="subjectId">
								X
							</html:link>
						</td> 
					</tr>
				</logic:iterate>
			</logic:notEmpty>
		</tbody>
	</table>

</div>