/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.auth;

import dao.AssignmentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;
import java.util.Date;
import model.Assignment;
import model.AssignmentSubmitted;

/**
 *
 * @author ThaiGay
 */
@WebServlet(name = "TakeAssignmentController", urlPatterns = {"/takeAssignment"})
@MultipartConfig
public class TakeAssignmentController extends HttpServlet {

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
            out.println("<title>Servlet TakeAssignmentController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TakeAssignmentController at " + request.getContextPath() + "</h1>");
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
        String learnerid = request.getParameter("learner_id");
        request.setAttribute("learnerid", learnerid);
        String courseid = request.getParameter("course_id");
        request.setAttribute("courseid", courseid);
        // Get asm by course id
        Assignment asm = ad.getAssignmentByCourseId(courseid);
        if (asm != null) {
            // set assignment detail
            request.setAttribute("asm", asm);
        }

        // Tim xem bai lam nay da duoc lam hay chua
        AssignmentSubmitted detail = ad.findSubmittedForLearner(courseid, learnerid);
        request.setAttribute("detail", detail);

        request.getRequestDispatcher("takeAssignment.jsp").forward(request, response);

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
        String learnerid = request.getParameter("learnerid");
        String assignmentid = request.getParameter("assignmentid");
        String answer = request.getParameter("answer");
        // Xử lý phần upload document (file word)
        String uploadFilePath = "C:\\Users\\Admin\\Desktop\\smarclassgit\\web\\assignment\\submitted_document";
        // Lấy part của file
        Part filePart = request.getPart("document");
        String fileName;
        // Kiếm tra xem learner có upload file hay không
        if (filePart == null || filePart.getSize() <= 0) {
            System.out.println("Khong upload file");
            fileName = "";
        } else {
            fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            fileName = ("learner_" + learnerid + "_" + fileName);
            // Lưu file
            filePart.write(uploadFilePath + File.separator + fileName);
        }
        String submittedDate = java.time.LocalDate.now().toString();
        ad.submitAssignment(learnerid, assignmentid, answer, fileName, "pending", submittedDate);

        // managementchapter?subject_id=1&course_id=1 => controller và jsp => chỉnh sửa thêm mess vào 
        response.sendRedirect("takeAssignment?course_id=" + courseid + "&learner_id=" + learnerid + "");

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
