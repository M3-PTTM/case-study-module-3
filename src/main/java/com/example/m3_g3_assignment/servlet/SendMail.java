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
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
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
                StringBuilder content = new StringBuilder();

                content.append("<!DOCTYPE html>");
                content.append("<html>");
                content.append("<head>");
                content.append("<style>");
                content.append("  body { font-family: Arial, sans-serif; margin: 0; padding: 20px; background-color: #f4f4f4; }");
                content.append("  .email-container { background-color: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); text-align: center; }");
                content.append("  h2 { color: #333333; }");
                content.append("  p { color: #666666; font-size: 16px; }");
                content.append("  table { width: 100%; border-collapse: collapse; margin: 20px 0; }");
                content.append("  table, th, td { border: 1px solid #dddddd; padding: 8px; text-align: left; }");
                content.append("  th { background-color: #f2f2f2; }");
                content.append("</style>");
                content.append("</head>");
                content.append("<body>");
                content.append("  <div class='email-container'>");
                content.append("    <h2>Hóa Đơn Thanh Toán</h2>");
                content.append("    <p>Cảm ơn bạn đã sử dụng dịch vụ của chúng tôi!</p>");
                content.append("    <table>");
                content.append("      <tr>");
                content.append("        <th>Mã giao dịch</th>");
                content.append("        <td>" + req.getParameter("vnp_TxnRef") + "</td>");
                content.append("      </tr>");
                content.append("      <tr>");
                content.append("        <th>Số tiền</th>");
                String amountParam = req.getParameter("vnp_Amount");
                double amount = Double.parseDouble(amountParam) / 100;
                NumberFormat formatter = NumberFormat.getInstance(new Locale("vi", "VN"));
                formatter.setMinimumFractionDigits(0);
                String formattedAmount = formatter.format(amount);
                content.append("        <td>" + formattedAmount + " VND</td>");
                content.append("      </tr>");
                content.append("      <tr>");
                content.append("        <th>Thanh toán đơn hàng</th>");
                String orderInfo = req.getParameter("vnp_OrderInfo");
                String formattedOrderInfo = orderInfo.replaceAll("Thanh toan don hang:", "");
                content.append("        <td>" + formattedOrderInfo + "</td>");
                content.append("      </tr>");
                content.append("      <tr>");
                content.append("        <th>Mã giao dịch VNPAY-QR</th>");
                content.append("        <td>" + req.getParameter("vnp_TransactionNo") + "</td>");
                content.append("      </tr>");
                content.append("      <tr>");
                content.append("        <th>Mã ngân hàng</th>");
                content.append("        <td>" + req.getParameter("vnp_BankCode") + "</td>");
                content.append("      </tr>");
                content.append("      <tr>");
                content.append("        <th>Thời gian thanh toán</th>");
                String payDateParam = req.getParameter("vnp_PayDate");
                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyyMMddHHmmss");
                SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date payDate = inputFormat.parse(payDateParam);
                String formattedPayDate = outputFormat.format(payDate);
                content.append("        <td>" + formattedPayDate + "</td>");
                content.append("      </tr>");
                content.append("      <tr>");
                content.append("        <th>Trạng thái giao dịch</th>");
                content.append("        <td>Thành công</td>");
                content.append("      </tr>");
                content.append("    </table>");
                content.append("    <p>Chúc bạn có trải nghiệm tuyệt vời!</p>");
                content.append("  </div>");
                content.append("</body>");
                content.append("</html>");

                messageBodyPart.setContent(content.toString(), "text/html; charset=UTF-8");

                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(messageBodyPart);

                msg.setContent(multipart);
                Transport.send(msg);

                System.out.println("Email đã được gửi thành công đến " + toEmail);
            } catch (MessagingException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Không tìm thấy thông tin khách hàng.");
        }
    }
}
