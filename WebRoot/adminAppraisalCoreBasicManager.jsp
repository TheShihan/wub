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
		<bean:message key="admin.appraisalmanager.core.basic" />
	</h1>
	<br />
	<br />
	<html:link action="appraisalCoreBasicEdit.do?do=addBasic"><bean:message key="admin.appraisalmanager.core.basic.add" /></html:link>
	<br />
	<br />
	<table border="1">
		<tbody>
			<tr>
				<th>
					<bean:message key="common.id" />
				</th>
				<th>
					<bean:message key="admin.appraisalmanager.core.appraisal" />
				</th>
				<th>
					<bean:message key="common.edit" />
				</th>
				<th>
					<bean:message key="common.delete" />
				</th>
			</tr>
			<logic:empty name="appraisalCoreBasicListForm" property="appraisalBasicList">
				<tr>
					<td colspan="4">
						<bean:message key="admin.appraisalmanager.core.basic.empty" />
					</td>
				</tr>
			</logic:empty>
			<logic:notEmpty name="appraisalCoreBasicListForm" property="appraisalBasicList">
				<logic:iterate name="appraisalCoreBasicListForm" property="appraisalBasicList" id="appraisalBasic">
					<tr>
						<td>
							<bean:write name="appraisalBasic" property="appraisalId" />
						</td>
						<td>
							<bean:write name="appraisalBasic" property="name" />
						</td>
						<td>
							<logic:equal name="appraisalBasic" property="locked" value="1">
								-
							</logic:equal>
							<logic:notEqual name="appraisalBasic" property="locked" value="1">
								<html:link action="appraisalCoreBasicEdit.do?do=editBasic"
									paramName="appraisalBasic" paramProperty="appraisalId"
									paramId="appraisalId">
									X
								</html:link>
							</logic:notEqual>
						</td>
						<td>
							<logic:equal name="appraisalBasic" property="locked" value="1">
								-
							</logic:equal>
							<logic:notEqual name="appraisalBasic" property="locked" value="1">
								<html:link action="appraisalCoreBasicEdit.do?do=deleteBasic"
									paramName="appraisalBasic" paramProperty="appraisalId"
									paramId="appraisalId">
									X
								</html:link>
							</logic:notEqual>
						</td> 
					</tr>
				</logic:iterate>
			</logic:notEmpty>
		</tbody>
	</table>

</div>