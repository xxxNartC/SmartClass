/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.auth;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;
import dao.RoleChangeRequestDAO;

/**
 *
 * @author admin
 */
@WebServlet(name = "SubmitRoleChangeRequestController", urlPatterns = {"/submitRoleChangeRequest"})
public class SubmitRoleChangeRequestController extends HttpServlet {

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
            out.println("<title>Servlet SubmitRoleChangeRequest</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SubmitRoleChangeRequest at " + request.getContextPath() + "</h1>");
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
        // Retrieve the Account object from the session
        Integer accountId = (Integer) request.getSession().getAttribute("account_id");
       

        // Check if the Account object is available
        if (accountId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Get the requested role ID and content from the form submission
        int requestedRoleId = Integer.parseInt(request.getParameter("role"));
        String content = request.getParameter("content");

        // Create an instance of RoleChangeRequestDAO and save the role change request
        RoleChangeRequestDAO dao = new RoleChangeRequestDAO();
        dao.saveRoleChangeRequest(accountId, requestedRoleId, content);

        // Set a confirmation message and forward to the JSP page
        request.setAttribute("Message", "You have successfully submitted!");
        request.getRequestDispatcher("requestRoleChange.jsp").forward(request, response);
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

