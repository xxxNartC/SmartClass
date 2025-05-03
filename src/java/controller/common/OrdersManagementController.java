package controller.common;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import dao.OrderDAO;
import model.Order;
import model.OrderDetails;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import dao.AccountDAO;
import model.Account;

@WebServlet(name = "OrdersManagementController", urlPatterns = {"/ordersManagement"})
public class OrdersManagementController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        OrderDAO orderDAO = new OrderDAO();
        AccountDAO accountDAO = new AccountDAO();

        // Lấy thông tin sắp xếp từ request và danh sách đơn hàng có sắp xếp
        String sortOption = request.getParameter("sortOption");
        if (sortOption == null) {
            sortOption = "orderDate_desc"; // Sắp xếp mặc định
        }

        // Lấy thông tin tìm kiếm từ request
        String searchOrderId = request.getParameter("searchOrderId");
        String searchOrderDate = request.getParameter("searchOrderDate");
        String searchAccountId = request.getParameter("searchAccountId");

        List<Order> orders;
        if (searchOrderId != null || searchOrderDate != null || searchAccountId != null) {
            orders = orderDAO.searchOrders(searchOrderId, searchOrderDate, searchAccountId);
        } else {
            orders = orderDAO.getAllOrderSoft(sortOption);
        }

        request.setAttribute("orders", orders);
        request.setAttribute("sortOption", sortOption);

        // Kiểm tra xem có yêu cầu chi tiết đơn hàng không
        String orderIdStr = request.getParameter("orderId");
        if (orderIdStr != null) {
            try {
                int orderId = Integer.parseInt(orderIdStr);
                OrderDetails orderDetails = orderDAO.getOrderDetail(orderId);
                request.setAttribute("orderDetails", orderDetails);
                request.getRequestDispatcher("orderDetail.jsp").forward(request, response);
                return;
            } catch (NumberFormatException e) {
                System.err.println("Invalid order ID: " + orderIdStr);
            }
        }

        // Kiểm tra xem có yêu cầu thông tin tài khoản không
        String accountIdStr = request.getParameter("accountId");
        if (accountIdStr != null) {
            try {
                int accountId = Integer.parseInt(accountIdStr);
                Account account = accountDAO.getAccountById(accountId);
                request.setAttribute("account", account);
                request.getRequestDispatcher("CheckAccountIn4.jsp").forward(request, response);
                return;
            } catch (NumberFormatException e) {
                System.err.println("Invalid account ID: " + accountIdStr);
            }
        }

        // Thống kê số lượng người dùng đã ghi danh vào các khóa học
        Map<String, Integer> enrollments = orderDAO.getEnrollments();
        request.setAttribute("enrollments", enrollments);

        // Tính toán khối lượng đơn hàng
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        String today = formatter.format(currentDate);
        String yesterday = formatter.format(new Date(currentDate.getTime() - (24 * 60 * 60 * 1000)));
        int orderVolumeToday = orderDAO.getOrderVolume(today, today);
        int orderVolumeYesterday = orderDAO.getOrderVolume(yesterday, yesterday);
        request.setAttribute("orderVolumeToday", orderVolumeToday);
        request.setAttribute("orderVolumeYesterday", orderVolumeYesterday);

        // Tính toán khối lượng đơn hàng theo tháng
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        Map<String, Integer> orderVolumeByMonth = orderDAO.getOrderVolumeByMonth(String.valueOf(currentYear));
        request.setAttribute("orderVolumeByMonth", orderVolumeByMonth);

        // Lấy độ phổ biến của khóa học
        Map<String, Integer> coursePopularity = orderDAO.getCoursePopularity();
        request.setAttribute("coursePopularity", coursePopularity);

        // Phân tích xu hướng người dùng
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -7);
        String lastWeekStartDate = formatter.format(cal.getTime());
        Map<String, Map<Integer, Integer>> userTrends = orderDAO.getUserTrends(lastWeekStartDate, today);
        request.setAttribute("userTrends", userTrends);

        // Phân tích xu hướng người dùng theo 4 tháng
        cal.add(Calendar.MONTH, -4);
        String fourMonthsAgoStartDate = formatter.format(cal.getTime());
        Map<String, Map<Integer, Integer>> userTrendsByMonth = orderDAO.getUserTrendsByMonth(String.valueOf(currentYear));
        request.setAttribute("userTrendsByMonth", userTrendsByMonth);

        // Tính tổng doanh thu theo từng tháng của năm hiện tại
        Map<String, Double> totalRevenueByMonth = orderDAO.getTotalRevenueByMonth(String.valueOf(currentYear));
        request.setAttribute("totalRevenueByMonth", totalRevenueByMonth);

        // Tính tổng doanh thu trong 4 tháng gần nhất
        Map<String, Double> totalRevenueLast4Months = orderDAO.getTotalRevenueLast4Months();
        request.setAttribute("totalRevenueLast4Months", totalRevenueLast4Months);
        
        // Tính tổng doanh thu trong 1 năm
        Map<String, Double> totalRevenueYear = orderDAO.getTotalRevenueByYear(String.valueOf(currentYear));
        request.setAttribute("totalRevenueLast4Months", totalRevenueLast4Months);
        // Chuyển tiếp đến trang quản lý đơn hàng
        request.getRequestDispatcher("ordersManagament.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("saveAll".equals(action)) {
            OrderDAO orderDAO = new OrderDAO();
            boolean allUpdated = true;

            for (String paramName : request.getParameterMap().keySet()) {
                if (paramName.startsWith("paymentStatus_")) {
                    int orderId = Integer.parseInt(paramName.split("_")[1]);
                    String paymentStatus = request.getParameter(paramName);

                    boolean isUpdated = orderDAO.updatePaymentStatus(orderId, paymentStatus);
                    if (!isUpdated) {
                        allUpdated = false;
                    }
                }
            }

            if (allUpdated) {
                request.setAttribute("message", "All payment statuses updated successfully.");
            } else {
                request.setAttribute("error", "Some payment statuses could not be updated.");
            }

            String sortOption = request.getParameter("sortOption");
            if (sortOption == null) {
                sortOption = "orderDate_desc";
            }
            List<Order> orders = orderDAO.getAllOrderSoft(sortOption);
            request.setAttribute("orders", orders);
            request.setAttribute("sortOption", sortOption);

            request.getRequestDispatcher("ordersManagament.jsp").forward(request, response);
        } else {
            processRequest(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Orders Management Controller";
    }
}



