<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng ký</title>
</head>
<body >
<div class="content-register" style="z-index: 100000">
		<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-4 frm-register mt-5">
				<div
					class="frm-register-header row text-center mt-4 justify-content-center"
					id="buttonRow">
					<button class="col-md-5 bg-white">
						<a href="logIn.html">Đăng nhập</a>
					</button>
					<button class="col-md-5 bg-white highlight">
						<a href="<c:url value='/dangki'/>">Đăng ký</a>
					</button>
				</div>
				<c:if test="${not empty message}">
					<div style="color: red;margin: 0;height: 20px;" class="alert alert-${alert}">${message}</div>
				</c:if>
				<form action="<c:url value='/dangki'/>" class="mt-4" method="post">
					<div class="frm-register-content row justify-content-center">
						<div class="col-md-10">
							<div class="form-floating mb-3">
								<input type="text" class="form-control" name="fullName"
									id="fullName" placeholder=""> <label for="fullName">Họ
									và tên (*)</label>
							</div>
						</div>
						<c:if test="${not empty message_fullName}">
							<div style="color: red;margin: 0;height: 20px;" class="alert alert-${alert}">${message_fullName}</div>
						</c:if>
						<div class="col-md-10">
							<div class="form-floating mb-3">
								<input type="date" class="form-control" name="dateView" id="dob">
								<label for="dob">Ngày sinh</label>
							</div>
						</div>
						<c:if test="${not empty message_dateOfBirth}">
							<div style="color: red;margin: 0;height: 20px;" class="alert alert-${alert}">${message_dateOfBirth}</div>
						</c:if>
						<div class="col-md-10">
							<div class="form-floating mb-3">
								<input type="tel" class="form-control" name="phone"
									placeholder=" "> <label for="phone">Số điện
									thoại</label>
							</div>
						</div>
						<c:if test="${not empty mess_phone}">
							<div style="color: red;margin: 0;height: 20px;" class="alert alert-${alert}">${mess_phone}</div>
						</c:if>
						<div class="col-md-10">
							<div class="form-floating mb-3">
								<input type="text" class="form-control" name="address"
									placeholder=" "> <label for="address">Địa chỉ</label>
							</div>

						</div>
						<c:if test="${not empty message_address}">
							<div style="color: red;margin: 0;height: 20px;" class="alert alert-${alert}">${message_address}</div>
						</c:if>
						<div class="col-md-10">
							<div class="form-floating mb-3">
								<input type="email" class="form-control" name="email"
									placeholder=" "> <label for="email">Email (*)</label>
							</div>

						</div>
						<c:if test="${not empty message_email}">
							<div style="color: red;margin: 0;height: 20px;" class="alert alert-${alert}">${message_email}</div>
						</c:if>
						<div class="col-md-10">
							<div class="form-floating mb-3">
								<input type="password" class="form-control" name="password"
									placeholder=" "> <label for="password">Mật khẩu
									(*)</label>
							</div>

						</div>
						<c:if test="${not empty message_password}">
							<div style="color: red;margin: 0;height: 20px;" class="alert alert-${alert}">${message_password}</div>
						</c:if>
						<div class="col-md-10">
							<div class="form-floating mb-3">
								<input type="password" class="form-control"
									name="repeat_password" placeholder=" "> <label
									for="password">Nhập lại mật khẩu (*)</label>
							</div>
						</div>
						<c:if test="${not empty message_repeat_password}">
							<div style="color: red ;margin: 0;height: 20px;" class="alert alert-${alert}">${message_repeat_password}</div>
						</c:if>
						<div class="frm-register-footer col-md-12 text-center">
							<p>
								Bạn đã có tài khoản? <a href="#"
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
</body>
</html>