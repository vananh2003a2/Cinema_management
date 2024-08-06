 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<head>
<title>Lịch chiếu</title>
</head>
<div class="container-showtime" style="margin-top: 50px;">
	<div class="box-infor row">
		<div class="col-3">
			<img
				src="<c:url value="/template/customer/img/${movie.getThumnail() }"/>"
				class="img-fluid" alt="...">
		</div>
		<div class="col-9 showtime-box-content">
			<div class="first-showtime px-4">
				<h4 class="showtime-title">${movie.getMovieName() }</h4>
				<br>
				<p
					class="item-title-sup mb-0 d-inline-block bg-color-main-100 fw-bold text-white mt-2">P</p>
				<div class="mt-2">${movie.getTypeName() }-
					${movie.getMovieFormat() }</div>
				<div>
					<div class="fw-bold d-inline">Đạo diễn: </div>${movie.getDirector() }</div>
				<div>
					<c:set var="formattedDate">
							<fmt:formatDate value="${movie.getMovieDate()}" pattern="dd/MM/yyyy" />
					</c:set>
					<div class="fw-bold d-inline">Ngày chiếu: </div>${formattedDate}</div>
				<div>
					<div class="fw-bold d-inline">Diễn viên: </div>${movie.getActors() }</div>
				<div>
					<div class="fw-bold d-inline">Thời lượng: </div>${movie.getMovieDuration() }
					phút
				</div>
				<div class="text-secondary">Chia sẻ</div>
			</div>
			<div class="last-showtime">
				<div class="description">${movie.getMovieScript() }</div>
			</div>
		</div>
	</div>
	<div class="box-times">
		<div class="box-time-title fw-bold h4">LỊCH CHIẾU</div>
		<!-- Bắt đầu sử dụng schedule (đặt id để đoạn mã js phía dưới thực hiện) -->
		<div id="movieSchedule">
			<c:forEach items="${ngaychieus}" var="date">
				<div class="row box-time-item mt-4">
					<div class="col-2 box-time-date">${date }</div>
					<div class="col-10 box-time-hours position-relative">
						<c:set var="foundShowtime" value="false" />
						<c:forEach items="${listmovietime}" var="time">
							<c:set var="formattedDate">
								<fmt:formatDate value="${time.getDate()}" pattern="dd/MM/yyyy" />
							</c:set>
							<c:choose>
								<c:when
									test="${date == formattedDate && time.idMovie.getIdMovie() == idMovie}">
									<c:set var="foundShowtime" value="true" />

									<c:choose>
										<c:when test="${time.getStatus() == 1}">
											<a
											href="<c:url value='/pickchair?idMovieTimeRoom=${time.getIdMovieTimeRoom()}&idRoom=${time.getIdRoom()}&idMovie=${time.idMovie.getIdMovie()}'/>"
											class="box-time-hour text-decoration-none text-white"><div>${time.getBeginTime().toString().substring(0, 5)}</div></a>
										</c:when>

										<c:otherwise>
											<a href="" class="box-time-hour-pass text-decoration-none text-white "><div>${time.getBeginTime().toString().substring(0, 5)}</div></a>
										</c:otherwise>
									</c:choose>
								</c:when>
							</c:choose>
						</c:forEach>
						<c:if test="${not foundShowtime}">
							<div
								class="text-center h5 text-bold position-absolute top-50 start-0 translate-middle-y px-3">Không
								có suất chiếu!</div>
						</c:if>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</div>
<script>
    $(document).ready(function() {
        function updateMovieSchedule(idMovie) {
            $.get("http://localhost:8080/RapChieuPhim_SpringMVC/api/lichchieu", {
                idMovie: idMovie
            }, function(response) {
                var movieSchedule = $("#movieSchedule");
                movieSchedule.empty();

                if (response.ngaychieuOptions && response.ngaychieuOptions.length > 0) {
                    response.ngaychieuOptions.forEach(function(date) {
                        var boxTimeItem = $('<div class="row box-time-item mt-4"></div>');
                        boxTimeItem.append('<div class="col-2 box-time-date">' + date + '</div>');

                        var boxTimeHours = $('<div class="col-10 box-time-hours position-relative"></div>');
                        var foundShowtime = false;

                        response.listmovietime.forEach(function(time) {
                            var dated = new Date(time.date);
                            var year = dated.getFullYear();
                            var month = ('0' + (dated.getMonth() + 1)).slice(-2);
                            var day = ('0' + dated.getDate()).slice(-2);
                            var formattedDate = day + '/' + month + '/' + year;

                            if (date == formattedDate && time.idMovie.idMovie == idMovie) {
                                foundShowtime = true;

                                var movieItem = '';

                              /*   if (time.status == 1) {
                                    movieItem += '<a href="<c:url value="/pickchair?idMovieTimeRoom=' + time.idMovieTimeRoom + '"/>"class="text-decoration-none"><span class="time item">' + time.beginTime.substring(0, 5) + '</span></a>';
                                } else {
                                    movieItem += '<span class="time past item">' + time.beginTime.substring(0, 5) + '</span>';
                                } */
                               
                            	if (time.status == 1) {
                                    var pickChairUrl ='pickchair?idMovieTimeRoom=' + time.idMovieTimeRoom + '&idRoom=' + time.idRoom + '&idMovie=' + time.idMovie.idMovie;
                                    movieItem += '<a href="' + pickChairUrl + '" class="text-decoration-none"><span class="time item">' + time.beginTime.substring(0, 5) + '</span></a>';
                                } else {
                                    movieItem += '<span class="time past item">' + time.beginTime.substring(0, 5) + '</span>';
                                }


                                boxTimeHours.append(movieItem);
                            }
                        });

                        if (!foundShowtime) {
                            var noShowtimeMsg = $('<div class="text-center h5 text-bold position-absolute top-50 start-0 translate-middle-y px-3">Không có suất chiếu!</div>');
                            boxTimeHours.append(noShowtimeMsg);
                        }

                        boxTimeItem.append(boxTimeHours);
                        movieSchedule.append(boxTimeItem);
                    });
                }
            });
        }

        // Lấy idMovie từ URL
        var urlParams = new URLSearchParams(window.location.search);
        var idMovie = urlParams.get('idMovie');

        // Gọi hàm cập nhật lịch phim với idMovie đã lấy được từ URL
        updateMovieSchedule(idMovie);

        // Lập lịch gọi AJAX mỗi 6 giây
        setInterval(function() {
            updateMovieSchedule(idMovie);
        }, 10000);
    });
</script>


