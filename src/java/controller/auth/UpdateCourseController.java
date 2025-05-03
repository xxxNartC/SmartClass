/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.auth;

import dao.CourseDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Course;

/**
 *
 * @author 
 */
@WebServlet(name = "UpdateCourseController", urlPatterns = {"/update-course"})
public class UpdateCourseController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try {
            String courseId = request.getParameter("course_id");
            CourseDAO cdao = new CourseDAO();
            Course c = cdao.getCourseById(Integer.parseInt(courseId));
            request.setAttribute("c", c);
            request.getRequestDispatcher("update-course.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    try {
        response.setContentType("text/html;charset=UTF-8");
        String subjecId = request.getParameter("subjecId");
        String courseId = request.getParameter("courseId");
        String courseNo = request.getParameter("courseNo");
        String courseName = request.getParameter("courseName");
        String image = request.getParameter("image");
        String description = request.getParameter("description");
        boolean status = request.getParameter("status").equals("1");

        CourseDAO cdao = new CourseDAO();
        Course c = new Course(courseId, courseName, image, subjecId, courseNo, description, status);
        cdao.updateCourse(c);

        // Thêm thông báo thành công vào session
        request.getSession().setAttribute("successMessage", "Update course successfully!");

        // Chuyển hướng tới trang quản lý khóa học
        response.sendRedirect("coursemanage?subject_id=" + subjecId);

    } catch (Exception e) {
        e.printStackTrace();
        // Nếu có lỗi, thêm thông báo lỗi vào session
        request.getSession().setAttribute("errorMessage", "Failed to update course.");
        response.sendRedirect("update-course?course_id=" + request.getParameter("courseId"));
    }
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
