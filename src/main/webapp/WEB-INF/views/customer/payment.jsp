<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Phương thức thanh toán</title>
</head>
<body>
<div class="content-payment" style="margin-top: 50px;">
        <div class="container-payment">
            <div class="payment-method">
                <div class="payment-method-gr">
                    <div class="payment-method-title">Phương thức thanh toán</div>
                    <label  class="payment-method-pick w-100">
                        <input  type="radio" checked> <span>Thanh toán qua VNPAY</span>
                    </label>
                </div>
                <div class="d-flex flex-row-reverse">
                    <a class="payment-btn-confirm" href="${vnpayUrl }">
                        Xác nhận
                    </a>
                </div>
            </div>
            <div class="payment-info flex-end">
                <div class="payment-info-titles">Thông tin đơn hàng</div>
                <div class="payment-info-box">
                    <div class="payment-info-title">Đơn vị chấp nhận thanh toán</div>
                    <div class="payment-info-title-name">STARLIGHT Đà Nẵng</div>
                    <div class="payment-info-title">Mã đơn hàng</div>
                    <div class="payment-info-title-name">${orderInfo }</div>
                    <hr>
                    <div class="payment-money">
                        <div class="payment-money-title">Số tiền thanh toán</div>
                        <div class="payment-money-content">
                        	<script>
									// Lấy giá trị combo.comboPrice từ server-side và chuyển đổi thành số
									var price = parseFloat('${orderTotal } ');
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
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <a href="/RapChieuPhim_SpringMVC/cancelTicket?orderInfo=${orderInfo }&orderTotal=${orderTotal}&idChairs=${idChairs }&idTimeRoom=${idTimeRoom}" class="cancel-payment text-decoration-none d-flex flex-row-reverse mt-2">
            <span>Hủy thanh toán</span><i class="fas fa-times mx-1" style="line-height: 24px;"></i>
        </a>
        <div class="footer-payment">
            <div class="footer-payment-title">
                Giải pháp thanh toán được cung cấp bởi <span>VNPay</span>
            </div>
            <div class="footer-payment-logos">
                <img src="./assets/img/logo/secure (1) 1.svg" alt="no-logo">
            </div>
        </div>
    </div>
</body>
</html>