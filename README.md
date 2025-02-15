# Military Equipment E-Commerce Website (JSP/Servlet)

## Giới thiệu

Trong lúc chiến sự giữa Nga và Ukraine đang căng thẳng tôi đã nghĩ ra ý tưởng xây dựng một website cung cấp các trang thiết bị quân sự cho các bên liên quan. Dự án này là một website thương mại điện tử được phát triển bằng JSP/Servlet. Đây là dự án do tôi "Phạm Văn Mẫn" tự lên ý tưởng và thiết kế làm việc cùng các thành viên khác trong nhóm thực hiện trong quá trình học tập.&#x20;

## Tính năng chính

- Đăng ký, đăng nhập (bao gồm đăng nhập bằng Google, Facebook)
- Lấy lại mật khẩu qua email
- Đặt hàng và thanh toán trực tuyến qua VNPay
- Gửi hóa đơn về email
- Đánh giá sản phẩm bằng bình luận
- Chatbox hỗ trợ khách hàng

## Công nghệ sử dụng

- Backend: Java Servlet, JSP
- Frontend: HTML, CSS, JavaScript, Bootstrap, jQuery, Ajax, Dialogflow
- Database: MySQL
- Authentication: OAuth (Google, Facebook)
- Payment Gateway: VNPay
- Email Service: JavaMail API

## Cài đặt và chạy dự án

1. Clone repository:
   ```bash
   git clone https://github.com/M3-PTTM/case-study-module-3.git
   ```
2. Import project vào IDE (Eclipse/IntelliJ)
3. Cấu hình database MySQL và dự liệu mẫu ở file `db.sql`
4. Chạy server Tomcat
5. Truy cập website tại: `http://localhost:8080/`

## Cấu trúc thư mục

```
E-Commerce-Project/
│── src/
│   ├── servlet/           # Servlet xử lý yêu cầu từ client
│   ├── model/             # Các class mô hình dữ liệu
│   ├── dao/               # Tầng truy vấn database
│   ├── utils/             # Kết nối database
│   ├── vnpay/             # Thanh toán vnpay
│── web/
│                          # Hình ảnh, CSS, JS
│                          # Giao diện JSP
│── db.sql                 # File chứa dữ liệu mẫu
│── README.md
```
## Dear HR

Nếu bạn đọc được những dòng này mà tôi vẫn không có được cơ hội phỏng vấn ở công ty bạn thì... Tôi cũng không buồn lắm đâu, tôi biết cái dự án này chẳng ra gì cả. Thật sự thì có khá nhiều thứ mà tôi muốn học, hoặc chí ít hiện tại tôi đang cần học khá nhiều thứ nên vẫn chưa có thời gian để làm một dự án hoàn chỉnh hơn. Chỉ là mong bạn có thể phản hồi lại mail của tôi và nói rõ cho tôi biết là tôi đang thiếu thứ gì. Chỉ cần tôi học xong những gì tôi nghĩ là cần thiết thì tôi sẽ bắt tay ngay vào việc code một dự án thật hoành tráng cho bạn xem. Biết đâu một ngày nào đó bạn lại đọc được mail của tôi và xem được một dự án khác hoàn thiện hơn.