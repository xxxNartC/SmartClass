package controller.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import dao.UserDAO;
import model.User;

@WebServlet(name = "ChangePasswordServlet", urlPatterns = {"/changePassword"})
public class ChangePasswordServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Lấy thông tin từ form
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        // Mã hóa mật khẩu hiện tại nhập vào bằng MD5
        String hashedCurrentPassword = hashPasswordMD5(currentPassword);

        // Kiểm tra mật khẩu hiện tại đã mã hóa có khớp với mật khẩu trong cơ sở dữ liệu
        if (!user.getPassword().equals(hashedCurrentPassword)) {
            response.sendRedirect("ChangePassword.jsp?error=Current password is incorrect.");
            return;
        }

        // Kiểm tra mật khẩu mới và xác nhận mật khẩu mới có khớp nhau
        if (!newPassword.equals(confirmPassword)) {
            response.sendRedirect("ChangePassword.jsp?error=New password and confirm password do not match.");
            return;
        }

        // Mã hóa mật khẩu mới trước khi lưu vào cơ sở dữ liệu
        String hashedNewPassword = hashPasswordMD5(newPassword);
        user.setPassword(hashedNewPassword);

        // Gọi DAO để cập nhật mật khẩu vào cơ sở dữ liệu
        UserDAO userDAO = new UserDAO();
        boolean updateSuccess = userDAO.updateUserPassword(user);

        if (updateSuccess) {
            response.sendRedirect("UserProfile.jsp?passwordSuccess=true");
        } else {
            response.sendRedirect("ChangePassword.jsp?error=Failed to change password. Please try again.");
        }

    }

    // Hàm mã hóa mật khẩu bằng MD5
    private String hashPasswordMD5(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
