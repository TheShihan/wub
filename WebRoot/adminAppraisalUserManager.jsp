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
		<bean:message key="admin.appraisalmanager.user.title" />
	</h1>
	<br />
	<br />
	<html:link action="userEdit.do?do=addUser"><bean:message key="admin.appraisalmanager.user.add" /></html:link>
	<br />
	<br />
	<table border="1">
		<tbody>
			<tr>
				<th>
					<bean:message key="common.id" />
				</th>
				<th>
					<bean:message key="admin.appraisalmanager.user" />
				</th>
				<th>
					<bean:message key="admin.appraisalmanager.user.mainclass" />
				</th>
				<th>
					<bean:message key="common.edit" />
				</th>
				<th>
					<bean:message key="common.delete" />
				</th>
			</tr>
			<logic:empty name="userListForm" property="userList">
				<tr>
					<td colspan="5">
						<bean:message key="admin.appraisalmanager.user.empty" />
					</td>
				</tr>
			</logic:empty>
			<logic:notEmpty name="userListForm" property="userList">
				<logic:iterate name="userListForm" property="userList" id="users">
					<tr>
						<td>
							<bean:write name="users" property="userId" />
						</td>
						<td>
							<bean:write name="users" property="name" />
						</td>
						<td>
							<bean:write name="users" property="mainClass" />
						</td>
						<td>
							<html:link action="userEdit.do?do=editUser"
								paramName="users" paramProperty="userId"
								paramId="userId">
								X
							</html:link>
						</td>
						<td>
							<html:link action="userEdit.do?do=deleteUser"
								paramName="users" paramProperty="userId"
								paramId="userId">
								X
							</html:link>
						</td> 
					</tr>
				</logic:iterate>
			</logic:notEmpty>
		</tbody>
	</table>

</div>