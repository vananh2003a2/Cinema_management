<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="dec"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%-- <jsp:forward page="ajax.html"></jsp:forward> --%>
<!doctype html>
<html>

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Thông tin người dùng</title>
</head>

<body>

		<div class="dashboard-wrapper">
			<div class="dashboard-ecommerce">
				<div class="container-fluid dashboard-content ">
					<div class="row">
						<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
							<div class="page-header">
								<h2 class="pageheader-title">Thông tin người dùng</h2>
								<div class="page-breadcrumb">
									<nav aria-label="breadcrumb">
										<ol class="breadcrumb">
											<li class="breadcrumb-item"><a href="#"
												class="breadcrumb-link">Home</a></li>
											<li class="breadcrumb-item active" aria-current="page">Thông tin người dùng</li>
										</ol>
									</nav>
								</div>
							</div>
						</div>
					</div>
					<div class="ecommerce-widget">
						<div class="box box-primary">
							<div class="box-body">
								<!-- Form nhập đầu vào tìm kiếm -->
								<form action='qladmin' method="get">
									<div class="input-group">
										<input id="name" value="${key}" name="txtsearch" type="text" class="form-control"
											placeholder="Nhập tên người dùng cần tìm" autofocus>
										<div class="input-group-btn">
											<button name="but" class="btn btn-primary" type="submit">
												<i class="fa fa-search" onclick="addViaAjax()"></i>
											</button>
											<!-- <a href="user_add.html" class="btn btn-success"
												style="margin-left: 5px"> <i class="fa fa-plus"></i> Bổ sung
											</a> -->
										</div>
									</div>
								</form>
								<!-- Hiển thị kết quả tìm kiếm -->
								<div>
								<c:if test="${not empty listuser}">
									<p style="margin: 10px 0 10px 0">
										Có <strong>${totalrow }</strong> khách hàng trong tổng số <strong>${totalPages}</strong>
										trang
									</p>
								</c:if>
									<div class="table-responsive">
										<table class="table table-bordered table-hover table-striped">
											<thead>
												<tr class="bg-primary" style="text-align: center;">
													<th style="width: 4%">STT</th>
													<th>Họ tên</th>
													<th>Ngày sinh</th>
													<th>Địa chỉ</th>
													<th>Điện thoại</th>
													<th>Email</th>
													
												</tr>
											</thead>
											<tbody>
												<c:if test="${empty listuser}">
													<p>Danh sách người dùng trống.</p>
												</c:if>
												<%int stt = 1; %>
												<c:if test="${not empty listuser}">
													<c:forEach items="${listuser}" var="user">
														<tr style="text-align: center;">
															<td><%=stt++ %></td>
															<td>${user.getFullName()}</td>
															<td><fmt:formatDate value="${user.dateOfBirth}" pattern="dd/MM/yyyy" /></td>
															<td>${user.getAddress()}</td>
															<td>${user.getPhone()}</td>
															<td>${user.getEmail()}</td>
															
														</tr>
													</c:forEach>
												</c:if>
											</tbody>
										</table>
										
									</div>
								</div>
								
								
								<c:choose>
								<c:when test="${not empty listuser}">
									<div class="row"
										style="justify-content: center; margin-top: 24px;">
										<ul class="pagination">
											<li class="page-item"><a class="page-link"
												href="?page=0&amp;status=${param.status}&amp;txtsearch=${param.txtsearch}"><i
													class="fas fa-angle-double-left"></i></a></li>
											<c:if test="${currentPage - 1 >= 0}">
												<li class="page-item"><a class="page-link"
													href="?page=${currentPage - 1}&amp;status=${param.status}&amp;txtsearch=${param.txtsearch}"><i
														class="fas fa-angle-left"></i></a></li>
											</c:if>

											<c:set var="startPage" value="${currentPage - 1}" />
											<c:if test="${startPage lt 0}">
												<c:set var="startPage" value="0" />
											</c:if>
											<c:set var="endPage" value="${currentPage + 1}" />
											<c:if test="${endPage ge totalPages}">
												<c:set var="endPage" value="${totalPages - 1}" />
											</c:if>

											<c:if test="${totalPages >= 3}">
												<c:choose>
													<c:when test="${currentPage == 0}">
														<c:set var="endPage" value="${startPage + 2}" />
													</c:when>
													<c:when test="${currentPage == totalPages - 1}">
														<c:set var="startPage" value="${endPage - 2}" />
													</c:when>
												</c:choose>
											</c:if>

											<c:forEach var="pageIndex" begin="${startPage}"
												end="${endPage}" step="1">
												<c:set var="pageNumber" value="${pageIndex + 1}" />
												<li
													class="page-item ${pageIndex == currentPage ? 'active' : ''}">
													<a class="page-link"
													href="?page=${pageIndex}&amp;status=${param.status}&amp;txtsearch=${param.txtsearch}">${pageNumber}</a>
												</li>
											</c:forEach>

											<c:if test="${currentPage + 1 < totalPages}">
												<li class="page-item"><a class="page-link"
													href="?page=${currentPage + 1}&amp;status=${param.status}&amp;txtsearch=${param.txtsearch}"><i
														class="fas fa-angle-right"></i></a></li>
											</c:if>
											<li class="page-item"><a class="page-link"
												href="?page=${totalPages - 1}&amp;status=${param.status}&amp;txtsearch=${param.txtsearch}"><i
													class="fas fa-angle-double-right"></i></a></li>
										</ul>
									</div>
								</c:when>
								<c:otherwise>
									<div class="text-center" role="alert">Không có kết quả
										nào được tìm thấy.</div>
								</c:otherwise>
							</c:choose>
								

							</div>

						</div>

					</div>
				</div>
			</div>

		</div>

	<script
		src="<c:url value='/template/admin/assets/vendor/jquery/jquery-3.3.1.min.js'/>"></script>
	<!-- bootstap bundle js -->
	<script
		src="<c:url value='/template/admin/assets/vendor/bootstrap/js/bootstrap.bundle.js'/>"></script>
	<!-- slimscroll js -->
	<script
		src="<c:url value='/template/admin/assets/vendor/slimscroll/jquery.slimscroll.js'/>"></script>
	<!-- main js -->
	<script src="<c:url value='/template/admin/assets/libs/js/main-js.js'/>"></script>
	<!-- chart chartist js -->
	<script
		src="<c:url value='/template/admin/assets/vendor/charts/chartist-bundle/chartist.min.js'/>"></script>
	<!-- sparkline js -->
	<script
		src="<c:url value='/template/admin/assets/vendor/charts/sparkline/jquery.sparkline.js'/>"></script>
	<!-- morris js -->
	<script
		src="<c:url value='/template/admin/assets/vendor/charts/morris-bundle/raphael.min.js'/>"></script>
	<script
		src="<c:url value='/template/admin/assets/vendor/charts/morris-bundle/morris.js'/>"></script>
	<!-- chart c3 js -->
	<script
		src="<c:url value='/template/admin/assets/vendor/charts/c3charts/c3.min.js'/>"></script>
	<script
		src="<c:url value='/template/admin/assets/vendor/charts/c3charts/d3-5.4.0.min.js'/>"></script>
	<script
		src="<c:url value='/template/admin/assets/vendor/charts/c3charts/C3chartjs.js'/>"></script>
	<script
		src="<c:url value='/template/admin/assets/libs/js/dashboard-ecommerce.js'/>"></script>
		
</body>

</html>