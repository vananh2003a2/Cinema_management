<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chọn Combo</title>
</head>
<body class="bg-dark">
	<div class="choosecombo-header text-center text-white h3 mt-3">
		<a href="./pickchair.html" class="choosecombo-header-back"><svg
				width="64px" height="64px" viewBox="-7.92 -7.92 87.84 87.84"
				id="emoji" xmlns="http://www.w3.org/2000/svg" fill="#FFFFFF"
				stroke="#FFFFFF">
				<g id="SVGRepo_bgCarrier" stroke-width="0"></g>
				<g id="SVGRepo_tracerCarrier" stroke-linecap="round"
					stroke-linejoin="round"></g>
				<g id="SVGRepo_iconCarrier"> <g id="color"></g> <g id="hair"></g> <g
					id="skin"></g> <g id="skin-shadow"></g> <g id="line"> <polyline
					fill="none" stroke="#FFFFFF" stroke-linecap="round"
					stroke-linejoin="round" stroke-miterlimit="10"
					stroke-width="3.8160000000000003"
						points="46.1964,16.2048 26.8036,35.6651 46.1964,55.1254"></polyline> </g> </g></svg></a>
			BƯỚC 2: CHỌN COMBO
	</div>
	<hr class="line">
	<div class="choosecombo my-5">
		<div class="container-fluid p-5">
			<div class="choosecombo-content pt-1">
				<div class="row g-4">
					<c:forEach items="${lstcombo}" var="combo">
						<div class=" col-3">
	                        <div class="card bg-white text-black">
	                          <img src="https://starlight.vn/Content/img/combo-doi.jpg" class="card-img-top" alt="">
	                          <div class="card-body text-black">
	                            <h5 class="comboName card-title text-center ">${combo.getComboname() }</h5>
	                            <p class="card-text text-center">${combo.getComboname() }</p>
	                            <p class="comboPrice card-text text-color-main-100 text-center mb-4" data-combo-price="${combo.comboprice}">
	                            <script>
							        // Lấy giá trị combo.comboPrice từ server-side và chuyển đổi thành số
							        var price = parseFloat('${combo.getComboprice()}');
							        // Định dạng số thành tiền Việt Nam
							        var formattedPrice = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(price);
							        // Bỏ đi ký hiệu "đ" sau giá tiền
							        var priceWithoutSymbol = formattedPrice.replace("₫", "đ");
							        // Hiển thị giá tiền đã định dạng trong thẻ p
							        document.write(priceWithoutSymbol);
							    </script>
	                            </p>
	                            <div class="choosecombo-content-quality d-flex justify-content-center">
	                                <a class="btn btn-minus" href="#"><i class="fa fa-minus"></i></a>
	                                <p id="quantity">0</p>
	                                <a class="btn btn-plus" href="#"><i class="fa fa-plus"></i></a>
	                            </div>
	                          </div>
	                        </div>
	                    </div>
					</c:forEach>

				</div>

			</div>

		</div>
	</div>
	<div class="container-fluid">
		<div class="row choosecombo-footer-info">
			<div class="choosecombo-footer-info-items col-10">
				<div class="pickchair-footer-info-item">
					<div class="pickchair-footer-info-item-title">Combo chọn</div>
					<span style="font-size: 24px" class="comboNameChange"></span>
				</div>
				<div class="pickchair-footer-info-item">
					<div class="pickchair-footer-info-item-title d-inline">Số
						lượng:</div>
					<span style="font-size: 24px" class="comboQuanlityChange">0</span>
				</div>
				<div class="pickchair-footer-info-item">
					<div class="pickchair-footer-info-item-title d-inline">Giá:</div>
					<span style="font-size: 24px" class="comboPriceChange"></span>
				</div>
			</div>
			<div class="col-2 d-flex align-items-center px-0">
				<form id="confirmationForm" action="xacnhanthongtin" method="POST">
				    <!-- Thông tin combo đã chọn -->
				    <input type="hidden" id="comboName" name="comboName" value="">
				    <input type="hidden" id="comboQuantity" name="comboQuantity" value="">
				    <input type="hidden" id="comboPrice" name="comboPrice" value="">
				    <!-- Các trường khác cần gửi đi -->
				    <button style="width: 190px;" type="submit" class="btn choosecombo-footer-info-btn fw-bold text-color-main-100 text-white">Tiếp theo</button>
				</form>
				
			</div>
		</div>
	</div>
	<script>
	document.addEventListener('DOMContentLoaded', function() {
	    const plusBtns = document.querySelectorAll('.btn-plus');
	    const minusBtns = document.querySelectorAll('.btn-minus');
	    const comboName = document.querySelectorAll('.comboName');
	    const comboPrice = document.querySelectorAll('.comboPrice');
	    
	    const quantityElements = document.querySelectorAll('.choosecombo-content-quality p');
	    const comboInfoTitle = document.querySelector('.comboNameChange');
	    const comboInfoQuantity = document.querySelector('.comboQuanlityChange');
	    const comboInfoPrice = document.querySelector('.comboPriceChange');
	    const comboBtn = document.querySelector('.pickchair-footer-info-btn');

	    let selectedCombos = [];
	    let totalPrice = 0;

	    plusBtns.forEach(function(btn, index) {
	        btn.addEventListener('click', function(event) {
	            event.preventDefault();
	            let quantity = parseInt(quantityElements[index].textContent);
	            quantity++;
	            quantityElements[index].textContent = quantity;
	            updateSelectedCombo(index, quantity);
	        });
	    });

	    minusBtns.forEach(function(btn, index) {
	        btn.addEventListener('click', function(event) {
	            event.preventDefault();
	            let quantity = parseInt(quantityElements[index].textContent);
	            if (quantity > 0) {
	                quantity--;
	                quantityElements[index].textContent = quantity;
	                updateSelectedCombo(index, quantity);
	            }
	        });
	    });

	    function updateSelectedCombo(index, quantity) {
	        let comboNameText = comboName[index].textContent.trim();
	        // Sử dụng dataset để lấy giá trị comboPrice từ thuộc tính data-combo-price
	        let comboPriceText = comboPrice[index].dataset.comboPrice;
	        totalPrice -= selectedCombos[index] ? selectedCombos[index].price : 0;
	        if (quantity === 0) {
	            delete selectedCombos[index];
	        } else {
	            selectedCombos[index] = {
	                name: comboNameText,
	                quantity: quantity,
	                price: parseFloat(comboPriceText) * quantity // ParseFloat để đảm bảo tính toán đúng
	            };
	            totalPrice += selectedCombos[index].price;
	        }

	        updateFooterInfo();
	    }

	    function updateFooterInfo() {
	        let selectedComboNames = Object.values(selectedCombos).map(combo => combo.name);
	        comboInfoTitle.textContent = selectedComboNames.join(', ');
	        comboInfoQuantity.textContent = Object.values(selectedCombos).reduce((total, combo) => total + combo.quantity, 0);
	        comboInfoPrice.textContent = totalPrice.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' }).replace("₫", "đ");
	        /* Cập nhật các trường hidden trong form */
	        document.getElementById('comboName').value = selectedComboNames.join(', ');
	        document.getElementById('comboQuantity').value = Object.values(selectedCombos).reduce((total, combo) => total + combo.quantity, 0);
	        document.getElementById('comboPrice').value = totalPrice;

	    }
	    
	    function submitForm() {
	        // Gửi form
	        document.getElementById('confirmationForm').submit();
	    }
	    comboBtn.addEventListener('click', function(event) {
	        event.preventDefault();
	        submitForm();
	        // Redirect or perform other action when combo button is clicked
	    });
	});


    </script>
</body>
</html>