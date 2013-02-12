<%@ page contentType="text/html; charset=Cp1252" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html:html xhtml="true" lang="true"> 
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=Cp1252"/>
		<title>WUB - Webbasierte Unterrichts-Beurteilung</title>
		<style type="text/css" media="screen">
			@import url(CSS/style.css );
		</style>
		<link rel="shortcut icon" type="image/x-icon" href="favicon.ico" />
	</head>
	
	<body>
		
		<tiles:insert attribute="header" />
		
		<tiles:insert attribute="menu" />
		
		<tiles:insert attribute="body" />
		
		<tiles:insert attribute="footer" />
		
	</body>
	
</html:html>
