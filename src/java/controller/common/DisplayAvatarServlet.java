/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.common;

import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author bacht
 */
@WebServlet(name = "DisplayAvatarServlet", urlPatterns = {"/displayAvatar"})
public class DisplayAvatarServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null) {
            UserDAO userDAO = new UserDAO();
            byte[] avatarBytes = userDAO.getAvatar(user.getAccount_id());

            if (avatarBytes != null) {
                response.setContentType("image/jpeg");
                response.setContentLength(avatarBytes.length);
                response.getOutputStream().write(avatarBytes);
            } else {
                // Serve a default image if no avatar is found
                response.sendRedirect("path/to/default/avatar.jpg");
            }
        }
    }
}
