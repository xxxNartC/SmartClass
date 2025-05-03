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
import model.Assignment;

/**
 *
 * @author ThaiGay
 */
@WebServlet(name = "AddAssignmentController", urlPatterns = {"/addAssignment"})
@MultipartConfig
public class AddAssignmentController extends HttpServlet {

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
            out.println("<title>Servlet AddAssignmentController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddAssignmentController at " + request.getContextPath() + "</h1>");
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

        // lấy course id & subject id
        String courseid = request.getParameter("course_id");
        request.setAttribute("courseid", courseid);
        String subjectid = request.getParameter("subject_id");
        request.setAttribute("subjectid", subjectid);

        Assignment detail = ad.getAssignmentByCourseId(courseid);
        request.setAttribute("detail", detail);
        request.getRequestDispatcher("addAssignment.jsp").forward(request, response);
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
        String action = request.getParameter("action");
        String subjectid = request.getParameter("subjectid");
        String courseid = request.getParameter("courseid");
        String content = request.getParameter("content").trim();

        String mess = "";
        // Xử lý phần upload document (file word)
        String uploadFilePath = "C:\\Users\\Admin\\Desktop\\smarclassgit\\web\\assignment\\assignment_document";
        // Lấy part của file
        Part filePart = request.getPart("document");

        // Add or update
        if (action.equalsIgnoreCase("add")) {
            mess = "Added Successfully";
            if (filePart == null || filePart.getSize() <= 0) {
                ad.addAssignment(content, "", courseid);
            } else {
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();  // asm.docx
                fileName = ("course_" + courseid + "_" + fileName);  //course_14_as.docx
                filePart.write(uploadFilePath + File.separator + fileName);
                ad.addAssignment(content, fileName, courseid);
            }
        } else {
            // Update
            mess = "Updated Successfully";
            Assignment detail = ad.getAssignmentByCourseId(courseid);
            // Kiem tra co change document khong
            if (filePart == null || filePart.getSize() <= 0) {
                // Neu khong change document file thi giu nguyen current document
                ad.updateAssignment(content, detail.getDocument(), detail.getAssignment_id());
            } else {
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                fileName = ("course_" + courseid + "_" + fileName);
                // Neu change document thi xoa file cu thay bang file moi
                // Xoa file cu:
                File docToDelete = new File(uploadFilePath + "\\" + detail.getDocument());
                if (docToDelete.exists() && docToDelete.isFile()) {
                    docToDelete.delete();
                }
                // Cap nhat asm voi file doc moi va content 
                filePart.write(uploadFilePath + File.separator + fileName);
                ad.updateAssignment(content, fileName, detail.getAssignment_id());
            }
        }

        
        
//        response.sendRedirect("managementcourse?subject_id=" + subjectid + "&mess=" + mess);
        response.sendRedirect("coursemanage?subject_id=" + subjectid + "&mess=" + mess);
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
