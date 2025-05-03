/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.auth;

import dao.ChapterDAO;
import dao.CourseDAO;
import dao.LearnerCourseDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Chapter;
import model.Course;
import model.LearnerCourse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CourseDetailController", urlPatterns = {"/course-details"})
public class CourseDetailController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            CourseDAO cd = new CourseDAO();
            ChapterDAO chd = new ChapterDAO();
            LearnerCourseDAO lcd = new LearnerCourseDAO();

            // Get course_id
            String course_id = request.getParameter("course_id");
            String subject_id = request.getParameter("subject_id");

            Course course = cd.getCourseById(Integer.parseInt(course_id));
            if (subject_id == null || subject_id.isEmpty()) {
                subject_id = String.valueOf(course.getSubject_id());
            }
            List<Chapter> chapters = chd.selectAllChapterByCourse_id(course_id);
            int numberOfChapters = chapters.size();
            List<Course> relatedCourses = cd.selectTop4CourseBySubject_id(course.getSubject_id());

            // Get the latest learner course for the course
            LearnerCourse latestLearnerCourse = lcd.getLatestLearnerCourseByCourseId(Integer.parseInt(course_id));

            // Set Attribute
            request.setAttribute("course", course);
            request.setAttribute("chapters", chapters);
            request.setAttribute("numberOfChapters", numberOfChapters);
            request.setAttribute("relatedCourses", relatedCourses);
            request.setAttribute("latestLearnerCourse", latestLearnerCourse);

            request.setAttribute("hasAssignment", cd.hasAssignmentForCourse(course_id));
            request.setAttribute("subject_id", subject_id);
            request.getRequestDispatcher("course-details.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}


