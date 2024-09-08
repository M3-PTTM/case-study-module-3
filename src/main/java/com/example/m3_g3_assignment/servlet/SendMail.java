package com.example.m3_g3_assignment.servlet;

import com.example.m3_g3_assignment.model.Customer;

import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Properties;

@WebServlet("/mail")
public class SendMail extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        String fromEmail = "phamvanmantestsendmail@gmail.com";
        String password = "vompcqfobctohcxf";
        HttpSession reqSessions = req.getSession();
        Customer customer = (Customer) reqSessions.getAttribute("customer");
        if (customer != null) {
            String toEmail = customer.getCustomer_email();
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            Authenticator auth = new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            };
            Session session = Session.getInstance(props, auth);
            try {
                Message msg = new MimeMessage(session);
                msg.setFrom(new InternetAddress(fromEmail));
                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
                String subject = MimeUtility.encodeText("Hóa Đơn Thanh Toán", "UTF-8", "B");
                msg.setSubject(subject);
                MimeBodyPart messageBodyPart = new MimeBodyPart();
                String content = "Cảm ơn bạn đã sử dụng dịch vụ của chúng tôi!\n\n";
                content += "Mã giao dịch: " + req.getParameter("vnp_TxnRef") + "\n";
                content += "Số tiền: " + req.getParameter("vnp_Amount") + "\n";
                content += "Mô tả giao dịch: " + req.getParameter("vnp_OrderInfo") + "\n";
                content += "Mã lỗi thanh toán: " + req.getParameter("vnp_ResponseCode") + "\n";
                content += "Mã giao dịch tại CTT VNPAY-QR: " + req.getParameter("vnp_TransactionNo") + "\n";
                content += "Mã ngân hàng thanh toán: " + req.getParameter("vnp_BankCode") + "\n";
                content += "Thời gian thanh toán: " + req.getParameter("vnp_PayDate") + "\n";
                content += "Trạng thái giao dịch: Thành công\n";
                messageBodyPart.setContent(content, "text/plain; charset=UTF-8");
                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(messageBodyPart);
                msg.setContent(multipart);
                Transport.send(msg);
                System.out.println("Email đã được gửi thành công đến " + toEmail);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Không tìm thấy thông tin khách hàng.");
        }
    }
}
