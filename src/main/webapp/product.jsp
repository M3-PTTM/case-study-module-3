<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:if test="${sessionScope.customer == null || sessionScope.customer.customer_role != 'ADMIN'}">
    <c:redirect url="/home"/>
</c:if>
<!doctype html>
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
    <title>Sản Phẩm</title>
    <link rel="icon" href="/man/images/logo.png" type="image/x-icon">
    <style>
        input {
            border: none;
            outline: none;
        }
    </style>
</head>
<body>
<div class="d-flex" id="wrapper">
    <div class="bg-white" id="sidebar-wrapper">
        <div class="sidebar-heading text-center py-4 primary-text fs-4 fw-bold border-bottom">
            <a href="/home"><img src="/man/images/logo.png"></a>
        </div>
        <div class="list-group list-group-flush my-3">
            <a href="dashboard.jsp" class="list-group-item list-group-item-action bg-transparent second-text fw-bold">
                <i class="fas fa-tachometer-alt me-2"></i> Tổng quan
            </a>
            <a href="/product" class="list-group-item list-group-item-action bg-transparent second-text fw-bold active">
                <i class="fas fa-box me-2"></i> Sản phẩm
            </a>
            <a href="customers-servlet" class="list-group-item list-group-item-action bg-transparent second-text fw-bold">
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
                <h2 class="fs-2 m-0">Sản phẩm</h2>
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
                        <h2>Danh sách sản phẩm</h2>
                        <button class="btn btn-primary" id="addProductBtn">Thêm mới</button>
                    </div>
                    <table class="table bg-white rounded shadow-sm table-hover">
                        <thead>
                        <tr>
                            <th scope="col">Mã sản phẩm</th>
                            <th scope="col">Tên sản phẩm</th>
                            <th scope="col">Giá tiền</th>
                            <th scope="col">Loại sản phẩm</th>
                            <th scope="col">Số lượng</th>
                            <th scope="col">Hình ảnh</th>
                            <th scope="col">Mô tả</th>
                            <th scope="col"></th>
                        </tr>
                        </thead>
                        <tbody id="productTable">
                        <c:forEach var="product" items="${products}" varStatus="status">
                            <tr>
                                <form action="/product" method="post">
                                    <input name="action" value="edit" type="hidden">
                                    <input name="id" value="${product.id}" type="hidden">
                                    <th scope="row">${status.count}</th>
                                    <td><input data-button="button${status.count}" type="text" name="name"
                                               value="${product.name}"></td>
                                    <td><input data-button="button${status.count}" type="text" name="price"
                                               value="<fmt:formatNumber value="${product.price}" pattern="###,### VND"/>">
                                    </td>
                                    <td><select name="category" onchange="handleInputChange(this)"
                                                data-button="button${status.count}">
                                        <option value="Model-F" ${product.category == "Model-F" ? "SELECTED" : ""}>
                                            Model-F
                                        </option>
                                        <option value="Model-G" ${product.category == "Model-G" ? "SELECTED" : ""}>
                                            Model-G
                                        </option>
                                        <option value="Model-T" ${product.category == "Model-T" ? "SELECTED" : ""}>
                                            Model-T
                                        </option>
                                    </select></td>
                                    <td><input data-button="button${status.count}" type="text" name="inventory"
                                               value="${product.inventory}"></td>
                                    <td><input data-button="button${status.count}" type="text" name="img"
                                               value="${product.img}"></td>
                                    <td><input data-button="button${status.count}" type="text" name="description"
                                               value="${product.description}"></td>
                                    <td>
                                        <button id="button${status.count}" class="btn btn-warning btn-sm" disabled>Lưu
                                        </button>
                                </form>
                                <a href="/product?action=delete&id=${product.id}"
                                   class="btn btn-danger btn-sm">Xóa</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div id="productFormModal" class="modal" tabindex="-1">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Thêm Sản Phẩm Mới</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form id="productForm" action="/product" method="post">
                            <input type="hidden" name="action" value="add">
                            <div class="mb-3">
                                <label for="productName" class="form-label">Tên sản phẩm</label>
                                <input type="text" class="form-control" id="productName" name="name" required>
                            </div>
                            <div class="mb-3">
                                <label for="productPrice" class="form-label">Giá tiền</label>
                                <input type="number" class="form-control" id="productPrice" name="price" value="0">
                            </div>
                            <div class="mb-3">
                                <label for="productType" class="form-label">Loại sản phẩm</label>
                                <select name="category" class="form-control" id="productType" required>
                                    <option value="Model-F">
                                        Model-F
                                    </option>
                                    <option value="Model-G">
                                        Model-G
                                    </option>
                                    <option value="Model-T">
                                        Model-T
                                    </option>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="productImage" class="form-label">Hình ảnh</label>
                                <input type="text" class="form-control" id="productImage" name="img">
                            </div>
                            <div class="mb-3">
                                <label for="productDescription" class="form-label">Mô tả</label>
                                <textarea class="form-control" id="productDescription" rows="3"
                                          name="description"></textarea>
                            </div>
                            <div class="mb-3">
                                <label for="productInventory" class="form-label">Số Lượng</label>
                                <input type="number" class="form-control" id="productInventory" name="inventory"
                                       value="0">
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
<script src="/phuc/js/admin.js"></script>
<script src="/phuc/js/product.js"></script>
<script>
    function handleInputChange(element) {
        const buttonId = element.getAttribute('data-button');
        const button = document.getElementById(buttonId);
        button.style.backgroundColor = 'red';
        button.disabled = false;
    }

    document.querySelectorAll('input[type="text"], select').forEach(input => {
        input.addEventListener('input', (event) => handleInputChange(event.target));
    });
</script>
</body>
</html>
