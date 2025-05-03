/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.auth;

import dao.BlockedAccountDAO;
import dao.CommentLessonDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.Date;
import model.Account;
import model.AccountReport;
import model.CommentLesson;
import model.User;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ReportCommentController", urlPatterns = {"/reportComment"})
public class ReportCommentController extends HttpServlet {

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
        CommentLessonDAO cld = new CommentLessonDAO();

        // Get subject_id and course_id
        String comment_id = request.getParameter("comment_id");
        String subject_id = request.getParameter("subject_id");
//        String course_id = request.getParameter("course_id");
        String lesson_id = request.getParameter("lesson_id");
        String chapter_id = request.getParameter("chapter_id");

        CommentLesson commentlesson = cld.selectCommentByComment_id(Integer.parseInt(comment_id));

        request.setAttribute("commentlesson", commentlesson);
        request.setAttribute("comment_id", comment_id);
        request.setAttribute("subject_id", subject_id);
//        request.setAttribute("course_id", course_id);
        request.setAttribute("lesson_id", lesson_id);
        request.setAttribute("chapter_id", chapter_id);
        request.getRequestDispatcher("reportcomment.jsp").forward(request, response);
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
        response.setContentType("text/html;charset=UTF-8");
        BlockedAccountDAO ard = new BlockedAccountDAO();
        CommentLessonDAO cld = new CommentLessonDAO();
//        Session
        User account = (User) request.getSession().getAttribute("user");

//       Get Param
        String subject_id = request.getParameter("subject_id");
        String lesson_id = request.getParameter("lesson_id");
        String chapter_id = request.getParameter("chapter_id");
        String message = "";
        try {
            int account_id = Integer.parseInt(request.getParameter("account_id"));
            int reported_account_id = account.getAccount_id();
            String report_type = request.getParameter("report_type");
            String report_description = request.getParameter("report_description");

//            Date
            Date report_date = Date.from(Instant.now());

            if (report_type.equals("other")) {
                report_type = request.getParameter("other_type");
            }

            AccountReport accountReport = new AccountReport(account_id, reported_account_id, report_type, report_description, report_date, 0);

            if (ard.insertAccountReport(accountReport)) {
                String comment_id = request.getParameter("comment_id");
                cld.reportCommentLesson(comment_id);
                message = "Report successful";
            } else {
                message = "Report Fail";
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }

        request.setAttribute("message", message);
        if (account.getRole_id() == 3) {
            response.sendRedirect("responemanagement?subject_id=" + subject_id);
        } else {
            response.sendRedirect("lessonview?chapter_id=" + chapter_id + "&lesson_id=" + lesson_id);
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

}
