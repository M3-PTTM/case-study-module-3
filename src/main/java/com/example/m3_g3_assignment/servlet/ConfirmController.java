package com.example.m3_g3_assignment.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ConfirmController", urlPatterns = "/confirmOTP")
public class ConfirmController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        String otp = req.getParameter("otp");
        String otpSession = (String) req.getSession().getAttribute("otp");
        Long timeOTP = (Long) req.getSession().getAttribute("timeOTP");
        if (timeOTP == null || System.currentTimeMillis() > timeOTP) {
            req.setAttribute("errorMessage", "Mã OTP đã hết hạn");
            req.getRequestDispatcher("forgotpassword.jsp").forward(req, resp);
            return;
        }
        if (otp != null && otp.equals(otpSession)) {
            resp.getWriter().write("<script>sessionStorage.removeItem('timeRemaining');</script>");
            req.getRequestDispatcher("resetpassword.jsp").forward(req, resp);
        } else {
            long timeRemaining = (timeOTP - System.currentTimeMillis()) / 1000;
            req.setAttribute("timeRemaining", timeRemaining);
            req.setAttribute("errorMessage", "Mã OTP không hợp lệ");
            req.getRequestDispatcher("confirmOTP.jsp").forward(req, resp);
        }
    }
}
