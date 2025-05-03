package controller.common;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.User;
import dao.DAOForget;

@WebServlet(name = "SaveUserProfileServlet", urlPatterns = {"/saveUserProfile"})
public class SaveUserProfileServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String phone = request.getParameter("phone");
        String dob = request.getParameter("dob");

        HttpSession session = request.getSession();
        byte[] avatarBytes = (byte[]) session.getAttribute("avatarBytes");

        User user = (User) session.getAttribute("user");

        if (user != null) {
            user.setUsername(username);
            user.setPhone(phone);
            user.setDob(dob);
            
            UserDAO userDAO = new UserDAO();
            boolean updateSuccess;

            // Update only if avatarBytes are set, otherwise keep the existing avatar
            if (avatarBytes != null) {
                updateSuccess = userDAO.updateUser(user, avatarBytes);
                // Clear avatarBytes after updating to avoid reusing them
                session.removeAttribute("avatarBytes");
            } else {
                // Update fields without changing avatar
                updateSuccess = userDAO.updateUser(user, null);
            }

            if (updateSuccess) {
                session.setAttribute("user", user);
                response.sendRedirect("UserProfile.jsp?success=true");
            } else {
                response.sendRedirect("UserProfile.jsp?error=true");
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}


