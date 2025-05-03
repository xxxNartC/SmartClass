package dao;

import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.OrderDetail;
import jakarta.servlet.http.HttpSession;

public class OrderDetailDAO extends DBContext {

    // Phương thức thêm OrderDetail vào cơ sở dữ liệu
    public boolean addOrderDetail(OrderDetail orderDetail) {
        String sql = "INSERT INTO OrderDetail (orderId, subjectId, price) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, orderDetail.getOrderId());
            ps.setInt(2, orderDetail.getSubjectId());
            ps.setFloat(3, orderDetail.getPrice());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Phương thức lấy thông tin giỏ hàng từ bảng OrderDetail và Subject
    public List<OrderDetail> getCartItems(HttpSession session) {
        List<OrderDetail> list = new ArrayList<>();
        String sql = "SELECT o.orderId, o.subjectId, s.subject_name, o.price "
                   + "FROM OrderDetail o "
                   + "JOIN Subject s ON o.subjectId = s.subject_id";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrderId(rs.getInt("orderId"));
                orderDetail.setSubjectId(rs.getInt("subjectId"));
                orderDetail.setSubjectName(rs.getString("subject_name"));
                orderDetail.setPrice(rs.getFloat("price"));
                list.add(orderDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
