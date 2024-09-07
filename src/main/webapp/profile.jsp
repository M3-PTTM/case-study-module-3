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
</head>
<body>
<c:if test="${not empty errorMessage}">
    <div class="alert alert-danger">${errorMessage}</div>
</c:if>
<div class="container mt-5">
    <div class="row">
        <div class="col-md-8 offset-md-2">
            <h2 class="text-center mb-4">Thông Tin Người Dùng</h2>
            <form action="profile" method="post" onsubmit="return validateProfileForm()">
                <div class="mb-3">
                    <label for="username" class="form-label">Tên đăng nhập</label>
                    <input type="text" class="form-control" id="username" name="username" value="${customer.username}" readonly>
                </div>
                <div class="mb-3">
                    <label for="customer_name" class="form-label">Họ tên</label>
                    <input type="text" class="form-control" id="customer_name" name="customer_name" value="${customer.customer_name}" required>
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" name="customer_email" value="${customer.customer_email}" required>
                </div>
                <div class="mb-3">
                    <label for="phone" class="form-label">Số điện thoại</label>
                    <input type="text" class="form-control" id="phone" name="customer_phone" value="${customer.customer_phone}" required>
                </div>
                <div class="mb-3">
                    <label for="citizen" class="form-label">Căn cước công dân</label>
                    <input type="text" class="form-control" id="citizen" name="customer_citizen" value="${customer.customer_citizen}" readonly>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Mật khẩu mới</label>
                    <input type="password" class="form-control" id="password" name="password">
                </div>
                <button type="submit" class="btn btn-primary">Cập nhật</button>
            </form>
        </div>
    </div>
</div>
<script>
    function validateProfileForm() {
        let name = document.getElementById('customer_name').value.trim();
        let email = document.getElementById('email').value.trim();
        let phone = document.getElementById('phone').value.trim();
        let citizen = document.getElementById('citizen').value.trim();

        if (name === "") {
            alert("Họ và tên không được để trống");
            return false;
        }

        const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
        if (!emailPattern.test(email)) {
            alert("Email không hợp lệ");
            return false;
        }

        const phonePattern = /^[0-9]{10}$/;
        if (!phonePattern.test(phone)) {
            alert("Số điện thoại phải có 10 chữ số");
            return false;
        }

        const citizenPattern = /^[0-9]{12}$/;
        if (!citizenPattern.test(citizen)) {
            alert("Căn cước công dân phải có 12 chữ số");
            return false;
        }

        return true;
    }
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

