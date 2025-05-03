/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.auth;

import dal.DBContext;
import dao.AccountDAO;
import dao.LearnerSubjectDAO;
import dao.SubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Learner_Subject;
import model.Subject;

/**
 *
 * @author Admin
 */
@WebServlet(name="MySubjectController", urlPatterns={"/subjectreview"})
public class SubjectReviewController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SubjectDAO sd = new SubjectDAO();
        String index_raw = request.getParameter("index");
        String search = request.getParameter("search_name");

        if (index_raw == null) {
            index_raw = "1";
        }
        int index = Integer.parseInt(index_raw);

        if (search != null && !search.isEmpty()) {
            // Trường hợp có tìm kiếm
            int page = sd.getNumPageByNameLecturer(search);
            request.setAttribute("page", page);
            List<Subject> listSearch = sd.filterSubjectPagingLecturer(search, index);
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

        request.getRequestDispatcher("subjectReview.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SubjectDAO sd = new SubjectDAO();
        String index_raw = request.getParameter("index");
        String search = request.getParameter("search_name");

        if (index_raw == null) {
            index_raw = "1";
        }
        int index = Integer.parseInt(index_raw);

        // Tìm kiếm và phân trang kết quả tìm kiếm
        int page = sd.getNumPageByNameLecturer(search);
        request.setAttribute("page", page);
        List<Subject> listSearch = sd.filterSubjectPagingLecturer(search, index);
        request.setAttribute("listSub", listSearch);
        request.setAttribute("search_name", search); // Giữ lại giá trị search

        request.getRequestDispatcher("subjectReview.jsp").forward(request, response);
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
