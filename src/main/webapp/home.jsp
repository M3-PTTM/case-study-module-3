<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<c:import url="/man/library/head.jsp"/>
<body>
<c:if test="${not empty sessionScope.successMessage}">
    <div id="success-alert" class="alert alert-success" role="alert"
         style="position: fixed; top: 10px; right: 10px; z-index: 1000; background-color: #4CAF50; color: white; padding: 15px; border-radius: 5px;">
            ${sessionScope.successMessage}
    </div>
    <c:remove var="successMessage" scope="session" />
</c:if>
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
                    <a class="nav-link" href="/home">Trang Chủ</a>
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
                            <a class="nav-link" href="/dashboard.jsp">Quản Lý</a>
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
                            <li><a href="/profile">Xin Chào ${sessionScope.customer.username}</a></li>
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
    <div id="content" class="container">
        <h1 class="cycle_taital">Thiết Bị Của Chúng Tôi</h1>
        <p class="cycle_text">Một thực tế đã được xác lập từ lâu là độc giả sẽ bị phân tâm bởi</p>
        <c:forEach var="product" items="${products}" varStatus="status">
            <c:if test="${product.inventory>0}">
                <c:choose>
                    <c:when test="${status.count%2!=0}">
                        <div class="product cycle_section_3 layout_padding">
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
                                        <div class="buy_bt"><a href="#" class="buy-now" data-id="${product.id}">Giỏ
                                            Hàng</a></div>
                                        <h4 class="price_text"><span
                                                style=" color: #325662"><fmt:formatNumber value="${product.price}"
                                                                                          pattern="###,###"/></span><span
                                                style=" color: #f7c17b">VND</span></h4>
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
                                        <div class="buy_bt"><a href="#" class="buy-now" data-id="${product.id}">Giỏ
                                            Hàng</a></div>
                                        <h4 class="price_text"><span
                                                style=" color: #325662"><fmt:formatNumber value="${product.price}"
                                                                                          pattern="###,###"/></span><span
                                                style=" color: #f7c17b">VND</span></h4>
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
    <div class="container">
        <div class="read_btn_main">
            <div class="read_bt">
                <button type="button" onclick="showMore()">Xem Thêm</button>
            </div>
        </div>
    </div>
</div>
<div class="about_section layout_padding">
    <div class="container">
        <h1 class="about_taital">Về Thiết Bị Quân Sự Của Chúng Tôi</h1>
        <p class="about_text">Một thực tế đã được chứng minh từ lâu là người đọc sẽ bị phân tâm bởi nội dung dễ đọc của
            một trang khi nhìn vào bố cục của nó. Mục đích của việc sử dụng Lorem Ipsum là nó có sự phân phối các chữ
            cái ít nhiều bình thường
        </p>>
        <div class="read_bt_1"><a href="#">Xem Thêm</a></div>
    </div>
</div>
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
<c:import url="/man/library/add_product.jsp"/>
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
<script>
    setTimeout(function () {
        var alert = document.getElementById('success-alert');
        if (alert) {
            alert.style.display = 'none';
        }
    }, 3000);
</script>
<c:import url="/man/library/script.jsp"/>
</body>
</html>