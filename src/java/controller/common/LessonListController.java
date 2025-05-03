package controller.common;

import dao.LearnerSubjectDAO;
import dao.LessonDAO;
import dao.QuizDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Lesson;
import model.Quiz;
import model.QuizResult;
import model.LearnerCourse;

@WebServlet(name = "LessonListController", urlPatterns = {"/lessonlist"})
public class LessonListController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Initialize DAOs
        LearnerSubjectDAO lsd = new LearnerSubjectDAO();
        LessonDAO ld = new LessonDAO();
        QuizDAO qd = new QuizDAO();

        // Retrieve parameters from the request
        String indexRaw = request.getParameter("index");
        String search = request.getParameter("search_name");
        String chapterId = request.getParameter("chapter_id");
        String courseId = request.getParameter("course_id");
        String subjectId = request.getParameter("subject_id");

        // Retrieve learner ID from session
        HttpSession session = request.getSession();
        String account_id = String.valueOf(session.getAttribute("account_id"));
        String learner_id = account_id;


        // Fetch and update quiz statuses based on learner's progress
        List<Quiz> listQuiz = qd.getAllQuizByCourseID(courseId);
        if (!listQuiz.isEmpty()) {

            for (Quiz q : listQuiz) {
                if (qd.CheckLearnerTakeQuiz(q.getQuiz_id(), learner_id)) {
                    QuizResult result = qd.getQuizResult(q.getQuiz_id(), learner_id);
                    if (result.getStatus().equals("1")) {
                        q.setStatus("passed");
                    } else {
                        q.setStatus("not pass");
                    }

                } else {
                    q.setStatus("false");
                }
            }
            request.setAttribute("listQuiz", listQuiz);
        }

//        if (lsd.CheckStatusLearnerSubject(accountId, subjectId) == 0) {
//            request.setAttribute("subject_id", subjectId);
//            request.setAttribute("course_id", courseId);
//            request.setAttribute("err", "You need to purchase the course in advance");
//            RequestDispatcher dispatcher = request.getRequestDispatcher("course-details");
//            dispatcher.forward(request, response);
//        } else {
// Pagination and searching
int index = 1; // Default to page 1
        List<Lesson> listLessons;
        int page;

        if (indexRaw != null && !indexRaw.isEmpty()) {
            try {
                index = Integer.parseInt(indexRaw);
            } catch (NumberFormatException e) {
                // Handle invalid input, e.g., log the error or display an error message
                // For now, just use the default page 1
            }
        }

        if (search != null && !search.isEmpty()) {
            page = ld.getNumPageByName(chapterId, search);
            listLessons = ld.filterLessonPaging(chapterId, search, index);
            request.setAttribute("search_name", search);
        } else {
            page = ld.getNumPageByChapterID(chapterId);
            listLessons = ld.getLessonPageByChapterID(chapterId, index);
        }

        // Set attributes for pagination and lessons
        request.setAttribute("page", page);
        request.setAttribute("listLes", listLessons);
        request.setAttribute("chapter_id", chapterId);
        request.setAttribute("course_id", courseId);
        request.setAttribute("subject_id", subjectId);

        // Forward to JSP
        request.getRequestDispatcher("lessonlist.jsp").forward(request, response);
    }
//    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}