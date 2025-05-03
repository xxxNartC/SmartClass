package controller.common;

import dao.LecturerDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Account;

@WebServlet(name = "LecturerListServlet", urlPatterns = {"/lecturerList"})
public class LecturerListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LecturerDAO dao = new LecturerDAO();
        List<Account> lecturers = dao.getLecturersWithCourses(); // Get lecturer list with courses
        request.setAttribute("lecturers", lecturers); // Set the list as a request attribute
        request.getRequestDispatcher("LectureSubjectList.jsp").forward(request, response); // Forward to JSP
    }
}
