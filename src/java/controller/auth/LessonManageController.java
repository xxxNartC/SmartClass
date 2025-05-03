package controller.auth;

import dao.LessonDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Chapter;
import model.Course;
import model.Lesson;
import model.Subject;

@WebServlet(name = "LessonManageController", urlPatterns = {"/manageLesson"})
public class LessonManageController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String course_id = request.getParameter("course_id");
        String subject_id = request.getParameter("subject_id");

        String chapter_id = request.getParameter("chapter_id");
        String action = request.getParameter("action");
        String lesson_id = request.getParameter("lesson_id");
        String mess = request.getParameter("mess");
        String searchLessonName = request.getParameter("searchLessonName"); // Get search term   
        String pageStr = request.getParameter("page");
        int page = pageStr != null ? Integer.parseInt(pageStr) : 1;
        LessonDAO lessonDAO = new LessonDAO();
        List<Lesson> lessons = new ArrayList<>();
        if (searchLessonName
                != null && !searchLessonName.isEmpty()) {
            int numPages = lessonDAO.getNumPageByName(chapter_id, searchLessonName);

            lessons = lessonDAO.filterLessonPaging(chapter_id, searchLessonName, page);

            request.setAttribute("numPages", numPages);
        } else {
            int numPages = lessonDAO.getNumPageByChapterID(chapter_id);
            lessons = lessonDAO.getLessonPageByChapterID(chapter_id, page);
            request.setAttribute("numPages", numPages);
        }
        Course course = new Course();
        course.setCourse_id(course_id);
        Subject subject = new Subject();
        subject.setSubject_id(subject_id);
        Chapter chapter = new Chapter();
        chapter.setChapter_id(chapter_id);
        if (action != null && action.equals("delete")) {
            lessonDAO.deleteLesson(lesson_id);

            lessons = lessonDAO.getLessonPageByChapterID(chapter_id, page); // Refresh the list after deleting      
        }

        request.setAttribute("lessons", lessons);
        request.setAttribute("course", course);
        request.setAttribute("course_id", course_id);
        request.setAttribute("subject_id", subject_id);
        request.setAttribute("chapter_id", chapter_id);
        request.setAttribute("chapter", chapter);
        request.setAttribute("mess", mess);
        request.setAttribute("searchLessonName", searchLessonName); // Pass search term to JSP    
        request.setAttribute("currentPage", page); // Pass current page to JSP      
        request.getRequestDispatcher("/manageLesson.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String course_id = request.getParameter("course_id");
        String subject_id = request.getParameter("subject_id");
        String chapter_id = request.getParameter("chapter_id");
        String lesson_id = request.getParameter("lesson_id");
        String lesson_no = request.getParameter("lesson_no");
        String lesson_name = request.getParameter("lesson_name");
        String video = request.getParameter("video");
        String document = request.getParameter("document");
        String description = request.getParameter("description");
        LessonDAO lessonDAO = new LessonDAO();
        if (action.equals("add")) {
            lessonDAO.insertLesson(chapter_id, lesson_no, lesson_name, video, document, description);
            response.sendRedirect("manageLesson?course_id=" + course_id + "&subject_id=" + subject_id + "&chapter_id=" + chapter_id + "&mess=Add lesson successfully");
        } else if (action.equals("update")) {
            lessonDAO.updateLesson(lesson_id, lesson_no, lesson_name, video, document, description, chapter_id);
            response.sendRedirect("manageLesson?course_id=" + course_id + "&subject_id=" + subject_id + "&chapter_id=" + chapter_id + "&mess=Update lesson successfully");
        }
    }
}
