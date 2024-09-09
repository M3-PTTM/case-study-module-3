package com.example.m3_g3_assignment.servlet;

import com.example.m3_g3_assignment.dao.impl.CustomerDAO;
import com.example.m3_g3_assignment.model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ConfirmRegister", urlPatterns = "/confirmRegister")
public class ConfirmRegister extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CustomerDAO customerDAO;

    public void init() {
        customerDAO = new CustomerDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        String otpRegister = req.getParameter("otpRegister");
        String otpSession = (String) req.getSession().getAttribute("otpRegister");
        Long timeOTP = (Long) req.getSession().getAttribute("timeOTP");

        if (timeOTP == null || System.currentTimeMillis() > timeOTP) {
            req.setAttribute("errorMessage", "Mã OTP đã hết hạn. Vui lòng đăng ký lại");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            return;
        }

        if (otpRegister != null && otpRegister.equals(otpSession)) {
            String username = (String) req.getSession().getAttribute("username");
            String password = (String) req.getSession().getAttribute("password");
            String customer_name = (String) req.getSession().getAttribute("customer_name");
            String customer_email = (String) req.getSession().getAttribute("customer_email");
            String customer_phone = (String) req.getSession().getAttribute("customer_phone");
            String customer_citizen = (String) req.getSession().getAttribute("customer_citizen");
            String customer_role = (String) req.getSession().getAttribute("customer_role");

            try {
                Customer customer = new Customer(username, password, customer_name, customer_email,
                        customer_phone, customer_citizen, customer_role);
                customerDAO.insertCustomer(customer);
                req.getSession().invalidate();
                resp.sendRedirect("login.jsp");
            } catch (SQLException e) {
                e.printStackTrace();
                req.setAttribute("errorMessage", "Đã xảy ra lỗi khi đăng ký. Vui lòng thử lại.");
                req.getRequestDispatcher("register.jsp").forward(req, resp);
            }
        } else {
            long timeRemaining = (timeOTP - System.currentTimeMillis()) / 1000;
            req.setAttribute("timeRemaining", timeRemaining);
            req.setAttribute("errorMessage", "Mã OTP không hợp lệ");
            req.getRequestDispatcher("confirmOTPregister.jsp").forward(req, resp);
        }
    }
}
