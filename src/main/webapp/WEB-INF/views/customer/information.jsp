<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="com.laptrinhjavaweb.utils.SecurityUtils" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thông tin cá nhân</title>
</head>

<body class="bg-dark">
    <div class="content-information" style="height: auto;margin-top: 50px;" >
        <div class="d-flex justify-content-center">
            <div class="col-md-4 frm-changepasswork mt-5 mb-5">
            	<c:if test="${not empty message}">
					<%-- <div style="color: red;margin: 0;height: 20px;" class="alert alert-${alert}">${message}</div> --%>
					<script>alert('cập nhật thành công!'); window.location.href = './index'; </script>
					<!-- <script> alert('Cập nhật thành công!');
						setTimeout(function() {
					        window.close();
					    }, 2);
					</script> -->
				</c:if>
				
                <form action="editUser" class="mt-4" method="get">
                    <div class="frm-information-content row justify-content-center">
                        <div class="col-md-10">  
                            <div class="col-auto text-center mb-4 mt-2">
                                <h3 class="text-color-main-100">Thông tin cá nhân</h3>
                            </div>
                        </div>
                        <div class="col-md-10">
                            <div class="form-floating mb-2 mt-3">
                                <input type="email" class="form-control" value="${emailOriginal }" name="email" id="email" placeholder="" autofocus="autofocus">
                                <label for="email"> 
                                	Email
                                </label>
                            </div>
                            <c:if test="${not empty message_email}">
								<div style="padding: 0px;color: red;margin: 0;" class="alert alert-${alert}">${message_email}</div>
							</c:if>
                        </div>        
                        <div class="col-md-10">
                            <div class="form-floating mb-2 mt-3">
                                <input type="text" class="form-control" name="fullName" value="${ht }" id="name" placeholder="">
                                <label for="name">Họ và tên</label>
                            </div>
                            <c:if test="${not empty message_fullName}">
								<div style="padding: 0px;color: red;margin: 0;" class="alert alert-${alert}">${message_fullName}</div>
							</c:if>
                        </div>        
                        <div class="col-md-10">
                            <div class="form-floating mb-2 mt-3">
                                <input type="text" class="form-control"  name="dateOfBirth" value="${date }" id="dob" placeholder="${dobOriginal }">
                                <label for="dob">Ngày sinh</label>
                            </div>
                            <c:if test="${not empty message_dateOfBirth}">
								<div style="padding: 0px;color: red;margin: 0;" class="alert alert-${alert}">${message_dateOfBirth}</div>
							</c:if>
                        </div>
                        <div class="col-md-10">
                        
                            <div class="form-floating mb-2 mt-3">
                                <input type="tel" class="form-control" name="phone" value="${sdt }" id="phone" placeholder=" ${phoneOriginal }">
                                <label for="phone">Số điện thoại</label>
                            </div>
                            <c:if test="${not empty mess_phone}">
								<div style="padding: 0px;color: red;margin: 0;" class="alert alert-${alert}">${mess_phone}</div>
							</c:if>
                        </div>
                        <div class="frm-information-footer col-md-10 mb-3 mt-4 text-center">
                            <button type="submit" class="btn">Cập nhật</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
	
</body>
</html>