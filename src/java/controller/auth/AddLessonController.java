package controller.auth;

import dao.LessonDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.Lesson;

@WebServlet(name = "AddLessonController", urlPatterns = {"/addLesson"})
public class AddLessonController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String course_id = request.getParameter("course_id");
        String subject_id = request.getParameter("subject_id");
        String chapter_id = request.getParameter("chapter_id");
        request.setAttribute("course_id", course_id);
        request.setAttribute("subject_id", subject_id);
        request.setAttribute("chapter_id", chapter_id);
        request.getRequestDispatcher("/addLesson.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String course_id = request.getParameter("course_id");
        String subject_id = request.getParameter("subject_id");
        String chapter_id = request.getParameter("chapter_id");
        String lesson_no = request.getParameter("lesson_no");
        String lesson_name = request.getParameter("lesson_name");
        String video = request.getParameter("video");
        String document = request.getParameter("document");
        String description = request.getParameter("description");
        LessonDAO lessonDAO = new LessonDAO();
        try {
            Integer.parseInt(lesson_no);
        } catch (NumberFormatException e) {
            request.setAttribute("lesson_no_error", "Lesson No must be a number.");
            request.setAttribute("course_id", course_id);
            request.setAttribute("subject_id", subject_id);
            request.setAttribute("chapter_id", chapter_id);
            request.getRequestDispatcher("/addLesson.jsp").forward(request, response);
            return;
        }
        if (Integer.parseInt(lesson_no) <= 0) {
            request.setAttribute("lesson_no_error", "Lesson No must be greater than 0.");
            request.setAttribute("course_id", course_id);
            request.setAttribute("subject_id", subject_id);
            request.setAttribute("chapter_id", chapter_id);
            request.getRequestDispatcher("/addLesson.jsp").forward(request, response);
            return;
        }
        if (lessonDAO.checkLessonNo(chapter_id, lesson_no)) {
            request.setAttribute("course_id", course_id);
            request.setAttribute("subject_id", subject_id);
            request.setAttribute("chapter_id", chapter_id);
            request.setAttribute("lesson_no_error", "Lesson No already exists");
            request.getRequestDispatcher("/addLesson.jsp").forward(request, response);
        } else if (lessonDAO.checkLessonName(chapter_id, lesson_name)) {
            request.setAttribute("course_id", course_id);
            request.setAttribute("subject_id", subject_id);
            request.setAttribute("chapter_id", chapter_id);
            request.setAttribute("lesson_name_error", "Lesson Name already exists");
            request.getRequestDispatcher("/addLesson.jsp").forward(request, response);
        } else if (description.length() < 10 || description.length() > 200) {
            request.setAttribute("course_id", course_id);
            request.setAttribute("subject_id", subject_id);
            request.setAttribute("chapter_id", chapter_id);
            request.setAttribute("description_error", "Description must be between 10 and 500 characters.");
            request.getRequestDispatcher("/addLesson.jsp").forward(request, response);
        } else {
            lessonDAO.insertLesson(chapter_id, lesson_no, lesson_name, video, document, description);
            response.sendRedirect("manageLesson?course_id=" + course_id + "&subject_id=" + subject_id + "&chapter_id=" + chapter_id + "&mess=Add lesson successfully");
        }
    }
}
