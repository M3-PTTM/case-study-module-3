<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:if test="${sessionScope.customer == null}">
    <c:redirect url="/home"/>
</c:if>
<!DOCTYPE html>
<html lang="en">
<c:import url="/man/vnpay_jsp/head.jsp"/>
<body>
<div class="header_section header_bg">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a href="/home" class="logo"><img src="/man/images/logo.png"></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon text-success"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="home">Trang Chủ</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Thông Tin</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Tin Mới</a>
                </li>
                <c:choose>
                    <c:when test="${sessionScope.customer.customer_role=='ADMIN'}">
                        <li class="nav-item">
                            <a class="nav-link" href="#">Quản Lý</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Liên Hệ</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
            <div class="form-inline my-2 my-lg-0">
                <div class="login_menu">
                    <ul>
                        <c:if test="${sessionScope.customer != null}">
                            <li><a href="/logout">Đăng Xuất</a></li>
                        </c:if>
                        <c:if test="${sessionScope.customer == null}">
                            <li><a href="/login.jsp">Đăng Nhập</a></li>
                        </c:if>
                        <li><a href="/home?action=cart"><img src="/man/images/trolly-icon.png"></a></li>
                        <li>
                            <form method="get" action="/search"><input class="form-control me-2" type="search"
                                                                       placeholder="Search"
                                                                       aria-label="Search" name="search"
                                                                       oninput="searchByNameOrModel(this)">
                                <button class="btn btn-outline-success" type="submit"><img
                                        src="/man/images/search-icon.png">
                                </button>
                            </form>
                        </li>
                        <c:if test="${sessionScope.customer != null}">
                            <li><a href="#">Xin Chào ${sessionScope.customer.username}</a></li>
                        </c:if>
                    </ul>
                </div>
            </div>
        </div>
        <div id="main">
            <span style="font-size:36px;cursor:pointer; color: #fff" onclick="openNav()"><img
                    src="/man/images/toggle-icon.png" style="height: 30px;"></span>
        </div>
    </nav>
    <div class="banner_section layout_padding">
        <div id="main_slider" class="carousel slide" data-ride="carousel">
            <div class="carousel-inner">
                <c:forEach var="product" items="${products}" varStatus="status">
                    <c:choose>
                        <c:when test="${status.count==1}">
                            <div class="carousel-item active">
                                <div class="container">
                                    <div class="row">
                                        <div class="col-md-7">
                                            <a href="/home?action=view&id=${product.id}">
                                                <div class="best_text">Mới</div>
                                                <div class="image_1"><img src="/man/images/${product.img}"></div>
                                            </a>
                                        </div>
                                        <div class="col-md-5">
                                            <a href="/home?action=view&id=${product.id}"><h1 class="banner_taital">Mô
                                                Hình Mới</h1>
                                                <p class="banner_text">${product.description}</p></a>
                                            <div class="contact_bt"><a href="#" class="buy-now" data-id="${product.id}">Giỏ
                                                Hàng</a></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="carousel-item">
                                <div class="container">
                                    <div class="row">
                                        <div class="col-md-7">
                                            <a href="/home?action=view&id=${product.id}">
                                                <div class="best_text">Mới</div>
                                                <div class="image_1"><img src="/man/images/${product.img}"></div>
                                            </a>
                                        </div>
                                        <div class="col-md-5">
                                            <a href="/home?action=view&id=${product.id}"><h1 class="banner_taital">Mô
                                                Hình Mới</h1>
                                                <p class="banner_text">${product.description}</p></a>
                                            <div class="contact_bt"><a href="#" class="buy-now" data-id="${product.id}">Giỏ
                                                Hàng</a></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </div>
            <a class="carousel-control-prev" href="#main_slider" role="button" data-slide="prev">
                <i class="fa fa-angle-left"></i>
            </a>
            <a class="carousel-control-next" href="#main_slider" role="button" data-slide="next">
                <i class="fa fa-angle-right"></i>
            </a>
        </div>
    </div>
