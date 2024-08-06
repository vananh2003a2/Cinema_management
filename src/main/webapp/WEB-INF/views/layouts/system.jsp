<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!doctype html>
<html lang="en">
 
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<c:url value = "/template/admin/assets/vendor/bootstrap/css/bootstrap.min.css"/>">
    <link href="<c:url value = "/template/admin/assets/vendor/fonts/circular-std/style.css"/>" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value = "/template/admin/assets/libs/css/style.css?v=1.0"/>">
    
    <link rel="stylesheet" href="<c:url value = "/template/admin/assets/vendor/fonts/fontawesome/css/all.css"/>">
    <link rel="stylesheet" href="<c:url value = "/template/admin/assets/vendor/charts/chartist-bundle/chartist.css"/>">
    <link rel="stylesheet" href="<c:url value = "/template/admin/assets/vendor/charts/morris-bundle/morris.css"/>">
    <link rel="stylesheet" href="<c:url value = "/template/admin/assets/vendor/fonts/material-design-iconic-font/css/materialdesignicons.min.css"/>">
    <link rel="stylesheet" href="<c:url value = "/template/admin/assets/vendor/charts/c3charts/c3.css"/>">
    <link rel="stylesheet" href="<c:url value = "/template/admin/assets/vendor/fonts/flag-icon-css/flag-icon.min.css"/>">
    
    <title><decorator:title default="Master-layout"/></title>
</head>

<body>
	<div class="dashboard-main-wrapper">
		<%@ include file="/WEB-INF/views/layouts/system/header.jsp" %>
		
		<%@ include file="/WEB-INF/views/layouts/system/sidebar.jsp" %>
		<decorator:body/>
	</div>
	<script src="<c:url value = "/template/admin/jquery.twbsPagination.js"/>" type="text/javascript"></script>
</body>

</html>