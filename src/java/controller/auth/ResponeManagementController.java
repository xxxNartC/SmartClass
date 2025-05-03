/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.auth;

import dao.CommentLessonDAO;
import dao.SubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Account;
import model.CommentLesson;
import model.Subject;
import model.User;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ResponeManagementController", urlPatterns = {"/responemanagement"})
public class ResponeManagementController extends HttpServlet {

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
            CommentLessonDAO cld = new CommentLessonDAO();
            SubjectDAO sd = new SubjectDAO();

            User account = (User) request.getSession().getAttribute("user");
            int account_id = account.getAccount_id();

            String subject_id = request.getParameter("subject_id");

            int pageUnRep = 1;
            int recordsPerPage = 5;
            if (request.getParameter("pageUnRep") != null) {
                pageUnRep = Integer.parseInt(request.getParameter("pageUnRep"));
            }
            int pageRep = 1;
            if (request.getParameter("pageRep") != null) {
                pageRep = Integer.parseInt(request.getParameter("pageRep"));
                request.setAttribute("isShowRep", true);
            }

//        Comment unreply
            ArrayList<CommentLesson> datacommentLessonUnreplied = cld.selectAllCommentLessonBySubject_id(subject_id, recordsPerPage * (pageUnRep - 1), recordsPerPage);
            int noOfRecords = cld.getNoOfCommentsBySubject(subject_id);
            if (noOfRecords > 0) {
                int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
                request.setAttribute("noOfPages", noOfPages);
            }

            int noOfRecordsReplied = cld.getNoOfCommentsRepliedBySubject(subject_id, account_id);
            if (noOfRecordsReplied > 0) {
                int noOfPages = (int) Math.ceil(noOfRecordsReplied * 1.0 / recordsPerPage);
                request.setAttribute("noOfRecordsReplied", noOfPages);
            }

            Subject subject = sd.selectNameAnDesSubject(subject_id);

            String message = (String) request.getSession().getAttribute("message");
            request.setAttribute("message", message);
            request.getSession().removeAttribute("message");
            request.setAttribute("datacommentLessonUnreplied", datacommentLessonUnreplied);
            request.setAttribute("subject", subject);
            request.setAttribute("pageUnRep", pageUnRep);
            request.setAttribute("pageRep", pageRep);
            request.setAttribute("subject_id", subject_id);

            request.getRequestDispatcher("responsemanagement.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
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
        processRequest(request, response);
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
