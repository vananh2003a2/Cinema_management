<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Xác nhận thông tin</title>
</head>
<body class="bg-dark">
	<div class="pickchair-header text-center text-white h3 mt-3">
		BƯỚC 3: XÁC NHẬN THÔNG TIN</div>
	<hr class="line">
	<div class="confirmInformation bg-dark">
		<div class="container">
			<div class="confirmInformation-content col-xl-6 pt-4">
				<div class="confirmInformation-customer ">
					<h5>THÔNG TIN NGƯỜI MUA</h5>
					<p>Người nhận: Đoàn Văn Dũng</p>
					<p>Email: dung711@gmail.com</p>
					<p>Số điện thoại: 0213123123</p>
				</div>
				<div class="confirmInformation-film mt-4">
					<p>Phòng: 5</p>
					<p>Tên Phim: Bố già</p>
					<p>Suất chiếu: 17:00 - 11/04/2024</p>
					<p style="color: #33aba5;">Số ghế: 4</p>
					<div class="d-flex ">
						<p style="color: #ea593c; margin-right: 4px;">A01:</p>
						<p>45.000đ</p>
					</div>
				</div>
				<div class="confirmInformation-combo mt-4">
					<h6 style="color: #33aba5;">COMBO: ${comboName }</h6>
					<p>Số lượng: ${comboQuantity }</p>
					<p>
						Giá: 
						<script>
							// Lấy giá trị combo.comboPrice từ server-side và chuyển đổi thành số
							var price = parseFloat('${comboPrice }');
							// Định dạng số thành tiền Việt Nam
							var formattedPrice = new Intl.NumberFormat('vi-VN',
									{
										style : 'currency',
										currency : 'VND'
									}).format(price);
							// Bỏ đi ký hiệu "đ" sau giá tiền
							var priceWithoutSymbol = formattedPrice.replace(
									"₫", "đ");
							// Hiển thị giá tiền đã định dạng trong thẻ p
							document.write(priceWithoutSymbol);
						</script>

					</p>
				</div>
				<div class="confirmInformation-pay  mt-4">
					<h5>HÌNH THỨC THANH TOÁN</h5>
					<div class="d-flex align-items-center">
						<div class="form-check">
							<input class="form-check-input" type="radio" name="paymentMethod"
								id="onepayRadio"> <label class="form-check-label"
								for="onepayRadio">OnePay - ATM nội địa</label>
						</div>
					</div>
				</div>
			</div>
			<div
				class="mt-4 confirmInformation-footer d-flex gap-2 justify-content-between">
				<a class="btn">XÁC NHẬN</a> <a class="btn" href="trangchu">HỦY
					GIAO DỊCH</a>
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
</body>
</html>