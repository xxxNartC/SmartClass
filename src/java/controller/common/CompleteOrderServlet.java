package controller.common;

import controller.common.MailUtil;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/completeOrder")
public class CompleteOrderServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Địa chỉ email nhận thông báo
        String emailRecipient = "luubachtung@gmail.com";
        String subject = "Thông Báo Đơn Hàng";
        String message = "Bạn có một đơn hàng mới cần được xét duyệt.";

        // Gửi email sử dụng MailUtil
        try {
            MailUtil.sendNewPasswordEmail(emailRecipient, message);
            System.out.println("Email đã được gửi thành công!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Sau khi gửi email, chuyển hướng về trang index.jsp
        response.sendRedirect("index.jsp");
    }
}
