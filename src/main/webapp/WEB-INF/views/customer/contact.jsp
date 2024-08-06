<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liên hệ</title>
</head>
<body>
	<div class="contact-content" style="margin-top: 50px;">
        <div class="container" style="background-color: rgb(227 227 243);">
            <div class="row">
                <div class="col-sm-5 mt-5" >
                    <h3 class="text-center mb-3" style="color: #45A29E;">THÔNG TIN</h3>
                    <div class="row">
                        <div class="col-sm-3">
                        
                        </div>
                        <div class="col-sm-9">
                            <p class="fw-bold">Hotline: <span class="fw-light">19001722</span></p>
                            <p class="fw-bold">TRỤ SỞ CHÍNH: <span class="fw-light">39 TRẦN KHÁNH DƯ, PHƯỜNG TÂN LỢI, THÀNH PHỐ BUÔN MA THUỘT, TỈNH DĂK LĂK, VIỆT NAM</span></p>
                            <p class="fw-bold">Web: <span class="fw-light">www.starlight.vn</span></p>
                            <p class="fw-bold">E-mail: <span class="fw-light">support@starlight.vn</span></p>

                        </div>
                    
                    </div>
                </div>
                <div class="col-sm-7 mt-5">
                    <h3 class="text-center" style="color: #45A29E;">GỬI LIÊN HỆ</h3>
                    <form action="#" class="mt-4">
                        <div class="frm-contact-content row justify-content-center">
                            <div class="col-md-10">
                                <div class="form-floating mb-3">
                                    <input type="text" class="form-control" name="fullName"  id="fullName" placeholder=" ">
                                    <label for="fullName">Họ tên</label>
                                </div>
                            </div>

                            <div class="col-md-10">
                                <div class="form-floating mb-3">
                                    <input type="email" class="form-control" name="email" id="email" placeholder=" ">
                                    <label for="email">Email</label>
                                </div>
                            </div>
    
                            <div class="col-md-10">
                                <div class="form-floating mb-3">
                                    <input type="text" class="form-control" name="phone"  id="phone" placeholder=" ">
                                    <label for="phone">Số điện thoại</label>
                                </div>
                            </div>
                            <div class="col-md-10">
                                <div class="form-floating mb-3">
                                    <textarea type="text" class="form-control" name="contactContent"  id="contactContent" placeholder=" "></textarea>
                                    <label for="">Nội dung cần liên hệ...</label>
                                </div>
                            </div>

                            <div class="frm-contact-footer col-md-12 text-center">
                                <button type="submit" class="btn mb-5 text-white fw-bold">Send</button>
                            </div>
                        </div>
                    </form>
    
                </div>

            </div>
        </div>
        
    </div>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
    <script>
    // Lắng nghe sự kiện change của combobox
    document.getElementById('ngaychieu').addEventListener('change', function() {
        // Lấy giá trị đã chọn từ combobox
        var selectedValue = this.value;
        // Kiểm tra nếu giá trị đã chọn là ngày 8/4/2024 thì mở modal
        if (selectedValue === 'ngay1') {
            var myModal = new bootstrap.Modal(document.getElementById('exampleModal'));
            myModal.show();
        }
    });
</script>
</body>
</html>