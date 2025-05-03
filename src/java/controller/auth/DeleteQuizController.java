/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.auth;

import dao.QuizDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Quiz;

/**
 *
 * @author admin
 */
@WebServlet(name = "DeleteQuizController", urlPatterns = {"/deletequiz"})
public class DeleteQuizController extends HttpServlet {

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
            out.println("<title>Servlet DeleteQuizController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DeleteQuizController at " + request.getContextPath() + "</h1>");
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
        String chapterid = request.getParameter("chapter_id");
        String subjectid = request.getParameter("subject_id");
        String courseid = request.getParameter("course_id");
        QuizDAO qd = new QuizDAO();

        // Check if the quiz exists before attempting to delete it
        if (qd.checkQuizExitsByChapterID(chapterid)) {
            Quiz q = qd.getQuizByChapterID(chapterid);
            qd.deleteQuiz(q.getQuiz_id());
            String mess_del = "Delete Quiz Sussecfully!!!!";
            response.sendRedirect("manageLesson?mess_del=" + mess_del + "&subject_id=" + subjectid + "&course_id=" + courseid + "&chapter_id=" + chapterid);
        } else {
            // Handle the case where the quiz doesn't exist
            // You can redirect to an error page or display an error message
            String mess_del = "Quiz not found!";
            response.sendRedirect("manageLesson?mess_del=" + mess_del + "&subject_id=" + subjectid + "&course_id=" + courseid + "&chapter_id=" + chapterid);
        }
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
