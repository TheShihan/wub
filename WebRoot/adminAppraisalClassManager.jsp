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
		<bean:message key="admin.appraisalmanager.class.title" />
	</h1>
	<br />
	<br />
	<html:link action="classEdit.do?do=addClass"><bean:message key="admin.appraisalmanager.class.add" /></html:link>
	<br />
	<br />
	<table border="1">
		<tbody>
			<tr>
				<th>
					<bean:message key="common.id" />
				</th>
				<th>
					<bean:message key="common.desc" />
				</th>
				<th>
					<bean:message key="admin.appraisalmanager.class.startdate" />
				</th>
				<th>
					<bean:message key="admin.appraisalmanager.class.enddate" />
				</th>
				<th>
					<bean:message key="common.edit" />
				</th>
				<th>
					<bean:message key="common.delete" />
				</th>
				<th>
					<bean:message key="admin.appraisalmanager.class.delivery" />
				</th>
			</tr>
			<logic:empty name="appraisalClassListForm" property="classList">
				<tr>
					<td colspan="7">
						<bean:message key="admin.appraisalmanager.class.empty" />
					</td>
				</tr>
			</logic:empty>
			<logic:notEmpty name="appraisalClassListForm" property="classList">
				<logic:iterate name="appraisalClassListForm" property="classList" id="appraisalMain">
					<tr>
						<td>
							<bean:write name="appraisalMain" property="classId" />
						</td>
						<td>
							<bean:write name="appraisalMain" property="name" />
						</td>
						<td>
							<bean:write name="appraisalMain" property="startDateString" />
						</td>
						<td>
							<bean:write name="appraisalMain" property="endDateString" />
						</td>
						<td>
							<logic:equal name="appraisalMain" property="sent" value="1">
								-
							</logic:equal>
							<logic:notEqual name="appraisalMain" property="sent" value="1">
								<html:link action="classEdit.do?do=editClass"
									paramName="appraisalMain" paramProperty="classId"
									paramId="classId">
									X
								</html:link>
							</logic:notEqual>
						</td>
						<td>
							<logic:equal name="appraisalMain" property="sent" value="1">
								-
							</logic:equal>
							<logic:notEqual name="appraisalMain" property="sent" value="1">
								<html:link action="classEdit.do?do=deleteClass"
									paramName="appraisalMain" paramProperty="classId"
									paramId="classId">
									X
								</html:link>
							</logic:notEqual>
						</td>
						<td>
							<%-- senden --%>
							<html:link action="classSend.do?do=prepareSendClass"
								paramName="appraisalMain" paramProperty="classId"
								paramId="classId">
								X
							</html:link>
						</td>
					</tr>
				</logic:iterate>
			</logic:notEmpty>
		</tbody>
	</table>

</div>