</div>
<div class="cycle_section layout_padding">
    <div class="container" id="content">
        <h1 class="cycle_taital">Giỏ Hàng</h1>
        <p class="cycle_text">Một thực tế đã được xác lập từ lâu là độc giả sẽ bị phân tâm bởi</p>
        <c:forEach var="product" items="${productsCart}" varStatus="status">
            <c:if test="${product.inventory>0}">
                <c:choose>
                    <c:when test="${status.count%2!=0}">
                        <div class="product cycle_section_3 layout_padding" id="product${product.id}">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="box_main_3">
                                        <a href="/home?action=view&id=${product.id}">
                                            <h6 class="number_text"></h6>
                                            <div class="image_2"><img src="/man/images/${product.img}"></div>
                                        </a>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <a href="/home?action=view&id=${product.id}"><h1
                                            class="cycles_text">${product.category} ${product.name}</h1>
                                        <p class="lorem_text">${product.description}</p></a>
                                    <div class="btn_main">
                                        <div class="buy_bt">
                                            <div class="quantity_selector">
                                                <button class="quantity_button"
                                                        onclick="decreaseQuantity(${product.id})">-
                                                </button>
                                                <input type="number" id="quantity${product.id}"
                                                       value="${product.quantity}"
                                                       min="1"
                                                       max="${product.inventory}" class="quantity_input"
                                                       onchange="updateTotalPrice()" data-min="1"
                                                       data-max="${product.inventory}"/>
                                                <button class="quantity_button"
                                                        onclick="increaseQuantity(${product.id})">+
                                                </button>
                                            </div>
                                        </div>
                                        <div class="buy_bt align-items-center pl-3">Sẳn
                                            có: ${product.inventory}</div>
                                        <h4 class="price_text">
                                            <span style=" color: #325662"><fmt:formatNumber
                                                    value="${product.price}" pattern="###,###"/></span>
                                            <span style=" color: #f7c17b"> VND</span>
                                        </h4>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="product cycle_section_3 layout_padding" id="product${product.id}">
                            <div class="row">
                                <div class="col-md-6">
                                    <a href="/home?action=view&id=${product.id}"><h1
                                            class="cycles_text">${product.category} ${product.name}</h1>
                                        <p class="lorem_text">${product.description}</p></a>
                                    <div class="btn_main">
                                        <div class="buy_bt">
                                            <div class="quantity_selector">
                                                <button class="quantity_button"
                                                        onclick="decreaseQuantity(${product.id})">-
                                                </button>
                                                <input type="number" id="quantity${product.id}"
                                                       value="${product.quantity}"
                                                       min="1"
                                                       max="${product.inventory}" class="quantity_input"
                                                       onchange="updateTotalPrice()" data-min="1"
                                                       data-max="${product.inventory}"/>
                                                <button class="quantity_button"
                                                        onclick="increaseQuantity(${product.id})">+
                                                </button>
                                            </div>
                                        </div>
                                        <div class="buy_bt align-items-center pl-3">Sẳn
                                            có: ${product.inventory}</div>
                                        <h4 class="price_text">
                                            <span style=" color: #325662"><fmt:formatNumber
                                                    value="${product.price}" pattern="###,###"/></span>
                                            <span style=" color: #f7c17b"> VND</span>
                                        </h4>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="box_main_3">
                                        <a href="/home?action=view&id=${product.id}">
                                            <h6 class="number_text"></h6>
                                            <div class="image_2"><img src="/man/images/${product.img}"></div>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </c:if>
        </c:forEach>
    </div>
</div>
<div class="client_section layout_padding">
    <div class="container">
        <div class="client_main">
            <h1 class="client_taital">Hóa Đơn</h1>
            <div class="client_section_2">
                <table style="width: 100%; table-layout: fixed; border-collapse: collapse;">
                    <tr>
                        <th style="width: 33.33%; text-align: center; border: 1px solid #ddd;">Tên Sản Phẩm</th>
                        <th style="width: 33.33%; text-align: center; border: 1px solid #ddd;">Số Lượng</th>
                        <th style="width: 33.33%; text-align: center; border: 1px solid #ddd;">Thành Giá</th>
                    </tr>
                    <c:forEach var="product" items="${productsCart}">
                        <c:if test="${product.inventory>0}">
                            <tr id="order${product.id}">
                                <th style="width: 33.33%; text-align: center; border: 1px solid #ddd;">${product.name}</th>
                                <th id="quantityCell${product.id}"
                                    style="width: 33.33%; text-align: center; border: 1px solid #ddd;">${product.quantity}</th>
                                <th style="width: 33.33%; text-align: center; border: 1px solid #ddd;"><span
                                        id="priceCell${product.id}"><fmt:formatNumber
                                        value="${product.price*product.quantity}" pattern="###,###"/></span> VND
                                </th>
                            </tr>
                        </c:if>
                    </c:forEach>
                    <form action="/vnpayajax" id="frmCreateOrder" method="post">
                        <input type="hidden" id="bankCode" name="bankCode" value="">
                        <input type="hidden" id="language" name="language" value="vn">
                        <input id="amount" name="amount" type="hidden" value="">
                        <tr>
                            <th colspan="2" style="text-align: center; border: 1px solid #ddd;">Tổng giá:</th>
                            <th style="text-align: center; border: 1px solid #ddd;"><span id="totalPrice">0</span> VND
                            </th>
                        </tr>
                        <tr>
                            <th colspan="3" style="text-align: center; border: 1px solid #ddd;">
                                <button class="btn btn-danger">Thanh Toán</button>
                            </th>
                        </tr>
                    </form>
                </table>
            </div>
        </div>
    </div>
