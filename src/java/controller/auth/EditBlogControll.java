/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.common;

import dao.BlogDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Blog;

/**
 *
 * @author TRUONG GIANG
 */
@WebServlet(name="EditBlogControll", urlPatterns={"/editBlog"})
public class EditBlogControll extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("account_id") == null) {
        // Phiên làm việc đã hết hạn hoặc account_id không tồn tại, chuyển hướng người dùng đến trang đăng nhập
        response.sendRedirect("login.jsp");  // Hoặc trang xử lý khi hết phiên
        return;
    }
        int marketerId = (int) session.getAttribute("account_id");
        BlogDAO blogDAO = new BlogDAO();
        
        String action = request.getParameter("action");
        if (action == null) {
            action = "listEdit";
        }
        
        switch (action) {
            case "listEdit":
                int page = 1;
                if (request.getParameter("page") != null) {
                    page = Integer.parseInt(request.getParameter("page"));
                }
                
                int pageSize = 3; // Số blog hiển thị trên mỗi trang
                // Use getBlogByMarketerId to fetch blogs for the marketer
                
                List<Blog> blogList = blogDAO.getBlogByMarketerId(marketerId);
                int totalBlogs = blogList.size(); // Get total blogs from the list
                int totalPages = (int) Math.ceil((double) totalBlogs / pageSize);
                
                request.setAttribute("blogList", blogList);
                request.setAttribute("currentPage", page);
                request.setAttribute("totalPages", totalPages);
                request.getRequestDispatcher("EditBlog.jsp").forward(request, response);
                break;
            case "edit":
                int blogId = Integer.parseInt(request.getParameter("id"));
                Blog blog = blogDAO.getBlogById(blogId);
                request.setAttribute("blog", blog);
                request.getRequestDispatcher("blogUpdate.jsp").forward(request, response);
                break;
            case "update":
                if (request.getMethod().equals("POST")) {
                    blogId = Integer.parseInt(request.getParameter("id"));
                    String title = request.getParameter("title");
                    String content = request.getParameter("content");
                    String description = request.getParameter("description");
                    String image = request.getParameter("image");
                    String tag = request.getParameter("tag");

                    Blog updatedBlog = new Blog();
                    updatedBlog.setId(blogId);
                    updatedBlog.setTitle(title);
                    updatedBlog.setContent(content);
                    updatedBlog.setDescription(description);
                    updatedBlog.setCreatedDate(new Date()); // Update createdDate if needed
                    updatedBlog.setStatus(1); // Set status to 1 (published)
                    updatedBlog.setMarketerId(1); // Set marketerId to 1 (you can change this)
                    updatedBlog.setTag(tag);
                    updatedBlog.setImage(image);

                    blogDAO.updateBlog(updatedBlog);

                    // Add success message to request attribute
                    request.setAttribute("successMessage", "Blog updated successfully!");
                    request.setAttribute("updatedBlogId", blogId);
                    
                    // Forward back to the same page (EditBlog.jsp)
                    request.getRequestDispatcher("EditBlogSucc.jsp").forward(request, response);
                }
                break;
            case "delete":
                blogId = Integer.parseInt(request.getParameter("id"));
                blogDAO.deleteBlog(blogId);
                request.setAttribute("successMessage", "Blog deleted successfully!");
                request.getRequestDispatcher("EditBlogSucc.jsp").forward(request, response);
                break;
            default:
                break;
        }
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(EditBlogControll.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(EditBlogControll.class.getName()).log(Level.SEVERE, null, ex);
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
