package com.example.m3_g3_assignment.servlet;

import com.example.m3_g3_assignment.dao.impl.CustomerDAO;

import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

@WebServlet(name = "RegisterController", urlPatterns = "/register")
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CustomerDAO customerDAO;

    public void init() {
        customerDAO = new CustomerDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String customer_name = req.getParameter("customer_name");
        String customer_email = req.getParameter("customer_email");
        String customer_phone = req.getParameter("customer_phone");
        String customer_citizen = req.getParameter("customer_citizen");
        String customer_role = req.getParameter("customer_role");
        if (username == null || password == null || customer_name == null || customer_email == null ||
                customer_phone == null || customer_citizen == null) {
            req.setAttribute("errorMessage", "Vui lòng nhập đầy đủ thông tin");
            forwardWithAttributes(req, resp, username, customer_name, customer_email, customer_phone, customer_citizen);
            return;
        }
        try {
            if (customerDAO.existsUsername(username)) {
                req.setAttribute("errorMessage", "Tên đăng nhập đã được sử dụng");
                forwardWithAttributes(req, resp, username, customer_name, customer_email, customer_phone, customer_citizen);
                return;
            } else if (customerDAO.existsEmail(customer_email)) {
                req.setAttribute("errorMessage", "Email đã được sử dụng");
                forwardWithAttributes(req, resp, username, customer_name, customer_email, customer_phone, customer_citizen);
                return;
            } else if (customerDAO.existsPhone(customer_phone)) {
                req.setAttribute("errorMessage", "Số điện thoại đã được sử dụng");
                forwardWithAttributes(req, resp, username, customer_name, customer_email, customer_phone, customer_citizen);
                return;
            } else if (customerDAO.existsCitizen(customer_citizen)) {
                req.setAttribute("errorMessage", "CCCD đã sử dụng");
                forwardWithAttributes(req, resp, username, customer_name, customer_email, customer_phone, customer_citizen);
                return;
            }
            String otpRegister = generateOTP();
            req.getSession().setAttribute("otpRegister", otpRegister);
            req.getSession().setAttribute("username", username);
            req.getSession().setAttribute("password", password);
            req.getSession().setAttribute("customer_name", customer_name);
            req.getSession().setAttribute("customer_email", customer_email);
            req.getSession().setAttribute("customer_phone", customer_phone);
            req.getSession().setAttribute("customer_citizen", customer_citizen);
            req.getSession().setAttribute("customer_role", customer_role);
            long timeOTP = System.currentTimeMillis() + 60 * 1000;
            req.getSession().setAttribute("timeOTP", timeOTP);
            sendOTPEmail(customer_name, customer_email, otpRegister);
            resp.sendRedirect("confirmOTPregister.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("errorMessage", "Đã có lỗi xảy ra. Vui lòng thử lại sau.");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        }
    }

    private void forwardWithAttributes(HttpServletRequest req, HttpServletResponse resp, String username, String customer_name, String customer_email, String customer_phone, String customer_citizen) throws ServletException, IOException {
        req.setAttribute("username", username);
        req.setAttribute("customer_name", customer_name);
        req.setAttribute("customer_email", customer_email);
        req.setAttribute("customer_phone", customer_phone);
        req.setAttribute("customer_citizen", customer_citizen);
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }

    private String generateOTP() {
        return String.format("%06d", (int) (Math.random() * 999999));
    }

    private void sendOTPEmail(String customer_name, String customer_email, String otp) {
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
            String subject = MimeUtility.encodeText("Mã OTP để kích hoạt tài khoản", "UTF-8", "B");
            message.setSubject(subject);
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            StringBuilder content = new StringBuilder();
            content.append("<!DOCTYPE html>");
            content.append("<html>");
            content.append("<head>");
            content.append("<style>");
            content.append("  body { font-family: Arial, sans-serif; margin: 0; padding: 20px; background-color: #f4f4f4; }");
            content.append("  .email-container { background-color: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); text-align: center; }");
            content.append("  h2 { color: #333333; }");
            content.append("  p { color: #666666; font-size: 12px; }");
            content.append("  .otp-code { font-size: 12px; font-weight: bold; color: #ff5722; }");
            content.append("  table { width: 100%; border-collapse: collapse; margin: 20px 0; }");
            content.append("  table, th, td { border: 1px solid #dddddd; padding: 8px; text-align: left; }");
            content.append("  th { background-color: #f2f2f2; }");
            content.append("</style>");
            content.append("</head>");
            content.append("<body>");
            content.append("  <div class='email-container'>");
            content.append("    <h2>Xin chào, " + customer_name + "!</h2>");
            content.append("    <p>Cảm ơn bạn đã đăng ký tài khoản.</p>");
            content.append("    <p>Mã OTP của bạn</p>");
            content.append("    <table>");
            content.append("      <tr>");
            content.append("        <th>Mã OTP</th>");
            content.append("        <th>Thời gian hết hạn</th>");
            content.append("      </tr>");
            content.append("      <tr>");
            content.append("        <td class='otp-code'>" + otp + "</td>");
            content.append("        <td>1 phút</td>");
            content.append("      </tr>");
            content.append("    </table>");
            content.append("    <p>Cảm ơn bạn đã sử dụng dịch vụ của chúng tôi.</p>");
            content.append("    <p>Trân trọng,</p>");
            content.append("    <p>War Machine</p>");
            content.append("  </div>");
            content.append("</body>");
            content.append("</html>");

            messageBodyPart.setContent(content.toString(), "text/html; charset=UTF-8");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
