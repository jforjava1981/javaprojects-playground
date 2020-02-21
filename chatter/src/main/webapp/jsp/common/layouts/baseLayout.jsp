<!DOCTYPE html>
<%@ page language="java" isErrorPage="true"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>


<html lang="en">
<head>
<meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/jquery/ui/jquery-ui.css" />
<script src="${pageContext.request.contextPath}/js/common/jquery/jquery.1.11.1.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.3.2.0.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap-theme.3.2.0.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/chatter.css" />
<script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.3.2.0.min.js"></script>
<title><tiles:insertAttribute name="title"></tiles:insertAttribute></title>
<!--[if lt IE 9]>
<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
</head>
<body>

<div class="container-fluid">
	<input type="hidden" id="appContext" value="${pageContext.request.contextPath}"></input>
<script src="${pageContext.request.contextPath}/js/common/chatter.js"></script>
	<!-- <div class="container"> -->
		<tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="body" />
		<%-- <tiles:insertAttribute name="footer" /> --%>
	<!-- </div> -->
</div>
</body>
</html>