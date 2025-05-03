/*  
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.common;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import dao.BlogDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Blog;
import java.text.SimpleDateFormat;
import dao.AccountDAO; // Import AccountDAO
import jakarta.servlet.http.HttpSession;
import model.Account;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import jakarta.servlet.http.Part;
import java.util.Date;

/**
 *
 * @author TRUONG GIANG
 */
@WebServlet(name="BlogController", urlPatterns={"/blog"})
public class BlogController extends HttpServlet {
   
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
        
        //
        BlogDAO blogDAO = new BlogDAO();
        AccountDAO accountDAO = new AccountDAO(); // Create an instance of AccountDAO
        HttpSession session = request.getSession();
        // Check if the account_id is present in the session
        if (session.getAttribute("account_id") != null) {
            // Get the account_id from the session
            int MarketerId = (int) session.getAttribute("account_id");
            String action = request.getParameter("action");
            if (action == null) {
                action = "list";
            }
            
            switch (action) {
                case "list":
                    int page = 1;
                    if (request.getParameter("page") != null) {
                        page = Integer.parseInt(request.getParameter("page"));
                    }
                    
                    int pageSize = 3; // Số blog hiển thị trên mỗi trang
                    
                    // Get search and sort parameters
                    String searchText = request.getParameter("searchText");
                    String selectedTag = request.getParameter("selectedTag"); // Get selected tag

                    List<Blog> blogList;
                    int totalBlogs;
                    int totalPages;
                    
                    if (searchText != null && !searchText.isEmpty()) {
                        // Search blogs by title or tag
                        blogList = blogDAO.searchBlogsByTitleOrTag(searchText, page, pageSize);
                        totalBlogs = blogDAO.getTotalBlogsBySearchText(searchText);
                        totalPages = (int) Math.ceil((double) totalBlogs / pageSize);
                    } else if (selectedTag != null && !selectedTag.isEmpty()) {
                        // Filter blogs by selected tag
                        blogList = blogDAO.getBlogsByTag(selectedTag, page, pageSize);
                        totalBlogs = blogDAO.getTotalBlogsByTag(selectedTag);
                        totalPages = (int) Math.ceil((double) totalBlogs / pageSize);
                    } else {
                        // Get blogs by page
                        blogList = blogDAO.getBlogsByPage(page, pageSize);
                        totalBlogs = blogDAO.getTotalBlogs();
                        totalPages = (int) Math.ceil((double) totalBlogs / pageSize);
                    }
                    
                    // Format createdDate to display in bloglist.jsp
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    for (Blog blog : blogList) {
                        if (blog.getCreatedDate() != null) {
                            String formattedDate = formatter.format(blog.getCreatedDate());
                            blog.setFormattedCreatedDate(formattedDate);
                        }
                        // Get marketer name
                        Account marketer = accountDAO.getAccountById(blog.getMarketerId()); // Get marketer by ID
                        if (marketer != null) {
                            blog.setMarketerName(marketer.getFullname()); // Set marketer name in the blog object
                        }
                    }
                    
                    request.setAttribute("blogList", blogList);
                    request.setAttribute("currentPage", page);
                    request.setAttribute("totalPages", totalPages);
                    request.setAttribute("searchText", searchText);
                    request.setAttribute("selectedTag", selectedTag); // Pass selected tag to JSP
                    request.setAttribute("allTags", blogDAO.getAllTags()); // Pass all tags to JSP
                    request.getRequestDispatcher("bloglist.jsp").forward(request, response);
                    break;
                case "detail":
                    int id = Integer.parseInt(request.getParameter("id"));
                    Blog blog = blogDAO.getBlogById(id);
                    
                    // Get marketer name
                    Account marketer = accountDAO.getAccountById(blog.getMarketerId()); // Get marketer by ID
                    if (marketer != null) {
                        blog.setMarketerName(marketer.getFullname()); // Set marketer name in the blog object
                    }
                    
                    request.setAttribute("blog", blog);
                    request.getRequestDispatcher("Blogdetail.jsp").forward(request, response);
                    break;
                case "add":
                    if (request.getMethod().equals("GET")) {
                    request.getRequestDispatcher("blogAdd.jsp").forward(request, response);
                } else {
                    String title = request.getParameter("title");
                    String content = request.getParameter("content");
                    String description = request.getParameter("description");
                    String image = request.getParameter("image");
                    String tag = request.getParameter("tag");
                    
                    Blog newBlog = new Blog();
                    newBlog.setTitle(title);
                    newBlog.setContent(content);
                    newBlog.setDescription(description);
                    newBlog.setCreatedDate(new Date());
                    newBlog.setStatus(1); // Set status to 1 (published)
                    newBlog.setMarketerId(MarketerId); // Set marketerId to 1 (you can change this)
                    newBlog.setTag(tag);
                    newBlog.setImage(image);
                    
                    blogDAO.insertBlog(newBlog);
                    
                    // Add success message to request attribute
                    request.setAttribute("successMessage", "Blog added successfully!");
                    
                    // Forward back to the same page (blogAdd.jsp)
                    request.getRequestDispatcher("blogAdd.jsp").forward(request, response);

                }
                    break;
                default:
                    break;
            }
        } else {
            // If account_id is not in the session, redirect to login page
            response.sendRedirect("login.jsp");
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
            Logger.getLogger(BlogController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(BlogController.class.getName()).log(Level.SEVERE, null, ex);
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
