<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.net.URLEncoder" %>
<%@page import="java.nio.charset.StandardCharsets" %>
<%@page import="com.example.m3_g3_assignment.vnpay.Config" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.Iterator" %>
<%@page import="java.util.Collections" %>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Enumeration" %>
<%@page import="java.util.Map" %>
<%@page import="java.util.HashMap" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <title>KẾT QUẢ THANH TOÁN</title>
    <!-- Bootstrap core CSS -->
    <link href="/man/assets/bootstrap.min.css" rel="stylesheet"/>
    <!-- Custom styles for this template -->
    <link href="/man/assets/jumbotron-narrow.css" rel="stylesheet">
    <script src="/man/assets/jquery-1.11.3.min.js"></script>
    <c:import url="/man/library/head.jsp"/>
</head>
<body>
<%
    //Begin process return from VNPAY
    Map fields = new HashMap();
    for (Enumeration params = request.getParameterNames(); params.hasMoreElements(); ) {
        String fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.US_ASCII.toString());
        String fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
        if ((fieldValue != null) && (fieldValue.length() > 0)) {
            fields.put(fieldName, fieldValue);
        }
    }
    String vnp_SecureHash = request.getParameter("vnp_SecureHash");
    if (fields.containsKey("vnp_SecureHashType")) {
        fields.remove("vnp_SecureHashType");
    }
    if (fields.containsKey("vnp_SecureHash")) {
        fields.remove("vnp_SecureHash");
    }
    String signValue = Config.hashAllFields(fields);
%>
<!--Begin display -->
<div class="container">
    <div class="header clearfix">
        <h3 class="text-muted">KẾT QUẢ THANH TOÁN</h3>
    </div>
    <div class="table-responsive">
        <div class="form-group">
            <label>Mã giao dịch thanh toán:</label>
            <label><%=request.getParameter("vnp_TxnRef")%>
            </label>
        </div>
        <div class="form-group">
            <label>Số tiền:</label>
            <label><%=request.getParameter("vnp_Amount")%>
            </label>
        </div>
        <div class="form-group">
            <label>Mô tả giao dịch:</label>
            <label><%=request.getParameter("vnp_OrderInfo")%>
            </label>
        </div>
        <div class="form-group">
            <label>Mã lỗi thanh toán:</label>
            <label><%=request.getParameter("vnp_ResponseCode")%>
            </label>
        </div>
        <div class="form-group">
            <label>Mã giao dịch tại CTT VNPAY-QR:</label>
            <label><%=request.getParameter("vnp_TransactionNo")%>
            </label>
        </div>
        <div class="form-group">
            <label>Mã ngân hàng thanh toán:</label>
            <label><%=request.getParameter("vnp_BankCode")%>
            </label>
        </div>
        <div class="form-group">
            <label>Thời gian thanh toán:</label>
            <label><%=request.getParameter("vnp_PayDate")%>
            </label>
        </div>
        <div class="form-group">
            <label>Tình trạng giao dịch:</label>
            <label>
                <%
                    if (signValue.equals(vnp_SecureHash)) {
                        if ("00".equals(request.getParameter("vnp_TransactionStatus"))) {
                            out.print("Thành công");
                        } else {
                            out.print("Không thành công");
                        }

                    } else {
                        out.print("invalid signature");
                    }
                %></label>
        </div>
    </div>
    <div class="container">
        <a class="btn btn-success" href="/home">Trở Về Trang Chủ</a>
    </div>
    <p>
        &nbsp;
    </p>
    <footer class="footer">
        <p>&copy; VNPAY 2020</p>
    </footer>
</div>

<script>
    function result() {
        var vnp_TransactionStatus = "${param.vnp_TransactionStatus}";
        var txnRef = "${param.vnp_TxnRef}";
        var amount = "${param.vnp_Amount}";
        var orderInfo = "${param.vnp_OrderInfo}"
        var responseCode = "${param.vnp_ResponseCode}"
        var transactionNo = "${param.vnp_TransactionNo}"
        var bankCode = "${param.vnp_BankCode}"
        var payDate = "${param.vnp_PayDate}";


        if (vnp_TransactionStatus === "00") {
            $.ajax({
                url: "/order",
                type: "POST",
                data: {},
                success: function (data) {
                    console.log("Đơn hàng xử lý thành công:", data);
                    $.ajax({
                        url: "/mail",
                        type: "POST",
                        data: {
                            vnp_TxnRef: txnRef,
                            vnp_Amount: amount,
                            vnp_OrderInfo: orderInfo,
                            vnp_ResponseCode: responseCode,
                            vnp_TransactionNo: transactionNo,
                            vnp_BankCode: bankCode,
                            vnp_PayDate: payDate
                        },
                        success: function (data) {
                            console.log("Email gửi thành công");
                        },
                        error: function (xhr) {
                            console.log("Lỗi khi gửi email:", xhr);
                        }
                    });
                },
                error: function (xhr) {
                    console.log("Lỗi khi xử lý đơn hàng:", xhr);
                }
            });
        }
    }

    result();
</script>
</body>
</html>
