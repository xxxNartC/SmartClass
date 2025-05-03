/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.common;

import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import model.User;

@WebServlet(name = "UserProfileServlet", urlPatterns = {"/userProfile"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 10)
public class UserProfileServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        switch (action) {
            case "uploadTempAvatar":
                handleUploadTempAvatar(request, response, session);
                break;
            case "saveAvatar":
                handleSaveAvatar(request, response, session);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        if ("displayTempAvatar".equals(action)) {
            handleDisplayTempAvatar(response, session);
        } else if ("displayAvatar".equals(action)) {
            handleDisplayAvatar(response, session);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    // Handle temporary avatar upload
    private void handleUploadTempAvatar(HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws IOException, ServletException {
        Part filePart = request.getPart("avatar");

        InputStream inputStream = filePart.getInputStream();
        byte[] avatarBytes = inputStream.readAllBytes();

        session.setAttribute("tempAvatarBytes", avatarBytes);

        response.setContentType("application/json");
        response.getWriter().write("{\"success\": true}");
    }

    // Handle displaying the temporary avatar
    private void handleDisplayTempAvatar(HttpServletResponse response, HttpSession session) throws IOException {
        byte[] avatarBytes = (byte[]) session.getAttribute("tempAvatarBytes");

        if (avatarBytes != null) {
            response.setContentType("image/jpeg");
            response.setContentLength(avatarBytes.length);
            response.getOutputStream().write(avatarBytes);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "No temporary avatar found");
        }
    }

    // Display the user's current avatar from the database
    private void handleDisplayAvatar(HttpServletResponse response, HttpSession session) throws IOException {
        User user = (User) session.getAttribute("user");
        byte[] avatarBytes = (byte[]) session.getAttribute("tempAvatarBytes");

        // Fetch avatar from database if not found in session
        if (avatarBytes == null && user != null) {
            UserDAO userDAO = new UserDAO();
            avatarBytes = userDAO.getAvatar(user.getAccount_id());
            if (avatarBytes != null) {
                session.setAttribute("tempAvatarBytes", avatarBytes); // Store in session for later use
            }
        }

        if (avatarBytes != null) {
            response.setContentType("image/jpeg");
            response.setContentLength(avatarBytes.length);
            response.getOutputStream().write(avatarBytes);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Avatar not found");
        }
    }

    // Handle saving the avatar to the database
    private void handleSaveAvatar(HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws IOException {
        User user = (User) session.getAttribute("user");
        byte[] avatarBytes = (byte[]) session.getAttribute("tempAvatarBytes");

        if (user != null && avatarBytes != null) {
            UserDAO userDAO = new UserDAO();
            boolean updateSuccess = userDAO.updateUser(user, avatarBytes);

            if (updateSuccess) {
                session.removeAttribute("tempAvatarBytes");
                session.setAttribute("user", user);
                response.setContentType("application/json");
                response.getWriter().write("{\"success\": true}");
            } else {
                response.getWriter().write("{\"success\": false}");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid avatar save request");
        }
    }
}
