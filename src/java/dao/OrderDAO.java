package dao;

import dal.DBContext;
import jakarta.mail.FetchProfile.Item;
import model.Orders;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Order;
import model.OrderDetails;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import model.Cart;
import model.Subject;
import model.User;


public class OrderDAO extends DBContext {

    // Phương thức để lấy danh sách đơn hàng từ cơ sở dữ liệu
    public List<Orders> getAllOrders() {
        List<Orders> ordersList = new ArrayList<>();
        String query = "SELECT "
                + "o.order_id AS [Mã Đơn Hàng], "
                + "a.fullname AS [Người Mua], "
                + "s.subject_name AS [Môn Học], "
                + "o.order_date AS [Ngày Mua], "
                + "o.total_money AS [Số Tiền], "
                + "o.payment_status AS [Trạng Thái] "
                + "FROM Orders o "
                + "JOIN Account a ON o.account_id = a.account_id "
                + "JOIN Subject s ON o.total_money = s.price";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Orders order = new Orders();
                order.setOrderId(rs.getInt("Mã Đơn Hàng"));
                order.setAccountName(rs.getString("Người Mua"));
                order.setSubjectName(rs.getString("Môn Học"));
                order.setOrderDate(rs.getString("Ngày Mua"));
                order.setTotalMoney(rs.getFloat("Số Tiền"));
                order.setPaymentStatus(rs.getInt("Trạng Thái") == 1 ? "Xác Nhận" : "Chưa Xác Nhận");

                ordersList.add(order);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ordersList;
    }

