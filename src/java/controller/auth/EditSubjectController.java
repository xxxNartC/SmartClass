/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.auth;

import dao.CategoryDAO;
import dao.SubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Category;
import model.Subject;

/**
 *
 * @author Admin
 */
@WebServlet(name = "EditSubjectController", urlPatterns = {"/editsubject"})
public class EditSubjectController extends HttpServlet {

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
            out.println("<title>Servlet EditSubjectController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditSubjectController at " + request.getContextPath() + "</h1>");
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
       SubjectDAO sd = new SubjectDAO();
        CategoryDAO cd = new CategoryDAO();
        String sid = request.getParameter("sid");
        boolean checkEnroll = sd.checkEnrollSubjects(sid);
        try {
            
            if (checkEnroll) {
                throw new Exception();
            }

            List<Category> listCate = cd.getAllCategory();
            request.setAttribute("listCate", listCate);

            Subject subject = sd.getSubjectByID(sid);
            request.setAttribute("subject", subject);
        } catch (Exception e) {
            request.setAttribute("mess", "This subject have enroller");
        }
        request.setAttribute("checkEnroll", checkEnroll);
        request.setAttribute("sid", sid);
        request.getRequestDispatcher("editsubject.jsp").forward(request, response);
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
      CategoryDAO cd = new CategoryDAO();
        List<Category> listCate = cd.getAllCategory();
        request.setAttribute("listCate", listCate);
        String sid = request.getParameter("sid");
        String updatedDate = java.time.LocalDate.now().toString();
        String name = request.getParameter("subjectname");
        String description = request.getParameter("description");
        String image = request.getParameter("image");
        String price = request.getParameter("price");
        String discount = request.getParameter("discount");
        String category = request.getParameter("category");
        // giu lai nhung gi thang nguoi dung nhap
        Subject subject_temp = new Subject();
        subject_temp.setSubject_id(sid);
        subject_temp.setSubject_name(name);
        subject_temp.setDescription(description);
        subject_temp.setImage(image);
        subject_temp.setCategory_id(category);
        String error = "";
        try {
            if (Double.parseDouble(price) < 0 || Double.parseDouble(price) > 1000000) {
                throw new NumberFormatException();
            } else if (Double.parseDouble(discount) > 100 || Double.parseDouble(discount) < 0) {
                throw new NumberFormatException();
            } else {
                SubjectDAO s = new SubjectDAO();
                s.updateSubject(name, description, image, price, updatedDate, discount, category, sid);

                Subject updatedSubject = s.getSubjectByID(sid);
                request.setAttribute("subject", updatedSubject);

                request.setAttribute("mess", "Updated successfully !");
                request.getRequestDispatcher("editsubject.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {

            if (Double.parseDouble(price) < 0) {
                error += "\n (PRICE must be > 0 and < 1000000$) ";
            }
            if (Double.parseDouble(discount) > 100 || Double.parseDouble(discount) < 0) {
                error += "\n (DISCOUNT must be  > 0 and < 100)";
            }
            if (Double.parseDouble(price) > 0) {
                error += "\n PRICE";
                subject_temp.setPrice(Integer.valueOf(price));
            }
            if (Double.parseDouble(discount) < 100 && Double.parseDouble(discount) > 0) {
                subject_temp.setDiscount(discount);
            }
            request.setAttribute("subject", subject_temp);
            request.setAttribute("mess", "Please check again " + error);
            request.getRequestDispatcher("editsubject.jsp").forward(request, response);
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
