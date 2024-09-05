<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <link rel="stylesheet" href="./phuc/css/login-register.css" />
  <link
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
          crossorigin="anonymous"
  />
  <title>Đăng ký</title>
</head>
<body>
<div class="wrapper">
  <div class="container main">
    <div class="row">
      <div class="col-md-6 side-image">
        <img src="./man/images/logo.png" alt="" />
        <div class="text">
        </div>
      </div>
      <div class="col-md-6 right">
        <form action="register" method="post">
          <div class="input-box">
            <header>Đăng ký</header>
            <c:if test="${not empty errorMessage}">
              <div class="alert alert-warning" role="alert">
                  ${errorMessage}
              </div>
            </c:if>
            <div class="input-field">
              <input type="text" class="input" id="username" name="username" required="" />
              <label for="username">Tên đăng nhập</label>
            </div>
            <div class="input-field">
              <input type="password" class="input" id="password" name="password" required="" />
              <label for="password">Mật khẩu</label>
            </div>
            <div class="input-field">
              <input type="text" class="input" id="customer_name" name="customer_name" required="" />
              <label for="customer_name">Họ và tên</label>
            </div>
            <div class="input-field">
              <input type="email" class="input" id="customer_email" name="customer_email" required="" />
              <label for="customer_email">Email</label>
            </div>
            <div class="input-field">
              <input type="text" class="input" id="customer_phone" name="customer_phone" required="" />
              <label for="customer_phone">Số điện thoại</label>
            </div>
            <div class="input-field">
              <input type="text" class="input" id="customer_citizen" name="customer_citizen" required="" />
              <label for="customer_citizen">Căn cước công dân</label>
            </div>
            <div class="input-field">
              <input type="submit" class="submit" value="Đăng ký" />
            </div>
            <div class="signin">
              <span>Bạn đã có tài khoản? <a href="register.jsp">Đăng nhập</a></span>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
</body>
</html>
