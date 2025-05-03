package controller.common;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part; // Thêm import cho Part
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import model.User;

@WebServlet(name = "UploadAvatarServlet", urlPatterns = {"/uploadAvatar"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 1 MB
                 maxFileSize = 1024 * 1024 * 5,  // 5 MB
                 maxRequestSize = 1024 * 1024 * 10) // 10 MB
public class UploadAvatarServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Part filePart = request.getPart("avatar");
        
        // Convert image to byte array
        InputStream inputStream = filePart.getInputStream();
        byte[] avatarBytes = inputStream.readAllBytes();
        
        // Store the avatar bytes in the session temporarily
        HttpSession session = request.getSession();
        session.setAttribute("avatarBytes", avatarBytes);

        // Return success response
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print("{\"success\": true}");
        out.flush();
    }
}
