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
		<bean:message key="user.appraisal.title" />
	</h1>
	<br />
	
	<div class="errors">
		<html:errors />
		<br />
	</div>
	
	<p class="block">
		<bean:message key="user.appraisal.info" />
	</p>
	<br />
	
	<html:form action="userSaveAppraisal">
	
		<p class="left">
			<b><bean:message key="user.appraisal.desc" /></b>
			<bean:write name="appraisalForm" property="description" />
			<br />
			<b><bean:message key="user.appraisal.teacher" /></b>
			<bean:write name="appraisalForm" property="teacher" />
			<br />
			<b><bean:message key="user.appraisal.subject" /></b>
			<bean:write name="appraisalForm" property="subject" />
			<br />
			<b><bean:message key="user.appraisal.subjectYear" /></b>
			<bean:write name="appraisalForm" property="subjectYear" />
			<br />
			<b><bean:message key="user.appraisal.timeperiod"/></b>
			<bean:write name="appraisalForm" property="startDate" />
			-
			<bean:write name="appraisalForm" property="endDate" />
			<br />
			<br />
		</p>
		
		<b>
			<bean:message key="user.appraisal.legend" />
		</b>
		<!-- Andere Legende je nach voteStyleId -->
		<logic:equal name="appraisalForm" property="voteStyleId" value="1">
			<bean:message key="user.appraisal.legend.voteStyleId1" />
		</logic:equal>
		<logic:equal name="appraisalForm" property="voteStyleId" value="2">
			<bean:message key="user.appraisal.legend.voteStyleId2" />
		</logic:equal>
		<br />
		<br />
		
		<table border="1">
			<tbody>
				<tr>
					<th>
						<bean:message key="user.appraisal.header.text" />
					</th>	
					<!-- Je nach voteStyleId, eine andere Notenskala (und Spaltenbreite) -->
					<logic:equal name="appraisalForm" property="voteStyleId" value="1">
						<th style="width: 100px;">
							<bean:message key="user.appraisal.header.voteStyleId1" />
						</th>
					</logic:equal>
					<logic:equal name="appraisalForm" property="voteStyleId" value="2">
						<th style="width: 145px;">
							<bean:message key="user.appraisal.header.voteStyleId2" />
						</th>
					</logic:equal>
					<th>
						<bean:message key="user.appraisal.header.comment" />
					</th>
				</tr>
				<logic:empty name="appraisalForm" property="voteElementList">
					<tr>
						<td colspan="3">
							<bean:message key="user.appraisal.empty" />
						</td>
					</tr>
				</logic:empty>
				<logic:notEmpty name="appraisalForm" property="voteElementList">
					<logic:iterate name="appraisalForm" property="voteElementList" id="appraisalVoteElementForm">
						<tr>
							<td>
								<p class="left">
									<b>
										<bean:write name="appraisalVoteElementForm" property="title" />
									</b>
									<ct:nl2br>
										<bean:write name="appraisalVoteElementForm" property="text" />
									</ct:nl2br>
								</p>
							</td>
							<td>
								<logic:equal name="appraisalForm" property="voteStyleId" value="1">
									<!-- VoteStyleID 1: 4 Bewertungsstufen -->
									<html:radio name="appraisalVoteElementForm" property="vote" value="1" indexed="true" />
									<html:radio name="appraisalVoteElementForm" property="vote" value="2" indexed="true" />
									<html:radio name="appraisalVoteElementForm" property="vote" value="3" indexed="true" />
									<html:radio name="appraisalVoteElementForm" property="vote" value="4" indexed="true" />
								</logic:equal>
								<logic:equal name="appraisalForm" property="voteStyleId" value="2">
									<!-- VoteStyleID 2: 6 Bewertungsstufen -->
									<html:radio name="appraisalVoteElementForm" property="vote" value="1" indexed="true"/>
									<html:radio name="appraisalVoteElementForm" property="vote" value="2" indexed="true" />
									<html:radio name="appraisalVoteElementForm" property="vote" value="3" indexed="true" />
									<html:radio name="appraisalVoteElementForm" property="vote" value="4" indexed="true" />
									<html:radio name="appraisalVoteElementForm" property="vote" value="5" indexed="true" />
									<html:radio name="appraisalVoteElementForm" property="vote" value="6" indexed="true" />
								</logic:equal>
							</td>
							<td>
								<html:textarea name="appraisalVoteElementForm" property="comment"
									rows="3" cols="30" indexed="true">
								</html:textarea>
							</td>
						</tr>
					</logic:iterate>
				</logic:notEmpty>
			</tbody>
		</table>
		
		<br />
		
		<html:hidden name="appraisalForm" property="statusId" />
		
		<!-- Senden nur ermöglichen wenn auch  Bewertungen vorhanden sind -->
		<logic:notEmpty name="appraisalForm" property="voteElementList">
			<html:submit>
				<bean:message key="common.send" />
			</html:submit>
		</logic:notEmpty>
		
	</html:form>

</div>