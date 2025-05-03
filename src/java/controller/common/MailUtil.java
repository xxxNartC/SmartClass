package controller.common;

import jakarta.mail.Session;
import java.util.Properties;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Random;
import model.Orders;

public class MailUtil {

    // Method to generate a 6-digit OTP
    public static String generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000); // 6-digit OTP
        return String.valueOf(otp);
    }

    // Method to send new password via email
    public static void sendNewPasswordEmail(String to, String newPassword) {
        String subject = "Your New Password";
        String content = "Your new password is: " + newPassword
                + ". Please log in and change your password in your user profile.";

        // Email credentials
        String username = "esmartlearnisp@gmail.com";
        String password = "cpqt hzfi czfh zupm"; // Application password

        // Properties for Gmail
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Mail session
        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create email message
            Message message = new MimeMessage(session);
            message.addHeader("Content-type", "text/html; charset=UTF-8");
            message.setFrom(new InternetAddress(username));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(content);

            // Send email
            Transport.send(message);
            System.out.println("New password sent successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendOrderStatusEmail(String to, Orders order, String status) {
        String subject = "Thông báo hóa đơn đơn hàng";
        StringBuilder content = new StringBuilder();
        content.append("Xin chào ").append(order.getAccountName()).append(",\n\n");
        content.append("Cảm ơn bạn đã mua hàng. Dưới đây là chi tiết hóa đơn của đơn hàng:\n");
        content.append("Mã đơn hàng: ").append(order.getOrderId()).append("\n");
        content.append("Tên môn học: ").append(order.getSubjectName()).append("\n");
        content.append("Ngày mua: ").append(order.getOrderDate()).append("\n");
        content.append("Tổng tiền: ").append(order.getTotalMoney()).append(" VND\n");
        content.append("Trạng thái đơn hàng: ").append(status.equals("Success") ? "Thành công" : "Thất bại").append("\n");
        content.append("\nCảm ơn bạn đã sử dụng dịch vụ của chúng tôi!\n");
        content.append("Trân trọng,\nE-SmartLearn ISP");

        String username = "esmartlearnisp@gmail.com";
        String password = "cpqt hzfi czfh zupm";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.addHeader("Content-type", "text/html; charset=UTF-8");
            message.setFrom(new InternetAddress(username));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(content.toString());

            Transport.send(message);
            System.out.println("Order invoice email sent successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to send OTP via email
    public static void sendOTP(String to, String otp) {
        String subject = "Your OTP Code";
        String content = "Your OTP code is: " + otp + ". This code is valid for 5 minutes.";

        // Email credentials
        String username = "esmartlearnisp@gmail.com";
        String password = "cpqt hzfi czfh zupm"; // Application password

        // Properties for Gmail
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Mail session
        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create email message
            Message message = new MimeMessage(session);
            message.addHeader("Content-type", "text/html; charset=UTF-8");
            message.setFrom(new InternetAddress(username));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(content);

            // Send email
            Transport.send(message);
            System.out.println("OTP sent successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to send a generic email
    public static void sendEmail(String to, String subject, String content) {
        String username = "esmartlearnisp@gmail.com";
        String password = "cpqt hzfi czfh zupm"; // Application password

        // Properties for Gmail
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Mail session
        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create email message
            Message message = new MimeMessage(session);
            message.addHeader("Content-type", "text/html; charset=UTF-8");
            message.setFrom(new InternetAddress(username));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(content);

            // Send email
            Transport.send(message);
            System.out.println("Email sent successfully to " + to);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
