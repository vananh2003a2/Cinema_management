<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Thêm phim mới</title>
</head>
<div class="dashboard-wrapper">
	<div class="dashboard-ecommerce">
		<div class="container-fluid dashboard-content ">
			<!-- ============================================================== -->
			<!-- pageheader  -->
			<!-- ============================================================== -->
			<div class="row">
				<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
					<div class="page-header">
						<h2 class="pageheader-title">Bổ sung phim</h2>
						<div class="page-breadcrumb">
							<nav aria-label="breadcrumb">
								<ol class="breadcrumb">
									<li class="breadcrumb-item"><a href="#" class="breadcrumb-link">Home</a></li>
									<li class="breadcrumb-item active" aria-current="page">Quản lý phim</li>
									<li class="breadcrumb-item active" aria-current="page">Bổ sung phim</li>
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
						<form class="form-horizontal" action="save-movie" method="post" enctype="multipart/form-data">
							<input type="hidden" name="isEdit" value="false" />
							<input type="hidden" name="idMovie" value="${newIdMovie }" /> 
							<input type="hidden" name="status" value="0" /> 
							<span class="text-danger">${message }</span>
							<div class="form-group">
								<label>Chọn loại phim</label> <select class="form-control" name="idMovieType" id="idMovieType">
									<option value="0">Chọn loại phim</option>
									<c:forEach items="${listMovieType }" var="movietype">
										<c:set var="id" value="${movietype.getIdMovieType() }"></c:set>
										<option value="${movietype.getIdMovieType() }" ${((message != null) && (idMovieType == id)) ? "selected" : "" }>${movietype.getTypeName() }</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<label>Tên phim:</label> <input type="text" value='${(message != null)? movieName : "" }' class="form-control" name="movieName" autofocus required>
							</div>
							<div class="form-group">
								<label>Ngày khởi chiếu:</label> <input class="form-control" name="movieDate" id="daterange" value='${(message != null)? movieDate : "dd/MM/yyyy" }' required>
							</div>
							<div class="form-group">
								<label>Thời lượng phim:</label> <input type="number" value='${(message != null)? movieDuration : "" }' class="form-control" name="movieDuration" required>
							</div>
							<div class="form-group">
								<label>Đạo diễn:</label> <input type="text" value='${(message != null)? director : "" }' class="form-control" name="director" required>

							</div>
							<div class="form-group">
								<label>Diễn viên:</label> <input type="text" value='${(message != null)? actors : "" }' class="form-control" name="actors" required>
							</div>

							<div class="form-group">
								<label>Kịch bản:</label>
								<textarea class="form-control" name="movieScript" required>${(message != null)? movieScript : "" }</textarea>
							</div>
							<%-- <div class="form-group">
								<label>Định dạng:</label> <input type="text" value='${(message != null)? movieFormat : "" }' class="form-control" name="movieFormat" required>
							</div> --%>
							<div class="form-group">
								<label>Định dạng:</label> 
								<select class="form-control" name="movieFormat" id="movieFormat">
									<option value="">Chọn định dạng phim</option>
									<option value="2D" ${movieFormat.equals("2D") ? "selected" : "" }>2D</option>
									<option value="IMAX" ${movieFormat.equals("IMAX") ? "selected" : "" }>IMAX</option>
									<option value="3D" ${movieFormat.equals("3D") ? "selected" : "" }>3D</option>
								</select>
							</div>
							<div class="form-group">
								<label>Ảnh chính(banner):</label>
								<div>
									<input type="file" class="form-control" name="file" id="txtMainImage" accept="image/*" required
										onchange="handleChangeFileInput(event)">
									<img class="img-thumbnail w-100" style='${(message == null)? "display: none;":""} margin-top: 12px' src='<c:url value="data:image/jpeg;base64,${mainImageBase64}"/>' alt="no-img">
								</div>
							</div>
							<div class="form-group">
								<label>Ảnh phụ(poster):</label>
								<div>
									<input type="file" class="form-control" name="file" id="txtThumnail" accept="image/*" required
										onchange="handleChangeFileInput(event)">
									<img class="img-thumbnail" style='${(message == null)? "display: none;":""} width: 230px; margin-top: 12px' src='<c:url value="data:image/jpeg;base64,${thumnailBase64}"/>' alt="no-img">
								</div>
							</div>
							<div class="form-group text-right">
								<button type="submit" class="btn btn-primary">
									<i class="fa fa-floppy-o"></i> Lưu dữ liệu
								</button>
								<a href="qlmovie" class="btn btn-secondary"> Quay lại </a>
							</div>
						</form>
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
					Copyright © 2018 Concept. All rights reserved. Dashboard by <a href="https://colorlib.com/wp/">Colorlib</a>.
				</div>
				<div class="col-xl-6 col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="text-md-right footer-links d-none d-sm-block">
						<a href="javascript: void(0);">About</a> <a href="javascript: void(0);">Support</a> <a href="javascript: void(0);">Contact Us</a>
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
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
<script>
	$(document).ready(function() {
		$('#daterange').datepicker({
			format : 'dd/mm/yyyy',
			autoclose : true
		});
	});
	
	function setFileImage(base64, filename, elementId) {
		const dataTransfer = new DataTransfer();
		
		fetch(base64)
		  .then(res => res.blob())
		  .then(blob => {
		    const file = new File([blob], filename, { type: "image/png" })
		    dataTransfer.items.add(file);
			// Gán giá trị của input file cho input có id là "txtMainImage"
			document.getElementById(elementId).files = dataTransfer.files;
		  })
	}
	
	function handleChangeFileInput (event) {
		const target = event.target;
		const file = target.files[0];
		
		if (!file) {
			target.nextElementSibling.style.display = 'none';
			return;
		}
		
		target.nextElementSibling.src = window.URL.createObjectURL(file)
		target.nextElementSibling.style.display = 'block';
	}

	// Trong view, sau khi nhận được giá trị từ controller
	var mainImageValue = "data:image/jpeg;base64,${mainImageBase64}"; // Giá trị được gửi từ controller
	var mainImageFileName = "${mainImage}";
	 
	var thumnailValue = "data:image/jpeg;base64,${thumnailBase64}"; // Giá trị được gửi từ controller
	var thumnailFileName = "${thumnail}";
	
	//Kiểm tra xem có phải chạy lần đầu
	var hasMessage = "${message != null && message != ''}";
	console.log(hasMessage === "false");
	if(hasMessage === "true") {
		setFileImage(mainImageValue, mainImageFileName, "txtMainImage");
		setFileImage(thumnailValue, thumnailFileName, "txtThumnail");
	}
</script>
</html>