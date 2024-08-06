<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="header">
        <nav class="navbar navbar-expand-lg navbar-light bg-white p-0">
            <div class="container-fluid">
                <a class="navbar-brand p-0" href="#"><img class="height-header img-fluid" src="<c:url value='/template/web/img/logo/sdasd40.png' />"
                        alt="no-logo" class=""></a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 d-flex justify-content-center align-items-center">
                        <li class="nav-item">
                            <a class="m-0 nav-link active fw-normal text-secondary h5 px-3" aria-current="page" href="#">TRANG CHỦ</a>
                        </li>
                        <li class="nav-item">
                            <a class="m-0 nav-link fw-normal text-secondary h5 px-3" href="./timeCinema.html">LỊCH CHIẾU</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link fw-normal text-secondary h5 px-3 m-0" href="#">LIÊN HỆ</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="m-0 nav-link dropdown-toggle fw-normal text-secondary h5 px-3" href="#"
                                id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                PHIM
                            </a>
                            <ul class="dropdown-menu m-0 p-0 shadow-sm" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="#">Phim đang chiếu</a></li>
                                <li><a class="dropdown-item" href="#">Phim sắp chiếu</a></li>
                            </ul>
                        </li>
                    </ul>
                    <form class="d-flex">
                        <a href="./logIn.html" class="btn btn-outline-success me-2 rounded-pill">Đăng nhập</a>
                        <a href="./register.html" class="btn btn-outline-success rounded-pill">Đăng ký</a>
                    </form>
                </div>
            </div>
        </nav>
    </div>