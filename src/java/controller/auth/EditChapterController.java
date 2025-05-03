package controller.auth;

import dao.ChapterDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.Chapter;

@WebServlet(name = "EditChapterController", urlPatterns = {"/editChapter"})
public class EditChapterController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String chapter_id = request.getParameter("chapter_id");
        String course_id = request.getParameter("course_id");
        String subject_id = request.getParameter("subject_id");
        ChapterDAO chapterDAO = new ChapterDAO();
        Chapter chapter = chapterDAO.getChapterById(chapter_id);
        request.setAttribute("chapter", chapter);
        request.setAttribute("course_id", course_id);
        request.setAttribute("subject_id", subject_id);
        request.getRequestDispatcher("/editChapter.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String chapter_id = request.getParameter("chapter_id");
        String course_id = request.getParameter("course_id");
        String subject_id = request.getParameter("subject_id");
        String chapter_no = request.getParameter("chapter_no");
        String chapter_name = request.getParameter("chapter_name");
        String description = request.getParameter("description");

        ChapterDAO chapterDAO = new ChapterDAO();

        try {
            Integer.parseInt(chapter_no);
        } catch (NumberFormatException e) {
            request.setAttribute("chapter_no_error", "Chapter No must be a number.");
            request.setAttribute("chapter", new Chapter(chapter_id, chapter_no, chapter_name, course_id, description));
            request.setAttribute("course_id", course_id);
            request.setAttribute("subject_id", subject_id);
            request.getRequestDispatcher("/editChapter.jsp").forward(request, response);
            return;
        }

        if (Integer.parseInt(chapter_no) <= 0) {
            request.setAttribute("chapter_no_error", "Chapter No must be greater than 0.");
            request.setAttribute("chapter", new Chapter(chapter_id, chapter_no, chapter_name, course_id, description));
            request.setAttribute("course_id", course_id);
            request.setAttribute("subject_id", subject_id);
            request.getRequestDispatcher("/editChapter.jsp").forward(request, response);
            return;
        }
        if (chapterDAO.checkChapterNo(course_id, chapter_no, chapter_id)) {
            request.setAttribute("chapter_no_error", "Chapter No already exists");
            request.setAttribute("chapter", new Chapter(chapter_id, chapter_no, chapter_name, course_id, description));
            request.setAttribute("course_id", course_id);
            request.setAttribute("subject_id", subject_id);
            request.getRequestDispatcher("/editChapter.jsp").forward(request, response);
            return;
        }

        if (chapterDAO.checkChapterName(course_id, chapter_name, chapter_id)) {
            request.setAttribute("chapter_name_error", "Chapter Name already exists");
            request.setAttribute("chapter", new Chapter(chapter_id, chapter_no, chapter_name, course_id, description));
            request.setAttribute("course_id", course_id);
            request.setAttribute("subject_id", subject_id);
            request.getRequestDispatcher("/editChapter.jsp").forward(request, response);
            return;
        }

        if (description.length() < 10 || description.length() > 200) {
            request.setAttribute("description_error", "Description must be between 10 and 200 characters.");
            request.setAttribute("chapter", new Chapter(chapter_id, chapter_no, chapter_name, course_id, description));
            request.setAttribute("course_id", course_id);
            request.setAttribute("subject_id", subject_id);
            request.getRequestDispatcher("/editChapter.jsp").forward(request, response);
            return;
        }

        chapterDAO.updateChapter(chapter_id, chapter_no, chapter_name, course_id, description);
        response.sendRedirect("manageChapter?course_id=" + course_id + "&subject_id=" + subject_id + "&mess=Update Chapter successfully");
    }

}
