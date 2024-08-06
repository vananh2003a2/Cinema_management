<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Đăng nhập</title>

</head>

<body class="">
	<div class="content-login" style="margin-top: 56px">
		<div class="d-flex justify-content-center">
			<div class="col-md-4 frm-login mt-5">
				<div
					class="frm-login-header row text-center mt-4 justify-content-center"
					id="buttonRow">
					<button class="col-md-5 bg-white highlight">
						<a href="login">Đăng nhập</a>
					</button>
					<button class="col-md-5 bg-white ">
						<a href="register">Đăng ký</a>
					</button>
				</div>
				<c:if test="${param.incorrectAccount != null}">
					<div class="alert alert-danger">Email hoặc mật khẩu sai</div>
				</c:if>
				<c:if test="${param.notPermission != null}">
					<div class="alert alert-danger">Tài khoản đang bị khóa</div>
				</c:if>
				<form action="j_spring_security_check" class="mt-4" method="post"
					id="LoginForm">
					<div class="frm-login-content row justify-content-center">
						<!-- <div class="col-md-10">
                            <div class="form-floating mb-3 mt-2">
                                    <div class="col-auto text-center">
                                        <img class="icon" src="./assets/img/logo/Social Icons.png" alt="no-logo">
                                        <a href="#" class="btn btn-block">Đăng nhập bằng Google</a>
                                    </div>
                            </div>
                        </div> -->

						<div class="col-md-10">
							<div class="form-floating mb-3">
								<input type="email" class="form-control" name="j_username"
									id="email" placeholder=" " required> <label for="email">Email
									(*)</label>
							</div>
						</div>
						<div class="col-md-10">
							<div class="form-floating mb-3">
								<input type="password" class="form-control" name="j_password"
									id="password" placeholder=" " required> <label
									for="password">Mật khẩu (*)</label>
							</div>
						</div>

						<div class="form-check mb-3 col-md-9">
							<input type="checkbox" class="form-check-input" id="remember-me">
							<label for="remember-me" class="form-check-label">Ghi nhớ
								tài khoản</label>
						</div>

						<div class="col-md-10" style="text-align: right;">
							<p>
								<a href="./forgotPassword.html"
									class="text-secondary text-decoration-none">Bạn đã có tài khoản chưa?</a>
								<a href="register"
									class="text-primary text-decoration-none">Đăng ký</a>
							</p>
						</div>
						<div class="frm-login-footer col-md-12 text-center">
							<button type="submit" class="btn mb-2">Đăng nhập</button>
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