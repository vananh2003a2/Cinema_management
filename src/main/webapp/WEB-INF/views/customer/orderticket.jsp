<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hóa đơn vé</title>
</head>
<body>
	<div class="contact-content" style="margin-top: 50px;">
		<div class="container"
			style="background-color: rgb(227, 227, 243); max-width: 760px;">
			<h3 class="text-center mt-2 pt-3" style="color: #45A29E;">THÔNG
				TIN HÓA ĐƠN VÉ</h3>
			<div class="row "
				style="display: flex; justify-content: space-around;">
				<div class="col-sm-6 mt-3" style="width: 35%;">
					<div class="">
						<h5>THÔNG TIN NGƯỜI DÙNG</h5>
						<p class="fw-bold">
							Họ tên: <span class="fw-light">${hoten }</span>
						</p>
						<p class="fw-bold">
							Số Điện thoại: <span class="fw-light">${sdt }</span>
						</p>
						<p class="fw-bold">
							Email: <span class="fw-light">${email }</span>
						</p>
					</div>
					<div class="">
						<h5>THÔNG TIN VÉ</h5>
						<p class="fw-bold">
							Mã vé: <span class="fw-light">${ticketCode }</span>
						</p>
						<p class="fw-bold">
							Phim: <span class="fw-light">${tenphim }</span>
						</p>
						<p class="fw-bold">
							Phòng: <span class="fw-light">${phongchieu }</span>
						</p>
						<p class="fw-bold">
							Suất chiếu: <span class="fw-light"><fmt:formatDate value="${suatchieu}" pattern="HH:mm" /> -
								<fmt:formatDate value="${ngaychieu }" pattern="dd/MM/yyyy" /></span>
						</p>

					</div>
				</div>
				<div class="col-sm-6 mt-3" style="width: 30%;">
					<div class="">
						<h5>THÔNG TIN GHẾ</h5>
						<p class="fw-bold">
							Tên ghế:
							<c:set var="first" value="true" />
							<c:forEach var="chair" items="${chairCode }">
								<c:if test="${not first}">
									<span class="fw-light">, </span>
								</c:if>
								<c:set var="first" value="false" />
								<span class="fw-light">${chair }</span>
							</c:forEach>

						</p>
						<p class="fw-bold">
							Số lượng: <span class="fw-light">${chairQuantity }</span>
						</p>
						<p class="fw-bold">
							Giá: <span class="fw-light"> <script>
								// Lấy giá trị combo.comboPrice từ server-side và chuyển đổi thành số
								var price = parseFloat('${chairPrice }');
								// Định dạng số thành tiền Việt Nam
								var formattedPrice = new Intl.NumberFormat(
										'vi-VN', {
											style : 'currency',
											currency : 'VND'
										}).format(price);
								// Bỏ đi ký hiệu "đ" sau giá tiền
								var priceWithoutSymbol = formattedPrice
										.replace("₫", "đ");
								// Hiển thị giá tiền đã định dạng trong thẻ p
								document.write(priceWithoutSymbol);
							</script>
							</span>
						</p>
					</div>
					<div class="">
						<h5>THÔNG TIN COMBO</h5>
						<p class="fw-bold">
							Tên combo:
							<c:set var="first" value="true" />
							<c:forEach var="combo" items="${comboName}">
								<c:if test="${not first}">
									<span class="fw-light">, </span>
								</c:if>
								<c:set var="first" value="false" />
								<span class="fw-light">${combo}</span>
							</c:forEach>

						</p>
						<p class="fw-bold">
							Số lượng: <span class="fw-light">${comboQuantity }</span>
						</p>
						<p class="fw-bold">
							Giá: <span class="fw-light"> <script>
								// Lấy giá trị combo.comboPrice từ server-side và chuyển đổi thành số
								var price = parseFloat('${comboPrice }');
								// Định dạng số thành tiền Việt Nam
								var formattedPrice = new Intl.NumberFormat(
										'vi-VN', {
											style : 'currency',
											currency : 'VND'
										}).format(price);
								// Bỏ đi ký hiệu "đ" sau giá tiền
								var priceWithoutSymbol = formattedPrice
										.replace("₫", "đ");
								// Hiển thị giá tiền đã định dạng trong thẻ p
								document.write(priceWithoutSymbol);
							</script>

							</span>
						</p>
					</div>

				</div>
				<p style="text-align: right; padding-right: 134px;" class="fw-bold">
					Tổng tiền: <span class="fw-light"> <script>
						// Lấy giá trị combo.comboPrice từ server-side và chuyển đổi thành số
						var price = parseFloat('${totalPrice }');
						// Định dạng số thành tiền Việt Nam
						var formattedPrice = new Intl.NumberFormat('vi-VN', {
							style : 'currency',
							currency : 'VND'
						}).format(price);
						// Bỏ đi ký hiệu "đ" sau giá tiền
						var priceWithoutSymbol = formattedPrice.replace("₫",
								"đ");
						// Hiển thị giá tiền đã định dạng trong thẻ p
						document.write(priceWithoutSymbol);
					</script>
					</span>
				</p>
			</div>
			<div class="mt-4 confirmInformation-footer gap-2 n"
				style="text-align: right; padding-right: 30px;">
				<a href="index"
					style="width: 109px; height: 47px; font-size: 14px; margin-left: 65px;"
					class="btn"><i class="fa fa-home"></i> Quay lại</a>
			</div>
		</div>

	</div>
	
	<script >
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