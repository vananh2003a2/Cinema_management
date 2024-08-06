<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="com.laptrinhjavaweb.utils.SecurityUtils"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>lịch sử giao dịch</title>
</head>

<body class="bg-dark">

	<div class="content" style="margin-top: 50px;">
		<div class="row w-100">
			<div class="col-3"></div>
			<div class="col-6 text-center mt-5 bg-white border rounded ">
				<h3 class="mt-4" style="color: #246d69;">Lịch sử giao dịch
					Online</h3>
				<div class="table-responsive" style="max-height: 400px;">
					<table class="table mt-4 table-bordered table-striped table-fixed">
						<thead class="table-header">
							<tr>
								<th scope="col">Mã đơn đặt</th>
								<th scope="col">Thời điểm</th>
								<th scope="col">Tổng tiền</th>
								<th scope="col">Trạng thái</th>
								<th scope="col">Chi tiết</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${listTr }" var="transaction">
								<tr>
									<td>${transaction.getTicketCode()}</td>
									<td><fmt:formatDate
											value="${transaction.getBookingTime()}" pattern="dd/MM/yyyy" /></td>
									<td>
											
											<script>
									// Lấy giá trị combo.comboPrice từ server-side và chuyển đổi thành số
									var price = parseFloat('${transaction.getTotalAmount()} ');
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
											</td>


									<td><c:choose>
											<c:when test="${transaction.getStatus() == 1}">Đặt vé thành công</c:when>
											<c:when test="${transaction.getStatus() == 2 }">Không thành công</c:when>
											<c:when test="${transaction.getStatus() == 0 }">Đã hủy vé</c:when>
										</c:choose></td>
									<td><a data-bs-toggle="modal"
										data-bs-target="#exampleModal${transaction.getTicketCode()}"
										href="#" class="btn"
										style="background-color: #28817d; font-size: 8px;"> <i
											style="font-size: 16px; color: #fff;" class="fa fa-list"></i>
									</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- Modal -->
				<c:forEach items="${listTr }" var="transaction">
					<div class="modal fade"
						id="exampleModal${transaction.getTicketCode()}" tabindex="-1"
						aria-labelledby="exampleModalLabel" aria-hidden="true">
						<div class="modal-dialog" style="max-width: 35%;">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title">CHI TIẾT GIAO DỊCH</h5>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<div class="modal-body">
									<table class="table text-left">
										<tbody>
											<tr>
												<th scope="row">Mã đơn đặt:</th>
												<td style="text-align: left;">${transaction.getTicketCode() }</td>
											</tr>
											<tr>
												<th scope="row">Thời điểm:</th>
												<td style="text-align: left;"><fmt:formatDate
														value="${transaction.getBookingTime()}"
														pattern="dd/MM/yyyy" /></td>
											</tr>
											<tr>
												<th scope="row">Khách hàng:</th>
												<td style="text-align: left;">${transaction.getFullName()}</td>
											</tr>
											<tr>
												<th scope="row">Phim:</th>
												<td style="text-align: left;">${transaction.getMovieName()}</td>
											</tr>
											<tr>
												<th scope="row">Suất chiếu:</th>
												<td style="text-align: left;">${transaction.getBeginTime().toString().substring(0, 5)}</td>
											</tr>
											<tr>
												<th scope="row">Phòng chiếu:</th>
												<td style="text-align: left;">${transaction.getRoomCode()}</td>
											</tr>
											<tr>

												<th scope="row">Ghế:</th>
												<td style="text-align: left;"><c:set var="temp"
														value="false" /> <c:forEach items="${getdetail}"
														var="detail">
														<c:if
															test="${detail.getTicketCode() eq transaction.getTicketCode()}">
															<c:if test="${temp}">,</c:if>
																			        ${detail.getChairCode()}
																			        <c:set var="temp" value="true" />
														</c:if>
													</c:forEach></td>
											</tr>
											<tr>
												<th scope="row">Thanh toán:</th>
												<td style="text-align: left;"><c:choose>
														<c:when test="${transaction.getStatus() == 1}">Đã thanh toán</c:when>
														<c:when test="${transaction.getStatus() == 2 }">Chưa thanh toán</c:when>
														<c:when test="${transaction.getStatus() == 0 }">Hủy vé</c:when>
													</c:choose></td>
											</tr>
										</tbody>
									</table>
								</div>
								<div class="modal-footer">
									<p>
										<strong>Tổng tiền:</strong>
										<script>
									// Lấy giá trị combo.comboPrice từ server-side và chuyển đổi thành số
									var price = parseFloat('${transaction.getTotalAmount()} ');
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
									</p>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>

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
		const message = "${message}";
		const hasMessage = (message != "");
		console.log(message);
		console.log(hasMessage);
		if(hasMessage === true) {
			alert(message);
		}
	    function highlightButton(button) {
	        // Lặp qua tất cả các nút trong hàng và loại bỏ lớp 'highlight' khỏi chúng
	        let buttons = document.getElementById('buttonRow').querySelectorAll('button');
	        buttons.forEach(btn => btn.classList.remove('highlight'));
	        
	        // Thêm lớp 'highlight' cho nút được chọn
	        button.classList.add('highlight');
	    }
	</script>
</body>
</html>