

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Lịch chiếu</title>
</head>
<body class="bg-dark">
<div class="lichchieu-content border-0 pb-5" style="margin-top: 50px;">
	<div class="container">
		<div class="row">
			<div class="col-sm-12">
				<div class="address-header">
					<h3>STARLIGHT ĐÀ NẴNG</h3>
					<p>1000 1722</p>
					<p>Tầng 4 Tòa nhà Nguyễn Kim, 46 Điện Biên Phủ, TP. Đà Nẵng</p>
				</div>
				<div class="p-3">
					<!-- Lấy ra 3 ngày gần nhất -->
					<form id="formSearch" action="lichchieu" method="get">
						<div class="row align-items-center mt-5 justify-content-end">
							<div class="col-auto">
								<p class="m-0">Ngày chiếu:</p>
							</div>
							<div class="col-auto">
								<select class="form-select" name="selectedDate"
									id="selectedDate" onChange="this.form.submit()">
									<c:forEach items="${ngaychieuOptions}" var="date">
										<option value="${date}"
											${date==selectedDate ? "selected" : ""}>${date}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</form>
					<!-- lấy các phim theo ngày được chọn hoặc mặc định ngày hiện tại -->
					<c:choose>
						<c:when test="${length == 0 }">
							<h5 class="text-center pt-4">Không có suất chiếu trong ngày ${selectedDate }!</h5>
						</c:when>
					</c:choose>
					<div id="movieSchedule">
						<c:forEach items="${listmovie}" var="movie">
							<div class="movie-list mt-4">
								<div class="row movie-item">
									<div class="movie-img col-lg-3 col-md-4 col-sm-12">
										<a href=""> <img
											src="<c:url value="/template/customer/img/${movie.idMovie.getThumnail() }"/>"
											class="img-fluid" alt="...">
										</a>
									</div>
									<div class="movie-content col-lg-9 col-md-8 col-sm-12">
										<h3 class="text-dark">${movie.idMovie.getMovieName() }</h3>
										<ul>

											<li>${movie.idMovie.getMovieFormat() }</li>
										</ul>
										<p>${movie.idMovie.getTypeName() }</p>
										<div class="movie-list-infor mt-2">
											<p>
												Đạo Diễn: <span>${movie.idMovie.getDirector() }</span>
											</p>
											<p>
												Diễn Viên: <span>${movie.idMovie.getActors() }</span>
											</p>
											<br> <span>${movie.idMovie.getMovieScript() }</span>
										</div>
										<div class="suatchieu-list col-md-12 col-sm-12">
											<hr>
											<!-- Lấy ra danh sách suất chiếu của từng phim -->
											<c:forEach items="${listmovietime}" var="time">
												<c:set var="formattedDate">
													<fmt:formatDate value="${time.getDate()}"
														pattern="dd/MM/yyyy" />
												</c:set>
												<c:choose>
													<c:when
														test="${time.idMovie.getIdMovie() == movie.idMovie.getIdMovie() && formattedDate.equals(selectedDate)}">
														<c:choose>
															<c:when test="${time.getStatus() == 1}">
																<a
																	href="<c:url value='/pickchair?idMovieTimeRoom=${time.getIdMovieTimeRoom()}&idRoom=${time.getIdRoom()}&idMovie=${time.getIdMovie().getIdMovie()}'/>"
																	class="text-decoration-none"> <span
																	class="time item">${time.getBeginTime().toString().substring(0, 5)}</span>
																</a>
															</c:when>
															<c:otherwise>
																<span class="time past item">${time.getBeginTime().toString().substring(0, 5)}</span>
															</c:otherwise>
														</c:choose>
													</c:when>
												</c:choose>
											</c:forEach>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
					<!-- kết thúc danh sách phim theo ngày -->
				</div>
				<!-- modal -->
				<div class="modal fade" id="exampleModal" tabindex="-1"
					aria-labelledby="exampleModalLabel" aria-hidden="true">
					<div class="modal-dialog modal-dialog-centered">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body text-center">
								<h3>CHƯA CẬP NHẬT SUẤT CHIẾU</h3>
								<h6 style="color: #1aa29b;">Chọn ngày chiếu khác!</h6>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
		integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
		crossorigin="anonymous"></script>
	<script>
