<%@ page contentType="text/html; charset=Cp1252"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>

<html:xhtml />

<div class="stdContainer">
	<h1>
		<bean:message key="admin.adminmanager" />
	</h1>
	<br />
	<br />
	<html:link action="administratorEdit.do?do=addAdmin"><bean:message key="admin.adminmanager.addadminlink" /></html:link>
	<br />
	<br />
	<table border="1">
		<tbody>
			<%--  set the header --%>
			<tr>
				<th>
					<bean:message key="admin.adminmanager.name" />
				</th>
				<th>
					<bean:message key="admin.adminmanager.email" />
				</th>
				<th>
					<bean:message key="admin.adminmanager.edit" />
				</th>
				<th>
					<bean:message key="admin.adminmanager.delete" />
				</th>
			</tr>
			<%-- check if book exists and display message or iterate over books  --%>

			<logic:empty name="adminListForm" property="adminlist">
				<tr>
					<td colspan="3">
						<bean:message key="admin.adminmanager.empty" />
					</td>
				</tr>
			</logic:empty>
			<logic:notEmpty name="adminListForm" property="adminlist">
				<logic:iterate name="adminListForm" property="adminlist"
					id="administrators">
					<tr>
						<%-- print out the admin informations --%>
						<td>
							<bean:write name="administrators" property="name" />
						</td>
						<td>
							<a href="mailto:<bean:write name="administrators" property="email" />">
							<bean:write name="administrators" property="email" />
							</a>
						</td>
						<%-- print out the edit and delete link for each admin --%>
						<td>
							<html:link action="administratorEdit.do?do=editAdmin"
								paramName="administrators" paramProperty="adminId"
								paramId="adminId">
								X
							</html:link>
						</td>
						<td>
							<html:link action="administratorEdit.do?do=deleteAdmin"
								paramName="administrators" paramProperty="adminId"
								paramId="adminId">
								X
							</html:link>
						</td> 
					</tr>
				</logic:iterate>
			</logic:notEmpty>

			<%-- end interate --%>
		</tbody>
	</table>

</div>