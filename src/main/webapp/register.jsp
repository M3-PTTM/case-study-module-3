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
  <link rel="icon" href="/man/images/logo.png" type="image/x-icon">
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
              <div class="error-message">
                  ${errorMessage}
              </div>
            </c:if>
            <div class="input-field">
              <input type="text" class="input" id="username" name="username" value="${username != null ? username : ''}"/>
              <label for="username">Tên đăng nhập</label>
            </div>
            <div class="input-field">
              <input type="password" class="input" id="password" name="password"/>
              <label for="password">Mật khẩu</label>
            </div>
            <div class="input-field">
              <input type="text" class="input" id="customer_name" name="customer_name" value="${customer_name != null ? customer_name : ''}"/>
              <label for="customer_name">Họ và tên</label>
            </div>
            <div class="input-field">
              <input type="text" class="input" id="customer_email" name="customer_email" value="${customer_email != null ? customer_email : ''}"/>
              <label for="customer_email">Email</label>
            </div>
            <div class="input-field">
              <input type="text" class="input" id="customer_phone" name="customer_phone" value="${customer_phone != null ? customer_phone : ''}"/>
              <label for="customer_phone">Số điện thoại</label>
            </div>
            <div class="input-field">
              <input type="text" class="input" id="customer_citizen" name="customer_citizen" value="${customer_citizen != null ? customer_citizen : ''}"/>
              <label for="customer_citizen">Căn cước công dân</label>
            </div>
            <div class="input-field">
              <input type="submit" class="submit" value="Đăng ký" />
            </div>
            <div class="signin">
              <span>Bạn đã có tài khoản? <a href="login.jsp">Đăng nhập</a></span>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
<script>
  document.addEventListener("DOMContentLoaded", function() {
    const form = document.querySelector("form");

    form.addEventListener("submit", function(event) {
      let valid = true;

      clearAllErrors();

      const username = document.getElementById("username");
      if (username.value.trim() === "") {
        showError(username, "Tên đăng nhập không được để trống");
        valid = false;
      } else {
        clearError(username);
      }

      const password = document.getElementById("password");
      if (password.value.length < 6) {
        showError(password, "Mật khẩu phải có ít nhất 6 ký tự");
        valid = false;
      } else {
        clearError(password);
      }

      const customer_name = document.getElementById("customer_name");
      if (customer_name.value.trim() === "") {
        showError(customer_name, "Họ và tên không được để trống");
        valid = false;
      } else {
        clearError(customer_name);
      }

      const email = document.getElementById("customer_email");
      const emailPattern = /^[^ ]+@[^ ]+\.[a-z]{2,3}$/;
      if (!email.value.match(emailPattern)) {
        showError(email, "Email không hợp lệ");
        valid = false;
      } else {
        clearError(email);
      }

      const phone = document.getElementById("customer_phone");
      const phonePattern = /^[0-9]{10}$/;
      if (!phone.value.match(phonePattern)) {
        showError(phone, "Số điện thoại không hợp lệ. Số điện thoại phải có 10 chữ số.");
        valid = false;
      } else {
        clearError(phone);
      }

      const citizen = document.getElementById("customer_citizen");
      const citizenPattern = /^[0-9]{12}$/;
      if (!citizen.value.match(citizenPattern)) {
        showError(citizen, "Căn cước công dân không hợp lệ. Phải có 12 chữ số.");
        valid = false;
      } else {
        clearError(citizen);
      }

      if (!valid) {
        event.preventDefault();
      }
    });

    function showError(input, message) {
      const inputField = input.closest(".input-field");
      const errorElement = document.createElement("div");
      errorElement.className = "error-message";
      errorElement.style.color = "red";
      errorElement.textContent = message;
      inputField.appendChild(errorElement);
      input.classList.add("error-input"); // Thêm CSS cho trường bị lỗi
    }

    function clearError(input) {
      const inputField = input.closest(".input-field");
      const errorMessage = inputField.querySelector(".error-message");
      if (errorMessage) {
        errorMessage.remove();
      }
      input.classList.remove("error-input");
    }

    function clearAllErrors() {
      const errorMessages = document.querySelectorAll(".error-message");
      errorMessages.forEach(function(error) {
        error.remove();
      });

      const inputs = document.querySelectorAll(".input");
      inputs.forEach(function(input) {
        input.classList.remove("error-input");
      });
    }

    function showError(input, message) {
      alert(message);
      input.classList.add("error-input");
    }
  });
</script>
</body>
</html>
