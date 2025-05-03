/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.common;

import dao.FavoriteSubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.FavoriteSubject;
import java.util.Date;

/**
 *
 * @author TRUONG GIANG
 */
@WebServlet(name = "FavoriteSubject", urlPatterns = {"/FavoriteSubject"})
public class FavoriteSubjectController extends HttpServlet {

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
            out.println("<title>Servlet FavoriteSubject</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FavoriteSubject at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        // Check if accountId is present in the session
        if (session.getAttribute("account_id") != null) {
            int accountId = (int) session.getAttribute("account_id");
            String searchTerm = request.getParameter("searchTerm"); // Get search term from request
            String sortBy = request.getParameter("sortBy"); // Get sort by parameter
            String categoryName = request.getParameter("categoryName"); // Get category name from request

            FavoriteSubjectDAO dao = new FavoriteSubjectDAO();
            List<FavoriteSubject> favoriteSubjects;
            
            // Handle filtering and sorting based on category and sort options
            if (categoryName != null && !categoryName.isEmpty()) {
                if (categoryName.equals("All")) {
                    // Filter by Category All
                    if (sortBy != null && !sortBy.isEmpty()) {
                        // Sort by Added Date or Favorites Count
                        if (sortBy.equals("addedDateDESC")) {
                            favoriteSubjects = dao.getFavoriteSubjectsByAccountIdDESC(accountId);
                        } else if (sortBy.equals("addedDateASC")) {
                            favoriteSubjects = dao.getFavoriteSubjectsByAccountIdASC(accountId);
                        } else if (sortBy.equals("favoritesCountDESC")) {
                            favoriteSubjects = dao.getFavoriteSubjectsByAccountIdSortedByFavoritesCountDESC(accountId);
                        } else if (sortBy.equals("favoritesCountASC")) {
                            favoriteSubjects = dao.getFavoriteSubjectsByAccountIdSortedByFavoritesCountASC(accountId);
                        } else {
                            favoriteSubjects = dao.getFavoriteSubjectsByAccountId(accountId);
                        }
                    } else {
                        // No sorting
                        favoriteSubjects = dao.getFavoriteSubjectsByAccountId(accountId);
                    }
                } else {
                    // Filter by specific category
                    if (sortBy != null && !sortBy.isEmpty()) {
                        // Sort by Added Date or Favorites Count
                        favoriteSubjects = dao.getFavoriteSubjectsByAccountIdAndCategoryName(accountId, categoryName, sortBy);
                    } else {
                        // No sorting
                        favoriteSubjects = dao.getFavoriteSubjectsByAccountIdAndCategoryName(accountId, categoryName, null);
                    }
                }
            } else {
                // No filtering by category
                if (searchTerm != null && !searchTerm.isEmpty()) {
                    // Search by term
                    if (sortBy != null && !sortBy.isEmpty()) {
                        // Sort by Added Date or Favorites Count
                        if (sortBy.equals("addedDateDESC")) {
                            favoriteSubjects = dao.getFavoriteSubjectsByAccountIdAndSearchTermDESC(accountId, searchTerm);
                        } else if (sortBy.equals("addedDateASC")) {
                            favoriteSubjects = dao.getFavoriteSubjectsByAccountIdAndSearchTermASC(accountId, searchTerm);
                        } else if (sortBy.equals("favoritesCountDESC")) {
                            favoriteSubjects = dao.getFavoriteSubjectsByAccountIdSortedByFavoritesCountDESC(accountId);
                        } else if (sortBy.equals("favoritesCountASC")) {
                            favoriteSubjects = dao.getFavoriteSubjectsByAccountIdSortedByFavoritesCountASC(accountId);
                        } else {
                            favoriteSubjects = dao.getFavoriteSubjectsByAccountIdAndSearchTerm(accountId, searchTerm);
                        }
                    } else {
                        // No sorting
                        favoriteSubjects = dao.getFavoriteSubjectsByAccountIdAndSearchTerm(accountId, searchTerm);
                    }
                } else {
                    // No filtering or searching
                    if (sortBy != null && !sortBy.isEmpty()) {
                        // Sort by Added Date or Favorites Count
                        if (sortBy.equals("addedDateDESC")) {
                            favoriteSubjects = dao.getFavoriteSubjectsByAccountIdDESC(accountId);
                        } else if (sortBy.equals("addedDateASC")) {
                            favoriteSubjects = dao.getFavoriteSubjectsByAccountIdASC(accountId);
                        } else if (sortBy.equals("favoritesCountDESC")) {
                            favoriteSubjects = dao.getFavoriteSubjectsByAccountIdSortedByFavoritesCountDESC(accountId);
                        } else if (sortBy.equals("favoritesCountASC")) {
                            favoriteSubjects = dao.getFavoriteSubjectsByAccountIdSortedByFavoritesCountASC(accountId);
                        } else {
                            favoriteSubjects = dao.getFavoriteSubjectsByAccountId(accountId);
                        }
                    } else {
                        // No sorting
                        favoriteSubjects = dao.getFavoriteSubjectsByAccountId(accountId);
                    }
                }
            }

            request.setAttribute("favoriteSubjects", favoriteSubjects);
            request.getRequestDispatcher("FavoriteSubjects.jsp").forward(request, response);
        } else {
            // Redirect to login page if accountId is not found in the session
            response.sendRedirect("login.jsp");
        }
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
        HttpSession session = request.getSession();
        if (session.getAttribute("account_id") != null) {
            int accountId = (int) session.getAttribute("account_id");
            
            // Check if the request is for adding or deleting a favorite subject
            if (request.getParameter("subject_id") != null) {
                // Add favorite subject
                int subjectId = Integer.parseInt(request.getParameter("subject_id"));
                FavoriteSubjectDAO dao = new FavoriteSubjectDAO();
                FavoriteSubject favoriteSubject = new FavoriteSubject();    
                favoriteSubject.setAccountId(accountId);
                favoriteSubject.setSubjectId(subjectId);
                favoriteSubject.setAddedDate(new Date());
                dao.insertFavoriteSubject(favoriteSubject);
                
                // Set a success message in the session
                session.setAttribute("successMessage", "Subject added to your favorites successfully!");
                
                response.sendRedirect("subjectlist");
            } else if (request.getParameter("favoriteId") != null) {
                // Delete favorite subject
                int favoriteId = Integer.parseInt(request.getParameter("favoriteId"));
                FavoriteSubjectDAO dao = new FavoriteSubjectDAO();
                dao.deleteFavoriteSubject(favoriteId);
                
                // Set a success message in the session
                session.setAttribute("successMessage", "Subject removed from your favorites successfully!");
                
                response.sendRedirect("FavoriteSubject");
            }
        } else {
            response.sendRedirect("login.jsp");
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

