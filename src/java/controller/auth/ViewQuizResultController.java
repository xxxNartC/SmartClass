package controller.auth;

import dao.QuizDAO;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.QuizResult;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ViewQuizResultController", urlPatterns = {"/viewquizresult"})
public class ViewQuizResultController extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String account_id = String.valueOf(session.getAttribute("account_id"));
        String learner_id = account_id;
        request.setAttribute("learner_id", learner_id);
        String course_id = request.getParameter("course_id");
        QuizDAO quizDAO = new QuizDAO();
        List<QuizResult> quizResults = quizDAO.getQuizResultsByLearnerIdAndCourseId(learner_id, course_id);

        int completedChaptersCount = quizDAO.getCompletedChaptersCount(learner_id, course_id);
        int totalChaptersCount = quizDAO.getTotalChaptersCount(course_id);
        int learnerProgressPercentage = (completedChaptersCount * 100) / totalChaptersCount;

        request.setAttribute("quizResults", quizResults);
        request.setAttribute("learner_id", learner_id);
        request.setAttribute("course_id", course_id);
        request.setAttribute("learnerProgressPercentage", learnerProgressPercentage);

        // Check if the learner has completed all quizzes in the course
        if (learnerProgressPercentage == 100) {
            // Add a button to update the course certificate
            request.setAttribute("showUpdateCertificateButton", true);
        }

        request.getRequestDispatcher("viewquizresult.jsp").forward(request, response);
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
