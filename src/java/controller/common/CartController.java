package controller.common;

import dao.OrderDetailDAO;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.OrderDetail;

@WebServlet(name = "CartController", urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy session hiện tại
        HttpSession session = request.getSession();

        // Lấy danh sách sản phẩm trong giỏ hàng từ DAO
        OrderDetailDAO dao = new OrderDetailDAO();
        List<OrderDetail> cartItems = dao.getCartItems(session);

        // Tính tổng giá trị giỏ hàng
        float cartTotal = 0;
        for (OrderDetail item : cartItems) {
            cartTotal += item.getPrice();
        }

        // Đặt giá trị vào request và chuyển tiếp đến trang Cart.jsp
        request.setAttribute("cartItems", cartItems);
        request.setAttribute("cartTotal", cartTotal);
        request.getRequestDispatcher("Cart.jsp").forward(request, response);
    }
}
