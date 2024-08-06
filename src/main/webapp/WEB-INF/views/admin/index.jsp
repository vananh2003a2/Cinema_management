<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="dec"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<head>
<meta charset="utf-8">
<title>Thống kê</title>
</head>
<body>
	<div class="dashboard-wrapper">
		<div class="dashboard-ecommerce">
			<div class="container-fluid dashboard-content ">
				<div class="row">
					<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
						<div class="page-header">
							<h2 class="pageheader-title">Thống kê</h2>
						</div>
					</div>
				</div>
				<div class="ecommerce-widget">
					<div class="row">
						<div class="col-md-12">
							<div class="card">
								<div class="card-body">
									<h5 class="card-title">Chọn phương thức thống kê:</h5>
									<!-- Thêm combobox tại đây -->
									<select id="selectMethod" class="form-control">
										<option value="tkdt">Thống kê doanh thu</option>
										<option value="tksc">Thống kê suất chiếu</option>
										<option value="tkp">Thống kê phim</option>
									</select>
								</div>
							</div>
						</div>
					</div>
					<div class="row" id="tkdoanhthu"
						style="display: flex; flex-direction: row;">
						<div class="col-md-12">
							<div class="card">
								<div class="card-body">
									<h5 class="card-title">DOANH THU THEO NGÀY</h5>
									<!-- Form chọn ngày bắt đầu và kết thúc -->
									<c:if test="${not empty message_date }">
										<div class="alert alert-danger">${message_date}</div>
									</c:if>
									<form action="index" method="post">
										<div class="form-row">
											<div class="col">
												<c:set var="fromdate" value="dd/mm/yyyy"></c:set>
												<c:if test="${empty fromDate}">
													<c:set var="fromdate" value="dd/mm/yyyy"></c:set>
												</c:if>
												<c:if test="${not empty fromDate}">
													<c:set var="fromdate" value="${fromDate}"></c:set>
												</c:if>
												<label for="start-date">Ngày bắt đầu</label> <input
													name="start-date" class="form-control" id="start-date"
													value="${fromdate}">
											</div>


											<div class="col">
												<c:set var="todate" value="dd/mm/yyyy"></c:set>
												<c:if test="${empty toDate}">
													<c:set var="todate" value="dd/mm/yyyy"></c:set>
												</c:if>
												<c:if test="${not empty toDate}">
													<c:set var="todate" value="${toDate}"></c:set>
												</c:if>
												<label for="end-date">Ngày kết thúc</label> <input
													name="end-date" class="form-control" id="end-date"
													value="${todate }">
											</div>
										</div>
										<button type="submit" class="btn btn-primary mt-3 float-right">Thống
											kê</button>
									</form>

									<!-- Biểu đồ -->
									<div class="chart-container mt-4" id="weekchart">
										<canvas id="weekRevenueChart"></canvas>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="card">
								<div class="card-body">
									<h5 class="card-title">DOANH THU PHIM</h5>
									<!-- <label for="">DOANH THU PHIM</label> -->
									<div class="chart-container">
										<canvas id="movieRevenueChart"></canvas>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row " id="tksuatchieu"
						style="display: flex; flex-direction: row;">
						<div class="col-md-12">
							<div class="card">
								<div class="card-body">
									<h5 class="card-title">Số lượng vé cho mỗi suất chiếu</h5>
									<div class="chart-container">
										<canvas id="bookingChart"></canvas>
									</div>
								</div>
							</div>
						</div>

					</div>
					<div class="row " id="tkphim"
						style="display: flex; flex-direction: row;">
						<div class="col-md-12">
							<div class="card">
								<div class="card-body">
									<h5 class="card-title">Số lượng vé đặt cho mỗi phim</h5>
									<div class="chart-container">
										<canvas id="bookingFilmChart"></canvas>
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
	<!-- slimscroll js -->
	<script
		src="<c:url value="/template/admin/assets/vendor/slimscroll/jquery.slimscroll.js"/>"></script>
	<script
		src="<c:url value="/template/admin/assets/vendor/bootstrap/js/bootstrap.bundle.js"/>"></script>
	<!-- main js -->
	<%-- <script src="<c:url value="/template/admin/assets/libs/js/main-js.js"/>"></script> --%>
	<!-- chart chartist js -->
	<script
		src="<c:url value="/template/admin/assets/vendor/charts/chartist-bundle/chartist.min.js"/>"></script>
	<!-- sparkline js -->
	<script
		src="<c:url value="/template/admin/assets/vendor/charts/sparkline/jquery.sparkline.js"/>"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.7.0/chart.min.js"></script>
	<script>
	// Lấy dữ liệu từ server và gán vào biến weekRevenueData
	var weekRevenueData = {
		labels: [<c:forEach items="${lst_dailyrevenue}" var="dr" varStatus="loop">
		<fmt:formatDate value="${dr.date}" pattern="dd/MM/yyyy" var="formattedDate" />
		'${formattedDate}'
		<c:if test="${!loop.last}">,</c:if>
	</c:forEach>],
		datasets: [{
			label: 'Doanh thu',
			data: [
				<c:forEach items="${lst_dailyrevenue}" var="dr" varStatus="loop">
				<c:out value="${dr.total}" />
					<c:if test="${!loop.last}">,</c:if> 
				</c:forEach> 
			],
			backgroundColor: 'rgba(255, 159, 64, 0.6)', // Màu cam
			borderColor: 'rgba(255, 159, 64, 1)', // Màu cam
			borderWidth: 1
		}]
	};
		const movieRevenueData = {
			labels : [ <c:forEach items="${lst_rvmovie}" var="movie" varStatus="loop">
	                    '<c:out value="${movie.movieName}"/>'<c:if test="${!loop.last}">,</c:if>
	                </c:forEach> ],
			datasets : [ {
				label : 'Phim',
				data : [<c:forEach items="${lst_rvmovie}" var="movie" varStatus="loop">
                <c:out value="${movie.total}"/><c:if test="${!loop.last}">,</c:if>
                </c:forEach>],
				backgroundColor : 'rgba(75, 192, 192, 0.6)', // Màu xanh lá cây
				borderColor : 'rgba(75, 192, 192, 1)', // Màu xanh lá cây
				borderWidth : 1
			} ]
		};
		const bookingFilmData = {
			labels : [<c:forEach items="${lst_tom}" var="tom" varStatus="loop">
			<c:set var="escapedName" value="${fn:escapeXml(tom.movieName)}"/>
			    '<c:out value="${escapedName}"/>'
                <c:if test="${!loop.last}">,</c:if>
                </c:forEach>],
			datasets : [ {
				label : 'Phim',
				data : [<c:forEach items="${lst_tom}" var="tom" varStatus="loop">
                <c:out value="${tom.total}"/><c:if test="${!loop.last}">,</c:if>
                </c:forEach>],
				backgroundColor : 'rgba(75, 192, 192, 0.6)', // Màu xanh lá cây
				borderColor : 'rgba(75, 192, 192, 1)', // Màu xanh lá cây
				borderWidth : 1
			} ]
		};
		const bookingData = {
			labels : [ <c:forEach items="${lst_screening}" var="screening" varStatus="loop">
			    '<c:out value="${screening.beginTime}"/>'
                <c:if test="${!loop.last}">,</c:if>
                </c:forEach> ],
			datasets : [ {
				label : 'Số lượng vé',
				data : [ <c:forEach items="${lst_screening}" var="screening" varStatus="loop">
                <c:out value="${screening.soVe}"/><c:if test="${!loop.last}">,</c:if>
                </c:forEach> ],
				fill : false,
				backgroundColor: 'rgba(255, 159, 64, 0.6)', // Màu cam
				borderColor : 'rgba(255, 99, 132, 1)',
				borderWidth : 2
			} ]
		};

		// Configure options for both charts
		const chartOptionRevenue = {
			scales : {
				y : {
					beginAtZero : true,
					ticks : {
						// Ẩn đơn vị của các giá trị trên trục y
						callback : function(value, index, values) {
							return value + " đ"; // Sử dụng hàm toLocaleString() để định dạng số
						}
					},

				}
			}
		};
		// Cấu hình biểu đồ đường
		const bookingOptions = {
			scales : {
				y : {
					beginAtZero : true,
					ticks : {
						// Ẩn đơn vị của các giá trị trên trục y
						callback : function(value, index, values) {
							return value + " vé"; // Sử dụng hàm toLocaleString() để định dạng số
						}
					},

				}
			}
		};
		// Initialize and render monthly revenue chart
		const bookingFilmChart = document.getElementById('bookingFilmChart')
				.getContext('2d');
		new Chart(bookingFilmChart, {
			type : 'bar',
			data : bookingFilmData,
			options : bookingOptions
		});
		const weekRevenueChart = document.getElementById('weekRevenueChart')
				.getContext('2d');
		new Chart(weekRevenueChart, {
			type : 'line',
			data : weekRevenueData,
			options : chartOptionRevenue
		});

		// Initialize and render movie revenue chart
		const movieRevenueChart = document.getElementById('movieRevenueChart')
				.getContext('2d');
		new Chart(movieRevenueChart, {
			type : 'bar',
			data : movieRevenueData,
			options : chartOptionRevenue
		});
		// Khởi tạo và vẽ biểu đồ đường
		const bookingChart = document.getElementById('bookingChart')
				.getContext('2d');
		new Chart(bookingChart, {
			type : 'bar',
			data : bookingData,
			options : bookingOptions
		});
	</script>
	<script>
		// Lắng nghe sự kiện khi giá trị của combobox thay đổi
		document
				.addEventListener(
						"DOMContentLoaded",
						function() {
							var method = document
									.getElementById('selectMethod').value; // Lấy giá trị mặc định

							// Ẩn hoặc hiển thị các biểu đồ tương ứng với phương thức được chọn
							if (method === 'tkdt') {
								document.getElementById('tkphim').style.display = 'none';
								document.getElementById('tksuatchieu').style.display = 'none';
								document.getElementById('tkdoanhthu').style.display = 'block';
								document.getElementById('tkdoanhthu').style.display = 'flex';
							} else if (method === 'tksc') {
								document.getElementById('tkphim').style.display = 'none';
								document.getElementById('tkdoanhthu').style.display = 'none';
								document.getElementById('tksuatchieu').style.display = 'block';
								document.getElementById('tksuatchieu').style.display = 'flex';
							} else if (method === 'tkp') {
								document.getElementById('tkphim').style.display = 'block';
								document.getElementById('tkdoanhthu').style.display = 'none';
								document.getElementById('tksuatchieu').style.display = 'none';
								document.getElementById('tkphim').style.display = 'flex';
							}

							// Lắng nghe sự kiện thay đổi của thẻ select
							document
									.getElementById('selectMethod')
									.addEventListener(
											'change',
											function() {
												var method = this.value; // Lấy giá trị được chọn

												// Ẩn hoặc hiển thị các biểu đồ tương ứng với phương thức được chọn
												if (method === 'tkdt') {
													document
															.getElementById('tkphim').style.display = 'none';
													document
															.getElementById('tksuatchieu').style.display = 'none';
													document
															.getElementById('tkdoanhthu').style.display = 'block';
													document
															.getElementById('tkdoanhthu').style.display = 'flex';
												} else if (method === 'tksc') {
													document
															.getElementById('tkphim').style.display = 'none';
													document
															.getElementById('tkdoanhthu').style.display = 'none';
													document
															.getElementById('tksuatchieu').style.display = 'block';
													document
															.getElementById('tksuatchieu').style.display = 'flex';
												} else if (method === 'tkp') {
													document
															.getElementById('tkphim').style.display = 'block';
													document
															.getElementById('tkdoanhthu').style.display = 'none';
													document
															.getElementById('tksuatchieu').style.display = 'none';
													document
															.getElementById('tkphim').style.display = 'flex';
												}
											});

						});
	</script>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
	<script>
	
    $(document).ready(function(){
        // Khởi tạo datepicker cho start-date và end-date
        $('#start-date, #end-date').datepicker({
        	 format: 'dd/mm/yyyy',
             autoclose: true,
            
        });
        $('#start-date, #end-date').change(function() {
            var startDate = $('#start-date').datepicker('getDate');
            var endDate = $('#end-date').datepicker('getDate');

            if (startDate && endDate && startDate > endDate) {
                alert('Ngày bắt đầu phải nhỏ hơn hoặc bằng ngày kết thúc.');
                // Cài đặt lại giá trị ngày bắt đầu thành ngày kết thúc
                $('#start-date').datepicker('setDate', endDate);
            }
        });
    });
</script>

</body>
