<%-- SpagoBI, the Open Source Business Intelligence suite

 Copyright (C) 2012 Engineering Ingegneria Informatica S.p.A. - SpagoBI Competency Center
 This Source Code Form is subject to the terms of the Mozilla Public
 License, v. 2.0, without the "Incompatible With Secondary Licenses" notice.  If a copy of the MPL was not distributed with this file,
 You can obtain one at http://mozilla.org/MPL/2.0/. --%>

<%@page import="it.eng.spagobi.tools.dataset.common.behaviour.UserProfileUtils"%>
<%@page import="it.eng.spagobi.commons.bo.UserProfile"%>

<%
/**
This page use the SpagoBI execution tag, that displays an iframe pointing to SpagoBI context with all information about document execution 
(document identifier, role to be used, values for parameters).
*/
%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spagobi" tagdir="/WEB-INF/tags/spagobi" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.*"%>
<%@page import="it.eng.spagobi.sdk.documents.bo.SDKDocumentParameter"%>
<%@page import="it.eng.spagobi.sdk.documents.bo.SDKDocument"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta http-equiv="cache-control" content="max-age=0" />

	<title>Document execution</title>
	<style>
	body, p { font-family:Tahoma; font-size:10pt; padding-left:30; }
	pre { font-size:8pt; }
	</style>
</head>
<body>


<% String login = (String) request.getParameter("login"); %>
<% String password = (String) request.getParameter("password");


%>
			
	<spagobi:execution 
			spagobiContext="http://localhost:8080/SpagoBI/"
			userId="<%= login %>"
			password="<%= password %>"
	        documentId="77"
	        iframeStyle="height:900px; width:100%" 
	        executionRole="/spagobi/admin"
	        parametersStr=""
	        displayToolbar="false"
	        displaySliders="false" />



	
</body>
</html>