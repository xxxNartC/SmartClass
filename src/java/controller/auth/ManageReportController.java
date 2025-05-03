/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.auth;

import dao.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import model.Account;
import model.AccountReport;
import model.User;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ManageReportController", urlPatterns = {"/manageReports"})
public class ManageReportController extends HttpServlet {

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
        try {
            AccountDAO accountDAO = new AccountDAO();
            List<AccountReport> reports = accountDAO.getAllPendingReports();
            request.setAttribute("reports", reports);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("manageReports.jsp").forward(request, response);
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
        AccountDAO accountDAO = new AccountDAO();
        String accountId = request.getParameter("account_id");
        String reportId = request.getParameter("report_id");
        User acc = (User) request.getSession().getAttribute("user");

        HttpSession session = request.getSession();
        String username = accountDAO.getUsernameById(Integer.parseInt(accountId));

        try {
            accountDAO.deleteReport(Integer.parseInt(reportId));
            session.setAttribute("manageSuccess", "Report deleted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("m\n" +
"    @Override\n" +
"    protected void doPost(HttpServletRequest request, HttpServletResponse response)\n" +
"            throws ServletException, IOException {\n" +
"        AccountDAO accountDAO = new AccountDAO();\n" +
"        String accountId = request.getParameter(\"account_id\");\n" +
"        String reportId = request.getParameter(\"report_id\");\n" +
"        User acc = (User) request.getSession().getAttribute(\"user\");\n" +
"\n" +
"        HttpSession session = request.getSession();\n" +
"        String username = accountDAO.getUsernameById(Integer.parseInt(accountId));\n" +
"\n" +
"        try {\n" +
"            accanageError", "An error occurred while processing the request.");
        }

        response.sendRedirect("manageReports");
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
