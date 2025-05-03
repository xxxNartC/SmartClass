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
import java.time.format.DateTimeFormatter;
import model.Assignment;
import model.AssignmentSubmitted;

/**
 *
 * @author ThaiGay
 */
@WebServlet(name = "ViewAssignmentSubmittedDetailController", urlPatterns = {"/viewAssignmentSubmittedDetail"})
public class ViewAssignmentSubmittedDetailController extends HttpServlet {

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
            out.println("<title>Servlet ViewAssignmentSubmittedDetailController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewAssignmentSubmittedDetailController at " + request.getContextPath() + "</h1>");
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
        String submitted_id = request.getParameter("submitted_id");
        request.setAttribute("submitted_id", submitted_id);
        AssignmentSubmitted detail = ad.getAssignmentSubmittedDetail(submitted_id);
        request.setAttribute("detail", detail);

        // Lấy đề:
        Assignment asm = ad.getAssignmentById(detail.getAssignment_id());
        request.setAttribute("asm", asm);
        request.setAttribute("courseid", request.getParameter("courseid"));
        request.getRequestDispatcher("lecturerViewAssignmentSubmittedDetail.jsp").forward(request, response);
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
        AssignmentDAO ad = new AssignmentDAO();

        String courseid = request.getParameter("courseid");

        String submitted_id = request.getParameter("submitted_id");
        String mark = request.getParameter("mark");
        String comment = request.getParameter("comment");
        String response_date = java.time.LocalDate.now().toString();
        try {
            String status = Double.parseDouble(mark) >= 4 ? "passed" : "reject";
            ad.markedAssignmentSubmitted(submitted_id, mark, comment, status, response_date);
            response.sendRedirect("listCourseAssignment?courseid=" + courseid);

        } catch (NumberFormatException e) {

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
