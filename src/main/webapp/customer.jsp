<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:if test="${sessionScope.customer == null || sessionScope.customer.customer_role != 'ADMIN'}">
    <c:redirect url="/home"/>
</c:if>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, customer-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
          integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link rel="stylesheet" href="/phuc/css/admin.css">
    <link rel="stylesheet" href="/phuc/css/customer.css">
    <title>Khách hàng</title>
    <link rel="icon" href="/man/images/logo.png" type="image/x-icon">
</head>
<body>
<div class="d-flex" id="wrapper">
    <div class="bg-white" id="sidebar-wrapper">
        <div class="sidebar-heading text-center py-4 primary-text fs-4 fw-bold border-bottom">
            <a href="/home"><img src="/man/images/logo.png"></a>
        </div>
        <div class="list-group list-group-flush my-3">
            <a href="dashboard.jsp" class="list-group-item list-group-item-action bg-transparent second-text fw-bold ">
                <i class="fas fa-tachometer-alt me-2"></i> Tổng quan
            </a>
            <a href="/product" class="list-group-item list-group-item-action bg-transparent second-text fw-bold">
                <i class="fas fa-box me-2"></i> Sản phẩm
            </a>
            <a href="/customers-servlet"
               class="list-group-item list-group-item-action bg-transparent second-text fw-bold active">
                <i class="fas fa-user-circle me-2"></i> Khách hàng
            </a>
            <a href="/orders" class="list-group-item list-group-item-action bg-transparent second-text fw-bold">
                <i class="fas fa-shopping-cart me-2"></i> Đơn hàng
            </a>
            <a href="#" class="list-group-item list-group-item-action bg-transparent second-text fw-bold">
                <i class="fas fa-comment-dots me-2"></i> Đánh giá
            </a>
        </div>
    </div>
    <div id="page-content-wrapper">
        <nav class="navbar navbar-expand-lg navbar-light bg-transparent py-4 px-4">
            <div class="d-flex align-items-center">
                <i class="fas fa-align-left primary-text fs-4 me-3" id="menu-toggle"></i>
                <h2 class="fs-2 m-0">Khách hàng</h2>
            </div>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                    <li class="nav-item-dropdown">
                        <a href="#" class="nav-link dropdown-toggle second-text fw-bold" id="navbarDropdown"
                           role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            <i class="fas fa-customer me-2"></i> Admin
                        </a>
                        <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                            <li><a href="#" class="dropdown-item">Thông tin</a></li>
                            <li><a href="#" class="dropdown-item">Cài đặt</a></li>
                            <li><a href="#" class="dropdown-item">Đăng xuất</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </nav>
        <div class="container-fluid px-4">
            <div class="row my-5">
                <div class="col">
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <h2>Danh sách khách hàng</h2>
                        <button class="btn btn-primary" id="addCustomerBtn">Thêm mới</button>
                    </div>
                    <table class="table bg-white rounded shadow-sm table-hover">
                        <thead>
                        <tr>
                            <th scope="col">Số thứ tự</th>
                            <th scope="col">Tên đăng nhập</th>
                            <th scope="col">Họ và tên</th>
                            <th scope="col">Email</th>
                            <th scope="col">Số điện thoại</th>
                            <th scope="col">Căn cước công dân</th>
                            <th scope="col">Vai trò</th>
                            <th scope="col">Hành động</th>
                        </tr>
                        </thead>
                        <tbody id="customerTable">
                        <c:forEach var="customer" items="${listCustomers}">
                            <tr>
                                <th scope="row">${customer.customer_id}</th>
                                <td>${customer.username}</td>
                                <td>${customer.customer_name}</td>
                                <td>${customer.customer_email}</td>
                                <td>${customer.customer_phone}</td>
                                <td>${customer.customer_citizen}</td>
                                <td>${customer.customer_role}</td>
                                <td>
                                    <c:if test="${customer.customer_role == 'CUSTOMER'}">
                                        <button class="btn btn-warning btn-sm"
                                                onclick="editCustomer('${customer.customer_id}', '${customer.username}', '${customer.customer_name}', '${customer.customer_email}', '${customer.customer_phone}', '${customer.customer_citizen}', '${customer.customer_role}')">
                                            Sửa
                                        </button>
                                        <form action="customers-servlet" method="post" style="display:inline-block;">
                                            <input type="hidden" name="action" value="delete">
                                            <input type="hidden" name="customer_id" value="${customer.customer_id}">
                                            <button type="submit" class="btn btn-danger btn-sm">Xóa</button>
                                        </form>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div id="customerFormModal" class="modal" tabindex="-1">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Thêm Khách Hàng Mới</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form id="customerForm" action="customers-servlet" method="post" novalidate>
                            <div class="mb-3">
                                <label for="username" class="form-label">Tên đăng nhập</label>
                                <input type="text" class="form-control" id="username" name="username" required>
                                <span class="error-message text-danger" id="username-error"></span>
                            </div>
                            <div class="mb-3">
                                <label for="customer_name" class="form-label">Họ và tên</label>
                                <input type="text" class="form-control" id="customer_name" name="customer_name"
                                       required>
                                <span class="error-message text-danger" id="customer_name-error"></span>
                            </div>
                            <div class="mb-3">
                                <label for="customer_email" class="form-label">Email</label>
                                <input type="email" class="form-control" id="customer_email" name="customer_email"
                                       required>
                                <span class="error-message text-danger" id="customer_email-error"></span>
                            </div>
                            <div class="mb-3">
                                <label for="customer_phone" class="form-label">Số điện thoại</label>
                                <input type="text" class="form-control" id="customer_phone" name="customer_phone"
                                       required>
                                <span class="error-message text-danger" id="customer_phone-error"></span>
                            </div>
                            <div class="mb-3">
                                <label for="customer_citizen" class="form-label">Căn cước công dân</label>
                                <input type="text" class="form-control" id="customer_citizen" name="customer_citizen"
                                       required>
                                <span class="error-message text-danger" id="customer_citizen-error"></span>
                            </div>
                            <button type="submit" class="btn btn-primary">Thêm mới</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div id="editCustomerFormModal" class="modal" tabindex="-1">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Thông tin khách hàng</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form id="editCustomerForm" action="customers-servlet" method="post">
                            <input type="hidden" id="editCustomerId" name="customer_id">
                            <input type="hidden" name="action" value="update">
                            <div class="mb-3">
                                <label for="username" class="form-label">Tên đăng nhập</label>
                                <input type="text" class="form-control" id="edit_username" name="username" required>
                            </div>
                            <div class="mb-3">
                                <label for="edit_customer_name" class="form-label">Họ và tên</label>
                                <input type="text" class="form-control" id="edit_customer_name" name="customer_name"
                                       required>
                            </div>
                            <div class="mb-3">
                                <label for="edit_customer_email" class="form-label">Email</label>
                                <input type="email" class="form-control" id="edit_customer_email" name="customer_email"
                                       required>
                            </div>
                            <div class="mb-3">
                                <label for="edit_customer_phone" class="form-label">Số điện thoại</label>
                                <input type="text" class="form-control" id="edit_customer_phone" name="customer_phone"
                                       required>
                            </div>
                            <div class="mb-3">
                                <label for="edit_customer_citizen" class="form-label">Căn cước công dân</label>
                                <input type="text" class="form-control" id="edit_customer_citizen"
                                       name="customer_citizen" required>
                            </div>
                            <div class="mb-3">
                                <label for="edit_customer_role" class="form-label">Vai trò</label>
                                <select id="edit_customer_role" name="customer_role" class="form-select">
                                    <option value="CUSTOMER">Khách hàng</option>
                                    <option value="CUSTOMER-VIP">Khách hàng VIP</option>
                                </select>
                            </div>
                            <button type="submit" class="btn btn-primary">Lưu</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>

<!-- Thêm Bootstrap và jQuery vào trang -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<script src="./phuc/js/admin.js"></script>
<script src="./phuc/js/customer.js"></script>
</body>
</html>