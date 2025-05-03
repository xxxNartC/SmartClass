/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.auth;

import dao.CommentLessonDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import model.Account;
import model.CommentLesson;
import model.User;

/**
 *
 * @author Admin
 */
@WebServlet(name = "AddCommentLessonController", urlPatterns = {"/addcommentlesson"}) 
public class AddCommentLessonController extends HttpServlet {

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
            out.println("<title>Servlet AddCommentLessonController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddCommentLessonController at " + request.getContextPath() + "</h1>");
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
        try {

            response.setContentType("text/html;charset=UTF-8");
            String message = "";
            // Add
//           DAO
            CommentLessonDAO cl = new CommentLessonDAO();

            // Session
            User account = (User) request.getSession().getAttribute("user");
            int account_id = account.getAccount_id();

//        Date
            LocalDateTime date = LocalDateTime.now();
            Date comment_date = Date.from(date.atZone(ZoneId.systemDefault()).toInstant());

            // Get input 
            String comment = request.getParameter("comment");
            String subject_id = request.getParameter("subject_id");
//            String course_id = request.getParameter("course_id");
            String chapter_id = request.getParameter("chapter_id");
            String lesson_id = request.getParameter("lesson_id");
            String parent_comment_id = request.getParameter("comment_id") == "" ? null : request.getParameter("comment_id");
            if (account.getRole_id() == 2) {
                // Set input 
                CommentLesson commentLesson = new CommentLesson(0, account_id, Integer.parseInt(lesson_id), comment, comment_date, "", parent_comment_id == null ? null : Integer.parseInt(parent_comment_id));
                cl.insertCommentLesson(commentLesson);
                response.sendRedirect("lessonview?chapter_id=" + chapter_id + "&lesson_id=" + lesson_id);
            } else {
                CommentLesson commentLesson = new CommentLesson(0, account_id, Integer.parseInt(lesson_id), comment, comment_date, "", Integer.parseInt(parent_comment_id));
                if (cl.insertCommentLesson(commentLesson)) {
                    message = "Reply successful";
                    cl.updateStatusComment(3, parent_comment_id);
                } else {
                    message = "Reply Fail";
                }
                request.getSession().setAttribute("message", message);
                response.sendRedirect("responemanagement?subject_id=" + subject_id);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
