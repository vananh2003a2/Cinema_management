<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Chọn combo</title>
<link href="<c:url value='/template/web/css/style.css'/>"
	rel="stylesheet" />

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

<body class="bg-dark">
	<%@ include file="/common/web/header.jsp"%>
	<dec:body />
	<%@ include file="/common/web/footer.jsp"%>

	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
		integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
		integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
		crossorigin="anonymous"></script>
	<script>
		document.addEventListener('DOMContentLoaded', function() {
			const plusBtns = document.querySelectorAll('.btn-plus');
			const minusBtns = document.querySelectorAll('.btn-minus');

			plusBtns.forEach(function(btn) {
				btn.addEventListener('click', function(event) {
					event.preventDefault();
					let quantityElement = btn.parentElement.querySelector('p');
					let quantity = parseInt(quantityElement.textContent);
					quantity++;
					quantityElement.textContent = quantity;
				});
			});

			minusBtns.forEach(function(btn) {
				btn.addEventListener('click', function(event) {
					event.preventDefault();
					let quantityElement = btn.parentElement.querySelector('p');
					let quantity = parseInt(quantityElement.textContent);
					if (quantity > 0) {
						quantity--;
						quantityElement.textContent = quantity;
					}
				});
			});
		});
	</script>
</body>
</html>