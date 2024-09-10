package com.example.m3_g3_assignment.servlet;

import com.example.m3_g3_assignment.dao.impl.CustomerDAO;
import com.example.m3_g3_assignment.model.Customer;

import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
            String customer_name = customer.getCustomer_name();
            String otp = generateOTP();
            req.getSession().setAttribute("otp", otp);
            long timeOTP = System.currentTimeMillis() + 60 * 1000;
            req.getSession().setAttribute("timeOTP", timeOTP);
            req.getSession().setAttribute("customer_email", customer_email);
            sendOTPEmail(customer_name, customer_email, otp);
            resp.sendRedirect("confirmOTP.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
            String subject = MimeUtility.encodeText("Mã OTP để khôi phục mật khẩu", "UTF-8", "B");
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
            content.append("    <p>Bạn đã yêu cầu khôi phục mật khẩu.</p>");
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
