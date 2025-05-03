/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.common;

import dao.ChapterDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Chapter;

/**
 *
 * @author slhoa
 */
@WebServlet(name = "ChapterListController", urlPatterns = {"/chapterlist"})
public class ChapterListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ChapterDAO cd = new ChapterDAO();
        String index_raw = request.getParameter("index");
        String search = request.getParameter("search_name");

        if (index_raw == null) {
            index_raw = "1";
        }
        int index = Integer.parseInt(index_raw);

        if (search != null && !search.isEmpty()) {
            // Trường hợp có tìm kiếm
            int page = cd.getNumPageByName(search);
            request.setAttribute("page", page);
                List<Chapter> listSearch = cd.filterChapterPaging(search, index);
            request.setAttribute("listChap", listSearch);
            request.setAttribute("search_name", search); // Giữ lại giá trị search
        } else {
            // Trường hợp không có tìm kiếm
            int page = cd.getNumPage();
            request.setAttribute("page", page);
            // lay subject phan trang
            List<Chapter> listChap = cd.getChapterPage(index);
            request.setAttribute("listChap", listChap);
        }

        request.getRequestDispatcher("chapterlist.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ChapterDAO cd = new ChapterDAO();
        String index_raw = request.getParameter("index");
        String search = request.getParameter("search_name");

        if (index_raw == null) {
            index_raw = "1";
        }
        int index = Integer.parseInt(index_raw);

        // Tìm kiếm và phân trang kết quả tìm kiếm
        int page = cd.getNumPageByName(search);
        request.setAttribute("page", page);
        List<Chapter> listSearch = cd.filterChapterPaging(search, index);
        request.setAttribute("listChap", listSearch);
        request.setAttribute("search_name", search); // Giữ lại giá trị search

        request.getRequestDispatcher("chapterlist.jsp").forward(request, response);
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
