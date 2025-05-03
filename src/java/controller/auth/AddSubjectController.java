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
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Account;
import model.Category;
import model.Subject;
import model.User;

/**
 *
 * @author slhoa
 */
@WebServlet(name="AddSubjectController", urlPatterns={"/addsubject"})
public class AddSubjectController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet AddSubjectController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddSubjectController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        CategoryDAO cd = new CategoryDAO();
        List<Category> listCate = cd.getAllCategory();
        request.setAttribute("listCate", listCate);
        request.getRequestDispatcher("addsubject.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        String createdDate = java.time.LocalDate.now().toString();
        String name = request.getParameter("subjectname");
        String description = request.getParameter("description");
        String image = request.getParameter("image");
        String price = request.getParameter("price");
        String discount = request.getParameter("discount");
        String category = request.getParameter("category");
        User u = (User) request.getSession().getAttribute("user");
        int u_id = u.getAccount_id();
        CategoryDAO cd = new CategoryDAO();
        List<Category> listCate = cd.getAllCategory();              
        request.setAttribute("listCate", listCate);
        SubjectDAO sd = new SubjectDAO();
        List<Subject> list = sd.selectSubjectManage(u_id);
        try {
            boolean flag = false;
            for(Subject s : list){
                if ((s.getSubject_name()).equalsIgnoreCase(name)){
                    flag = true;
                }
            }
          
            if (Double.parseDouble(price) < 0 || Double.parseDouble(price) > 1000000) {
                throw new NumberFormatException();

            } else if (Double.parseDouble(discount) > 100 || Double.parseDouble(discount) < 0) {
                throw new NumberFormatException();
            
            }
            else if (flag == true) {
                throw new Exception();
            }    
             else {
                SubjectDAO s = new SubjectDAO();
                s.addSubject(name, description, image, createdDate, price, discount, u_id, category);
                request.setAttribute("mess", "A new subject added successfully !");
                request.getRequestDispatcher("addsubject.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {

            request.setAttribute("mess", "The course price does not exceed 1,000,000$ and the discount does not exceed 100");
            request.getRequestDispatcher("addsubject.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("mess", "Subject name don't duplicate");
            request.getRequestDispatcher("addsubject.jsp").forward(request, response);
            
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