$(document).ready(function() {
    var previousSelect; // Biến lưu giá trị của lần chọn trước đó
    var currentSelect; // Biến lưu giá trị của lần chọn hiện tại

    function updateMovieSchedule(selectedDate) {
        $.get(
            "http://localhost:8080/RapChieuPhim_SpringMVC/api/lichchieu", {
                selectedDate: selectedDate
            },
            function(response) {
                var movieSchedule = $("#movieSchedule");
                movieSchedule.empty();
                if (response.listmovie && response.listmovie.length > 0) {
                    response.listmovie.forEach(function(movie) {
                        var movieItem = '<div class="movie-list mt-4">';
                        movieItem += '<div class="row movie-item">';
                        movieItem += '<div class="movie-img col-lg-3 col-md-4 col-sm-12">';
                        movieItem += '<a href=""><img src="<c:url value="/template/customer/img/' + movie.idMovie.thumnail + '"/>" class="img-fluid" alt="..."></a>';
                        movieItem += '</div>';
                        movieItem += '<div class="movie-content col-lg-9 col-md-8 col-sm-12">';
                        movieItem += '<h3 class="text-dark">' + movie.idMovie.movieName + '</h3>';
                        movieItem += '<ul>';
                        movieItem += '<li>' + movie.idMovie.movieFormat + '</li>';
                        movieItem += '</ul>';
                        movieItem += '<p>' + movie.idMovie.typeName + '</p>';
movieItem += '<div class="movie-list-infor mt-2">';
                        movieItem += '<p>Đạo Diễn: <span>' + movie.idMovie.director + '</span></p>';
                        movieItem += '<p>Diễn Viên: <span>' + movie.idMovie.actors + '</span></p>';
                        movieItem += '<br><span>' + movie.idMovie.movieScript + '</span>';
                        movieItem += '</div>';
                        movieItem += '<div class="suatchieu-list col-md-12 col-sm-12">';
                        movieItem += '<hr>';

                        response.listmovietime.forEach(function(time) {
                            var date = new Date(time.date);
                            var year = date.getFullYear();
                            var month = ('0' + (date.getMonth() + 1)).slice(-2);
                            var day = ('0' + date.getDate()).slice(-2);
                            /* var formattedDate = year + '-' + month + '-' + day; */
                          var formattedDate = day + '/' + month + '/' + year;
                            if (time.idMovie.idMovie == movie.idMovie.idMovie && formattedDate == selectedDate) {
                            	if (time.status == 1) {
                                    var pickChairUrl = 'pickchair?idMovieTimeRoom=' + time.idMovieTimeRoom + '&idRoom=' + time.idRoom + '&idMovie=' + time.idMovie.idMovie;
                                    movieItem += '<a href="' + pickChairUrl + '" class="text-decoration-none"><span class="time item">' + time.beginTime.substring(0, 5) + '</span></a>';
                                } else {
                                    movieItem += '<span class="time past item">' + time.beginTime.substring(0, 5) + '</span>';
                                }
                            }
                        });

                        movieItem += '</div>';
                        movieItem += '</div>';
                        movieItem += '</div>';
                        movieItem += '</div>';

                        movieSchedule.append(movieItem);
                    });
                }
            }
        );
    }

    $('#selectedDate').change(function() {
        previousSelect = currentSelect; // Lưu giá trị của lần chọn trước đó
        currentSelect = $(this).val(); // Lưu giá trị của lần chọn hiện tại
        console.log(currentSelect);
        console.log("Form submitted!");

        // Gọi hàm cập nhật lịch phim với giá trị của lần chọn hiện tại
        updateMovieSchedule(currentSelect);
    });

    // Gọi hàm cập nhật lịch phim khi trang được tải
    currentSelect = $('#selectedDate').val();
    updateMovieSchedule(currentSelect);

    // Thiết lập cập nhật lịch phim mỗi 60 giây
    setInterval(function() {
        // Sử dụng giá trị của lần chọn trước đó nếu có
        if (previousSelect) {
updateMovieSchedule(previousSelect);
        }

        // Gọi hàm cập nhật lịch phim với giá trị của lần chọn hiện tại
        updateMovieSchedule(currentSelect);
    }, 10000);
});
</script>
</div>
