package controller.auth;

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

@WebServlet(name = "CourseManageController", urlPatterns = {"/coursemanage"})
public class CourseManageController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CourseDAO cd = new CourseDAO();
//        AccountDAO ad = new AccountDAO();
//        SubjectDAO sd = new SubjectDAO();

        // Lấy subject_id từ request (nếu có)
        String subject_id = request.getParameter("subject_id");

//        // Lấy giá trị tìm kiếm nếu có
//        String nameSearch = request.getParameter("name");
//        nameSearch = nameSearch == null ? "" : nameSearch.trim();
        List<Course> data_course = cd.getCourseBySubjectId(subject_id);

               // get message
        String mess = request.getParameter("mess");
        request.setAttribute("mess", mess);
//        // Kiểm tra xem subject_id có tồn tại không
//        if (subject_id != null && !subject_id.isEmpty()) {
//            // Nếu có subject_id, lọc các khóa học theo subject_id
//            data_course = cd.getCoursesBySubjectId(subject_id, nameSearch); // Cần có phương thức này trong CourseDAO
//        } else {
//            // Nếu không có subject_id, lấy tất cả khóa học
//            data_course = cd.getAllCourse(nameSearch); // Đảm bảo bạn đã có phương thức này trong CourseDAO
//        }
//        List<Account> data_account = ad.getAllAccount();
//        List<Subject> data_subject = sd.getAllSubject();
        // Kiểm tra xem danh sách khóa học có rỗng không
//        if (data_course.isEmpty()) {
//            request.getRequestDispatcher("isEmpty.jsp").forward(request, response);
//        } else {
//            request.setAttribute("data_account", data_account);
//            request.setAttribute("data_subject", data_subject);
            request.setAttribute("subject_id", subject_id);
            request.setAttribute("data_course", data_course);
//            request.setAttribute("name", nameSearch);
            request.getRequestDispatcher("/coursemanage.jsp").forward(request, response);
//        }
    }
}
