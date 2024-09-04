<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <form action="signin" method="post">
          <div class="input-box">
            <header>Đăng nhập</header>
            <div class="input-field">
              <input type="text" class="input" id="username" required="" />
              <label for="username">Tên đăng nhập</label>
            </div>
            <div class="input-field">
              <input type="password" class="input" id="pass" required="" />
              <label for="pass">Mật khẩu</label>
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
