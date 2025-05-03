package controller.common;

import dao.DAOForget;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Random;
import model.User;

@WebServlet(name = "ForgetPasswordServlet", urlPatterns = {"/forgotpass"})
public class ForgetPasswordServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String step = request.getParameter("step");
        DAOForget dao = new DAOForget();
        HttpSession session = request.getSession();

        // Step 1: Validate email
        if ("1".equals(step)) {
            String email = request.getParameter("email");
            User acc = dao.checkEmail(email);
            if (acc != null) {
                request.setAttribute("email", email);
                request.getRequestDispatcher("forgetpass/ForgetPassword2.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Email không tồn tại trong hệ thống.");
                request.setAttribute("email", email);
                request.getRequestDispatcher("forgetpass/ForgetPassword.jsp").forward(request, response);
            }
        }

        // Step 2: Validate username and phone
        if ("2".equals(step)) {
            String email = request.getParameter("email");
            String username = request.getParameter("username");
            String phone = request.getParameter("phone");

            boolean valid = dao.checkUsernameAndPhone(username, phone, email);
            if (valid) {
                request.setAttribute("email", email);
                request.getRequestDispatcher("forgetpass/ForgetPassword3.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Username hoặc phone sai. Vui lòng nhập lại.");
                request.setAttribute("email", email);
                request.setAttribute("username", username);
                request.setAttribute("phone", phone);
                request.getRequestDispatcher("forgetpass/ForgetPassword2.jsp").forward(request, response);
            }
        }

        // Step 3: Generate and send OTP
        if ("3".equals(step)) {
            String email = request.getParameter("email");

            String otpCode = MailUtil.generateOTP();
            MailUtil.sendOTP(email, otpCode);

            session.setAttribute("otpCode", otpCode);
            session.setAttribute("otpGenerationTime", LocalDateTime.now());

            request.setAttribute("email", email);
            request.getRequestDispatcher("forgetpass/ForgetPassword4.jsp").forward(request, response);
        }

        // Step 4: Validate OTP
        // Step 4: Validate OTP
        if ("4".equals(step)) {
            String enteredOtp = request.getParameter("otp");
            String email = request.getParameter("email");
            String sessionOtp = (String) session.getAttribute("otpCode");
            LocalDateTime otpGenerationTime = (LocalDateTime) session.getAttribute("otpGenerationTime");

            // Kiểm tra OTP có hợp lệ và còn trong thời gian không
            if (enteredOtp.equals(sessionOtp) && LocalDateTime.now().isBefore(otpGenerationTime.plusMinutes(5))) {
                // Tạo mật khẩu ngẫu nhiên gồm 6 chữ số
                String newPassword = generateRandomPassword();
                String hashedPassword = hashPassword(newPassword);

                // Cập nhật mật khẩu mới vào database
                boolean updateSuccess = dao.updatePassword(email, hashedPassword);

                if (updateSuccess) {
                    // Gửi email thông báo mật khẩu mới cho người dùng
                    MailUtil.sendNewPasswordEmail(email, newPassword);

                    // Chuyển hướng đến trang ForgetPassword5.jsp
                    request.setAttribute("email", email);
                    request.getRequestDispatcher("forgetpass/ForgetPassword5.jsp").forward(request, response);
                } else {
                    request.setAttribute("errorMessage", "Failed to update password. Please try again.");
                    request.setAttribute("email", email);
                    request.getRequestDispatcher("forgetpass/ForgetPassword4.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("errorMessage", "OTP không chính xác hoặc đã hết hạn.");
                request.setAttribute("email", email);
                request.getRequestDispatcher("forgetpass/ForgetPassword4.jsp").forward(request, response);
            }
        }
        

    }

    private String generateRandomPassword() {
        Random random = new Random();
        int password = 100000 + random.nextInt(900000); // Tạo mật khẩu gồm 6 chữ số
        return String.valueOf(password);
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            return null;
        }
    }
}
