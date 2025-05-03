/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.common;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Lesson;
import dao.LessonDAO;
import model.CommentLesson;
import dao.CommentLessonDAO;
import java.util.Date;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import dao.LearnerLessonDAO;
import jakarta.servlet.http.HttpSession;
import model.LearnerLesson;

/**
 *
 * @author TRUONG GIANG
 */
@WebServlet(name = "LessonViewController", urlPatterns = {"/lessonview"})
public class LessonViewController extends HttpServlet {

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
        // Get the chapter ID from the request parameters
        String chapterId = request.getParameter("chapter_id");
//        String lessonName = request.getParameter("lesson_name");
        
        String lesson_id = request.getParameter("lesson_id");

        // Get the lessons by chapter ID and lesson name
        LessonDAO lessonDAO = new LessonDAO();
        List<Lesson> lessons = lessonDAO.getLessonsById(lesson_id);
        List<Lesson> lessons1 = lessonDAO.getLessonByChapterID(chapterId);

        // Set the lessons in the request attribute
        request.setAttribute("lessons", lessons);
        request.setAttribute("lessons1", lessons1);
        
        if (lesson_id != null && !lesson_id.trim().isEmpty() && !"null".equals(lesson_id)) {
                CommentLessonDAO cld = new CommentLessonDAO();
                ArrayList<CommentLesson> datacommentlesson = cld.selectAllCommentLesson(lesson_id);
                ArrayList<CommentLesson> datacommentlessonIsParent = cld.selectAllCommentLessonISParent(lesson_id);

                request.setAttribute("datacommentlesson", datacommentlesson);
                request.setAttribute("datacommentlessonIsParent", datacommentlessonIsParent);

            }
        
         request.setAttribute("chapter_id", chapterId);
                request.setAttribute("lesson_id", lesson_id);
        
        
        // Forward the request to the LessonView.jsp
          request.getRequestDispatcher("LessonView.jsp").forward(request, response);
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
         String chapterId = request.getParameter("chapter_id");
        
        // Get the lesson name from the request
        String lessonName = request.getParameter("lesson_name");

        // Get the learner ID from the session
        HttpSession session = request.getSession();
        // Check if the learnerId is present in the session
        if (session.getAttribute("account_id") != null) {
            int learnerId = (int) session.getAttribute("account_id");

            // Get the lesson ID from the request
           String lessonId = request.getParameter("lesson_id");

            // Update the learner lesson status
            LearnerLessonDAO learnerLessonDAO = new LearnerLessonDAO();
            learnerLessonDAO.updateLearnerLessonStatus(learnerId, lessonId);
            LessonDAO lessonDAO = new LessonDAO();
            List<Lesson> lessons1 = lessonDAO.getLessonByChapterID(chapterId);
            request.setAttribute("lessons1", lessons1);
            request.setAttribute("chapter_id",chapterId);
            // Set success message            
            request.setAttribute("successMessage", "Lesson marked as completed successfully!");
            // Redirect back to the lesson view with chapter_id and lesson_id
            response.sendRedirect("lessonview?lesson_name=" + lessonName + "&chapter_id=" + chapterId + "&lesson_id=" + lessonId);
        } else {
            // Handle the case where learnerId is not found in the session
            // You can redirect to a login page or display an error message
            response.sendRedirect("login.jsp"); // Replace with your desired action
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
