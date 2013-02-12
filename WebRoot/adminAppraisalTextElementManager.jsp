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
		<bean:message key="admin.appraisalmanager.textelementmanager" />
	</h1>
	<br />
	<br />
	<html:link action="textElementEdit.do?do=addTextElement"><bean:message key="admin.appraisalmanager.textelements.addtextelement" /></html:link>
	<br />
	<br />
	<bean:message key="admin.appraisalmanager.textelements.editinfo" />
	<br />
	<br />
	<table border="1">
		<tbody>
			<tr>
				<th>
					<bean:message key="common.id" />
				</th>
				<th>
					<bean:message key="common.title" />
				</th>
				<th>
					<bean:message key="common.text" />
				</th>
				<th>
					<bean:message key="common.edit" />
				</th>
				<th>
					<bean:message key="common.delete" />
				</th>
			</tr>
			<logic:empty name="textElementListForm" property="textElementList">
				<tr>
					<td colspan="5">
						<bean:message key="admin.appraisalmanager.textelements.empty" />
					</td>
				</tr>
			</logic:empty>
			<logic:notEmpty name="textElementListForm" property="textElementList">
				<logic:iterate name="textElementListForm" property="textElementList" id="textElements">
					<tr>
						<td>
							<bean:write name="textElements" property="textId" />
						</td>
						<td>
							<bean:write name="textElements" property="title" />
						</td>
						<td>
							<ct:nl2br>
								<div class="left">
									<bean:write name="textElements" property="text" />
								</div>
							</ct:nl2br>
						</td>
						<td>
							<html:link action="textElementEdit.do?do=editTextElement"
								paramName="textElements" paramProperty="textId"
								paramId="textId">
								X
							</html:link>
						</td>
						<td>
							<html:link action="textElementEdit.do?do=deleteTextElement"
								paramName="textElements" paramProperty="textId"
								paramId="textId">
								X
							</html:link>
						</td> 
					</tr>
				</logic:iterate>
			</logic:notEmpty>
		</tbody>
	</table>

</div>