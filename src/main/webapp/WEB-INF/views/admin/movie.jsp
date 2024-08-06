<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!doctype html>
<html lang="en">

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<title>Quản lý phim</title>
</head>

<body>
	<!-- ============================================================== -->
	<!-- main wrapper -->
	<!-- ============================================================== -->
	<!-- ============================================================== -->
	<!-- navbar -->
	<!-- ============================================================== -->

	<!-- ============================================================== -->
	<!-- end navbar -->
	<!-- ============================================================== -->
	<!-- ============================================================== -->
	<!-- left sidebar -->
	<!-- ============================================================== -->

	<!-- ============================================================== -->
	<!-- end left sidebar -->
	<!-- ============================================================== -->
	<!-- ============================================================== -->
	<!-- wrapper  -->
	<!-- ============================================================== -->
	<div class="dashboard-wrapper">
		<div class="dashboard-ecommerce">
			<div class="container-fluid dashboard-content ">
				<!-- ============================================================== -->
				<!-- pageheader  -->
				<!-- ============================================================== -->
				<div class="row">
					<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
						<div class="page-header">
							<h2 class="pageheader-title">Quản lý phim</h2>
							<div class="page-breadcrumb">
								<nav aria-label="breadcrumb">
									<ol class="breadcrumb">
										<li class="breadcrumb-item"><a href="#"
											class="breadcrumb-link">Home</a></li>
										<li class="breadcrumb-item active" aria-current="page">Quản
											lý phim</li>
									</ol>
								</nav>
							</div>
						</div>
					</div>
				</div>
				<!-- ============================================================== -->
				<!-- end pageheader  -->
				<!-- ============================================================== -->
				<div class="ecommerce-widget">
					<div class="box box-primary">
						<div class="box-body">
							<form action="<c:url value='/admin/searchMovie'/>" method="get">
								<input type="hidden" name="page" value="0"> <input
									type="hidden" name="size" value="5">
								<div class="form-row d-flex align-items-center">
									<div class="form-group col-sm-3">
										<label for="">Thể loại phim:</label> <select
											class="form-control" name="idMovieType" id="idMovieType">
											<option value="">--Chọn loại phim--</option>
											<c:forEach var="movietype" items="${lstmovietype}">
												<option value="${movietype.getIdMovieType()}"
													${movietype.getIdMovieType() == idMovieType ? "selected" : "" }>${movietype.getTypeName()}</option>
											</c:forEach>
										</select>
									</div>

									<div class="form-group col-sm-3">
										<label for="">Chọn ngày chiếu:</label> <input
											class="form-control h-40" name="movieDate" id="daterange"
											placeholder="dd/mm/yyyy" value="${movieDate}" type="text"
											style="height: 39px">

									</div>
									<div class="form-group col-sm-6">
										<label for="">Nhập phim cần tìm: </label>
										<div class="input-group">
											<input type="text" name="searchValue" value="${searchValue}"
												class="form-control" autofocus>
											<div class="input-group-append">
												<button class="btn btn-info" type="submit">
													<i class="fa fa-search"></i>
												</button>
												<a href="show-add-movie" class="btn btn-success"
													style="margin-left: 5px;"> <i class="fa fa-plus"></i>
													Bổ sung
												</a>
											</div>
										</div>
									</div>
								</div>
							</form>
							<div>
								<p style="margin: 10px 0 10px 0">
									Có<strong> ${totalItems }</strong> phim trong tổng số <strong>${totalPages }</strong>
									trang
								</p>
								<div class="table-responsive mt-2">
									<table class="table table-bordered table-hover table-striped">
										<thead>
											<tr class="bg-primary">
												<th style="width: 80px">Ảnh</th>
												<th>Tên phim</th>
												<th>Ngày khởi chiếu</th>
												<th>Thời lượng phim</th>
												<th>Đạo diễn</th>
												<th>Diễn viên</th>
												<th>Thể loại</th>
												<th>Định dạng</th>
												<th style="width: 100px"></th>
											</tr>
										</thead>
										<tbody>
											<c:if test="${isListMovieEmpty}">
												<tr>
													<td colspan="9" class="text-center">Danh sách phim
														trống.</td>
												</tr>
											</c:if>
											<c:if test="${!isListMovieEmpty}">
												<c:forEach var="movie" items="${lstmovie}" varStatus="status">
													<tr>
														<td><img style="height: 140px; width: 110px;"
															src='<c:url value="/template/customer/img/${movie.thumnail}"/>' /></td>
														<td>${movie.movieName}</td>
														<c:set var="formattedDate"
															value="${fn:substring(movie.movieDate, 8, 10)}/${fn:substring(movie.movieDate, 5, 7)}/${fn:substring(movie.movieDate, 0, 4)}" />
														<td>${formattedDate}</td>
														<td>${movie.movieDuration} phút</td>
														<td>${movie.director}</td>
														<td>${movie.actors}</td>
														<td>${movie.idMovieType.typeName}</td>
														<td>${movie.movieFormat}</td>
														<td class="text-center"><a
															href="show-edit-movie?idMovie=${movie.idMovie }"
															class="btn btn-xs btn-info" style="width: 35px;"> <i
																class="fa fa-edit"></i>
														</a> <c:if test="${checkMovie[status.index]}">
																<a data-toggle="modal"
																	data-target="#myModal${movie.idMovie}"
																	class="btn btn-xs btn-danger"
																	style="width: 35px; color: #fff;"> <i
																	class="fa fa-remove"></i>
																</a>
															</c:if>
															<c:if test="${checkMovie[status.index] == false}">
																<a data-toggle="modal"
																	data-target="#"
																	class="btn btn-xs btn-secondary"
																	style="width: 35px; color: #fff;"> <i
																	class="fa fa-remove"></i>
																</a>
															</c:if>
															</td>
													</tr>
												</c:forEach>
											</c:if>
										</tbody>
									</table>
									<!-- The Modal -->
									<c:forEach var="movie" items="${lstmovie}">
										<div class="modal fade" id="myModal${movie.idMovie }">
											<div class="modal-dialog">
												<div class="modal-content">

													<!-- Modal Header -->
													<div class="modal-header">
														<h4 class="modal-title ">Xóa phim</h4>
														<button type="button" class="close" data-dismiss="modal">×</button>
													</div>

													<div class="modal-body">
														<table class="table text-left">
															<tbody>
																<tr>
																	<th scope="row">Ảnh:</th>
																	<td><img style="height: 140px; width: 110px;"
																		src='<c:url value="/template/customer/img/${movie.thumnail}"/>' /></td>
																</tr>
																<tr>
																	<th scope="row">Tên phim:</th>
																	<td>${movie.movieName}</td>
																</tr>
																<tr>
																	<th scope="row">Ngày khởi chiếu:</th>
																	<c:set var="formattedDate"
																		value="${fn:substring(movie.movieDate, 8, 10)}/${fn:substring(movie.movieDate, 5, 7)}/${fn:substring(movie.movieDate, 0, 4)}" />
																	<td>${formattedDate}</td>
																</tr>
																<tr>
																	<th scope="row">Thời lượng phim:</th>
																	<td>${movie.movieDuration}phút</td>
																</tr>
																<tr>
																	<th scope="row">Đạo diễn:</th>
																	<td>${movie.director}</td>
																</tr>
																<tr>
																	<th scope="row">Diễn viên:</th>
																	<td>${movie.actors}</td>
																</tr>

																<tr>
																	<th scope="row">Định dạng:</th>
																	<td>${movie.movieFormat}</td>
																</tr>
															</tbody>
														</table>
													</div>
													<!-- Modal footer -->
													<div class="modal-footer">

														<a href="deletemovie?idMovie=${movie.idMovie}"
															class="btn btn-danger btn-sm">Xác nhận</a>


													</div>
												</div>
											</div>
										</div>
									</c:forEach>
								</div>
							</div>
							<div class="row"
								style="justify-content: center; margin-top: 24px;">
								<ul class="pagination">
									<li class="page-item ${currentPage == 0 ? 'disabled' : ''}">
										<a class="page-link" href="?page=0"><i
											class="fas fa-angle-double-left"></i></a>
									</li>
									<li class="page-item ${currentPage == 0 ? 'disabled' : ''}">
										<a class="page-link" href="?page=${currentPage - 1}"><i
											class="fas fa-angle-left"></i></a>
									</li>
									<c:forEach var="i" begin="${Math.max(0, currentPage - 1)}"
										end="${Math.max(0, Math.min(totalPages - 1, currentPage + 1))}">
										<li class="page-item ${i == currentPage ? 'active' : ''}">
											<a class="page-link"
											href="?page=${i}&idMovieType=${idMovieType}&searchValue=${searchValue}&movieDate=${movieDate}">${i + 1}</a>
										</li>
									</c:forEach>
									<li
										class="page-item ${currentPage == totalPages - 1 ? 'disabled' : ''}">
										<a class="page-link" href="?page=${currentPage + 1}"><i
											class="fas fa-angle-right"></i></a>
									</li>
									<li
										class="page-item ${currentPage == totalPages - 1 ? 'disabled' : ''}">
										<a class="page-link" href="?page=${totalPages - 1}"><i
											class="fas fa-angle-double-right"></i></a>
									</li>
								</ul>




							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- ============================================================== -->
		<!-- footer -->
		<!-- ============================================================== -->
		<div class="footer">
			<div class="container-fluid">
				<div class="row">
					<div class="col-xl-6 col-lg-6 col-md-6 col-sm-12 col-12">
						Copyright © 2018 Concept. All rights reserved. Dashboard by <a
							href="https://colorlib.com/wp/">Colorlib</a>.
					</div>
					<div class="col-xl-6 col-lg-6 col-md-6 col-sm-12 col-12">
						<div class="text-md-right footer-links d-none d-sm-block">
							<a href="javascript: void(0);">About</a> <a
								href="javascript: void(0);">Support</a> <a
								href="javascript: void(0);">Contact Us</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- ============================================================== -->
		<!-- end footer -->
		<!-- ============================================================== -->
	</div>
	<!-- ============================================================== -->
	<!-- end wrapper  -->
	<!-- ============================================================== -->
	<!-- ============================================================== -->
	<!-- end main wrapper  -->
	<!-- ============================================================== -->
	<!-- Optional JavaScript -->
	<!-- jquery 3.3.1 -->
	<!-- <script src="assets/vendor/jquery/jquery-3.3.1.min.js"></script> -->
	<!-- bootstap bundle js -->
	<script src="assets/vendor/bootstrap/js/bootstrap.bundle.js"></script>
	<!-- slimscroll js -->
	<script src="assets/vendor/slimscroll/jquery.slimscroll.js"></script>
	<!-- main js -->
	<script src="assets/libs/js/main-js.js"></script>
	<!-- chart chartist js -->
	<script src="assets/vendor/charts/chartist-bundle/chartist.min.js"></script>
	<!-- sparkline js -->
	<script src="assets/vendor/charts/sparkline/jquery.sparkline.js"></script>
	<!-- morris js -->
	<script src="assets/vendor/charts/morris-bundle/raphael.min.js"></script>
	<script src="assets/vendor/charts/morris-bundle/morris.js"></script>
	<!-- chart c3 js -->
	<script src="assets/vendor/charts/c3charts/c3.min.js"></script>
	<script src="assets/vendor/charts/c3charts/d3-5.4.0.min.js"></script>
	<script src="assets/vendor/charts/c3charts/C3chartjs.js"></script>
	<script src="assets/libs/js/dashboard-ecommerce.js"></script>

	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
	<script>
		$(document).ready(function() {
			$('#daterange').datepicker({
				format : 'dd/mm/yyyy',
				autoclose : true
			});
		});

		const message = "${message}";
		var hasMessage = message != "";
		console.log(hasMessage);
		if (hasMessage === true) {
			alert(message);
		}
	</script>

</body>

</html>