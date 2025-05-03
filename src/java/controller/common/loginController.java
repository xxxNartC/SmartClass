package controller.common;

import dao.UserDAO;
import model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
@WebServlet(name = "loginController", urlPatterns = {"/login"})
public class loginController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            String usernameOrEmail = request.getParameter("usernameOrEmail");
            String password = request.getParameter("password");

            // Sử dụng MD5Encryption để băm mật khẩu
            MD5Encryption md5Encryption = new MD5Encryption();
            String hashedPassword = md5Encryption.hashPassword(password);

            UserDAO dao = new UserDAO();

            if (!dao.checkUsernameExists(usernameOrEmail) && !dao.checkEmailExists(usernameOrEmail)) {                   
                request.setAttribute("error", "Username or Email not found. Please try again.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;   
            }
            
                User user = dao.login(usernameOrEmail, hashedPassword);

                if (user == null) {
                    // Tên người dùng hoặc mật khẩu không hợp lệ
                    request.setAttribute("usernameOrEmail", usernameOrEmail);
                    request.setAttribute("error", "Invalid password. Please try again.");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                } else if (user.getActive() == 0) {
                    // Tài khoản tồn tại nhưng chưa được kích hoạt
                    request.setAttribute("usernameOrEmail", usernameOrEmail);
                    request.setAttribute("error", "Account is not active. Please check your email to activate.");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                } else {
                    // Tài khoản hợp lệ và đang hoạt động
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    session.setAttribute("role_id", user.getRole_id());
                    session.setAttribute("account_id", user.getAccount_id());

                    // Chuyển hướng dựa trên role_id
                    switch (user.getRole_id()) {
                        case 1: // Admin
                            response.sendRedirect("index.jsp");
                            break;
                        case 2: // User role 2
                        case 3: // User role 3
                        case 4: // User role 4
                            response.sendRedirect("index.jsp");
                            break;
                        default:
                            response.sendRedirect("index.jsp");
                            break;
                    }
                }
            }catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }

        @Override
        protected void doGet
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
        }

        @Override
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
        }

        @Override
        public String getServletInfo
        
            () {
        return "Handles user login";
        }
    }
