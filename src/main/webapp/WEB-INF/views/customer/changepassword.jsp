<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.laptrinhjavaweb.utils.SecurityUtils"%>
<title>Đổi mật khẩu</title>
<body>
	<div class="content-changepassword" style="margin-top: 50px; height: auto; ">
		<div class="row w-100 justify-content-center">
			<div class="col-md-4 frm-changepasswork mt-5 mb-5">
				<form action="changepassword" class="mt-4 " method="post">
					<div class="frm-changepasswork-content row justify-content-center">
						<div class="col-md-10">
							<div class="col-auto text-center mb-4 mt-2">
								<h2 style="color: #45A29E;">Đổi mật khẩu</h2>
							</div>
						</div>
	
						<div class="col-md-10">
							<div class="col-auto text-center mb-4 mt-2">
								<p style="color: #246d69;">Đổi mật khẩu tài khoản của bạn.</p>
							</div>
						</div>

						<div class="col-md-10">
							<div class="form-floating mb-4">
								<input type="email" class="form-control" name="email" id="email"
									placeholder="Email" disabled="disabled"> <label
									for="email">${ SecurityUtils.getPrincipal().getEmail()}</label>
							</div>
						</div>
						<div class="col-md-10">
							<div class="form-floating mb-3">
								<input type="password" class="form-control" name="oldPassword"
									id="password" placeholder=" "> <label for="password">Mật
									khẩu cũ</label>
								<c:if test="${ not empty alert_oldpass}">
									<p style="color: #f40505a1;">${alert_oldpass }</p>
								</c:if>

							</div>
						</div>
						<div class="col-md-10">
							<div class="form-floating mb-3">
								<input type="password" class="form-control" name="newPassword"
									id="newPassword" placeholder=" "> <label for="password">Mật
									khẩu mới</label>
									<c:if test="${ not empty alert_newpass}">
									<p style="color: #f40505a1;">${alert_newpass }</p>
								</c:if>
								<c:if test="${ not empty alert_pass}">
									<p style="color: #f40505a1;">${alert_pass }</p>
								</c:if>
							</div>
						</div>
						<div class="col-md-10">
							<div class="form-floating mb-3">
								<input type="password" class="form-control"
									name="confirmPassword" id="ConfirmPassword" placeholder=" ">
								<label for="password">Nhập lại mật khẩu mới</label>
								<c:if test="${ not empty alert_confirmpass}">
									<p style="color: #f40505a1;">${alert_confirmpass }</p>
								</c:if>
							</div>
						</div>

						<div
							class="frm-changepasswork-footer col-md-10 mb-3 mt-2 text-center">
							<button type="submit" class="btn">Đổi mật khẩu</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
		integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
		crossorigin="anonymous"></script>
</body>