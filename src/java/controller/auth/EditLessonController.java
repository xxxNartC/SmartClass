package controller.auth;

import dao.LessonDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.Lesson;

@WebServlet(name = "EditLessonController", urlPatterns = {"/editLesson"})
public class EditLessonController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String lesson_id = request.getParameter("lesson_id");
        String course_id = request.getParameter("course_id");
        String subject_id = request.getParameter("subject_id");
        String chapter_id = request.getParameter("chapter_id");
        LessonDAO lessonDAO = new LessonDAO();
        Lesson lesson = lessonDAO.getLessonById(lesson_id);
        request.setAttribute("lesson", lesson);
        request.setAttribute("course_id", course_id);
        request.setAttribute("subject_id", subject_id);
        request.setAttribute("chapter_id", chapter_id);
        request.getRequestDispatcher("/editLesson.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String lesson_id = request.getParameter("lesson_id");
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
            request.setAttribute("lesson", new Lesson(lesson_id, lesson_no, lesson_name, video, document, chapter_id, description));
            request.setAttribute("course_id", course_id);
            request.setAttribute("subject_id", subject_id);
            request.setAttribute("chapter_id", chapter_id);
            request.getRequestDispatcher("/editLesson.jsp").forward(request, response);
            return;
        }

        if (Integer.parseInt(lesson_no) <= 0) {
            request.setAttribute("lesson_no_error", "Lesson No must be greater than 0.");
            request.setAttribute("lesson", new Lesson(lesson_id, lesson_no, lesson_name, video, document, chapter_id, description));
            request.setAttribute("course_id", course_id);
            request.setAttribute("subject_id", subject_id);
            request.setAttribute("chapter_id", chapter_id);
            request.getRequestDispatcher("/editLesson.jsp").forward(request, response);
            return;
        }

        if (lessonDAO.checkLessonNoExists(chapter_id, lesson_no, lesson_id)) {
            request.setAttribute("lesson_no_error", "Lesson No already exists");
            request.setAttribute("lesson", new Lesson(lesson_id, lesson_no, lesson_name, video, document, chapter_id, description));
            request.setAttribute("course_id", course_id);
            request.setAttribute("subject_id", subject_id);
            request.setAttribute("chapter_id", chapter_id);
            request.getRequestDispatcher("/editLesson.jsp").forward(request, response);
            return;
        }

        if (lessonDAO.checkLessonNameExists(chapter_id, lesson_name, lesson_id)) {
            request.setAttribute("lesson_name_error", "Lesson Name already exists");
            request.setAttribute("lesson", new Lesson(lesson_id, lesson_no, lesson_name, video, document, chapter_id, description));
            request.setAttribute("course_id", course_id);
            request.setAttribute("subject_id", subject_id);
            request.setAttribute("chapter_id", chapter_id);
            request.getRequestDispatcher("/editLesson.jsp").forward(request, response);
            return;
        }

        if (description.length() < 10 || description.length() > 200) {
            request.setAttribute("description_error", "Description must be between 10 and 200 characters.");
            request.setAttribute("lesson", new Lesson(lesson_id, lesson_no, lesson_name, video, document, chapter_id, description));
            request.setAttribute("course_id", course_id);
            request.setAttribute("subject_id", subject_id);
            request.setAttribute("chapter_id", chapter_id);
            request.getRequestDispatcher("/editLesson.jsp").forward(request, response);
            return;
        }

        lessonDAO.updateLesson(lesson_id, lesson_no, lesson_name, video, document, description, chapter_id);
        response.sendRedirect("manageLesson?course_id=" + course_id + "&subject_id=" + subject_id + "&chapter_id=" + chapter_id + "&mess=Update Lesson successfully");
    }
}
