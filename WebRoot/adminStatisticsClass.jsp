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
		<bean:message key="admin.statistics.title" />
	</h1>
	<br />

	<div class="label">
		<bean:message key="common.title" />
	</div>
	<bean:write name="appraisalStatForm" property="description"/>
	<br />
	
	<div class="label">
		<bean:message key="admin.appraisalmanager.class.teacher" />
	</div>
	<bean:write name="appraisalStatForm" property="teacher"/>
	<br />
	
	<div class="label">
		<bean:message key="admin.appraisalmanager.subject" />
	</div>
	<bean:write name="appraisalStatForm" property="subject"/>
	(<bean:write name="appraisalStatForm" property="subjectYear"/>)
	<br />
	
	<div class="label">
		<bean:message key="admin.appraisalmanager.class.startdate" />
	</div>
	<bean:write name="appraisalStatForm" property="startDate"/>
	<br />
	
	<div class="label">
		<bean:message key="admin.appraisalmanager.class.enddate" />
	</div>
	<bean:write name="appraisalStatForm" property="endDate"/>
	<br />
	
	<div class="label">
		<bean:message key="admin.statistics.votecount" />
	</div>
	<bean:write name="appraisalStatForm" property="voteCount"/>
	<br />
	
	<div class="label">
		<bean:message key="admin.statistics.maxvote" />
	</div>
	<bean:write name="appraisalStatForm" property="maxVote"/>
	<br />
	
	<br />
	
	<%-- Bewertungen ausgeben --%>
	
	<logic:empty name="appraisalStatForm" property="voteElementList">
		<bean:message key="admin.statistics.empty" />
	</logic:empty>
	<logic:notEmpty name="appraisalStatForm" property="voteElementList">
		<table border="1">
			<tbody style="width: 95%;">
				<tr>
					<th style="width: 30%;">
						<bean:message key="admin.statistics.text" />
					</th>
					<th style="width: 15%;">
						<bean:message key="admin.statistics.vote" />
					</th>
					<th style="width: 55%;">
						<bean:message key="admin.statistics.comments" />
					</th>
				</tr>
				<logic:iterate name="appraisalStatForm" property="voteElementList" id="appraisalStatisticVoteElementForm">
					<tr>
						<td>
							<bean:write name="appraisalStatisticVoteElementForm" property="text"/>
						</td>
						<td>
							<bean:write name="appraisalStatisticVoteElementForm" property="voteString"/>
						</td>
						<td>
							<logic:empty name="appraisalStatisticVoteElementForm" property="commentList">
								-
							</logic:empty>
							<logic:notEmpty name="appraisalStatisticVoteElementForm" property="commentList">
								<ul class="left">
									<logic:iterate name="appraisalStatisticVoteElementForm" property="commentList" 
									 id="commentBean">
										<li><bean:write name="commentBean" property="commentText" /></li>
									</logic:iterate>
								</ul>
							</logic:notEmpty>
						</td>
					</tr>
				</logic:iterate>
			</tbody>
		</table>
	</logic:notEmpty>
	
</div>