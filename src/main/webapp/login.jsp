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
  <title>Đăng nhập</title>
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
        <form action="login" method="post">
          <div class="input-box">
            <header>Đăng nhập</header>
            <c:if test="${not empty errorMessage}">
              <div class="alert alert-danger" role="alert">
                  ${errorMessage}
              </div>
            </c:if>
            <div class="input-field">
              <input type="text" class="input" id="username" name="username" value="${username != null ? username : ''}" required="" />
              <label for="username">Tên đăng nhập</label>
            </div>
            <div class="input-field">
              <input type="password" class="input" id="password" name="password" required="" />
              <label for="password">Mật khẩu</label>
            </div>
            <div class="input-field">
              <input type="submit" class="submit" value="Đăng nhập" />
            </div>
            <div class="signin">
              <span>Bạn chưa có tài khoản? <a href="register.jsp">Đăng ký</a></span>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
</body>
</html>
