<%@ page contentType="text/html; charset=Cp1252"%>
<%@page import="com.wub.handlers.VoteStyleHandler"%>
<%@page import="java.util.List"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>

<html:xhtml />

<div class="stdContainer">
	<h1>
		<bean:message key="admin.appraisalmanager.core.basic.add" />
	</h1>
	<br />

	<div class="errors">
		<html:errors />
		<br />
	</div>

	<html:form action="appraisalCoreBasicEdit">
		<div class="label">
			<bean:message key="common.name" />
		</div>
		<html:text name="coreBasicEditForm" maxlength="45" size="40" property="name" />
		<br />
		<br />
		<div class="label">
			<bean:message key="admin.appraisalmanager.core.basic.votestyle" />
		</div>
		<%-- Liste mit allen verfügbaren vote styles ausgeben (id wird als value genommen)
			Liste wird zuerst noch vorbereitet und dann in einem Scope gespeichert --%>
		<%
			List voteStyles = VoteStyleHandler.getInstance()
						.getAllVoteStyles();
				pageContext.setAttribute("voteStyleList", voteStyles,
						PageContext.PAGE_SCOPE);
		%>
		<html:select name="coreBasicEditForm" size="1" property="voteStyle" style="width: 40px;">
			<html:options collection="voteStyleList" property="voteStyleId"
				labelProperty="voteCount" />
		</html:select>
		<br />
		<br />
		<span class="important">
			<bean:message key="admin.appraisalmanager.textelements" />
		</span>
		<br />
		<br />
	
		<logic:iterate name="coreBasicEditForm" property="appraisalConfigs" id="appraisalConfigEditForm" >
			<!-- START ITEM -->
			<html:checkbox name="appraisalConfigEditForm" property="active" indexed="true">
				&nbsp;<bean:message key="common.activate" />
			</html:checkbox>
			&nbsp;&nbsp;&nbsp;		
			<html:textarea rows="5" cols="55" name="appraisalConfigEditForm" property="text" indexed="true" readonly="true"/>
			<html:hidden name="appraisalConfigEditForm" property="textId" indexed="true" />
			<br />
			<br />
			<!-- END ITEM -->
		</logic:iterate>
		
		<br />
		<br />
		<html:hidden name="coreBasicEditForm" property="do" value="saveBasic" />
		<html:submit>
			<bean:message key="common.button.add" />
		</html:submit>
	</html:form>


</div>