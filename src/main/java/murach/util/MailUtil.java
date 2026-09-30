package murach.util;

import java.util.Properties;
import jakarta.mail.Address;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class MailUtil {

    public static void sendMail(String to, String from, String subject, String body, boolean bodyIsHTML) 
            throws MessagingException {
        
        // 1. Cấu hình các thông số kết nối SMTP (Ví dụ dùng Gmail SMTP)
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // 2. Tạo phiên làm việc (Session)
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true); // In log ra cửa sổ Output để dễ kiểm tra lỗi

        // 3. Soạn thảo nội dung email
        Message message = new MimeMessage(session);
        message.setSubject(subject);
        
        if (bodyIsHTML) {
            message.setContent(body, "text/html; charset=utf-8");
        } else {
            message.setText(body);
        }

        // 4. Thiết lập địa chỉ người gửi và người nhận
        Address fromAddress = new InternetAddress(from);
        Address toAddress = new InternetAddress(to);
        message.setFrom(fromAddress);
        message.setRecipient(Message.RecipientType.TO, toAddress);

        // 5. Tiến hành gửi mail qua Transport
        Transport transport = session.getTransport();
        // Thay thế bằng tài khoản email thực tế và Mật khẩu ứng dụng (App Password) của bạn
        transport.connect("nguyeductrunh123@gmail.com", "gatb hymr havq dyws"); 
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
}