<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<title>Chọn ghế</title>
<body class="bg-dark">
	<div class="container-pickchair" style="margin-top: 68px;">
		<div class="pickchair-header text-center text-white h3 mt-3">
			BƯỚC 1: CHỌN GHẾ</div>
		<hr class="line">
		<div class="pickchair-body text-center">
			<div class="pickchair-body-title text-white fw-border h4">MÀN
				HÌNH</div>
			<div class="pickchair-body-screen">
				<img
					src="<c:url value="/template/customer/img/background/screen.png"/>"
					alt="no-screen">
			</div>
			<!-- Ghế đơn -->
			<div class="pickchair-body-chairs">
				<c:forEach var="chair" items="${lst_chair}">

						<c:if
							test="${(chair.idCinemaChair - lst_chair[0].idCinemaChair + 1 ) / 16 <= 10}">
							<!-- 	Kiểm tra ghế đã dặt -->
							<c:set var="isBooked" value="false" />
							<c:forEach var="bookedChair" items="${lst_bookedChair}">
								<c:if test="${bookedChair.idCinemaChair == chair.idCinemaChair}">
								<c:set var="isBooked" value="true" />
								</c:if>
							</c:forEach>
							<c:if test="${isBooked == false}">
							<a href="javascript:void(0)"
								class="chair-container d-inline-block"
								onclick="changeImage(this)"
								data-chair-id="${chair.idCinemaChair}"
								data-chair-code="${chair.chairCode }"
								data-chair-price="${chair.getIdChairType().getPrice()}"> <img
								class="chair"
								src="<c:url value="/template/customer/img/chairs/chair-df.png"/>"
								alt="no-chair"> <span class="chair-text">${chair.chairCode }</span>
							</a>
							</c:if>
							<c:if test="${isBooked == true}">
							<a href="javascript:void(0)"
								class="chair-container d-inline-block"
								data-chair-id="${chair.idCinemaChair}"
								data-chair-code="${chair.chairCode }"
								data-chair-price="${chair.getIdChairType().getPrice()}"> <img
								class="chair"
								src="<c:url value="/template/customer/img/chairs/chair-saled.png"/>"
								alt="no-chair"> <span class="chair-text">${chair.chairCode }</span>
							</a>
							</c:if>
							<c:if
								test="${(chair.idCinemaChair - lst_chair[0].idCinemaChair + 1 ) % 16 == 0}">
								<br>
							</c:if>
						</c:if>
				</c:forEach>
				<!-- Ghế đơn -->

				<!-- Ghế đôi -->
				<div class="last-chairs">
					<c:forEach var="chair" items="${lst_chair}">

						<c:if
							test="${(chair.idCinemaChair - lst_chair[0].idCinemaChair + 1 ) / 16 > 10}">
							<c:set var="st" value="${chair.chairCode }" />
							<c:set var="split" value="${fn:split(st, '-')}" />
						<!-- 	Kiểm tra ghế đã dặt -->
							<c:set var="isBooked" value="false" />
							<c:forEach var="bookedChair" items="${lst_bookedChair}">
								<c:if test="${bookedChair.idCinemaChair == chair.idCinemaChair}">
								<c:set var="isBooked" value="true" />
								</c:if>
							</c:forEach>
							
							<c:if test="${isBooked == false}"> 
							<a href="javascript:void(0)"
								class="chair-container d-inline-block"
								onclick="changeImageChairDouble1(this)"
								data-chair-id="${chair.idCinemaChair}"
								data-chair-code="${chair.chairCode }"
								data-chair-price="${chair.getIdChairType().getPrice()}"> <img
								class="chair-double-1"
								src="<c:url value="/template/customer/img/chairs/chair-double.png"/>"
								alt="no-chair"> <span class="chair-text">${split[0]}</span>
							</a>
							<a href="javascript:void(0)"
								class="chair-container d-inline-block"
								onclick="changeImageChairDouble2(this)"
								data-chair-id="${chair.idCinemaChair}"
								data-chair-code="${chair.chairCode }"
								data-chair-price="${chair.getIdChairType().getPrice()}"> <img
								class="chair-double-2" data-chair-id="${chair.idCinemaChair}"
								src="<c:url value="/template/customer/img/chairs/chair-double.png"/>"
								alt="no-chair"> <span class="chair-text">${split[1]}</span>
							</a>
							</c:if>
							
							<c:if test="${isBooked == true}"> 
							<a href="javascript:void(0)"
								class="chair-container d-inline-block"
								data-chair-id="${chair.idCinemaChair}"
								data-chair-code="${chair.chairCode }"
								data-chair-price="${chair.getIdChairType().getPrice()}"> <img
								class="chair-double-1"
								src="<c:url value="/template/customer/img/chairs/chair-saled.png"/>"
								alt="no-chair"> <span class="chair-text">${split[0]}</span>
							</a>
							<a href="javascript:void(0)"
								class="chair-container d-inline-block"
								onclick="changeImageChairDouble2(this)"
								data-chair-id="${chair.idCinemaChair}"
								data-chair-code="${chair.chairCode }"
								data-chair-price="${chair.getIdChairType().getPrice()}"> <img
								class="chair-double-2" data-chair-id="${chair.idCinemaChair}"
								src="<c:url value="/template/customer/img/chairs/chair-saled.png"/>"
								alt="no-chair"> <span class="chair-text">${split[1]}</span>
							</a>
							</c:if>
							
							<c:if
								test="${(chair.idCinemaChair - lst_chair[0].idCinemaChair + 2 ) % 160 == 0}">
								<br>
							</c:if>

						</c:if>

					</c:forEach>
					<!-- Ghế đôi -->
				</div>
			</div>
		</div>
		<div class="pickchair-footer">
			<div class="pickchair-footer-container row w-100">
				<div class="pickchair-footer-note col-6">
					<div class="pickchair-footer-note-title h4">CHÚ THÍCH GHẾ</div>
					<div class="pickchair-footer-note-items">
						<div class="pickchair-footer-note-item">
							<img src="./template/customer/img/chairs/chair-df.png"
								alt="no-img"> Ghế thường
						</div>
						<div class="pickchair-footer-note-item">
							<img src="./template/customer/img/chairs/chair-double.png"
								alt="no-img"> Ghế đôi
						</div>
						<div class="pickchair-footer-note-item">
							<img src="./template/customer/img/chairs/chair-saled.png"
								alt="no-img"> Ghế đã bán
						</div>
						<div class="pickchair-footer-note-item">
							<img
								src="./template/customer/img/chairs/chair-picking.png"
								alt="no-img"> Ghế đang chọn
						</div>
					</div>
				</div>
				<div class="pickchair-footer-info col-6 row">
					<div class="pickchair-footer-info-items col-8">
						<div class="pickchair-footer-info-item">
							<div class="pickchair-footer-info-item-title">Ghế đã chọn</div>
							<span id="selectedChairs_text"></span>
						</div>
						<div class="pickchair-footer-info-item">
							<div class="pickchair-footer-info-item-title">Giá vé</div>
							<span id="totalPrice_text">0 đ</span>
						</div>
					</div>
					<security:authorize access="isAnonymous()">
						<a href="<c:url value="/login"/>"
							class="pickchair-footer-info-btn btn col-4 fw-bold text-color-main-100 text-white bg-color-main-100">Tiếp
							theo</a>
					</security:authorize>
					<security:authorize access="isAuthenticated()">
						<%-- 
						<c:url var="confirmChairURL" value="/confirm-pickchair">
								<c:param name="idMovieTimeRoom" value="${mtr.idMovieTimeRoom}" />
								<c:param name=""/>
							</c:url> --%>
						<a href="javascript:void(0)" onclick="confirmChairs()"
							class="pickchair-footer-info-btn btn col-4 fw-bold text-color-main-100 text-white bg-color-main-100">Tiếp
							theo</a>
					</security:authorize>
				</div>
			</div>
		</div>
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
		var selectedChairs = [];
		var selectedchairCode = [];
		var totalPrice = Number(0);
		function updateSelectedChairs() {
			var selectedChairsSpan = document
					.getElementById("selectedChairs_text");
			var selectedChairsText = "";
			for (var i = 0; i < selectedchairCode.length; i++) {
				selectedChairsText += selectedchairCode[i];
				if (i < selectedchairCode.length - 1) {
					selectedChairsText += ", ";
				}
			}
			selectedChairsSpan.innerHTML = selectedChairsText;
		}
		function updatePrice() {
			var totalPriceSpan = document.getElementById("totalPrice_text");
			totalPriceSpan.innerHTML = totalPrice ;
		}
		function changeImage(element) {
			var image = element.querySelector('.chair');
			var chairId = element.getAttribute('data-chair-id');
			var chairCode = element.getAttribute('data-chair-code');
			var chairPrice = element.getAttribute('data-chair-price');
			if (image.src.includes("chair-df.png")) {
				image.src = "./template/customer/img/chairs/chair-picking.png";
				selectedChairs.push(chairId);
				selectedchairCode.push(chairCode);
				totalPrice += parseFloat(chairPrice);
			} else {
				image.src = "./template/customer/img/chairs/chair-df.png";
				var index = selectedChairs.indexOf(chairId);
				var indexCode = selectedchairCode.indexOf(chairCode);
				totalPrice -= parseFloat(chairPrice);
				if (index !== -1) {
					selectedChairs.splice(index, 1);
					selectedchairCode.splice(indexCode, 1);
				}
			}
			updateSelectedChairs();
			updatePrice();
		}

		function changeImageChairDouble1(element) {
			var imageChairDouble1 = element.querySelector('.chair-double-1');
			var imageChairDouble2 = element.nextElementSibling
					.querySelector('.chair-double-2');
			var chairId = element.getAttribute('data-chair-id');
			var chairCode = element.getAttribute('data-chair-code');
			var chairPrice = element.getAttribute('data-chair-price');
			if (imageChairDouble1.src.includes("chair-double.png")) {
				imageChairDouble1.src = "./template/customer/img/chairs/chair-picking.png";
				imageChairDouble2.src = "./template/customer/img/chairs/chair-picking.png";
				selectedChairs.push(chairId);
				selectedchairCode.push(chairCode);
				totalPrice += parseFloat(chairPrice);
			} else {
				imageChairDouble1.src = "./template/customer/img/chairs/chair-double.png";
				imageChairDouble2.src = "./template/customer/img/chairs/chair-double.png";
				var index = selectedChairs.indexOf(chairId);
				var indexCode = selectedchairCode.indexOf(chairCode);
				totalPrice -= parseFloat(chairPrice);
				if (index !== -1) {
					selectedChairs.splice(index, 1);
					selectedchairCode.splice(indexCode, 1);
				}
			}
			updateSelectedChairs();
			updatePrice();
		}
		function changeImageChairDouble2(element) {
			var imageChairDouble1 = element.previousElementSibling
					.querySelector('.chair-double-1');
			var imageChairDouble2 = element.querySelector('.chair-double-2');
			var chairId = element.getAttribute('data-chair-id');
			var chairCode = element.getAttribute('data-chair-code');
			var chairPrice = element.getAttribute('data-chair-price');
			if (imageChairDouble2.src.includes("chair-double.png")) {
				imageChairDouble2.src = "./template/customer/img/chairs/chair-picking.png";
				imageChairDouble1.src = "./template/customer/img/chairs/chair-picking.png";
				selectedChairs.push(chairId);
				selectedchairCode.push(chairCode);
				totalPrice += parseFloat(chairPrice);
			} else {
				imageChairDouble2.src = "./template/customer/img/chairs/chair-double.png";
				imageChairDouble1.src = "./template/customer/img/chairs/chair-double.png";
				var index = selectedChairs.indexOf(chairId);
				var indexCode = selectedchairCode.indexOf(chairCode);
				totalPrice -= parseFloat(chairPrice);
				;
				if (index !== -1) {
					selectedChairs.splice(index, 1);
					selectedchairCode.splice(indexCode, 1);
				}
			}
			updateSelectedChairs();
			updatePrice();
		}
		function convertSelectedChairsToString() {
			return selectedChairs.join(',');
		}
		function buildURLWithSelectedChairs() {
			var selectedChairsString = convertSelectedChairsToString();
			var confirmChairURL = '<c:url value="/confirm-pickchair"/>';
			confirmChairURL += '?idMovieTimeRoom=${mtr.idMovieTimeRoom}&idUser=${idUser}&idMovie=${idMovie}&idRoom=${idRoom}&selectedChairs='
					+ selectedChairsString;
			return confirmChairURL;
		}
		function confirmChairs() {
			/* Kiểm tra cần phải chọn ghế nếu muốn tiếp tục */
			if (selectedChairs.length === 0) {
				alert("Vui lòng chọn ghế trước khi tiếp tục.");
				return;
			}
			var confirmChairURL = buildURLWithSelectedChairs();
			window.location.href = confirmChairURL;
		}
		// Hàm để định dạng giá tiền thành định dạng tiền tệ Việt Nam (VND) và thay thế ký hiệu "₫" bằng "đ"
		function formatPrice(price) {
		    return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(price).replace('₫', 'đ');
		}

		// Gọi hàm để định dạng giá vé
		function updatePrice() {
		    var totalPriceSpan = document.getElementById("totalPrice_text");
		    totalPriceSpan.innerHTML = formatPrice(totalPrice);
		}

	</script>
</body>
