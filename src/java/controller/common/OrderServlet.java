package controller.common;

import dao.OrderDAO;
import model.Orders;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/orderHistory")
public class OrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy account_id từ session của người dùng đã đăng nhập
        HttpSession session = request.getSession();
        Integer accountId = (Integer) session.getAttribute("account_id");

        // Kiểm tra nếu accountId không có thì chuyển hướng về trang login
        if (accountId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Lấy danh sách đơn hàng từ OrderDAO
        OrderDAO orderDAO = new OrderDAO();
        List<Orders> orderList = orderDAO.getOrdersByAccountId(accountId);

        // Đặt danh sách đơn hàng vào request
        request.setAttribute("orderList", orderList);

        // Chuyển hướng đến trang JSP để hiển thị dữ liệu
        request.getRequestDispatcher("OrderHistory.jsp").forward(request, response);
    }
}
