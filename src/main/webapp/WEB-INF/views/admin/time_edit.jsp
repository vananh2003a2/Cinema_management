
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
							<h2 class="pageheader-title">Cập nhật suất chiếu</h2>
							<div class="page-breadcrumb">
								<nav aria-label="breadcrumb">
									<ol class="breadcrumb">
										<li class="breadcrumb-item"><a href="#"
											class="breadcrumb-link">Home</a></li>
										<li class="breadcrumb-item active" aria-current="page">Quản
											lý suất chiếu</li>
										<li class="breadcrumb-item active" aria-current="page">Cập
											nhật suất chiếu</li>
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
							<h6>
								Phim: <span>${movieTimeRoom.getIdMovie().getMovieName()}</span>
							</h6>
							<h6>
								Thời lượng: <span id="movieDuration">${movieTimeRoom.getIdMovie().getMovieDuration()}</span>
							</h6>
							<div class="d-flex">
								<h6 class="mr-5">
									<c:set var="formattedDate">
										<fmt:formatDate value="${movieTimeRoom.getDate()}"
											pattern="dd/MM/yyyy" />
									</c:set>
									Ngày chiếu: <span>${formattedDate}</span>
								</h6>
								<h6 class="text-right ">
									Phòng: <span>${movieTimeRoom.getIdRoom().getRoomCode()}</span>
								</h6>
							</div>
							<div class="d-flex">
								<div class="mr-auto">
									<div class="d-flex">
										<h6 class="mr-2 mt-1">Suất chiếu cũ:</h6>
										<div>
											<input class="timeInput" placeholder="hh:mm" name="timeInput"
												style="width: 55px;" type="text" maxlength="5"
												value="${movieTimeRoom.getBeginTime().toString().substring(0, 5)}"
												disabled> - <input class="timeInput"
												placeholder="hh:mm" name="timeInput" style="width: 55px;"
												type="text" maxlength="5"
												value="${movieTimeRoom.getEndTime().toString().substring(0, 5)}"
												disabled>
										</div>
									</div>

									<div class="d-flex">
										<h6 class="mr-2 mt-1">Suất chiếu mới:</h6>
										<div>
											<input class="timeInput" placeholder="hh:mm" id="timeBegin"
												name="startTimeInput" style="width: 55px;" type="text"
												maxlength="5" onkeydown="handleStartTimeKeyPress(event)">

											- <input class="timeInput" placeholder="hh:mm" id="timeEnd"
												name="endTimeInput" style="width: 55px;" type="text"
												maxlength="5" disabled>
										</div>
									</div>

								</div>
								<div class="text-right ml-auto">
									<a 
									data-toggle="modal" data-target="#myModal"
										class="btn btn-info text-white mr-4">Kiểm tra</a> <a
										href="<c:url value='time?page=0&size=5&searchValue=&dateRange=&idRoom=0&result=1'/>" class="btn btn-secondary text-white">Quay
										lại</a>
								</div>
							</div>
							<c:if test="${empty lstTimeRange}">
								<p style="font-size: 16px; margin: 7px;">Không có thời gian
									bắt đầu hợp lệ</p>
							</c:if>
							<c:if test="${not empty lstTimeRange}">
								<p style="font-size: 16px; margin: 7px;">Thời gian bắt đầu
									có thể chỉnh sửa</p>
								<table class="table table-bordered table-hover table-striped">
									<thead class="text-center">
										<tr class="bg-primary">
											<th>Từ</th>
											<th>Đến</th>
										</tr>
									</thead>
									<tbody class="text-center">
										<c:forEach items="${lstTimeRange}" var="time">
											<tr>
												<td>${time.getTimeBegin().toString().substring(0, 5)}</td>
												<td>${time.getTimeEnd().toString().substring(0, 5)}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</c:if>

						</div>

						 <!-- The Modal -->
                        <div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="staticBackdropLiveLabel" role="dialog">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <!-- Modal Header -->
                                    <div class="modal-header">
                                        <h4 class="modal-title">Cập nhật suất chiếu</h4>
                                       
                                        <button type="button" class="close" data-dismiss="modal">×</button>
                                    </div>
                                    <div class="modal-body">
                                        <form action="#" method="post">
                                            <div class="text-center">
                                                <h5>Phim: <span>${movieTimeRoom.getIdMovie().getMovieName()}</span></h5>
                                                <h6>Thời lượng:<span>${movieTimeRoom.getIdMovie().getMovieDuration()} phút</span></h6>
                                                <h6>
                                                    <c:set var="formattedDate">
                                                        <fmt:formatDate value="${movieTimeRoom.getDate()}" pattern="dd/MM/yyyy" />
                                                    </c:set>
                                                    Ngày chiếu: <span>${formattedDate}</span>
                                                </h6>
                                                <h6>Phòng: <span>${movieTimeRoom.getIdRoom().getRoomCode()}</span></h6>
                                            </div>
                                            <div class="form-row d-flex align-items-center" style="margin-left: 72px;">
                                                <h6 class="mr-2 mt-1">Chuyển suất chiếu:</h6>
                                                <div class="form-group">
                                                    <input class="timeInput" placeholder="HH:mm" name="timeInput" style="width: 55px;" type="text" maxlength="5" disabled="disabled" value="${movieTimeRoom.getBeginTime().toString().substring(0, 5)}" />
                                                </div>
                                                <i class="fas fa-arrow-right mx-2" style="margin-bottom: 9px;"></i>
                                                <div class="form-group flex-grow-1">
                                                    <input class="timeInput" placeholder="HH:mm" name="timeInput" style="width: 55px;" type="text" maxlength="5" disabled="disabled" id="timeInput">
                                                </div>
                                            </div>
                                        </form>
                                         <div class="success-message" style="text-align: center; color: green; font-size: 16px; font-weight: bold;"></div>
                                    </div>
                                    <!-- Modal Footer -->
                                    <div class="modal-footer">
                                        <a class="btn btn-success btn-sm text-white" onclick="updateShowTimes()">Cập nhật</a> 
                                        <a class="btn btn-danger btn-sm text-white" data-dismiss="modal">Hủy bỏ</a>
                                    </div>
                                </div>
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
				<div class="row" style="text-align: end;">
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

	<script>
	document.querySelectorAll('.timeInput').forEach(function(input) {
	    input.addEventListener('keyup', function(event) {
	        var value = event.target.value;
	        var key = event.key;
			//đưa giá trị beginTime đã nhập vào modal
			 document.getElementById('timeInput').value=value;
	        // Kiểm tra nếu là phím Enter và đang ở trường startTimeInput
	        if (key === 'Enter' && event.target.name === 'startTimeInput') {
	            // Xử lý logic của bạn ở đây
	            var startTime = value; // Giả sử startTime là giá trị nhập vào trường startTimeInput
	            var movieDuration = parseInt(document.getElementById('movieDuration').innerText);
	            var bufferTime = 10; // Thời gian buffer (đơn vị: phút)
	            var endTime = calculateEndTime(startTime, movieDuration, bufferTime);

	            // Đưa giá trị đã tính toán lên trường endTimeInput
	            document.querySelector('input[name="endTimeInput"]').value = endTime;
	        }

	        // Các xử lý khác của bạn ở đây
	    });
	});

