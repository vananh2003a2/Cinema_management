<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng ký</title>
</head>
<body >
<div class="content-register pb-4" style="margin-top: 56px;">
		<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-4 frm-register mt-5">
				<div
					class="frm-register-header row text-center mt-4 justify-content-center"
					id="buttonRow">
					<button class="col-md-5 bg-white">
						<a href="login">Đăng nhập</a>
					</button>
					<button class="col-md-5 bg-white highlight">
						<a href="register">Đăng ký</a>
					</button>
				</div>
				<c:if test="${not empty message}">
					<div style="text-align:center; color: green; font-size: 16px; font-weight: bold;" class="alert alert-${alert}">${message}</div>
				</c:if>
				<form action="register" class="mt-2" method="post">
					<div class="frm-register-content row justify-content-center">
						<div class="col-md-10 mb-4">
							<div class="form-floating">
								<input type="text" class="form-control" name="fullName"
									id="fullName" placeholder=""> <label for="fullName">Họ
									và tên (*)</label>
							</div>
							<c:if test="${not empty message_fullName}">
								<div style="padding: 0px;color: red;margin: 0;" class="alert alert-${alert}">${message_fullName}</div>
							</c:if>
						</div>
						
						<div class="col-md-10 mb-4">
							<div class="form-floating">
								<input type="text" class="form-control" name="dateView" id="daterange" placeholder="dd/mm/yyyy">
								<label for="dob">Ngày sinh</label>
							</div>
							<c:if test="${not empty message_dateOfBirth}">
								<div style="padding: 0px;color: red;margin: 0;" class="alert alert-${alert}">${message_dateOfBirth}</div>
							</c:if>
						</div>
						
						<div class="col-md-10 mb-4">
							<div class="form-floating ">
								<input type="tel" class="form-control" name="phone"
									placeholder=" "> <label for="phone">Số điện
									thoại</label>
							</div>
							<c:if test="${not empty mess_phone}">
								<div style="padding: 0px;color: red;margin: 0;" class="alert alert-${alert}">${mess_phone}</div>
							</c:if>
						</div>
						
						<div class="col-md-10 mb-4">
							<div class="form-floating">
								<input type="text" class="form-control" name="address"
									placeholder=" "> <label for="address">Địa chỉ</label>
							</div>
							<c:if test="${not empty message_address}">
								<div style="padding: 0px;color: red;margin: 0;" class="alert alert-${alert}">${message_address}</div>
							</c:if>
	
						</div>
						
						<div class="col-md-10 mb-4">
							<div class="form-floating ">
								<input type="email" class="form-control" name="email"
									placeholder=" "> <label for="email">Email (*)</label>
							</div>
							<c:if test="${not empty message_email}">
								<div style="padding: 0px;color: red;margin: 0;" class="alert alert-${alert}">${message_email}</div>
							</c:if>

						</div>
						
						<div class="col-md-10 mb-4">
							<div class="form-floating">
								<input type="password" class="form-control" name="password"
									placeholder=" "> <label for="password">Mật khẩu
									(*)</label>
							</div>
							<c:if test="${not empty message_password}">
								<div style="padding: 0px;color: red;margin: 0;" class="alert alert-${alert}">${message_password}</div>
							</c:if>
						</div>
						
						<div class="col-md-10 mb-4">
							<div class="form-floating">
								<input type="password" class="form-control"
									name="repeat_password" placeholder=" "> <label
									for="password">Nhập lại mật khẩu (*)</label>
							</div>
							<c:if test="${not empty message_repeat_password}">
								<div style="padding: 0px;color: red;margin: 0;" class="alert alert-${alert}">${message_repeat_password}</div>
							</c:if>
						</div>
						
						<div class="frm-register-footer col-md-12 text-center">
							<p>
								Bạn đã có tài khoản? <a href="login"
									class="text-primary text-decoration-none">Đăng nhập</a>
							</p>
							<button style="" type="submit" class="btn mb-2"
								name="click_true">Đăng ký</button>
						</div>
					</div>
				</form>
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
		
		<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
	<script>
		$(document).ready(function() {
			$('#daterange').datepicker({
				format : 'dd/mm/yyyy',
				autoclose : true
			});
		});

		const message = "${message}";
		var hasMessage = message != "";
		console.log(hasMessage);
		if (hasMessage === true) {
			alert(message);
		}
	</script>
</body>
</html>