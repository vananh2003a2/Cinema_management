<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ page import="com.laptrinhjavaweb.utils.SecurityUtils" %>
<div class="header">
        <nav class="navbar navbar-expand-lg navbar-light bg-white p-0 fixed-top">
            <div class="container-fluid">
                <a class="navbar-brand p-0" href="index"><img class="height-header img-fluid" src='<c:url value="/template/customer/img/logo/sdasd40.png"/>'"
                        alt="no-logo" class=""></a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 d-flex justify-content-center align-items-center">
                        <li class="nav-item">
                            <a class="m-0 nav-link active fw-normal text-secondary h5 px-3" aria-current="page" href="index">TRANG CHỦ</a>
                        </li>
                        <li class="nav-item">
                            <a class="m-0 nav-link fw-normal text-secondary h5 px-3" href="lichchieu">LỊCH CHIẾU</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link fw-normal text-secondary h5 px-3 m-0" href="contact">LIÊN HỆ</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="m-0 nav-link dropdown-toggle fw-normal text-secondary h5 px-3" 
                                id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                PHIM
                            </a>
                            <ul class="dropdown-menu m-0 p-0 shadow-sm" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="phimdangchieu">Phim đang chiếu</a></li>
                                <li><a class="dropdown-item" href="phimsapchieu">Phim sắp chiếu</a></li>
                            </ul>
                        </li>
                    </ul>
                    
					<security:authorize access="isAnonymous()">
						<form class="d-flex">
							<a href="login"
								class="btn btn-outline-success me-2 rounded-pill">Đăng nhập</a>
							<a href="register"
								class="btn btn-outline-success rounded-pill">Đăng ký</a>
						</form>
					</security:authorize>
					<security:authorize access="isAuthenticated()">
						<form class="d-flex">
							<div
								class="d-flex align-items-center position-relative header-user">
								<i class="fa fa-user"></i> 
								<a style="color: #33aba5;"
									class="nav-link dropdown-toggle fw-normal px-3" type="button"
									id="dropdownMenuButton1" data-bs-toggle="dropdown"
									aria-expanded="false">${SecurityUtils.getPrincipal().getFullName() }
								</a>
								<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
									<li><a class="dropdown-item" href="editUser">Thông tin cá
											nhân</a></li>
									<li><a class="dropdown-item" href="transaction">Lịch sử giao
											dịch</a></li>
									<li><a class="dropdown-item" href="changepassword">Đổi mật khẩu</a></li>
									<li><hr class="dropdown-divider"></li>
									<li><a class="dropdown-item" href="logout">Đăng xuất</a></li>
								</ul>
							</div>
						</form>
					</security:authorize>
				
                </div>
            </div>
        </nav>
    </div>