function calculateEndTime(startTime, movieDuration, bufferTime) {
    var [startHour, startMinute] = startTime.split(':').map(Number); // Chuyển đổi giờ bắt đầu thành số

    // Tính toán thời gian kết thúc
    var totalMinutes = startHour * 60 + startMinute + movieDuration + bufferTime;
    var endHour = Math.floor(totalMinutes / 60); // Giờ kết thúc
    var endMinute = totalMinutes % 60; // Phút kết thúc

    // Định dạng thời gian kết thúc thành chuỗi hh:mm
    return (endHour < 10 ? '0' : '') + endHour + ':' + (endMinute < 10 ? '0' : '') + endMinute;
}


function updateShowTimes() {
	// Lấy idMovie từ URL
    var urlParams = new URLSearchParams(window.location.search);
    var idMovieTimeRoom = urlParams.get('idMovieTimeRoom');
    // Lấy thời gian cần cập nhật
	var timeBegin = document.getElementById('timeBegin').value;
	var timeEnd = document.getElementById('timeEnd').value;

	// Tạo đối tượng JSON chứa thông tin của phim, phòng và checkedValues
	var data = {
		"idMovieTimeRoom" : idMovieTimeRoom,
		"timeBegin" : timeBegin,
		"timeEnd" : timeEnd
	};

	// Gửi yêu cầu POST qua AJAX
	var xhr = new XMLHttpRequest();
	xhr.open('POST', '<c:url value="saveTimeUpdate"/>', true);
	xhr.setRequestHeader('Content-Type', 'application/json');
	console.log(data);
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
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
</body>
</html>