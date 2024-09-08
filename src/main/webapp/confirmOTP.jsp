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
                <form action="confirmOTP" method="post" id="otpForm">
                    <div class="input-box">
                        <header>Xác nhận mã OTP</header>
                        <c:if test="${not empty errorMessage}">
                            <div class="alert alert-danger" role="alert">
                                    ${errorMessage}
                            </div>
                        </c:if>
                        <div class="input-field">
                            <input type="text" class="input" id="otp" name="otp"/>
                            <label for="otp">Nhập mã OTP</label>
                        </div>
                        <div class="input-field">
                            <input type="submit" class="submit" value="Xác nhận"/>
                        </div>
                        <div class="input-field">
                            <p>Thời gian còn lại <span id="countdown">${timeRemaining}</span> giây</p>
                        </div>
                    </div>
                </form>
                <p id="expiredMessage" style="display: none; color: red;">Mã OTP hết hạn. Vui lòng thử lại</p>
            </div>
        </div>
    </div>
</div>
<script>
    let countdownTime = ${timeRemaining != null ? timeRemaining : 60};
    let countdownElement = null;
    window.onload = function () {
        countdownElement = document.getElementById("countdown");
        startCountdown();
    }

    function startCountdown() {
        let countdownInterval = setInterval(function () {
            if (countdownTime <= 0) {
                clearInterval(countdownInterval);
                document.getElementById("otpForm").style.display = "none";
                document.getElementById("expiredMessage").style.display = "block";
                setTimeout(function () {
                    window.location.href = "forgotpassword.jsp";
                }, 3000);
            } else {
                countdownElement.innerText = countdownTime;
                countdownTime--;
            }
        }, 1000);
    }
</script>
</body>
</html>
