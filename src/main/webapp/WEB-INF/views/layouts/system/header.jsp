<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ page import="com.laptrinhjavaweb.utils.SecurityUtils" %>
<c:import var="securityUtils"  url="./com/laptrinhjavaweb/utils/SecurityUtils" />
<div class="dashboard-header">
	<nav class="navbar navbar-expand-lg bg-white fixed-top">
		<a class="navbar-brand" href="index.html">ADMIN STARLIGHT</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<security:authorize access="isAuthenticated()">
			<div class="collapse navbar-collapse " id="navbarSupportedContent">
				<ul class="navbar-nav ml-auto navbar-right-top">
					<li class="nav-item dropdown nav-user"><a
						class="nav-link nav-user-img" href="#"
						id="navbarDropdownMenuLink2" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false">Admin: <span>${SecurityUtils.getPrincipal().getFullName() }</span>
							<i class="fas fa-caret-down"></i>
					</a>
						<div class="dropdown-menu dropdown-menu-right nav-user-dropdown"
							aria-labelledby="navbarDropdownMenuLink2">
							<a class="dropdown-item" href="<c:url value="/logout"/>"><i
								class="fas fa-power-off mr-2"></i>Logout</a>
						</div></li>
				</ul>
			</div>
		</security:authorize>
	</nav>
</div>