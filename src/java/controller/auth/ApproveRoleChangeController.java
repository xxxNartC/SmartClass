/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.auth;

import dao.AccountDAO;
import dao.AdminDAO;
import dao.RoleChangeRequestDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
@WebServlet(name = "ApproveRoleChangeController", urlPatterns = {"/approveRoleChange"})
public class ApproveRoleChangeController extends HttpServlet {

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
            out.println("<title>Servlet ApproveRoleChangeController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ApproveRoleChangeController at " + request.getContextPath() + "</h1>");
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
        int accountId = Integer.parseInt(request.getParameter("account_id"));
        int newRoleId = Integer.parseInt(request.getParameter("new_role_id"));
        
        AdminDAO ad = new AdminDAO();
        AccountDAO accountDAO = new AccountDAO();    
        accountDAO.updateRole(accountId, newRoleId);

        RoleChangeRequestDAO reDAO = new RoleChangeRequestDAO();
        String fullname = accountDAO.getFullnameById(accountId);
        String email = accountDAO.getEmailById(accountId);
        String Role_name = reDAO.getRoleNameById(newRoleId);
        String username = accountDAO.getUsernameById(accountId);
        
//        ad.insertAdminHistory(acc.getAccount_id(), "Approve", "Role request of "+ username + " to be " +  Role_name  );
//        EmailUtils.sendEmail(email, "Hello " + fullname + "! \nWelcome to a part of us ", "You have become " + Role_name + " for learn X");

        RoleChangeRequestDAO requestDAO = new RoleChangeRequestDAO();
        requestDAO.deleteRoleChangeRequest(accountId);

        response.sendRedirect("manageRoleRequests");
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

}
