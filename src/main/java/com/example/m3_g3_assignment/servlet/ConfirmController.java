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
        if (otpSession != null && otpSession.equals(otp)) {
            resp.sendRedirect("resetpassword.jsp");
        } else {
            req.setAttribute("errorMessage", "Mã OTP không hợp lệ");
            req.getRequestDispatcher("confirmOTP.jsp").forward(req, resp);
        }
    }
}
