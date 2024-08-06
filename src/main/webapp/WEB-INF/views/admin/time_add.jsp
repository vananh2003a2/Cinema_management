<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Thêm Suất Chiếu</title>
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
							<h2 class="pageheader-title">Thêm suất chiếu</h2>
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
							<form action="<c:url value='time_add'/>" method="post">
								<div class="form-row align-items-center">
									<div class="form-group col-md-3">
										<label for="roomName">Chọn phòng chiếu:</label> <select
											class="form-control" id="roomName" name="roomName" required>
											<c:if test="${empty(room)}">
												<option value="">--Chọn phòng--</option>
											</c:if>
											<c:if test="${not empty(room)}">
												<option value="${room.getIdRoom()}">${room.getRoomCode()}</option>
											</c:if>
											<c:forEach items="${lstRoom}" var="r">
												<c:if test="${r.getIdRoom()!=room.getIdRoom()}">
													<option value="${r.getIdRoom()}">${r.getRoomCode()}</option>
												</c:if>
											</c:forEach>
										</select>
									</div>
									<div class="form-group col-md-3">
										<label for="startDate">Chọn ngày chiếu:</label>
										<c:if test="${empty(dateView)}">
											<input class="form-control h-40" style="height: 39px"
												id="daterange" name="dateView" placeholder="dd/mm/yyyy"
												required>
										</c:if>
										<c:if test="${not empty(dateView)}">
											<input class="form-control h-40" style="height: 39px"
												id="daterange" name="dateView" value="${dateView}"
												placeholder="dd/mm/yyyy" required>
										</c:if>

									</div>
									<div class="form-group col-md-3">
										<label for="roomName">Chọn phim:</label> <select
											class="form-control" id="movieName" name="movieName" required>
											<c:if test="${empty(movie)}">
												<option value="">--Chọn phim--</option>
											</c:if>
											<c:if test="${not empty(movie)}">
												<option value="${movie.getIdMovie()}">${movie.getMovieName()}</option>
											</c:if>
											<c:forEach items="${lstMovie}" var="mov">
												<c:if test="${mov.getIdMovie()!=movie.getIdMovie()}">
													<option value="${mov.getIdMovie()}">${mov.getMovieName()}</option>
												</c:if>
											</c:forEach>
										</select>
									</div>
									<div class="form-group col-md-1 ">
										<!-- Nhập giờ mong muốn tạo -->
										<label for="dadad">Giờ bắt đầu:</label>
										<c:if test="${empty timeInput}">
											<input  class="timeInput" id="timeInput" placeholder="hh:mm"
												name="timeInput"
												style="width: 100%; height: 38px; font-size: 14px; line-height: 1.42857143; color: #71748d; background-color: #fff; background-image: none; border: 1px solid #d2d2e4; border-radius: 2px; text-align: center;"
												type="text" maxlength="5" value="07:00">
										</c:if>
										<c:if test="${not empty timeInput }">
											<input class="timeInput" id="timeInput" placeholder="hh:mm"
												name="timeInput"
												style="width: 100%; height: 38px; font-size: 14px; line-height: 1.42857143; color: #71748d; background-color: #fff; background-image: none; border: 1px solid #d2d2e4; border-radius: 2px; text-align: center;"
												type="text" maxlength="5" value="${timeInput}">
										</c:if>
									</div>
									<div class="form-group col-md-2 text-right "
										style="margin-top: 26px;">
										<!-- <a style="margin-top: 26px;" href="" class="btn btn-info" type="submit">Hiển
											thị lịch trống</a> -->
										<button href="" class="btn btn-info" type="submit">Hiển
											thị lịch trống</button>
									</div>
								</div>
							</form>
							<div>
								<c:choose>
									<c:when test="${status == 'true'}">
										<c:choose>
											<c:when test="${empty newShowTimes}">
												<div class="text-center mt-4">
													<h6>Không có suất chiếu trống!</h6>
												</div>
											</c:when>
											<c:otherwise>
												<div class="table-responsive mt-4"
													style="margin: auto; width: 60%;">
													<table
														class="table table-bordered table-hover table-striped mx-auto">
														<thead>
															<tr class="bg-primary text-center">
																<th>Thời gian</th>
																<th style="width: 50px;"><input type="checkbox"
																	id="checkAll" onclick="checkAll(this)"></th>
															</tr>
														</thead>
														<tbody>
															<c:forEach items="${newShowTimes}" var="showTime">
																<tr class="text-center">
																	<td>${showTime.getBeginTime().toString().substring(0, 5)}
																		- ${showTime.getEndTime().toString().substring(0, 5)}</td>
																	<td><input type="checkbox"
																		value="${showTime.getBeginTime().toString().substring(0, 5)}"
																		onchange="displayCheckedValues(this)"></td>
																</tr>
															</c:forEach>
														</tbody>
													</table>
												</div>
											</c:otherwise>
										</c:choose>
									</c:when>
								</c:choose>
								<div class="text-right mt-4">
									<a data-toggle="modal" data-target="#myModal"
										class="btn btn-success mr-4 text-white">Xác nhận</a> <a
										href="<c:url value='time?page=0&size=5&searchValue=&dateRange=&idRoom=0&result=1'/>"
										class="btn btn-secondary text-white ">Quay lại</a>
								</div>
								<!-- The Modal -->
								<c:choose>
									<c:when test="${status=='true' }">
										<div class="modal fade" id="myModal">
											<div class="modal-dialog">
												<div class="modal-content">
													<!-- Modal Header -->
													<div class="modal-header">
														<h4 class="modal-title">Tạo suất chiếu</h4>

														<button type="button" class="close" data-dismiss="modal">×</button>
													</div>
													<div class="modal-body">
														<div class="text-center mb-4">
															<h5>
																Phim: <span>${movie.getMovieName()}</span>
															</h5>
															<h6>
																Thời lượng: <span>${movie.getMovieDuration()}
																	Phút</span>
															</h6>
														</div>
														<div class="form-row m-2">
															<div class="col">
																<h6>

																	Ngày chiếu: <span>${dateView}</span>
																</h6>
															</div>
															<div class="col text-right">
																<h6>
																	Phòng: <span>${room.getRoomCode()}</span>
																</h6>
															</div>
														</div>
														<div class="form-row m-2">
															<div class="col">
																<h6>Suất chiếu:</h6>
																<div id="selectedShowTimes"></div>
															</div>
														</div>
														<div class="success-message" style="text-align:center; color: green; font-size: 16px; font-weight: bold;"></div>
													</div>
													<!-- Modal footer -->
													<div class="modal-footer">
														<button class="btn btn-success btn-sm"
															onclick="createShowTimes()" style="color: #fff;">Tạo
															suất chiếu</button>
														<a class="btn btn-danger btn-sm" data-dismiss="modal"
															style="color: #fff;">Hủy bỏ</a>
													</div>
												</div>
											</div>
										</div>
									</c:when>
								</c:choose>

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
	<script src="assets/vendor/bootstrap/js/bootstrap.bundle.js"></script>
	<!-- slimscroll js -->
	<script src="assets/vendor/slimscroll/jquery.slimscroll.js"></script>
	<!-- main js -->
	<script src="assets/libs/js/main-js.js"></script>


	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
	<script>
		// Lắng nghe sự kiện keydown trên các input có lớp CSS 'timeInput'
		document.querySelectorAll('.timeInput').forEach(
				function(input) {
					input.addEventListener('keydown', function(event) {
						var value = event.target.value;
						var key = event.key;

						if (value.length === 2 && !value.includes(':')
								&& !isNaN(key)) {
							event.target.value = value + ':';
						}

						if ((key >= '0' && key <= '9') || key === ':'
								|| key === 'Backspace') {
							return true;
						} else {
							event.preventDefault();
							return false;
						}
					});
				});
	</script>
	<script type="text/javascript">
		document.querySelector('form')
				.addEventListener(
						'submit',
						function(event) {
							var roomName = document.getElementById('roomName');
							var startDate = document
									.getElementById('startDate');
							var movieName = document
									.getElementById('movieName');

							if (!roomName.value || !startDate.value
									|| !movieName.value) {
								alert('Vui lòng điền đầy đủ thông tin.');
								event.preventDefault(); // Ngăn chặn gửi biểu mẫu nếu thông tin không hợp lệ
							}
						});
	</script>
	<script>
		function getCheckedValues() {
			var checkedValues = [];
			var checkboxes = document
					.querySelectorAll('input[type="checkbox"]:checked');
			checkboxes.forEach(function(checkbox) {
				if (checkbox.id !== 'checkAll') { // Loại bỏ nút "checkAll" ra khỏi danh sách
					checkedValues.push(checkbox.value);
				}
			});
			return checkedValues;
		}

		function checkAll(checkbox) {
			var checkboxes = document
					.querySelectorAll('input[type="checkbox"]');
			checkboxes.forEach(function(cb) {
				if (cb.id !== 'checkAll') { // Kiểm tra ID của checkbox trước khi thực hiện
					cb.checked = checkbox.checked;
					displayCheckedValues(cb);
				}
			});
		}
		function displayCheckedValues(checkbox) {
			var container = document.getElementById('selectedShowTimes');
			if (checkbox.checked) {
				var span = document.createElement('span');
				span.className = 'mr-1';
				span.style.border = '1px solid';
				span.style.padding = '4px';
				span.textContent = checkbox.value;
				container.appendChild(span);
			} else {
				var spans = container.querySelectorAll('span');
				spans.forEach(function(span) {
					if (span.textContent === checkbox.value) {
						span.remove();
					}
				});
			}
		}
	</script>
	<script>
		$(document).ready(function() {
			$('#daterange').datepicker({
				format : 'dd/mm/yyyy',
				autoclose : true
			});
		});

		function createShowTimes() {
			var checkedValues = getCheckedValues(); // Lấy mảng checkedValues

			// Lấy giá trị của phim và phòng
			var movieId = document.getElementById('movieName').value;
			var roomId = document.getElementById('roomName').value;
			var dateView = document.getElementById('daterange').value;
			var timeInput = document.getElementById('timeInput').value;
			// Tạo đối tượng JSON chứa thông tin của phim, phòng và checkedValues
			var data = {
				"movieId" : movieId,
				"roomId" : roomId,
				"dateView" : dateView,
				"checkedValues" : checkedValues,
				"timeInput" : timeInput
			};

			// Gửi yêu cầu POST qua AJAX
			var xhr = new XMLHttpRequest();
			xhr.open('POST', '<c:url value="saveTime"/>', true);
			xhr.setRequestHeader('Content-Type', 'application/json');
			xhr.onreadystatechange = function() {
				if (xhr.readyState === 4) {
					if (xhr.status === 200) {
						// Xử lý phản hồi từ controller nếu cần
						console.log(xhr.responseText);
						// Hiển thị successMessage nếu tồn tại
						var successMessage = xhr.responseText;
						if (successMessage.trim() !== "") {
							document.querySelector('.success-message').innerHTML = successMessage;
						}
					} else {
						// Xử lý lỗi nếu có
						console.error('Đã có lỗi xảy ra: ' + xhr.status);
					}
				}
			};
			xhr.send(JSON.stringify(data));
		}
	</script>
</body>
</html>