    public List<Order> getAllOrder() {//Truong Giang
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT order_id, account_id, order_date, total_money, payment_status FROM orders";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int orderId = rs.getInt("order_id");
                Integer accountId = rs.getObject("account_id") != null ? rs.getInt("account_id") : null;
                String orderDate = rs.getString("order_date");
                Float totalMoney = rs.getObject("total_money") != null ? rs.getFloat("total_money") : null;
                String paymentStatus = rs.getString("payment_status");

                Order order = new Order(orderId, accountId, orderDate, totalMoney, paymentStatus);
                orders.add(order);
            }
            rs.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return orders;
    }

    // Phương thức để lấy chi tiết đơn hàng theo ID
    public OrderDetails getOrderDetail(int orderId) {
        OrderDetails orderDetail = null;
        String query = "SELECT od.id, od.order_id, od.subject_id, od.price, "
                + "s.subject_name, "
                + "a.username, a.email, a.phone, " // Add phone to the query
                + "o.order_date, o.total_money, o.payment_status "
                + "FROM order_detail od "
                + "JOIN subject s ON od.subject_id = s.subject_id "
                + "JOIN orders o ON od.order_id = o.order_id "
                + "JOIN account a ON o.account_id = a.account_id "
                + "WHERE od.order_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                orderDetail = new OrderDetails();
                orderDetail.setId(rs.getInt("id"));
                orderDetail.setOrderId(rs.getInt("order_id"));
                orderDetail.setSubjectId(rs.getInt("subject_id"));
                orderDetail.setPrice(rs.getDouble("price"));
                orderDetail.setSubjectName(rs.getString("subject_name"));
                orderDetail.setUsername(rs.getString("username"));
                orderDetail.setEmail(rs.getString("email"));
                orderDetail.setPhone(rs.getString("phone")); // Set phone from the result set
                orderDetail.setOrderDate(rs.getDate("order_date"));
                orderDetail.setTotalMoney(rs.getDouble("total_money"));
                orderDetail.setPaymentStatus(rs.getString("payment_status"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderDetail;
    }

    // Phương thức để cập nhật trạng thái thanh toán của đơn hàng
    public boolean updatePaymentStatus(int orderId, String paymentStatus) {
        String query = "UPDATE Orders SET payment_status = ? WHERE order_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, paymentStatus);
            ps.setInt(2, orderId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Phương thức mới để lấy danh sách đơn hàng theo sortOption
    public List<Order> getAllOrderSoft(String sortOption) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT order_id, account_id, order_date, total_money, payment_status FROM orders";

        if (sortOption != null && !sortOption.isEmpty()) {
            switch (sortOption) {
                case "orderDate_desc":
                    sql += " ORDER BY order_date DESC";
                    break;
                case "orderDate_asc":
                    sql += " ORDER BY order_date ASC";
                    break;
                case "orderId_asc":
                    sql += " ORDER BY order_id ASC";
                    break;
                case "orderId_desc":
                    sql += " ORDER BY order_id DESC";
                    break;
                case "totalMoney_asc":
                    sql += " ORDER BY total_money ASC";
                    break;
                case "totalMoney_desc":
                    sql += " ORDER BY total_money DESC";
                    break;
                default:
                    // Default sorting (no sorting)
                    break;
            }
        }

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int orderId = rs.getInt("order_id");
                Integer accountId = rs.getObject("account_id") != null ? rs.getInt("account_id") : null;
                String orderDate = rs.getString("order_date");
                Float totalMoney = rs.getObject("total_money") != null ? rs.getFloat("total_money") : null;
                String paymentStatus = rs.getString("payment_status");

                Order order = new Order(orderId, accountId, orderDate, totalMoney, paymentStatus);
                orders.add(order);
            }
            rs.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return orders;
    }

    // Phương thức mới để tìm kiếm đơn hàng
    public List<Order> searchOrders(String searchOrderId, String searchOrderDate, String searchAccountId) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT order_id, account_id, order_date, total_money, payment_status FROM orders WHERE ";
        boolean hasCondition = false;

        if (searchOrderId != null && !searchOrderId.isEmpty()) {
            sql += "order_id = ?";
            hasCondition = true;
        }

        if (searchOrderDate != null && !searchOrderDate.isEmpty()) {
            if (hasCondition) {
                sql += " AND ";
            }
            sql += "order_date = ?";
            hasCondition = true;
        }

        if (searchAccountId != null && !searchAccountId.isEmpty()) {
            if (hasCondition) {
                sql += " AND ";
            }
            sql += "account_id = ?";
            hasCondition = true;
        }

        if (!hasCondition) {
            // Default to no search (return all orders)
            return getAllOrder();
        }

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            int paramIndex = 1;
            if (searchOrderId != null && !searchOrderId.isEmpty()) {
                statement.setInt(paramIndex++, Integer.parseInt(searchOrderId));
            }
            if (searchOrderDate != null && !searchOrderDate.isEmpty()) {
                statement.setString(paramIndex++, searchOrderDate);
            }
            if (searchAccountId != null && !searchAccountId.isEmpty()) {
                statement.setInt(paramIndex++, Integer.parseInt(searchAccountId));
            }
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int orderId = rs.getInt("order_id");
                Integer accountId = rs.getObject("account_id") != null ? rs.getInt("account_id") : null;
                String orderDate = rs.getString("order_date");
                Float totalMoney = rs.getObject("total_money") != null ? rs.getFloat("total_money") : null;
                String paymentStatus = rs.getString("payment_status");

                Order order = new Order(orderId, accountId, orderDate, totalMoney, paymentStatus);
                orders.add(order);
            }
            rs.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return orders;
    }

    // Phương thức mới để thống kê số lượng người dùng đã ghi danh vào các khóa học
    public Map<String, Integer> getEnrollments() {
        Map<String, Integer> enrollments = new HashMap<>();
        String sql = "SELECT s.subject_name, COUNT(DISTINCT o.account_id) AS enrollment_count "
                + "FROM subject s "
                + "JOIN order_detail od ON s.subject_id = od.subject_id "
                + "JOIN orders o ON od.order_id = o.order_id "
                + "GROUP BY s.subject_name";

        try (PreparedStatement statement = connection.prepareStatement(sql); ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                String subjectName = rs.getString("subject_name");
                int enrollmentCount = rs.getInt("enrollment_count");
                enrollments.put(subjectName, enrollmentCount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return enrollments;
    }

    // Phương thức mới để tính toán khối lượng đơn hàng trong một khoảng thời gian
    public int getOrderVolume(String startDate, String endDate) {
        int orderCount = 0;
        String sql = "SELECT COUNT(*) FROM orders WHERE order_date BETWEEN ? AND ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, startDate);
            statement.setString(2, endDate);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                orderCount = rs.getInt(1);
            }
            rs.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderCount;
    }

    // Phương thức mới để tính toán khối lượng đơn hàng theo tháng
    public Map<String, Integer> getOrderVolumeByMonth(String year) {
        Map<String, Integer> orderVolumeByMonth = new HashMap<>();
        String sql = "SELECT MONTH(order_date) AS order_month, COUNT(*) AS order_count "
                + "FROM orders "
                + "WHERE YEAR(order_date) = ? "
                + "GROUP BY MONTH(order_date)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, year);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int orderMonth = rs.getInt("order_month");
                int orderCount = rs.getInt("order_count");
                // Use getOrDefault to handle missing months
                orderVolumeByMonth.put(String.valueOf(orderMonth), orderVolumeByMonth.getOrDefault(String.valueOf(orderMonth), 0) + orderCount);
            }
            rs.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return orderVolumeByMonth;
    }

    // Phương thức mới để lấy độ phổ biến của khóa học
    public Map<String, Integer> getCoursePopularity() {
        Map<String, Integer> coursePopularity = new HashMap<>();
        String sql = "SELECT s.subject_name, COUNT(DISTINCT o.account_id) AS enrollment_count "
                + "FROM subject s "
                + "JOIN order_detail od ON s.subject_id = od.subject_id "
                + "JOIN orders o ON od.order_id = o.order_id "
                + "GROUP BY s.subject_name "
                + "ORDER BY enrollment_count DESC";

        try (PreparedStatement statement = connection.prepareStatement(sql); ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                String subjectName = rs.getString("subject_name");
                int enrollmentCount = rs.getInt("enrollment_count");
                coursePopularity.put(subjectName, enrollmentCount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return coursePopularity;
    }

    // Phương thức mới để phân tích xu hướng người dùng theo ngày
    public Map<String, Map<Integer, Integer>> getUserTrends(String startDate, String endDate) {
        Map<String, Map<Integer, Integer>> userTrends = new HashMap<>();
        String sql = "SELECT s.subject_name, MONTH(o.order_date) AS order_month, COUNT(*) AS count "
                + "FROM orders o "
                + "JOIN account a ON o.account_id = a.account_id "
                + "JOIN order_detail od ON o.order_id = od.order_id "
                + "JOIN subject s ON od.subject_id = s.subject_id "
                + "WHERE o.order_date BETWEEN ? AND ? "
                + "GROUP BY s.subject_name, MONTH(o.order_date)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, startDate);
            statement.setString(2, endDate);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String subjectName = rs.getString("subject_name");
                int orderMonth = rs.getInt("order_month");
                int count = rs.getInt("count");

                // Nhóm theo subjectName và orderMonth
                userTrends.computeIfAbsent(subjectName, k -> new HashMap<>()).put(orderMonth, count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userTrends;
    }

    // Phương thức mới để phân tích xu hướng người dùng theo 4 tháng
    public Map<String, Map<Integer, Integer>> getUserTrendsByMonth(String year) {
        Map<String, Map<Integer, Integer>> userTrends = new HashMap<>();
        String sql = "SELECT s.subject_name, MONTH(o.order_date) AS order_month, COUNT(*) AS count "
                + "FROM orders o "
                + "JOIN order_detail od ON o.order_id = od.order_id "
                + "JOIN subject s ON od.subject_id = s.subject_id "
                + "WHERE YEAR(o.order_date) = ? "
                + "GROUP BY s.subject_name, MONTH(o.order_date)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, year);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String subjectName = rs.getString("subject_name");
                int orderMonth = rs.getInt("order_month");
                int count = rs.getInt("count");

                // Nhóm theo subjectName và orderMonth
                userTrends.computeIfAbsent(subjectName, k -> new HashMap<>()).put(orderMonth, count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userTrends;
    }
    // Phương thức tính tổng doanh thu theo từng tháng trong một năm

    public Map<String, Double> getTotalRevenueByMonth(String year) {
        Map<String, Double> revenueByMonth = new HashMap<>();
        String sql = "SELECT MONTH(order_date) AS month, SUM(total_money) AS total_revenue "
                + "FROM orders "
                + "WHERE YEAR(order_date) = ? "
                + "GROUP BY MONTH(order_date)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, year);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int month = rs.getInt("month");
                double totalRevenue = rs.getDouble("total_revenue");
                revenueByMonth.put(String.valueOf(month), totalRevenue);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return revenueByMonth;
    }

    // Phương thức tính tổng doanh thu trong 4 tháng gần nhất
    public Map<String, Double> getTotalRevenueLast4Months() {
        Map<String, Double> revenueLast4Months = new HashMap<>();
        String sql = "SELECT MONTH(order_date) AS month, YEAR(order_date) AS year, SUM(total_money) AS total_revenue "
                + "FROM orders "
                + "WHERE order_date >= DATEADD(MONTH, -4, GETDATE()) "
                + // Lấy các đơn hàng trong 4 tháng gần nhất
                "GROUP BY YEAR(order_date), MONTH(order_date) "
                + "ORDER BY YEAR(order_date), MONTH(order_date)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int month = rs.getInt("month");
                int year = rs.getInt("year");
                double totalRevenue = rs.getDouble("total_revenue");
                revenueLast4Months.put(year + "-" + month, totalRevenue);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return revenueLast4Months;
    }
    public Map<String, Double> getTotalRevenueByYear(String year) {
    Map<String, Double> revenueByMonth = new HashMap<>();
    String sql = "SELECT MONTH(order_date) AS month, SUM(total_money) AS total_revenue "
               + "FROM orders "
               + "WHERE YEAR(order_date) = ? "
               + "GROUP BY MONTH(order_date)";

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, year);
        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            int month = rs.getInt("month");
            double totalRevenue = rs.getDouble("total_revenue");
            revenueByMonth.put(String.valueOf(month), totalRevenue);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return revenueByMonth;
}

    

    public static void main(String[] args) {
        OrderDAO orderDAO = new OrderDAO();
        List<Order> orders = orderDAO.getAllOrder();
        for (Order order : orders) {
            System.out.println(order);
        }
        OrderDetails orderDetails = orderDAO.getOrderDetail(1);
        System.out.println(orderDetails);
    }

public void addOrder(User c, Cart cart, String payment) throws ParseException {
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date date = new java.sql.Date(utilDate.getTime());
        try {
            //add order
            String sql = "INSERT INTO [dbo].[orders]\n"
                    + "           ([account_id]\n"
                    + "           ,[order_date]\n"
                    + "           ,[total_money]\n"
                    + "           ,[payment_status])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, c.getAccount_id());
            st.setDate(2, date);
            st.setFloat(3, cart.getTotalMoney());
            st.setString(4, payment);
            st.executeUpdate();
            //lay id cua order vua add

            String sql1 = "select top 1 order_id from [dbo].[orders] order by order_id desc";
            PreparedStatement st1 = connection.prepareStatement(sql1);
            ResultSet rs = st1.executeQuery();
            //add bang OrderDetail
            while (rs.next()) {
                int oid = rs.getInt("order_id");
                for (model.Item i : cart.getItems()) {
                    try {
                        String sql2 = "INSERT INTO [dbo].[order_detail]\n"
                                + "           ([order_id]\n"
                                + "           ,[subject_id]\n"
                                + "           ,[price])\n"
                                + "     VALUES\n"
                                + "           (?,?,?)";
                        PreparedStatement st2 = connection.prepareStatement(sql2);
                        st2.setInt(1, oid);
                        st2.setInt(2, Integer.parseInt(i.getSubject().getSubject_id()));
                        st2.setFloat(3, i.getPrice());
                        st2.executeUpdate();
                    } catch (Exception e) {
                        System.out.println("Error from Dao1: " + e);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error from DAO2: " + e);
        }
    }
    public Orders getOrder() {

        String sql = "select top 1 order_id,account_id,order_date,total_money,payment_status from [orders]\n"
                + "order by order_id desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Orders c = new Orders(rs.getInt("order_id"), rs.getString("order_date"), rs.getFloat("total_money"),
                        rs.getString("payment_status"));
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void UpdateOrder(int id, String mess) {
        String sql = "UPDATE [dbo].[orders]\n"
                + "   SET [payment_status] = ?\n"
                + " WHERE order_id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, mess);
            st.setInt(2, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<Orders> getOrdersByAccountId(int accountId) {
        List<Orders> ordersList = new ArrayList<>();
        String query = "SELECT "
                + "s.subject_name AS [Môn Học], "
                + "o.order_date AS [Ngày Mua], "
                + "od.price AS [Giá], "
                + "o.total_money AS [Tổng Tiền], "
                + "o.payment_status AS [Trạng Thái] "
                + "FROM orders o "
                + "JOIN order_detail od ON o.order_id = od.order_id "
                + "JOIN subject s ON od.subject_id = s.subject_id "
                + "WHERE o.account_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Orders order = new Orders();
                order.setSubjectName(rs.getString("Môn Học"));
                order.setOrderDate(rs.getString("Ngày Mua"));
                order.setPrice(rs.getFloat("Giá"));
                order.setTotalMoney(rs.getFloat("Tổng Tiền"));
                order.setPaymentStatus(rs.getString("Trạng Thái"));
                ordersList.add(order);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ordersList;
    }

    public Orders getOrderById(int orderId) {
        String query = "SELECT o.order_id, a.fullname AS accountName, s.subject_name AS subjectName, o.order_date, o.total_money, o.payment_status "
                + "FROM orders o "
                + "JOIN account a ON o.account_id = a.account_id "
                + "JOIN subject s ON s.subject_id = o.subject_id " // Thay `subject_id` bằng trường phù hợp nếu cần
                + "WHERE o.order_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Orders(
                        rs.getInt("order_id"),
                        rs.getString("accountName"),
                        rs.getString("subjectName"),
                        rs.getString("order_date"),
                        rs.getFloat("total_money"),
                        rs.getString("payment_status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean hasPurchasedSubject(int accountId, String subjectId) {
        String sql = """
            SELECT od.subject_id 
            FROM orders o
            JOIN order_detail od ON o.order_id = od.order_id
            WHERE o.account_id = ? AND o.payment_status = 'Success' AND od.subject_id = ?
        """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, accountId);
            ps.setString(2, subjectId);
            ResultSet rs = ps.executeQuery();
            return rs.next();  // Returns true if a matching row is found
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Subject> getPurchasedSubjectsByAccountId(String accountId) {
        List<Subject> subjects = new ArrayList<>();
        String sql = """
            SELECT s.subject_id, s.subject_name, s.image, s.price 
            FROM orders o
            JOIN order_detail od ON o.order_id = od.order_id
            JOIN subject s ON od.subject_id = s.subject_id
            WHERE o.account_id = ? AND o.payment_status = 'Success'
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, accountId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Subject subject = new Subject();
                subject.setSubject_id(rs.getString("subject_id"));
                subject.setSubject_name(rs.getString("subject_name"));
                subject.setImage(rs.getString("image"));
                subject.setPrice(rs.getInt("price"));
                subjects.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjects;
    }

    public boolean checkUserAccessToCourse(int accountId, String subjectId) {
        String sql = """
        SELECT od.subject_id 
        FROM orders o
        JOIN order_detail od ON o.order_id = od.order_id
        WHERE o.account_id = ? AND o.payment_status = 'Success' AND od.subject_id = ?
    """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, accountId);
            ps.setString(2, subjectId);

            ResultSet rs = ps.executeQuery();
            return rs.next(); // Return true if access exists
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Lấy danh sách order_id có payment_status = "Success" dựa vào account_id
    public List<Integer> getSuccessfulOrderIdsByAccountId(int accountId) {
        List<Integer> orderIds = new ArrayList<>();
        String query = "SELECT order_id FROM orders WHERE account_id = ? AND payment_status = 'Success'";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                orderIds.add(rs.getInt("order_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderIds;
    }

    // Lấy subject_id từ order_detail dựa vào order_id
    public List<String> getSubjectIdsByOrderId(int orderId) {
        List<String> subjectIds = new ArrayList<>();
        String query = "SELECT subject_id FROM order_detail WHERE order_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                subjectIds.add(rs.getString("subject_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjectIds;
    }

}
