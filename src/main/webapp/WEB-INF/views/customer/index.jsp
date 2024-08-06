<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Trang chủ</title>
</head>
<body class="bg-dark">
	<div class="content-home" style="margin-top: 50px;">
		<!--Begin Slider -->
		<div id="carouselExampleControls" class="carousel slide"
			data-bs-ride="carousel" data-bs-interval="3000">
			<div class="carousel-inner">
				<c:forEach varStatus="index" items="${lstMovieDTOTOP}" var="movie">
					<c:choose>
						<c:when test="${index.first}">
							<div class="carousel-item active">
								<img src="./template/customer/img/${movie.getMainImage()}"
									class="d-block w-100" alt="...">
							</div>
						</c:when>
						<c:otherwise>
							<div class="carousel-item ">
								<img src="./template/customer/img/${movie.getMainImage()}"
									class="d-block w-100" alt="...">
							</div>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</div>
			<button class="carousel-control-prev" type="button"
				data-bs-target="#carouselExampleControls" data-bs-slide="prev">
				<span aria-hidden="true"><img
					src="./template/customer/img/icon/l-ar 2.png"></span> <span
					class="visually-hidden">Previous</span>
			</button>
			<button class="carousel-control-next" type="button"
				data-bs-target="#carouselExampleControls" data-bs-slide="next">
				<span aria-hidden="true"><img
					src="./template/customer/img/icon/r-ar 2.png"></span> <span
					class="visually-hidden">Next</span>
			</button>
		</div>
		<!-- End Slider -->

		<!--Begin Showing -->
		<div class="container-background h-100 p-1">
			<div class="row mx-5">
				<h3 class="commingsoon-header-title px-4 text-white text-bold">
					Phim:
					<p class="h3 d-inline-block text-color-main-100">Đang chiếu</p>
				</h3>
			</div>
			<div class="showing row m-5">
				<!-- khối slide đầu -->
				<div class="item col-3 row mx-auto">
					<div id="carouselSlider1" class="carousel slide hoverable"
						data-bs-ride="carousel" data-bs-wrap="true"
						data-bs-interval="3000">
						<!-- Bat dau movie dangchieu -->
						<div class="carousel-inner">
							<c:forEach varStatus="index" items="${lstMovieDTODangChieu}"
								var="movie">
								<c:choose>
									<c:when test="${index.first}">
										<div class="carousel-item active">
											<img src="./template/customer/img/${movie.getThumnail()}"
												class="showing-img d-block w-100" alt="...">
										</div>
									</c:when>
									<c:otherwise>
										<div class="carousel-item ">
											<img src="./template/customer/img/${movie.getThumnail()}"
												class="showing-img d-block w-100" alt="...">
										</div>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</div>
						<!-- KT movie dangchieu -->
					</div>
				</div>
				<!-- khối slide 2 -->
				<div class="item col-6 row mx-auto p-0">
					<div id="carouselSlider2" class="carousel slide p-0 hoverable"
						data-bs-ride="carousel" data-bs-wrap="true"
						data-bs-interval="3000">
						<div class="container carousel-inner p-0 position-relative">
							<button id="prevButton" class="btn z-index-2">
								<img class="img-fluid"
									src="./template/customer/img/icon/l-ar 2.png" alt="">
							</button>
							<button id="nextButton" class="btn z-index-2">
								<img class="img-fluid"
									src="./template/customer/img/icon/r-ar 2.png" alt="">
							</button>
							<!-- bat dau chi tiet phim -->
							<!-- bắt đầu từ phần tử thứ 2 -->
							<c:forEach varStatus="index" items="${lstMovieDTODangChieu}"
								var="movie" begin="1">
								<c:choose>
									<c:when test="${index.index==1}">
										<div class="carousel-item active">
											<div class="row p-0">
												<img class="showing-img-center col-6 p-0 img-fluid"
													src="./template/customer/img/${movie.getThumnail()}"
													alt="no-poster">
												<div class="item-content col-6 bg-white p-3 m-0">
													<div>
														<h5 class="item-title ">${movie.getMovieName()}</h5>
														<div class="mb-2">
															<p
																class="item-title-sup mb-0 d-inline-block bg-color-main-200">${movie.getMovieFormat()}</p>
															<p class="d-inline text-color-main-100">${movie.getMovieDuration()}
																Phút</p>
														</div>
													</div>
													<div class="pt-4 px-0 text-center item-logo">
														<p class="showing-logo border rounded-circle">P</p>
													</div>
													<div class="showing-content-body">
														<div class="mb-2">${movie.getIdMovieType().getTypeName()}</div>
														<div class="mb-2">
															<div class="fw-bold d-inline">Đạo diễn:</div>
															${movie.getDirector()}
														</div>
														<div class="mb-2">
															<div class="fw-bold d-inline">Diễn viên:</div>
															${movie.getActors()}
														</div>
														<div class="mb-2">${movie.getMovieScript()}</div>
													</div>
													<div class="btn-tiket">
														<%-- <a class="text-dark text-decoration-none"
															href="<c:url value='/showtime-detail?idMovie=${movie.getIdMovie()}'/>"> <img
															src="./template/customer/img/logo/ticket-8-svgrepo-com.svg"
															alt="no"> Đặt vé
														</a> --%>
														<a class="text-dark text-decoration-none ticket-link"
															href="<c:url value='/showtime-detail?idMovie=${movie.getIdMovie()}'/>">
															<img
															src="./template/customer/img/logo/ticket-8-svgrepo-com.svg"
															alt="no"> Đặt vé
														</a>

													</div>
												</div>
											</div>
										</div>
									</c:when>
									<c:otherwise>
										<div class="carousel-item ">
											<div class="row p-0">
												<img class="showing-img-center col-6 p-0 img-fluid"
													src="./template/customer/img/${movie.getThumnail()}"
													alt="no-poster">
												<div class="item-content col-6 bg-white p-3 m-0">
													<div>
														<h5 class="item-title ">${movie.getMovieName()}</h5>
														<div class="mb-2">
															<p
																class="item-title-sup mb-0 d-inline-block bg-color-main-200">${movie.getMovieFormat()}</p>
															<p class="d-inline text-color-main-100">${movie.getMovieDuration()}
																Phút</p>
														</div>
													</div>
													<div class="pt-4 px-0 text-center item-logo">
														<p class="showing-logo border rounded-circle">P</p>
													</div>
													<div class="showing-content-body">
														<div class="mb-2">${movie.getIdMovieType().getTypeName()}</div>
														<div class="mb-2">
															<div class="fw-bold d-inline">Đạo diễn:</div>
															${movie.getDirector()}
														</div>
														<div class="mb-2">
															<div class="fw-bold d-inline">Diễn viên:</div>
															${movie.getActors()}
														</div>
														<div class="mb-2">${movie.getMovieScript()}</div>
													</div>
													<div class="btn-tiket">
														<a class="text-dark text-decoration-none ticket-link"
															href="<c:url value='/showtime-detail?idMovie=${movie.getIdMovie()}'/>">
															<img
															src="./template/customer/img/logo/ticket-8-svgrepo-com.svg"
															alt="no"> Đặt vé
														</a>
													</div>
												</div>
											</div>
										</div>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<!-- chạy về lại phần tử đầu tiên -->
							<div class="carousel-item ">
								<div class="row p-0">
									<img class="showing-img-center col-6 p-0 img-fluid"
										src="./template/customer/img/${lstMovieDTODangChieu.get(0).getThumnail()}"
										alt="no-poster">
									<div class="item-content col-6 bg-white p-3 m-0">
										<div>
											<h5 class="item-title ">${lstMovieDTODangChieu.get(0).getMovieName()}</h5>
											<div class="mb-2">
												<p
													class="item-title-sup mb-0 d-inline-block bg-color-main-200">${lstMovieDTODangChieu.get(0).getMovieFormat()}</p>
												<p class="d-inline text-color-main-100">${lstMovieDTODangChieu.get(0).getMovieDuration()}
													Phút</p>
											</div>
										</div>
										<div class="pt-4 px-0 text-center item-logo">
											<p class="showing-logo border rounded-circle">P</p>
										</div>
										<div class="showing-content-body">
											<div class="mb-2">${lstMovieDTODangChieu.get(0).getIdMovieType().getTypeName()}</div>
											<div class="mb-2">
												<div class="fw-bold d-inline">Đạo diễn:</div>
												${lstMovieDTODangChieu.get(0).getDirector()}
											</div>
											<div class="mb-2">
												<div class="fw-bold d-inline">Diễn viên:</div>
												${lstMovieDTODangChieu.get(0).getActors()}
											</div>
											<div class="mb-2">${lstMovieDTODangChieu.get(0).getMovieScript()}</div>
										</div>
										<div class="btn-tiket">
											<a class="text-dark text-decoration-none ticket-link"
												href="<c:url value='/showtime-detail?idMovie=${lstMovieDTODangChieu.get(0).getIdMovie()}'/>">
												<img
												src="./template/customer/img/logo/ticket-8-svgrepo-com.svg"
												alt="no"> Đặt vé
											</a>
										</div>
									</div>
								</div>
							</div>
							<!-- kết thúc thẻ -->
						</div>
					</div>
				</div>
				<!-- khối slide 3 -->
				<div class="item col-3 row mx-auto">
					<div id="carouselSlider3" class="carousel slide hoverable"
						data-bs-ride="carousel" data-bs-wrap="true"
						data-bs-interval="3000">
						<!-- bắt đầu từ phần tử thứ 3 -->
						<div class="carousel-inner">
							<c:forEach varStatus="index" items="${lstMovieDTODangChieu}"
								var="movie" begin="2">
								<c:choose>
									<c:when test="${index.index==2}">
										<div class="carousel-item active">
											<img src="./template/customer/img/${movie.getThumnail()}"
												class="showing-img d-block w-100" alt="...">
										</div>
									</c:when>
									<c:otherwise>
										<div class="carousel-item ">
											<img src="./template/customer/img/${movie.getThumnail()}"
												class="showing-img d-block w-100" alt="...">
										</div>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<div class="carousel-item ">
								<img
									src="./template/customer/img/${lstMovieDTODangChieu.get(0).getThumnail()}"
									class="showing-img d-block w-100" alt="...">
							</div>
							<div class="carousel-item">
								<img
									src="./template/customer/img/${lstMovieDTODangChieu.get(1).getThumnail()}"
									class="showing-img d-block w-100" alt="...">
							</div>
						</div>
						<!-- ket thuc thumnail phim dang chieu -->
					</div>
				</div>
				<!-- kết thúc khối 3 -->
			</div>
		</div>
		<!-- End Showing -->

		<!-- Begin CommingSoon -->
		<div class="container-background-commingsoon p-1">
			<div class="commingsoon-container">
				<div class="commingsoon-header row">
					<h3 class="commingsoon-header-title col-9 text-white text-bold">
						Phim:
						<p class="h3 d-inline-block text-color-main-100">Sắp chiếu</p>
					</h3>
					<div class="commingsoon-header-btn col-3 text-end">
						<img id="prev" class=" btn border-bottom border-4 rounded-0"
							src="./template/customer/img/icon/l-ar 2.png" alt="no"> <img
							id="next" class=" btn border-bottom border-4 rounded-0"
							src="./template/customer/img/icon/r-ar 2.png" alt="no">
					</div>
				</div>
				<!-- <div class="item" style="background: url();"> -->
				<div id="commingsoon-slide">
					<!-- bat dau thumnail phim dang chieu -->
					<c:forEach varStatus="index" items="${lstMovieDTOSapChieu}"
						var="movie">
						<div class="commingsoon-item">
							<div class="commingSoon-image">
								<img class="img-fluid"
									src="./template/customer/img/${movie.getThumnail()}" alt="">
							</div>
						</div>
					</c:forEach>
				</div>

				<!-- </div> -->
			</div>
		</div>
		<!-- End CommingSoon -->
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
		integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
		integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
		crossorigin="anonymous"></script>
	<script>
        document.addEventListener('DOMContentLoaded', function () {
            const prevButton = document.getElementById('prevButton');
            const nextButton = document.getElementById('nextButton');
            const carousels = document.querySelectorAll('.showing .carousel');
            let isNextButtonClicked = false;

            carousels.forEach((carousel, index) => {
                carousel.addEventListener('slide.bs.carousel', function (event) {
                    const direction = event.direction;
                    const slideTo = event.to;

                    if (isNextButtonClicked) {
                        if (direction === 'right') {
                            carousels.forEach((otherCarousel, otherIndex) => {
                                if (otherIndex !== index) {
                                    const carouselInstance = bootstrap.Carousel.getInstance(otherCarousel);
                                    carouselInstance.next();
                                }
                            });
                        } else {
                            carousels.forEach((otherCarousel, otherIndex) => {
                                if (otherIndex !== index) {
                                    const carouselInstance = bootstrap.Carousel.getInstance(otherCarousel);
                                    carouselInstance.prev();
                                }
                            });
                        }
                    }

                    isNextButtonClicked = false;
                });
            });

            prevButton.addEventListener('click', function () {
                carousels.forEach(carousel => {
                    const carouselInstance = bootstrap.Carousel.getInstance(carousel);
                    carouselInstance.prev();
                });
            });

            nextButton.addEventListener('click', function () {
                carousels.forEach(carousel => {
                    const carouselInstance = bootstrap.Carousel.getInstance(carousel);
                    carouselInstance.next();
                });
            });
        });

        //Hover vào 1 trong 3 carouselslide thì 2 cái còn lại dừng chạy
        document.addEventListener("DOMContentLoaded", function() {
            const hoverableItems = document.querySelectorAll(".hoverable");

            hoverableItems.forEach(function(item) {
                item.addEventListener("mouseenter", function() {
                    pauseAllCarousels();
                });
                item.addEventListener("mouseleave", function() {
                    startAllCarousels();
                });
            });

            function pauseAllCarousels() {
                const carousels = document.querySelectorAll(".carousel");
                carousels.forEach(function(carousel) {
                    const carouselInstance = bootstrap.Carousel.getInstance(carousel);
                    if (carouselInstance) {
                        carouselInstance.pause();
                    }
                });
            }

            function startAllCarousels() {
                const carousels = document.querySelectorAll(".carousel");
                carousels.forEach(function(carousel) {
                    const carouselInstance = bootstrap.Carousel.getInstance(carousel);
                    if (carouselInstance) {
                        carouselInstance.cycle();
                    }
                });
            }
        });

        document.getElementById('next').onclick = function() {
            let lists = document.querySelectorAll('.commingsoon-container .commingsoon-item');
            document.getElementById('commingsoon-slide').appendChild(lists[0]);
        }
        document.getElementById('prev').onclick = function() {
            let lists = document.querySelectorAll('.commingsoon-container .commingsoon-item');
            document.getElementById('commingsoon-slide').prepend(lists[lists.length - 1]);
        }
    </script>
	<!-- <script>
    $(document).ready(function() {
        // Lắng nghe sự kiện click trên các liên kết đặt vé
        $('.ticket-link').click(function(e) {
            e.preventDefault(); // Ngăn chặn hành động mặc định của thẻ a

            var idMovie = $(this).attr('href').split('=')[1]; // Lấy idMovie từ URL của liên kết
            sendIdMovieToApi(idMovie);
        });

        // Hàm gửi idMovie đến API
        function sendIdMovieToApi(idMovie) {
            $.get("http://localhost:8080/RapChieuPhim_SpringMVC/api/lichchieu?idMovie=" + idMovie, function(response) {
                // Xử lý phản hồi từ server
            });
        }
    });
    </script> -->
	<script>
    const message = "${message}";
	const hasMessage = (message != "");
	console.log(message);
	console.log(hasMessage);
	if(hasMessage === true) {
		alert(message);
	}
    </script>
</body>
</html>