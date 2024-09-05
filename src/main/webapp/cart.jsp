<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<c:import url="/man/library/head.jsp"/>
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
                    <a class="nav-link" href="/home">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">About</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">News</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Contact Us</a>
                </li>
            </ul>
            <div class="form-inline my-2 my-lg-0">
                <div class="login_menu">
                    <ul>
                        <c:if test="${sessionScope.account != null}">
                            <li><a href="#">Logout</a></li>
                        </c:if>
                        <c:if test="${sessionScope.account == null}">
                            <li><a href="#">Login</a></li>
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
                        <c:if test="${sessionScope.account != null}">
                            <li><a href="#">Welcome ${sessionScope.account.name}</a></li>
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
                                                <div class="best_text">New</div>
                                                <div class="image_1"><img src="/man/images/${product.img}"></div>
                                            </a>
                                        </div>
                                        <div class="col-md-5">
                                            <a href="/home?action=view&id=${product.id}"><h1 class="banner_taital">New
                                                Model</h1>
                                                <p class="banner_text">${product.description}</p></a>
                                            <div class="contact_bt"><a href="#">Buy Now</a></div>
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
                                                <div class="best_text">New</div>
                                                <div class="image_1"><img src="/man/images/${product.img}"></div>
                                            </a>
                                        </div>
                                        <div class="col-md-5">
                                            <a href="/home?action=view&id=${product.id}"><h1 class="banner_taital">New
                                                Model</h1>
                                                <p class="banner_text">${product.description}</p></a>
                                            <div class="contact_bt"><a href="#">Buy Now</a></div>
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
    <div class="container">
        <h1 class="cycle_taital">Cart</h1>
        <p class="cycle_text">It is a long established fact that a reader will be distracted by the </p>
        <c:forEach var="entry" items="${map}" varStatus="status">
            <c:if test="${entry.key.inventory>0}">
                <c:choose>
                    <c:when test="${status.count%2!=0}">
                        <div class="product cycle_section_3 layout_padding">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="box_main_3">
                                        <a href="/home?action=view&id=${entry.key.id}"><h6
                                                class="number_text">${status.count}</h6>
                                            <div class="image_2"><img src="/man/images/${entry.key.img}"></div>
                                        </a>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <a href="/home?action=view&id=${entry.key.id}"><h1
                                            class="cycles_text">${entry.key.category} ${entry.key.name}</h1>
                                        <p class="lorem_text">${entry.key.description}</p></a>
                                    <div class="btn_main">
                                        <div class="buy_bt">
                                            <div class="quantity_selector">
                                                <button class="quantity_button"
                                                        onclick="decreaseQuantity(${status.count})">-
                                                </button>
                                                <input type="number" id="quantity${status.count}" value="1" min="1"
                                                       max="${entry.key.inventory}" class="quantity_input"
                                                       onchange="updateTotalPrice()"/>
                                                <button class="quantity_button"
                                                        onclick="increaseQuantity(${status.count})">+
                                                </button>
                                            </div>
                                        </div>
                                        <h4 class="price_text">
                                            <span style=" color: #325662">${entry.key.price}</span>
                                            <span style=" color: #f7c17b"> USD</span>
                                        </h4>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="product cycle_section_3 layout_padding">
                            <div class="row">
                                <div class="col-md-6">
                                    <a href="/home?action=view&id=${entry.key.id}"><h1
                                            class="cycles_text">${entry.key.category} ${entry.key.name}</h1>
                                        <p class="lorem_text">${entry.key.description}</p></a>
                                    <div class="btn_main">
                                        <div class="buy_bt">
                                            <div class="quantity_selector">
                                                <button class="quantity_button"
                                                        onclick="decreaseQuantity(${status.count})">-
                                                </button>
                                                <input type="number" id="quantity${status.count}" value="1" min="1"
                                                       max="${entry.key.inventory}" class="quantity_input"
                                                       onchange="updateTotalPrice()"/>
                                                <button class="quantity_button"
                                                        onclick="increaseQuantity(${status.count})">+
                                                </button>
                                            </div>
                                        </div>
                                        <h4 class="price_text">
                                            <span style=" color: #325662">${entry.key.price}</span>
                                            <span style=" color: #f7c17b"> USD</span>
                                        </h4>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="box_main_3">
                                        <a href="/home?action=view&id=${entry.key.id}"><h6
                                                class="number_text">${status.count}</h6>
                                            <div class="image_2"><img src="/man/images/${entry.key.img}"></div>
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
            <h1 class="client_taital">Payment</h1>
            <div class="client_section_2">
                <table style="width: 100%; table-layout: fixed; border-collapse: collapse;">
                    <tr>
                        <th style="width: 33.33%; text-align: center; border: 1px solid #ddd;">Tên Sản Phẩm</th>
                        <th style="width: 33.33%; text-align: center; border: 1px solid #ddd;">Số Lượng</th>
                        <th style="width: 33.33%; text-align: center; border: 1px solid #ddd;">Thành Giá</th>
                    </tr>
                    <c:forEach var="entry" items="${map}" varStatus="status">
                        <tr>
                            <th style="width: 33.33%; text-align: center; border: 1px solid #ddd;">${entry.key.name}</th>
                            <th id="quantityCell${status.count}"
                                style="width: 33.33%; text-align: center; border: 1px solid #ddd;">${entry.value}</th>
                            <th style="width: 33.33%; text-align: center; border: 1px solid #ddd;"><span
                                    id="priceCell${status.count}">${entry.key.price*entry.value}</span> USD
                            </th>
                        </tr>
                    </c:forEach>
                    <tr>
                        <th colspan="2" style="text-align: center; border: 1px solid #ddd;">Tổng giá:</th>
                        <th style="text-align: center; border: 1px solid #ddd;"><span id="totalPrice">0</span> USD</th>
                    </tr>
                    <tr>
                        <th colspan="3" style="text-align: center; border: 1px solid #ddd;">
                            <button class="btn btn-danger" onclick="submitPayment()">Thanh Toán</button>
                        </th>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
