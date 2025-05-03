package controller.auth;

import dao.ChapterDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Chapter;
import model.Course;
import model.Subject;

@WebServlet(name = "ChapterManageController", urlPatterns = {"/manageChapter"})
public class ChapterManageController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String course_id = request.getParameter("course_id");
        String subject_id = request.getParameter("subject_id");
        String action = request.getParameter("action");
        String chapter_id = request.getParameter("chapter_id");
        String mess = request.getParameter("mess");
        String searchChapterName = request.getParameter("searchChapterName"); // Get search term

        String pageStr = request.getParameter("page");
        int page = pageStr != null ? Integer.parseInt(pageStr) : 1;

        ChapterDAO chapterDAO = new ChapterDAO();
        ArrayList<Chapter> chapters = new ArrayList<>();
        if (searchChapterName != null && !searchChapterName.isEmpty()) {
            chapters = chapterDAO.searchChapterByName(course_id, searchChapterName); // Search by chapter name
            int numPages = chapterDAO.getNumPageById(course_id, searchChapterName);
            request.setAttribute("numPages", numPages);
        } else {
            chapters = chapterDAO.selectAllChapterByCourse_id(course_id, page); // Get all chapters if no search term
            int numPages = chapterDAO.getNumPageByCourseId(course_id);
            request.setAttribute("numPages", numPages);
        }

        Course course = new Course();
        course.setCourse_id(course_id);
        Subject subject = new Subject();
        subject.setSubject_id(subject_id);

        if (action != null && action.equals("delete")) {
            chapterDAO.deleteChapter(chapter_id);
            chapters = chapterDAO.selectAllChapterByCourse_id(course_id, page); // Refresh the list after deleting
        }

        request.setAttribute("chapters", chapters);
        request.setAttribute("course", course);
        request.setAttribute("course_id", course_id);
        request.setAttribute("subject_id", subject_id);
        request.setAttribute("mess", mess);
        request.setAttribute("searchChapterName", searchChapterName); // Pass search term to JSP
        request.setAttribute("currentPage", page); // Pass current page to JSP
        request.getRequestDispatcher("/manageChapter.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String course_id = request.getParameter("course_id");
        String subject_id = request.getParameter("subject_id");
        String chapter_id = request.getParameter("chapter_id");
        String chapter_no = request.getParameter("chapter_no");
        String chapter_name = request.getParameter("chapter_name");
        String description = request.getParameter("description");
        ChapterDAO chapterDAO = new ChapterDAO();
        if (action.equals("add")) {
            chapterDAO.insertChapter(course_id, chapter_no, chapter_name, description);
            response.sendRedirect("manageChapter?course_id=" + course_id + "&subject_id=" + subject_id + "&mess=Add chapter successfully");
        } else if (action.equals("update")) {
            chapterDAO.updateChapter(chapter_id, chapter_no, chapter_name, course_id, description);
            response.sendRedirect("manageChapter?course_id=" + course_id + "&subject_id=" + subject_id + "&mess=Update chapter successfully");
        }
    }
}
