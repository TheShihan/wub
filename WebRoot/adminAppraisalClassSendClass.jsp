<%@ page contentType="text/html; charset=Cp1252"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>

<html:xhtml />

<div class="stdContainer">
	<h1>
		<bean:message key="admin.appraisalmanager.class.send.title" />
	</h1>
	<br />


	<html:form action="classSend">

		<logic:equal name="appraisalSendForm" property="error" value="true">
			<bean:message key="error.appraisal.send.date" />
		</logic:equal>
		<logic:notEqual name="appraisalSendForm" property="error" value="true">
			<b><bean:message key="admin.appraisalmanager.class.name" />
			</b>
			:&nbsp;<bean:write name="appraisalSendForm" property="name" />
			<br />
			<br />

			<b><bean:message key="admin.appraisalmanager.class.teacher" />
			</b>
			:&nbsp;<bean:write name="appraisalSendForm" property="teacher" />
			<br />
			<br />

			<b><bean:message key="admin.appraisalmanager.subject" />
			</b>
			:&nbsp;<bean:write name="appraisalSendForm" property="subject" />
			<br />
			<br />

			<b><bean:message key="admin.appraisalmanager.class.startdate" />
			</b>
			:&nbsp;<bean:write name="appraisalSendForm" property="start" />
			<br />
			<br />

			<b><bean:message key="admin.appraisalmanager.class.enddate" />
			</b>
			:&nbsp;<bean:write name="appraisalSendForm" property="end" />
			<br />
			<br />
			<br />

			<b><bean:message key="admin.appraisalmanager.class.send.desc" />
			</b>
			<br />
			<br />

			<%-- Fall 1) Alle Beurteilungen wurden bereits gesendet --%>
			<logic:empty name="appraisalSendForm" property="userList">
				<span class="important"> <bean:message
						key="admin.appraisalmanager.class.send.empty" />
				</span>
				
				<br />
				<br />
				<html:hidden property="do" value="showList" />
				<html:submit>
					<bean:message key="common.button.cancel" />
				</html:submit>
			</logic:empty>
			<%-- Fall 2) Es gibt noch Beurteilungen, zum Versenden --%>
			<logic:notEmpty name="appraisalSendForm" property="userList">
				<table border="1">
					<tbody>
						<logic:iterate name="appraisalSendForm" property="userList"	id="classSendForm">
							<!-- START ITEM -->
							<tr>
								<html:hidden name="classSendForm" property="userId" indexed="true" />
								<html:hidden name="classSendForm" property="statusId" indexed="true" />
								<td>
									<bean:write name="classSendForm" property="userName" />
								</td>
								<td>
									<!-- 
									<A href="mailto:<bean:write name="classSendForm" property="userEmail" />">
										<bean:write name="classSendForm" property="userEmail" />
									</A>
									 -->
									 <bean:write name="classSendForm" property="userEmail" />
								</td>
								<td>
									<logic:equal name="appraisalSendForm" property="sent" value="true">
										<html:checkbox name="classSendForm" property="send" indexed="true" value="1"/>
									</logic:equal>
									<logic:notEqual name="appraisalSendForm" property="sent"
										value="true">
										<html:checkbox name="classSendForm" property="send" indexed="true" value="1" disabled="true" />
									</logic:notEqual>
								</td>
							</tr>
							<!-- END ITEM -->
						</logic:iterate>
					</tbody>
				</table>

				<br />
				<br />
				<html:hidden name="appraisalSendForm" property="sendToAll" />
				<html:hidden property="do" value="sendClass" />
				<html:submit>
					<bean:message key="common.send" />
				</html:submit>
			</logic:notEmpty>
			
		</logic:notEqual>
	</html:form>


</div>