<script>
    function decreaseQuantity(count) {
        var quantityInput = document.getElementById('quantity' + count);
        var currentValue = parseInt(quantityInput.value);
        if (currentValue > 1) {
            quantityInput.value = currentValue - 1;
        }
        updateTotalPrice();
    }

    function increaseQuantity(count) {
        var quantityInput = document.getElementById('quantity' + count);
        var currentValue = parseInt(quantityInput.value);
        quantityInput.value = currentValue + 1;
        updateTotalPrice();
    }

    function updateTotalPrice() {
        var totalPrice = 0;
        <c:forEach var="entry" items="${map}" varStatus="status">
        var quantity = document.getElementById('quantity${status.count}').value;
        var price = ${entry.key.price};
        totalPrice += price * quantity;
        document.querySelector(`#quantityCell${status.count}`).innerHTML = quantity;
        document.querySelector(`#priceCell${status.count}`).innerHTML = (price * quantity).toFixed(2);
        </c:forEach>
        document.getElementById('totalPrice').innerHTML = totalPrice.toFixed(2);
    }

    function submitPayment() {
        // Chuyển hướng đến trang xác nhận thanh toán hoặc thực hiện các thao tác cần thiết.
        alert('Đã thanh toán thành công!');
        // Hoặc bạn có thể điều hướng tới trang thanh toán.
        // window.location.href = '/checkout';
    }

    updateTotalPrice();
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
                        <iframe src="https://www.google.com/maps/embed/v1/place?key=AIzaSyA0s1a7phLN0iaD6-UE7m4qP-z21pH0eSc&amp;q=Eiffel+Tower+Paris+France"
                                width="600" height="400" frameborder="0" style="border:0; width: 100%;"
                                allowfullscreen=""></iframe>
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