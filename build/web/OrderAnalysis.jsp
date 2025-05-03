<%-- 
    Document   : OrderAnalysis
    Created on : Nov 3, 2024, 6:42:33 PM
    Author     : TRUONG GIANG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="dao.OrderDAO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Analysis</title>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <style>
            body {
                font-family: 'Arial', sans-serif;
                margin: 0;
                padding: 20px;
                background-color: #f4f4f4;
            }

            h1 {
                text-align: center;
                color: #333;
            }

            table {
                width: 80%;
                margin: 20px auto;
                border-collapse: collapse;
                background-color: #fff;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            th, td {
                padding: 12px 15px;
                text-align: left;
                border-bottom: 1px solid #ddd;
            }

            th {
                background-color: #f0f0f0;
                font-weight: bold;
            }

            tr:nth-child(even) {
                background-color: #f9f9f9;
            }

            .chart-container {
                width: 80%;
                margin: 20px auto;
            }
            /* Thêm lớp CSS cho các nút */
            .button-link {
                display: inline-block;
                padding: 10px 20px;
                margin: 10px auto;
                text-align: center;
                text-decoration: none;
                color: white;
                font-weight: bold;
                border-radius: 5px;
                transition: background-color 0.3s ease;
                width: 100%;
                max-width: 250px;
            }

            /* Màu sắc cho các nút */
            .button-link.course-popularity {
                background-color: #4CAF50;
            } /* Xanh lá cho độ phổ biến */
            .button-link.order-volume {
                background-color: #FF5722;
            } /* Cam cho khối lượng đơn hàng */
            .button-link.order-volume-month {
                background-color: #2196F3;
            } /* Xanh da trời cho theo tháng */
            .button-link.trends-month {
                background-color: #9C27B0;
            } /* Tím cho xu hướng theo tháng */
            .button-link.revenue-4months {
                background-color: #FFC107;
            } /* Vàng cho doanh thu 4 tháng */
            .button-link.revenue-year {
                background-color: #607D8B;
            } /* Xám cho doanh thu theo năm */

            /* Hiệu ứng hover */
            .button-link:hover {
                opacity: 0.8;
            }

            /* Định dạng bố cục các nút */
            .button-container {
                display: flex;
                flex-direction: column;
                align-items: center;
                gap: 10px;
                margin: 20px 0;
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <div class="button-container">
            <a href="CoursePopularity.jsp" class="button-link course-popularity">View Course Popularity Chart</a>
            <a href="OrderVolume.jsp" class="button-link order-volume">View Order Volume Chart</a>
            <a href="OrderVolumebyMonth.jsp" class="button-link order-volume-month">View Order Volume by Month Chart</a>
            <a href="TrendsbyMonth.jsp" class="button-link trends-month">View User Trends by Month Chart</a>
            <a href="TotalRevenueforLast4Months.jsp" class="button-link revenue-4months">View Total Revenue for Last 4 Months Chart</a>
            <a href="TotalRevenuebyYear.jsp" class="button-link revenue-year">View Total Revenue by Year Chart</a>
            <a href="AnalysisRerport.jsp" class="button-link revenue-year">Make Report</a>
        </div>
        <h1>Order Analysis</h1>
        <a href='ordersManagement' class="nav-item nav-link">Orders Manage</a>
        <%
            OrderDAO orderDAO = new OrderDAO();
            Map<String, Integer> enrollments = orderDAO.getEnrollments();
            Map<String, Integer> coursePopularity = orderDAO.getCoursePopularity();
            
            // Get current date and yesterday's date
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date currentDate = new Date();
            String today = formatter.format(currentDate);
            String yesterday = formatter.format(new Date(currentDate.getTime() - (24 * 60 * 60 * 1000)));
            
            // Calculate order volume
            int orderVolumeToday = orderDAO.getOrderVolume(today, today);
            int orderVolumeYesterday = orderDAO.getOrderVolume(yesterday, yesterday);
            
            // Get current year
            Calendar calendar = Calendar.getInstance();
            int currentYear = calendar.get(Calendar.YEAR);
            Map<String, Integer> orderVolumeByMonth = orderDAO.getOrderVolumeByMonth(String.valueOf(currentYear));
            
            // Get user trends for the last 7 days
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -7); // Go back 7 days
            String lastWeekStartDate = formatter.format(cal.getTime());
            Map<String, Map<Integer, Integer>> userTrends = orderDAO.getUserTrends(lastWeekStartDate, today); // Get trends for the last 7 days
            
            // Get user trends for the last 4 months
            cal.add(Calendar.MONTH, -4); // Go back 4 months
            String fourMonthsAgoStartDate = formatter.format(cal.getTime());
            Map<String, Map<Integer, Integer>> userTrendsByMonth = orderDAO.getUserTrendsByMonth(String.valueOf(currentYear));
            
            // Convert userTrendsByMonth to the expected format
            Map<String, Map<String, Integer>> userTrendsByMonthFormatted = new HashMap<>();
            for (Map.Entry<String, Map<Integer, Integer>> entry : userTrendsByMonth.entrySet()) {
                String subjectName = entry.getKey();
                Map<Integer, Integer> monthCounts = entry.getValue();
                Map<String, Integer> formattedMonthCounts = new HashMap<>();
                for (Map.Entry<Integer, Integer> monthEntry : monthCounts.entrySet()) {
                    formattedMonthCounts.put(String.valueOf(monthEntry.getKey()), monthEntry.getValue());
                }
                userTrendsByMonthFormatted.put(subjectName, formattedMonthCounts);
            }
        %>
        <h2>Enrollment Statistics</h2>
        <table>
            <thead>
                <tr>
                    <th>Subject Name</th>
                    <th>Enrollments</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (Map.Entry<String, Integer> entry : enrollments.entrySet()) {
                %>
                <tr>
                    <td><%= entry.getKey() %></td>
                    <td><%= entry.getValue() %></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>

        


    </body>
</html>

