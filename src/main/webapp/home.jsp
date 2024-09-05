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
                    <a class="nav-link" href="home">Home</a>
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
                        <c:if test="${sessionScope.customer != null}">
                            <li>Hello ${sessionScope.customer.username}</li>
                        </c:if>
                        <c:if test="${sessionScope.customer != null}">
                            <li><a href="logout">Logout</a></li>
                        </c:if>
                        <c:if test="${sessionScope.customer == null}">
                            <li><a href="login.jsp">Login</a></li>
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
    <div id="content" class="container">
        <h1 class="cycle_taital">Our Gear</h1>
        <p class="cycle_text">It is a long established fact that a reader will be distracted by the </p>
        <c:forEach var="product" items="${products}" varStatus="status">
            <c:if test="${product.inventory>0}">
                <c:choose>
                    <c:when test="${status.count%2!=0}">
                        <div class="product cycle_section_3 layout_padding">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="box_main_3">
                                        <a href="/home?action=view&id=${product.id}"><h6
                                                class="number_text">${status.count}</h6>
                                            <div class="image_2"><img src="/man/images/${product.img}"></div>
                                        </a>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <a href="/home?action=view&id=${product.id}"><h1
                                            class="cycles_text">${product.category} ${product.name}</h1>
                                        <p class="lorem_text">${product.description}</p></a>
                                    <div class="btn_main">
                                        <div class="buy_bt"><a href="#" class="buy-now" data-id="${product.id}">Buy Now</a></div>
                                        <h4 class="price_text">Price <span style=" color: #f7c17b">$</span> <span
                                                style=" color: #325662">${product.price}</span></h4>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="product cycle_section_3 layout_padding">
                            <div class="row">
                                <div class="col-md-6">
                                    <a href="/home?action=view&id=${product.id}"><h1
                                            class="cycles_text">${product.category} ${product.name}</h1>
                                        <p class="lorem_text">${product.description}</p></a>
                                    <div class="btn_main">
                                        <div class="buy_bt"><a href="#" class="buy-now" data-id="${product.id}">Buy Now</a></div>
                                        <h4 class="price_text">Price <span style=" color: #f7c17b">$</span> <span
                                                style=" color: #325662">${product.price}</span></h4>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="box_main_3">
                                        <a href="/home?action=view&id=${product.id}"><h6
                                                class="number_text">${status.count}</h6>
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
    <div class="container">
        <div class="read_btn_main">
            <div class="read_bt">
                <button type="button" onclick="showMore()">Show More</button>
            </div>
        </div>
    </div>
</div>
<div class="about_section layout_padding">
    <div class="container">
        <h1 class="about_taital">About Our Military Equipment</h1>
        <p class="about_text">It is a long established fact that a reader will be distracted by the readable content of
            a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal
            distribution of letters
        </p>>
        <div class="read_bt_1"><a href="#">Read More</a></div>
    </div>
</div>
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
<div id="notification" style="display: none; position: fixed; top: 10px; right: 10px; background-color: #4CAF50; color: white; padding: 15px; border-radius: 5px;">
    Sản phẩm đã được thêm vào giỏ hàng thành công!
</div>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        var buyNow = document.querySelectorAll('.buy-now');
        buyNow.forEach(function (button) {
            button.addEventListener('click', function (event) {
                event.preventDefault()
                var id = this.getAttribute('data-id');
                var xhr = new XMLHttpRequest();
                xhr.open("GET", "/home?action=add-to-cart&id=" + id, true);
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        var notification = document.getElementById('notification');
                        notification.style.display = 'block';
                        window.scrollTo({ top: 0, behavior: 'smooth' });
                        setTimeout(function () {
                            notification.style.display = 'none';
                        }, 3000)
                    }
                };
                xhr.send();
            });
        });
    });
</script>
<script>
    function showMore() {
        var amount = document.getElementsByClassName("product").length;
        $.ajax({
            url: "/load",
            type: "get",
            data: {
                exits: amount
            },
            success: function (data) {
                var row = document.getElementById("content");
                row.innerHTML += data;
            },
            error: function (xhr) {
            }
        });
    }
</script>
<c:import url="/man/library/script.jsp"/>
</body>
</html>