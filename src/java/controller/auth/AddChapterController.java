package controller.auth;

import dao.ChapterDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddChapterController", urlPatterns = {"/addChapter"})
public class AddChapterController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String course_id = request.getParameter("course_id");
        String subject_id = request.getParameter("subject_id");
        request.setAttribute("course_id", course_id);
        request.setAttribute("subject_id", subject_id);
        request.getRequestDispatcher("/addChapter.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String course_id = request.getParameter("course_id");
        String subject_id = request.getParameter("subject_id");
        String chapter_no = request.getParameter("chapter_no");
        String chapter_name = request.getParameter("chapter_name");
        String description = request.getParameter("description");
        ChapterDAO chapterDAO = new ChapterDAO();

        try {
            Integer.parseInt(chapter_no);
        } catch (NumberFormatException e) {
            request.setAttribute("course_id", course_id);
            request.setAttribute("subject_id", subject_id);
            request.setAttribute("chapter_no_error", "Chapter No must be a number.");
            request.getRequestDispatcher("/addChapter.jsp").forward(request, response);
            return;
        }
        if (Integer.parseInt(chapter_no) <= 0) {
            request.setAttribute("course_id", course_id);
            request.setAttribute("subject_id", subject_id);
            request.setAttribute("chapter_no_error", "Chapter No must be greater than 0.");
            request.getRequestDispatcher("/addChapter.jsp").forward(request, response);
            return;

        }
        if (chapterDAO.checkChapterNo(course_id, chapter_no)) {
            request.setAttribute("course_id", course_id);
            request.setAttribute("subject_id", subject_id);
            request.setAttribute("chapter_no_error", "Chapter_no already exists.");
            request.getRequestDispatcher("/addChapter.jsp").forward(request, response);

        } else if (chapterDAO.checkChapterName(course_id, chapter_name)) {
            request.setAttribute("course_id", course_id);
            request.setAttribute("subject_id", subject_id);
            request.setAttribute("chapter_name_error", "Chapter_name already exists");
            request.getRequestDispatcher("/addChapter.jsp").forward(request, response);
        } else if (description.length() < 10 || description.length() > 200) {
            request.setAttribute("course_id", course_id);
            request.setAttribute("subject_id", subject_id);
            request.setAttribute("description_error", "Description must be between 10 and 200 characters.");
            request.getRequestDispatcher("/addChapter.jsp").forward(request, response);
        } else {
            chapterDAO.insertChapter(course_id, chapter_no, chapter_name, description);
            response.sendRedirect("manageChapter?course_id=" + course_id + "&subject_id=" + subject_id + "&mess=Add chapter successfully");
        }

    }
}
