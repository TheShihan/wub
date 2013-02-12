<%@ page contentType="text/html; charset=Cp1252"%>
<%@page import="com.wub.handlers.RoleHandler"%>
<%@page import="java.util.List"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>

<html:xhtml />

<div class="stdContainer">
	<h1>
		<bean:message key="admin.adminmanager.editadmin" />
	</h1>
	<br />

	<logic:notEmpty name="administratorEditForm" property="infoMessage">
		<p class="infomessage">
			<bean:write name="administratorEditForm" property="infoMessage" />
		</p>
		<br />
	</logic:notEmpty>

	<html:form action="administratorEdit">
		
		<div class="label">
			<bean:message key="common.id" />
		</div>
		<html:text maxlength="45" size="40" property="id" readonly="true"/>
		<br />
		<br />
		<div class="label">
			<bean:message key="admin.adminmanager.name" />
		</div>
		<html:text maxlength="45" size="40" property="name" />
		<br />
		<br />
		<div class="label">
			<bean:message key="admin.adminmanager.password" />
		</div>
		<html:password maxlength="45" size="40" property="password" />
		<br />
		<br />
		<div class="label">
			<bean:message key="admin.adminmanager.email" />
		</div>
		<html:text maxlength="255" size="40" property="email" />
		<br />
		<br />
		<div class="label">
			<bean:message key="admin.adminmanager.role" />
		</div>
		<%-- Liste mit allen verfügbaren Rollen ausgeben (id wird als value genommen)
			Liste wird zuerst noch vorbereitet und dann in einem Scope gespeichert.
			Die alte Rolle wird vorseleketiert.
			 --%>
		<%
			List roles = RoleHandler.getInstance().getAllRoles();
			pageContext.setAttribute("roleList", roles, PageContext.PAGE_SCOPE);			
		%>
		<html:select size="1" property="role">
			<html:options collection="roleList" property="roleId"
				labelProperty="name" />
		</html:select>
		<br />
		<br />
		<html:hidden property="do" value="saveAdmin" />
		<html:submit>
			<bean:message key="common.button.submit" />
		</html:submit>
	</html:form>


</div>