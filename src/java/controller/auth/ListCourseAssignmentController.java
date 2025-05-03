/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.auth;

import dao.AssignmentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.AssignmentSubmitted;
import model.Course;
import model.Subject;

/**
 *
 * @author ThaiGay
 */
@WebServlet(name = "ListCourseAssignmentController", urlPatterns = {"/listCourseAssignment"})
public class ListCourseAssignmentController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListCourseAssignmentController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListCourseAssignmentController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        AssignmentDAO ad = new AssignmentDAO();
        String lecturerid = request.getParameter("lecturerid");
        String subjectid = request.getParameter("subjectid");
        String courseid = request.getParameter("courseid");
        // List subject để lựa chọn
        if (lecturerid != null) {
            List<Subject> listSubject = ad.getSubjectByLecturerId(lecturerid);
            request.setAttribute("listSubject", listSubject);
        }

        // List course de lua chon
        if (subjectid != null) {
            List<Course> listCourse = ad.getCourseBySubjectId(subjectid);
            request.setAttribute("listCourse", listCourse);
        }

        if (courseid != null) {
            request.setAttribute("subjectId", ad.getSubjectIdByCourseId(courseid));
            request.setAttribute("courseid", courseid);
            int records_per_page = 3;
            
            String status = request.getParameter("status");
            if(status == null) status = "all";
            request.setAttribute("status", status);
            
            int page = 1;
            if (request.getParameter("p") != null) {
                page = Integer.parseInt(request.getParameter("p"));
            }
            int totalRecords = ad.getTotalSubmittedAssignmentByCourseId(courseid, status);
            request.setAttribute("totalRecords", totalRecords);
            int totalPages = (int) Math.ceil(totalRecords * 1.0 / records_per_page);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);

            List<AssignmentSubmitted> listAsmSubmitted = ad.getAssignmentSubmittedByCourseId(courseid, page, records_per_page, status);
            System.out.println(listAsmSubmitted.size());
            request.setAttribute("listAsmSubmitted", listAsmSubmitted);
        }

        request.getRequestDispatcher("listCourseAssignment.jsp").forward(request, response);
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
