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
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
@WebServlet(name = "registerController", urlPatterns = {"/register"})
public class registerController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String fullname = request.getParameter("fullname");
            String email = request.getParameter("email");
            String dob = request.getParameter("dob");
            String phone = request.getParameter("phone");
            int active = 1;
            int role_id = 2;

            // Use MD5Encryption to hash the password
            MD5Encryption md5Encryption = new MD5Encryption();
            String hashedPassword = md5Encryption.hashPassword(password);

            UserDAO dao = new UserDAO();
            User user = new User(username, hashedPassword, fullname, email, dob, phone, active, role_id);

            if (dao.checkUsernameExists(username)) {
                // Truyền lại các trường đã nhập và thông báo lỗi
                request.setAttribute("username", username);
                request.setAttribute("fullname", fullname);
                request.setAttribute("email", email);
                request.setAttribute("dob", dob);
                request.setAttribute("phone", phone);
                request.setAttribute("error", "Username already exists");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            } else if (dao.checkEmailExists(email)) {
                // Truyền lại các trường đã nhập và thông báo lỗi
                request.setAttribute("username", username);
                request.setAttribute("fullname", fullname);
                request.setAttribute("email", email);
                request.setAttribute("dob", dob);
                request.setAttribute("phone", phone);
                request.setAttribute("error", "Email already exists");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            } else if (dao.register(user)) {
                // Generate OTP         
                Random rand = new Random();
                int otp = rand.nextInt(900000) + 100000;
                // Send OTP to user's email         
                mail.sendOTP(email, String.valueOf(otp));
                // Store OTP in session       
                request.getSession().setAttribute("otp", otp);
                // Redirect to OTP verification page       
                response.sendRedirect("verifyOTP.jsp");
            } else {
                request.setAttribute("username", username);
                request.setAttribute("fullname", fullname);
                request.setAttribute("email", email);
                request.setAttribute("dob", dob);
                request.setAttribute("phone", phone);
                request.setAttribute("error", "Registration failed. Please try again.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(registerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private String generateOTP(int length) {
        Random random = new Random();
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < length; i++) {
            otp.append(random.nextInt(10));
        }
        return otp.toString();
    }
}
