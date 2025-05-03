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
 * @author slhoa
 */
@WebServlet(name="AddQuizController", urlPatterns={"/addquiz"})
public class AddQuizController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet AddQuizController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddQuizController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String chapterid = request.getParameter("chapterid");
        String subjectid = request.getParameter("subjectid");
        String courseid = request.getParameter("courseid");
        QuizDAO quizd = new QuizDAO();
        String mess = request.getParameter("mess");
        request.setAttribute("mess", mess);

        if (quizd.checkQuizExitsByChapterID(chapterid)) {
            request.setAttribute("action", "update");
            request.setAttribute("detailQuiz", quizd.getQuizByChapterID(chapterid));
        } else {
            request.setAttribute("action", "add");
        }

        // check xem la co add duoc quiz khong > 10 cau hoi & 4,4,2 level
        if (quizd.checkNumberOfQuestion(chapterid) && quizd.checkNumberOfQuestionEachLevel(chapterid)) {
            // thoa man
            request.setAttribute("canAdd", true);
        } else {
            // khong thoa man
            request.setAttribute("canAdd", false);
        }

        if (quizd.checkOption15Questions(chapterid)) {

            request.setAttribute("displayOption15", true);
        } else {

            request.setAttribute("displayOption15", false);
        }
        
        if (quizd.checkOption20Questions(chapterid)) {

            request.setAttribute("displayOption20", true);
        } else {

            request.setAttribute("displayOption20", false);
        }


        request.setAttribute("chapterid", chapterid);
        request.setAttribute("subjectid", subjectid);
        request.setAttribute("courseid", courseid);
        request.getRequestDispatcher("addquiz.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        QuizDAO qd = new QuizDAO();

        String name = request.getParameter("quiz_name");
        String chapterid = request.getParameter("chapter_id");
        String subjectid = request.getParameter("subject_id");
        String courseid = request.getParameter("course_id");
        String num_of_question = request.getParameter("no_question");
        String mess;
        if (!qd.checkQuizExitsByChapterID(chapterid)) {
            qd.addQuiz(name, chapterid, num_of_question);
            mess = "Add Quiz Successfully!!";
        } else {
            qd.updateQuiz(name, chapterid, num_of_question);
            Quiz q = qd.getQuizByChapterID(chapterid);
            request.setAttribute("detailQuiz", q);
            mess = "Update Quiz Successfully!!";
        }

        request.setAttribute("chapterid", chapterid);
        request.setAttribute("subjectid", subjectid);
        request.setAttribute("courseid", courseid);
        request.setAttribute("mess", mess);
        request.getRequestDispatcher("addquiz.jsp").forward(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}



