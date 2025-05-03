/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.common;

import dao.SubjectDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Subject;
import model.User;

/**
 *
 * @author slhoa
 */
@WebServlet(name = "SubjectListController", urlPatterns = {"/subjectlist"})
public class SubjectListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User u = (User) request.getSession().getAttribute("user");
        SubjectDAO sd = new SubjectDAO();
        String index_raw = request.getParameter("index");
        String search = request.getParameter("search_name");

        if (index_raw == null) {
            index_raw = "1";
        }
        int index = Integer.parseInt(index_raw);

        List<Subject> listSearch = new ArrayList<>();
        if (u == null || u.getRole_id() != 3) {
            if (search != null && !search.isEmpty()) {
                // Trường hợp có tìm kiếm
                int page = sd.getNumPageByNameLearner(search);
                request.setAttribute("page", page);
                listSearch = sd.filterSubjectPagingLearner(search, index);
                request.setAttribute("listSub", listSearch);
                request.setAttribute("search_name", search); // Giữ lại giá trị search
            } else {
                // Trường hợp không có tìm kiếm
                int page = sd.getNumPageByNameLearner(search);
                request.setAttribute("page", page);
                // lay subject phan trang
                List<Subject> listSub = sd.getSubjectPageLearner(index);
                request.setAttribute("listSub", listSub);
            }
        } else {
            if (search != null && !search.isEmpty()) {
                // Trường hợp có tìm kiếm
                int page = sd.getNumPageByNameLecturer(search);
                request.setAttribute("page", page);
                listSearch = sd.filterSubjectPagingLecturer(search, index);
                request.setAttribute("listSub", listSearch);
                request.setAttribute("search_name", search); // Giữ lại giá trị search
            } else {
                // Trường hợp không có tìm kiếm
                int page = sd.getNumPageByNameLecturer(search);
                request.setAttribute("page", page);
                // lay subject phan trang
                List<Subject> listSub = sd.getSubjectPageLecturer(index);
                request.setAttribute("listSub", listSub);
            }
        }

        request.getRequestDispatcher("subjectlist.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User u = (User) request.getSession().getAttribute("user");
        SubjectDAO sd = new SubjectDAO();
        String index_raw = request.getParameter("index");
        String search = request.getParameter("search_name");

        if (index_raw == null) {
            index_raw = "1";
        }
        int index = Integer.parseInt(index_raw);
        
        List<Subject> listSearch = new ArrayList<>();
        
        if (u == null || u.getRole_id() != 3) {
            int page = sd.getNumPageByNameLearner(search);
            request.setAttribute("page", page);
            listSearch = sd.filterSubjectPagingLearner(search, index);
        } else {
            int page = sd.getNumPageByNameLecturer(search);
            request.setAttribute("page", page);
            listSearch = sd.filterSubjectPagingLecturer(search, index);
        }

        request.setAttribute("listSub", listSearch);
        request.setAttribute("search_name", search); // Giữ lại giá trị search

        request.getRequestDispatcher("subjectlist.jsp").forward(request, response);
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
