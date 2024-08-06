<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Phim</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700;900&display=swap"
	rel="stylesheet">
</head>

<body class="">
	<div class="phimsapchieuchieu-content" style="margin-top: 50px;">
		<div class="container-fluid p-5 py-5">
			<div class="row row-cols-md-4 g-4">
				<c:choose>
					<c:when test="${status eq 'dangChieu'}">
						<c:forEach items="${lstMovieDTODangChieu}" var="movie">
							<div class="col">
								<div class="card image-container">
									<img
										src="./template/customer/img/${movie.getThumnail()}"
										alt="Your Image" class="img-fluid">
									<div class="card-body">
										<div class="movie-content text-center">
											<h4 class="text-dark phimsapchieu-title">${movie.getMovieName()}</h4>
											<p class="" style="color: #1aa29b;">${movie.getIdMovieType().getTypeName()}</p>
										</div>
									</div>
									<div class="card-overlay">
										<div class=" card border-0"
											style="background-color: rgba(11, 0, 0, 0.915);">
											<div class="card-body">
												<div class="movie-content">
													<h4 class="text-light">${movie.getMovieName()}</h4>
													<p class="fst-italic" style="color: #1aa29b;">${movie.getIdMovieType().getTypeName()}</p>
													<p class="text-warning">${movie.getMovieDuration()}
														Phút</p>
													<P class="text-light">${movie.getMovieScript()}</P>
													<div class="entry-button">
														<a href=""
															class=" play-video text-decoration-none text-warning">
															<i aria-hidden="true" class="fa fa-play"></i>Trailer
														</a> <a href="" class="text-decoration-none text-warning">
															<i aria-hidden="true" class="fa fa-eye"></i>Chi tiết
														</a>
													</div>
													<p class="" style="color: #1aa29b;">Đạo diễn</p>
													<p class="text-light">${movie.getDirector()}</p>
													<p class="" style="color: #1aa29b;">Diễn viên</p>
													<p class="text-light">${movie.getActors()}</p>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</c:when>
					<c:when test="${status eq 'sapChieu'}">
						<c:forEach items="${lstMovieDTOSapChieu}" var="movie">
							<div class="col">
								<div class="card image-container">
									<img
										src="./template/customer/img/${movie.getThumnail()}"
										alt="Your Image" class="img-fluid">
									<div class="card-body">
										<div class="movie-content text-center">
											<h4 class="text-dark phimsapchieu-title">${movie.getMovieName()}</h4>
											<p class="" style="color: #1aa29b;">${movie.getIdMovieType().getTypeName()}</p>
										</div>
									</div>
									<div class="card-overlay">
										<div class=" card border-0"
											style="background-color: rgba(11, 0, 0, 0.915);">
											<div class="card-body">
												<div class="movie-content">
													<h4 class="text-light">${movie.getMovieName()}</h4>
													<p class="fst-italic" style="color: #1aa29b;">${movie.getIdMovieType().getTypeName()}</p>
													<p class="text-warning">${movie.getMovieDuration()}
														Phút</p>
													<P class="text-light">${movie.getMovieScript()}</P>
													<div class="entry-button">
														<a href=""
															class=" play-video text-decoration-none text-warning">
															<i aria-hidden="true" class="fa fa-play"></i>Trailer
														</a> <a href="" class="text-decoration-none text-warning">
															<i aria-hidden="true" class="fa fa-eye"></i>Chi tiết
														</a>
													</div>
													<p class="" style="color: #1aa29b;">Đạo diễn</p>
													<p class="text-light">${movie.getDirector()}</p>
													<p class="" style="color: #1aa29b;">Diễn viên</p>
													<p class="text-light">${movie.getActors()}</p>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</c:when>
				</c:choose>
			</div>

		</div>

	</div>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
<script>
    // Lắng nghe sự kiện change của combobox
    document.getElementById('ngaychieu').addEventListener('change', function() {
        // Lấy giá trị đã chọn từ combobox
        var selectedValue = this.value;
        // Kiểm tra nếu giá trị đã chọn là ngày 8/4/2024 thì mở modal
        if (selectedValue === 'ngay1') {
            var myModal = new bootstrap.Modal(document.getElementById('exampleModal'));
            myModal.show();
        }
    });
</script>

<script>
    $(document).ready(function () {
        $('[data-bs-toggle="tooltip"]').tooltip();
    });
</script>
</html>