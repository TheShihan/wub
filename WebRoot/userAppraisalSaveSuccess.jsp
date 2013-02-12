<%@ page contentType="text/html; charset=Cp1252" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<html:xhtml/>

<div class="stdContainer">
	<bean:message key="user.appraisal.save.success" />
	<br />
	<br />
	<html:link action="prepareAppraisalList">
		<bean:message key="user.appraisallist" />
	</html:link>
</div>