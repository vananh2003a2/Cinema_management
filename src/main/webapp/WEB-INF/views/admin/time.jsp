<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:url var="searchURL" value="/search-movie" />
<!doctype html>
<html>

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Quản lý suất chiếu</title>
</head>
<body>
	<div class="dashboard-wrapper">
		<div class="dashboard-ecommerce">
			<div class="container-fluid dashboard-content ">
				<!-- ============================================================== -->
				<!-- pageheader  -->
				<!-- ============================================================== -->
				<div class="row">
					<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
						<div class="page-header">
							<h2 class="pageheader-title">Quản lý suất chiếu</h2>
							<div class="page-breadcrumb">
								<nav aria-label="breadcrumb">
									<ol class="breadcrumb">
										<li class="breadcrumb-item"><a href="#"
											class="breadcrumb-link">Home</a></li>
										<li class="breadcrumb-item active" aria-current="page">Quản
											lý suất chiếu</li>
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
							<form action="time" method="get">
								<input type="hidden" name="page" value="0"> <input
									type="hidden" name="size" value="5"> <input
									type="hidden" name="result" value="1">
								<div class="form-row align-items-center">
									<div class="form-group col-md-3">
										<label for="roomName">Phòng chiếu:</label> <select
											class="form-control" id="idRoom" name="idRoom">
											<c:if test="${idRoom == 0 }">
												<option value="0">-- Phòng --</option>
												<c:forEach var="room" items="${lst_room}">
													<option value="${room.idRoom}">${room.roomCode }</option>
												</c:forEach>
											</c:if>
											<c:if test="${idRoom > 0 }">
												<option value="0">-- Phòng --</option>
												<c:forEach var="room" items="${lst_room}">
													<c:if test="${room.idRoom == idRoom}">
														<option value="${room.idRoom}" selected="selected">${room.roomCode }</option>
													</c:if>
													<c:if test="${room.idRoom != idRoom}">
														<option value="${room.idRoom}">${room.roomCode }</option>
													</c:if>
												</c:forEach>
											</c:if>
										</select>
									</div>
									<div class="form-group col-md-3">
										<label for="dateRange">Ngày chiếu:</label>
										<div class="input-group">
											<c:set var="test" value="" />
											<c:if test="${!dateRange.equals(test)}">
												<input type="text" class="form-control" style="height: 39px" id="dateRange"
													name="dateRange" value="${dateRange}" />
											</c:if>
											<c:if test="${dateRange.equals(test)}"> <!-- sửa input ngày -->
												<input type="text" class="form-control" style="height: 39px" id="dateRange"
													name="dateRange" value="" placeholder="dd/mm/yyyy" />
											</c:if>
										</div>
									</div>

									<div class="form-group col-sm-6">
										<label for="">Nhập phim cần tìm: </label>
										<div class="input-group">
											<c:if test="${empty searchValue }">
												<input type="text" name="searchValue" class="form-control"
													value="" autofocus>
											</c:if>
											<c:if test="${not empty searchValue }">
												<input type="text" name="searchValue" class="form-control"
													value="${searchValue }" autofocus>
											</c:if>
											<div class="input-group-append">
												<button class="btn btn-info" type="submit">
													<i class="fa fa-search"></i>
												</button>
												<a href="time_add" class="btn btn-success"
													style="margin-left: 5px;"> <i class="fa fa-plus"></i>
													Tạo suất chiếu
												</a>
											</div>
										</div>
									</div>
								</div>
							</form>

							<div>
								<c:if test="${result.equals('1')}">
									<p class="mt-0">
										Có<strong> ${totalItems }</strong> phim trong tổng số <strong>${totalPages }</strong>
										trang
									</p>
								</c:if>

								<div class="table-responsive">
									<table class="table table-bordered table-hover table-striped">
										<thead>
											<tr class="bg-primary">
												<th>Suất chiếu</th>
												<th>Ngày chiếu</th>
												<th>Phim</th>
												<th>Thời lượng</th>
												<th>Thể loại</th>
												<th>Phòng chiếu</th>
												<th style="width: 100px;"></th>

											</tr>
										</thead>
										<c:if test="${!result.equals('1')}">
											<tbody>
												<p class="mt-0">Không tìm thấy kết quả.</p>
											</tbody>
										</c:if>
										<c:if test="${result.equals('1')}">
											<tbody>
												<c:forEach var="mtr" items="${lst_mtr}">
													<tr>
														<c:set var="formattedBeginTime"
															value="${fn:substring(mtr.beginTime, 0, 5)}" />
														<c:set var="formattedEndTime"
															value="${fn:substring(mtr.endTime, 0, 5)}" />
														<td>${formattedBeginTime } - ${formattedEndTime }</td>
														<c:set var="date" value="${mtr.getDate()}" />
														<fmt:formatDate value="${date}" pattern="dd/MM/yyyy"
															var="formattedDate" />
														<td>${formattedDate }</td>
														<c:forEach var="movie" items="${lst_movie}">
															<c:if
																test="${movie.idMovie == mtr.getIdMovie().getIdMovie()}">
																<c:set var="moviename" value="${movie.movieName }" />
																<td>${moviename}</td>
																<td>${movie.movieDuration }</td>
																<td>${movie.getIdMovieType().getTypeName() }</td>
															</c:if>
														</c:forEach>
														<c:forEach var="room" items="${lst_room}">
															<c:if
																test="${room.idRoom == mtr.getIdRoom()}">
																<td>Phòng ${room.roomCode }</td>
																
															</c:if>
														</c:forEach>
														<td class="text-center">
														<c:url var="editTimeURL" value="time_edit">
																<c:param name="idMovieTimeRoom" value="${mtr.idMovieTimeRoom}" />
															</c:url>
															<c:if test="${mtr.getStatus() == 1}">
															<a href="${editTimeURL}" class="btn btn-xs btn-info"
															style="width: 35px;"> <i class="fa fa-edit"></i>
														</a>
															</c:if>
															 <c:if test="${mtr.getStatus() == 0}">
															<a href="#" class="btn btn-xs btn-secondary"
															style="width: 35px; pointer-events: none;"> <i class="fa fa-edit" style="pointer-events: none;"></i>
														</a>
															</c:if>
														 <a data-toggle="modal"
															data-target="#myModal${mtr.idMovieTimeRoom}"
															class="btn btn-xs btn-danger"
															style="width: 35px; color: #fff;"> <i
																class="fa fa-remove"></i>
														</a></td>
													</tr>
												</c:forEach>
											</tbody>
										</c:if>
									</table>
									<!-- The Modal -->
									<c:forEach var="mtr" items="${lst_mtr}">
										<div class="modal fade" id="myModal${mtr.idMovieTimeRoom}">
											<div class="modal-dialog">
												<div class="modal-content">
													<!-- Modal Header -->
													<div class="modal-header">
														<h4 class="modal-title">Xóa suất chiếu</h4>
														<button type="button" class="close" data-dismiss="modal">×</button>
													</div>
													<!-- Modal Body -->
													<div class="modal-body">
														<table class="table text-left">
															<tbody>
																<tr>
																	<th scope="row">Thời gian:</th>
																	<td>${fn:substring(mtr.beginTime, 0, 5)} - 
																		${fn:substring(mtr.endTime, 0, 5)}</td>
																</tr>
																<tr>
																	<th scope="row">Phim:</th>
																	<c:forEach var="movie" items="${lst_movie}">
																		<c:if
																			test="${movie.idMovie == mtr.getIdMovie().getIdMovie()}">
																			<c:set var="moviename" value="${movie.movieName }" />
																			<td>${moviename}</td>
																		</c:if>
																	</c:forEach>

																</tr>
																<tr>
																	<th scope="row">Thời lượng phim:</th>
																	<c:forEach var="movie" items="${lst_movie}">
																		<c:if
																			test="${movie.idMovie == mtr.getIdMovie().getIdMovie()}">
																			<c:set var="moviename" value="${movie.movieName }" />
																			<td>${movie.movieDuration }phút</td>
																		</c:if>
																	</c:forEach>
																</tr>
																<tr>
																	<th scope="row">Thể loại:</th>
																	<c:forEach var="movie" items="${lst_movie}">
																		<c:if
																			test="${movie.idMovie == mtr.getIdMovie().getIdMovie()}">
																			<c:forEach var="mtype" items="${lst_movietype}">
																				<c:if
																					test="${movie.getIdMovieType().getIdMovieType() == mtype.getIdMovieType()}">
																					<td>${mtype.getTypeName()}</td>
																				</c:if>
																			</c:forEach>
																		</c:if>
																	</c:forEach>
																</tr>
																<tr>
																	<th scope="row">Phòng chiếu:</th>
																	<c:forEach var="room" items="${lst_room}">
																		<c:if
																			test="${room.idRoom == mtr.getIdRoom()}">
																			<td>Phòng ${room.roomCode }</td>
																		</c:if>
																	</c:forEach>
																</tr>
															</tbody>
														</table>
													</div>
													<!-- Modal Footer -->
													<div class="modal-footer">
														<a href="#" class="btn btn-danger btn-sm">Xác nhận</a>
													</div>
												</div>
											</div>
										</div>
									</c:forEach>

								</div>
							</div>

							<c:if test="${result.equals('1')}">
								<div class="row"
									style="justify-content: center; margin-top: 24px;">
									<ul class="pagination">
										<li class="page-item ${currentPage == 0 ? 'disabled' : ''}">
											<a class="page-link"
											href="<c:url value="/admin/time?page=0&size=5&searchValue=&dateRange=&idRoom=0&result=1" />"><i
												class="fas fa-angle-double-left"></i></a>
										</li>
										<li class="page-item ${currentPage == 0 ? 'disabled' : ''}">
											<a class="page-link"
											href="<c:url value="/admin/time?page=${currentPage - 1}&size=5&searchValue=${searchValue }&dateRange=${dateRange}&idRoom=${idRoom }&result=1" />"><i
												class="fas fa-angle-left"></i></a>
										</li>
										<c:forEach var="i" begin="${Math.max(0, currentPage - 1)}"
											end="${Math.max(0, Math.min(totalPages - 1, currentPage + 1))}">
											<li class="page-item ${i == currentPage ? 'active' : ''}">
												
												 <a class="page-link"
												href="<c:url value="/admin/time?page=${i}&size=5&searchValue=${searchValue }&dateRange=${dateRange}&idRoom=${idRoom }&result=1" />">${i + 1}</a>

											</li>
										</c:forEach>
										<li
											class="page-item ${currentPage == totalPages - 1 ? 'disabled' : ''}">
											<a class="page-link"
											href="<c:url value="/admin/time?page=${currentPage + 1}&size=5&searchValue=${searchValue }&dateRange=${dateRange}&idRoom=${idRoom }&result=1" />"><i
												class="fas fa-angle-right"></i></a>
										</li>
										<li
											class="page-item ${currentPage == totalPages - 1 ? 'disabled' : ''}">
											<a class="page-link"
											href="<c:url value="/admin/time?page=${totalPages - 1}&size=5&searchValue=${searchValue }&dateRange=${dateRange}&idRoom=${idRoom }&result=1" />"><i
												class="fas fa-angle-double-right"></i></a>
										</li>
									</ul>
								</div>
							</c:if>
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
	<script
		src="<c:url value="/template/admin/assets/vendor/bootstrap/js/bootstrap.bundle.js"/>"></script>
	<!-- slimscroll js -->
	<script
		src="<c:url value="/template/admin/assets/vendor/slimscroll/jquery.slimscroll.js"/>"></script>
	<!-- main js -->
	<script
		src="<c:url value="/template/admin/assets/libs/js/main-js.js"/>"></script>
	<!-- chart chartist js -->
	<script
		src="<c:url value="/template/admin/assets/vendor/charts/chartist-bundle/chartist.min.js"/>"></script>
	<!-- sparkline js -->
	<script
		src="<c:url value="/template/admin/assets/vendor/charts/sparkline/jquery.sparkline.js"/>"></script>
	<!-- morris js -->
	<script
		src="<c:url value="/template/admin/assets/vendor/charts/morris-bundle/raphael.min.js"/>"></script>
	<script
		src="<c:url value="/template/admin/assets/vendor/charts/morris-bundle/morris.js"/>"></script>
	<!-- chart c3 js -->
	<script
		src="<c:url value="/template/admin/assets/vendor/charts/c3charts/c3.min.js"/>"></script>
	<script
		src="<c:url value="/template/admin/assets/vendor/charts/c3charts/d3-5.4.0.min.js"/>"></script>
	<script
		src="<c:url value="/template/admin/assets/vendor/charts/c3charts/C3chartjs.js"/>"></script>
	<script
		src="<c:url value="/template/admin/assets/libs/js/dashboard-ecommerce.js"/>"></script>

	<!-- <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script> -->
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
	<script>
		$(document).ready(function() {
			$('#dateRange').datepicker({
				format : 'dd/mm/yyyy',
				autoclose : true
			});
		});
	</script>
</body>