package com.example.m3_g3_assignment.servlet;

import com.example.m3_g3_assignment.dao.impl.CustomerDAO;
import com.example.m3_g3_assignment.model.Customer;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

@WebServlet(name = "ForgotController", urlPatterns = "/forgotPassword")
public class ForgotController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CustomerDAO customerDAO;

    public void init() {
        customerDAO = new CustomerDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        String customer_email = req.getParameter("customer_email");
        try {
            Customer customer = customerDAO.findCustomerByEmail(customer_email);
            if (customer == null) {
                req.setAttribute("errorMessage", "Email chưa được đăng ký");
                req.setAttribute(customer_email, "customer_email");
                req.getRequestDispatcher("forgotpassword.jsp").forward(req, resp);
                return;
            }
            String otp = generateOTP();
            req.getSession().setAttribute("otp", otp);
            req.getSession().setAttribute("customer_email", customer_email);
            sendOTPEmail(customer_email, otp);
            resp.sendRedirect("confirmOTP.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String generateOTP() {
        return String.format("%06d", (int) (Math.random() * 999999));
    }

    private void sendOTPEmail(String customer_email, String otp) {
        final String fromEmail = "phamvanmantestsendmail@gmail.com";
        final String password = "vompcqfobctohcxf";

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(customer_email));
            message.setSubject("Mã OTP của bạn");
            message.setText("Mã OTP: " + otp);
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
