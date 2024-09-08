package com.example.m3_g3_assignment.servlet;

import com.example.m3_g3_assignment.dao.impl.CustomerDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ResetController", urlPatterns = "/resetPassword")
public class ResetController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CustomerDAO customerDAO;

    public void init() {
        customerDAO = new CustomerDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        String customer_email = (String) req.getSession().getAttribute("customer_email");
        String newPassword = req.getParameter("newPassword");

        if (customer_email == null) {
            req.setAttribute("errorMessage", "Yêu cầu không hợp lệ. Vui lòng thử lại.");
            req.getRequestDispatcher("forgotpassword.jsp").forward(req, resp);
            return;
        }
        try {
            customerDAO.updatePassword(customer_email, newPassword);
            req.getSession().removeAttribute("otp");
            req.getSession().removeAttribute("customer_email");
            req.setAttribute("successMessage", "Mật khẩu đã được thay đổi thành công.");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
