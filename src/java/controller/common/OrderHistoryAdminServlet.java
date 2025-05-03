package controller.common;

import dao.OrderDAO;
import model.Orders;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/orderHistoryAdmin")
public class OrderHistoryAdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        OrderDAO orderDAO = new OrderDAO();
        List<Orders> orderList = orderDAO.getAllOrders();

        request.setAttribute("orderList", orderList);
        request.getRequestDispatcher("OrderHistoryAdmin.jsp").forward(request, response);
    }
}
