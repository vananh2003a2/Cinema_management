<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ page import="com.laptrinhjavaweb.utils.SecurityUtils" %>
<c:import var="securityUtils"  url="./com/laptrinhjavaweb/utils/SecurityUtils" />
<div class="nav-left-sidebar sidebar-dark">
	<div class="menu-list">
		<nav class="navbar navbar-expand-lg navbar-light">
			<a class="d-xl-none d-lg-none" href="#">Dashboard</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarNav" aria-controls="navbarNav"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav flex-column">
					<li class="nav-divider">QUẢN LÝ DỮ LIỆU</li>
					<li class="nav-item"><a class="nav-link" href="<c:url value ='/admin/index' />"
						aria-expanded="false"><i class="fa fa-fw fa-chart-bar"></i>Thống
							kê </a></li>
					<li class="nav-item"><a class="nav-link" href="<c:url value ='/admin/qlmovie' />" 
						aria-expanded="false"><i class="fa fa-fw fa-video"></i>Quản
							lý phim </a></li>
					<li class="nav-item"><a class="nav-link" href="<c:url value ='/admin/time?page=0&size=5&searchValue=&dateRange=&idRoom=0&result=1' />"
						aria-expanded="false"><i class="fa fa-fw fa-calendar-days"></i>Quản
							lý suất chiếu phim </a></li>
					<li class="nav-item"><a class="nav-link" href="<c:url value ='/admin/ticket' />"
						aria-expanded="false"><i class="fa fa-fw fa-ticket"></i>Quản
							lý vé xem phim </a></li>
					<li class="nav-divider">QUẢN LÝ NGƯỜI DÙNG</li>
					<li class="nav-item"><a class="nav-link "
						href="<c:url value ='/system/admin/account' />" aria-expanded="false"><i
							class="fa fa-fw fa-users"></i>Quản lý người dùng</a></li>
				</ul>
			</div>
		</nav>
	</div>
</div>