</div>
<div id="notification"
     style="display: none; position: fixed; top: 10px; right: 10px; background-color: #4CAF50; color: white; padding: 15px; border-radius: 5px;">
    Sản phẩm đã được thêm vào giỏ hàng thành công!
</div>
<script src="https://www.gstatic.com/dialogflow-console/fast/messenger/bootstrap.js?v=1"></script>
<df-messenger
        intent="WELCOME"
        chat-title="Liên-Hệ"
        agent-id="8d2fb5cb-8310-4459-9162-d9bb468e135d"
        language-code="vi"
></df-messenger>
<link href="https://pay.vnpay.vn/lib/vnpay/vnpay.css" rel="stylesheet"/>
<script src="https://pay.vnpay.vn/lib/vnpay/vnpay.min.js"></script>
<c:import url="/man/library/add_product_cart.jsp"/>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        const inputs = document.querySelectorAll('input[type="number"]');
        inputs.forEach(input => {
            const min = parseFloat(input.getAttribute('min'));
            const max = parseFloat(input.getAttribute('max'));
            let value = parseFloat(input.value);
            if (value > max) {
                input.value = max;
            } else if (value < min) {
                input.value = min;
            }
            input.addEventListener('input', function () {
                value = parseFloat(input.value);
                if (isNaN(value)) {
                    input.value = min;
                } else if (value > max) {
                    input.value = max;
                } else if (value < min) {
                    input.value = min;
                }
            });
        });
        reloadQuantity();
        updateTotalPrice();
    });

    function reloadQuantity() {
        <c:forEach var="product" items="${productsCart}">
        var id = ${product.id};
        console.log(id);
        var quantityInput = document.getElementById('quantity' + id);
        console.log(quantityInput);
        var currentValue = parseInt(quantityInput.value);
        console.log(currentValue);
        $.ajax({
            url: "/cart",
            type: "post",
            data: {
                id: id,
                quantity: currentValue
            },
            success: function () {
            },
            error: function (xhr) {
            }
        });
        </c:forEach>
    }
</script>
<script>
    function decreaseQuantity(id) {
        var quantityInput = document.getElementById('quantity' + id);
        var currentValue = parseInt(quantityInput.value);
        if (currentValue > 1) {
            quantityInput.value = currentValue - 1;
            updateQuantity(id);
        } else if (currentValue === 1) {
            removeProduct(id);
        }
        updateTotalPrice();
    }

    function removeProduct(id) {
        var productElement = document.getElementById('order' + id);
        productElement.remove();
        $.ajax({
            url: "/cart",
            type: "get",
            data: {
                id: id
            },
            success: function () {
                location.reload(true);
            },
            error: function (xhr) {
            }
        });
    }

    function increaseQuantity(id) {
        var quantityInput = document.getElementById('quantity' + id);
        var currentValue = parseInt(quantityInput.value);
        const maxValue = quantityInput.getAttribute('max');
        if (currentValue < maxValue) {
            quantityInput.value = currentValue + 1;
            updateQuantity(id);
            updateTotalPrice();
        }
    }

    function updateQuantity(id) {
        var quantityInput = document.getElementById('quantity' + id);
        var currentValue = parseInt(quantityInput.value);
        console.log(currentValue);
        $.ajax({
            url: "/cart",
            type: "post",
            data: {
                id: id,
                quantity: currentValue
            },
            success: function () {
            },
            error: function (xhr) {
            }
        });
    }

    function updateTotalPrice() {
        var totalPrice = 0;
        <c:forEach var="product" items="${productsCart}">
        var quantity = document.getElementById('quantity${product.id}').value;
        var price = ${product.price};
        totalPrice += price * quantity;
        var formatter = new Intl.NumberFormat('vi-VN', {
            minimumFractionDigits: 0,
            maximumFractionDigits: 0
        });
        document.querySelector(`#quantityCell${product.id}`).innerHTML = quantity;
        document.querySelector(`#priceCell${product.id}`).innerHTML = formatter.format(price * quantity);
        </c:forEach>
        document.getElementById('totalPrice').innerHTML = formatter.format(totalPrice);
        document.getElementById('amount').value = totalPrice.toFixed(2);
    }

    updateTotalPrice();
