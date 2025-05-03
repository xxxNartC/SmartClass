package controller.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.OrderDetail;
import dao.OrderDetailDAO;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/addToCart")
public class AddToCartServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        // Lấy thông tin từ form
        int subjectId = Integer.parseInt(request.getParameter("subject_id"));
        float price = Float.parseFloat(request.getParameter("price"));

        // Lấy session hiện tại
        HttpSession session = request.getSession();

        // Kiểm tra xem orderId đã tồn tại trong session chưa
        Integer orderId = (Integer) session.getAttribute("orderId");

        // Nếu orderId chưa tồn tại, tạo orderId mới và lưu vào session
        if (orderId == null) {
            Random rand = new Random();
            orderId = rand.nextInt(100000);
            session.setAttribute("orderId", orderId);
        }

        // Lưu thông tin vào bảng OrderDetail
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(orderId);
        orderDetail.setSubjectId(subjectId);
        orderDetail.setPrice(price);

        // Lưu vào database
        OrderDetailDAO dao = new OrderDetailDAO();
        boolean isAdded = dao.addOrderDetail(orderDetail);

        if (isAdded) {
            out.print("{\"success\": true}");
        } else {
            out.print("{\"success\": false}");
        }
        out.flush();
    }
}

