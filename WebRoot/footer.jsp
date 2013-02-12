<%@ page contentType="text/html; charset=Cp1252" %>
<%@page import="com.wub.misc.Helper"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<html:xhtml/>

<div id="footer">
	<bean:message key="common.footer.copyright" />
	<%-- Copyright einfügen --%>
	<%
		out.print(" " + Helper.getInstance().getCopyright() + " ");
	 %>
	<a target="_new" href="http://www.shihan-online.ch">
	<bean:message key="common.footer.copyrightholder" />
	</a>
</div>