</script>
<script>
    $("#frmCreateOrder").submit(function () {
        var postData = $("#frmCreateOrder").serialize();
        var submitUrl = $("#frmCreateOrder").attr("action");
        $.ajax({
            type: "POST",
            url: submitUrl,
            data: postData,
            dataType: 'JSON',
            success: function (x) {
                if (x.code === '00') {
                    if (window.vnpay) {
                        vnpay.open({width: 768, height: 600, url: x.data});
                    } else {
                        location.href = x.data;
                    }
                    return false;
                } else {
                    alert(x.Message);
                }
            }
        });
        return false;
    });
</script>
<style>
    .quantity_selector {
        display: flex;
        align-items: center;
        justify-content: center;
    }

    .quantity_button {
        display: flex;
        width: 34px;
        height: 34px;
        background-color: #325662;
        color: white;
        border: none;
        padding: 10px;
        cursor: pointer;
        font-size: 16px;
        align-items: center;
        justify-content: center;
    }

    .quantity_input {
        width: 60px;
        text-align: center;
        margin: 0 10px;
        font-size: 16px;
    }

    .quantity_input {
        width: 60px;
        text-align: center;
        margin: 0 10px;
        font-size: 16px;
        -webkit-appearance: none;
        -moz-appearance: textfield;
    }

    .quantity_input::-webkit-inner-spin-button,
    .quantity_input::-webkit-outer-spin-button {
        -webkit-appearance: none;
        margin: 0;
    }
</style>
<div class="footer_section layout_padding">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-8 col-sm-12 padding_0">
                <div class="map_main">
                    <div class="map-responsive">
                        <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3833.749168916386!2d108.20956419999999!3d16.078500899999998!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x314219a486b7f699%3A0xae6269b629a63e82!2zQ29kZUd5bSDEkMOgIE7hurVuZw!5e0!3m2!1svi!2s!4v1725580014381!5m2!1svi!2s"
                                width="600" height="450" style="border:0; width: 100%;" allowfullscreen=""
                                loading="lazy"
                                referrerpolicy="no-referrer-when-downgrade"></iframe>
                    </div>
                </div>
            </div>
            <div class="col-lg-4 col-sm-12">
                <div class="call_text"><a href="#"><img src="/man/images/map-icon.png"><span class="padding_left_0">Page when looking at its layou</span></a>
                </div>
                <div class="call_text"><a href="#"><img src="/man/images/call-icon.png"><span class="padding_left_0">Call Now  +01 123467890</span></a>
                </div>
                <div class="call_text"><a href="#"><img src="/man/images/mail-icon.png"><span class="padding_left_0">demo@gmail.com</span></a>
                </div>
                <div class="social_icon">
                    <ul>
                        <li><a href="#"><img src="/man/images/fb-icon1.png"></a></li>
                        <li><a href="#"><img src="/man/images/twitter-icon.png"></a></li>
                        <li><a href="#"><img src="/man/images/linkedin-icon.png"></a></li>
                        <li><a href="#"><img src="/man/images/instagram-icon.png"></a></li>
                    </ul>
                </div>
                <div class="container-fluid">
                    <input type="text" class="email_text" placeholder="Enter Your Email" name="Enter Your Email">
                    <div class="subscribe_bt"><a href="#">Subscribe</a></div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="copyright_section">
    <div class="container">
        <p class="copyright_text">Copyright 2019 All Right Reserved By.<a href="https://html.design"> Free html
            Templates</a></p>
        <p class="copyright_text">Disrtributed By. <a href="https://themewagon.com">ThemeWagon </a></p>
    </div>
</div>
<c:import url="/man/library/script.jsp"/>
</body>
</html>