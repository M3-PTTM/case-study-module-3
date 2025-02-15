<%--
  Created by IntelliJ IDEA.
  User: tienm
  Date: 9/7/2024
  Time: 10:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Thông Tin Người Dùng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="./phuc/css/profile.css">
</head>
<body>
<div class="container mt-5">
    <div class="row">
        <div class="col-md-8 offset-md-2">
            <h2 class="text-center mb-4">Thông Tin Người Dùng</h2>
            <form action="profile" method="post" onsubmit="return validateProfileForm()">
                <div class="mb-3">
                    <label for="username" class="form-label">Tên đăng nhập</label>
                    <input type="text" class="form-control" id="username" name="username" value="${customer.username}"
                           readonly>
                </div>
                <div class="mb-3">
                    <label for="customer_name" class="form-label">Họ tên</label>
                    <input type="text" class="form-control" id="customer_name" name="customer_name"
                           value="${customer.customer_name}">
                    <div class="invalid-feedback">
                        Họ và tên không được để trống.
                    </div>
                </div>

                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="text" class="form-control" id="email" name="customer_email"
                           value="${customer.customer_email}">
                    <div class="invalid-feedback">
                        Email không hợp lệ.
                    </div>
                </div>

                <div class="mb-3">
                    <label for="phone" class="form-label">Số điện thoại</label>
                    <input type="text" class="form-control" id="phone" name="customer_phone"
                           value="${customer.customer_phone}">
                    <div class="invalid-feedback">
                        Số điện thoại không hợp lệ. Số điện thoại phải có 10 chữ số.
                    </div>
                </div>
                <div class="mb-3">
                    <label for="citizen" class="form-label">Căn cước công dân</label>
                    <input type="text" class="form-control" id="citizen" name="customer_citizen"
                           value="${customer.customer_citizen}" readonly>
                </div>
                <div class="mb-3">
                    <a href="forgotpassword.jsp" class="btn btn-secondary btn-left">Thay đổi mật khẩu</a>
                </div>
                <br><br>
                <div class="mb-3" text-center>
                    <button type="submit" class="btn btn-primary btn-center">Cập nhật</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    function validateProfileForm() {
        let valid = true;
        let name = document.getElementById('customer_name');
        let email = document.getElementById('email');
        let phone = document.getElementById('phone');

        name.classList.remove('is-invalid');
        email.classList.remove('is-invalid');
        phone.classList.remove('is-invalid');

        if (name.value.trim() === "") {
            name.classList.add('is-invalid');
            valid = false;
        }

        const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
        if (!emailPattern.test(email.value.trim())) {
            email.classList.add('is-invalid');
            valid = false;
        }

        const phonePattern = /^[0-9]{10}$/;
        if (!phonePattern.test(phone.value.trim())) {
            phone.classList.add('is-invalid');
            valid = false;
        }

        return valid;
    }
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>


