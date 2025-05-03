/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.common;

import dao.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "CheckPurchaseController", urlPatterns = {"/checkPurchase"})
public class CheckPurchaseController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Integer accountId = (Integer) (session != null ? session.getAttribute("account_id") : null);

        String courseId = request.getParameter("course_id");
        boolean purchased = false;

        if (accountId != null && courseId != null) {
            OrderDAO orderDAO = new OrderDAO();
            purchased = orderDAO.hasPurchasedSubject(accountId, courseId);
        }

        // Trả về kết quả dưới dạng JSON
        response.setContentType("application/json");
        response.getWriter().write("{\"purchased\": " + purchased + "}");
    }
}
