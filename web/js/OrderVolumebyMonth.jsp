<%-- 
    Document   : OrderVolumebyMonth
    Created on : Nov 5, 2024, 12:01:16 AM
    Author     : TRUONG GIANG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<%@page import="dao.OrderDAO"%>
<%@page import="java.util.Calendar"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Volume by Month</title>
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

            .chart-container {
                width: 80%;
                margin: 20px auto;
            }
        </style>
    </head>
    <body>
        <h1>Order Volume by Month</h1>
        <%
            OrderDAO orderDAO = new OrderDAO();
            Calendar calendar = Calendar.getInstance();
            int currentYear = calendar.get(Calendar.YEAR);
            Map<String, Integer> orderVolumeByMonth = orderDAO.getOrderVolumeByMonth(String.valueOf(currentYear));
        %>
        <div class="chart-container">
            <canvas id="orderVolumeByMonthChart"></canvas>
        </div>
        <script>
            var orderVolumeByMonthData = {
                labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
                datasets: [{
                        label: 'Order Volume',
                        data: [
            <%= orderVolumeByMonth.getOrDefault("1", 0) %>,
            <%= orderVolumeByMonth.getOrDefault("2", 0) %>,
            <%= orderVolumeByMonth.getOrDefault("3", 0) %>,
            <%= orderVolumeByMonth.getOrDefault("4", 0) %>,
            <%= orderVolumeByMonth.getOrDefault("5", 0) %>,
            <%= orderVolumeByMonth.getOrDefault("6", 0) %>,
            <%= orderVolumeByMonth.getOrDefault("7", 0) %>,
            <%= orderVolumeByMonth.getOrDefault("8", 0) %>,
            <%= orderVolumeByMonth.getOrDefault("9", 0) %>,
            <%= orderVolumeByMonth.getOrDefault("10", 0) %>,
            <%= orderVolumeByMonth.getOrDefault("11", 0) %>,
            <%= orderVolumeByMonth.getOrDefault("12", 0) %>
                        ],
                        backgroundColor: 'rgba(54, 162, 235, 0.2)',
                        borderColor: 'rgba(54, 162, 235, 1)',
                        borderWidth: 1
                    }]
            };

            var orderVolumeByMonthChart = new Chart(document.getElementById('orderVolumeByMonthChart'), {
                type: 'bar',
                data: orderVolumeByMonthData,
                options: {
                    scales: {
                        y: {
                            beginAtZero: true
                        }
                    }
                }
            });
        </script>
    </body>
</html>

