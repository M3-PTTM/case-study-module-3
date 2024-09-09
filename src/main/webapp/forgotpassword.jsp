<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" href="./phuc/css/login-register.css"/>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
            crossorigin="anonymous"
    />
    <title>Quên mật khẩu</title>
    <link rel="icon" href="/man/images/logo.png" type="image/x-icon">
</head>
<body>
<div class="wrapper">
    <div class="container main">
        <div class="row">
            <div class="col-md-6 side-image">
                <img src="./man/images/logo.png" alt=""/>
                <div class="text">
                </div>
            </div>
            <div class="col-md-6 right">
                <form action="forgotPassword" method="post">
                    <div class="input-box">
                        <header>Quên mật khẩu</header>
                        <c:if test="${not empty errorMessage}">
                            <div class="alert alert-danger" role="alert">
                                    ${errorMessage}
                            </div>
                        </c:if>
                        <div class="input-field">
                            <input type="email" class="input" id="customer_email" name="customer_email"
                                   value="${customer_email != null ? customer_email : ''}" required=""/>
                            <label for="customer_email">Email</label>
                        </div>
                        <div class="input-field">
                            <input type="submit" class="submit" value="Gửi mã OTP"/>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script>
    sessionStorage.removeItem("timeRemaining");
</script>
</body>
</html>
