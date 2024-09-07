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
<div class="container">
    <h2>Thông Tin Người Dùng</h2>
    <form action="profile" method="post">
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
</body>
</html>

