package controller.common;

import dao.AccountDAO;
import dao.CourseDAO;
import dao.SubjectDAO;
import model.Course;
import model.Subject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.Account;

@WebServlet(name = "CourseController", urlPatterns = {"/course"})
public class CourseController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CourseDAO cd = new CourseDAO();
        AccountDAO ad = new AccountDAO();
        SubjectDAO sd = new SubjectDAO();

        // L?y subject_id t? request (n?u có)
        String subject_id = request.getParameter("subject_id");
        
//        // L?y giá tr? t́m ki?m n?u có
//        String nameSearch = request.getParameter("name");
//        nameSearch = nameSearch == null ? "" : nameSearch.trim();

        List<Course> data_course = cd.getCourseBySubjectId(subject_id);
        
//        // Ki?m tra xem subject_id có t?n t?i không
//        if (subject_id != null && !subject_id.isEmpty()) {
//            // N?u có subject_id, l?c các khóa h?c theo subject_id
//            data_course = cd.getCoursesBySubjectId(subject_id, nameSearch); // C?n có ph??ng th?c này trong CourseDAO
//        } else {
//            // N?u không có subject_id, l?y t?t c? khóa h?c
//            data_course = cd.getAllCourse(nameSearch); // ??m b?o b?n ?ă có ph??ng th?c này trong CourseDAO
//        }

        List<Account> data_account = ad.getAllAccount();
        List<Subject> data_subject = sd.getAllSubject();

        // Ki?m tra xem danh sách khóa h?c có r?ng không
        if (data_course.isEmpty()) {
            request.getRequestDispatcher("isEmpty.jsp").forward(request, response);
        } else {
            request.setAttribute("data_account", data_account);
            request.setAttribute("data_subject", data_subject);
            request.setAttribute("data_course", data_course);
            request.setAttribute("subject_id", subject_id);
//            request.setAttribute("name", nameSearch);
            request.getRequestDispatcher("/Course.jsp").forward(request, response);
        }
    }
}
