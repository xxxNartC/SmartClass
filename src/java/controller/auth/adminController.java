package controllerAuth;

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

/**
 *
 * @author admin
 */
@WebServlet(name = "adminController", urlPatterns = {"/admin"})
public class adminController extends HttpServlet {

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
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    try (PrintWriter out = response.getWriter()) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user != null && user.getRole_id() == 1) {
            // Admin user
            UserDAO dao = new UserDAO();
            String action = request.getParameter("action");
            
            if (action != null) {
                if (action.equals("editRoleChange")) {
                    // Xử lý yêu cầu thay đổi role
                    int accountId = Integer.parseInt(request.getParameter("account_id"));
                    int newRoleId = Integer.parseInt(request.getParameter("new_role_id"));

                    boolean success = dao.approveRoleChangeRequest(accountId, newRoleId);
                    if (success) {
                        request.setAttribute("message", "Role change updated successfully.");
                    } else {
                        request.setAttribute("message", "Failed to update role change.");
                    }
                    
                    request.getRequestDispatcher("admin.jsp").forward(request, response);
                }
            } else {
                request.getRequestDispatcher("admin.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}


}
