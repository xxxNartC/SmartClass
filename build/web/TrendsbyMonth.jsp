<%-- 
    Document   : TrendsbyMonth
    Created on : Nov 5, 2024, 12:01:44 AM
    Author     : TRUONG GIANG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<%@page import="dao.OrderDAO"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.HashMap"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Trends by Month</title>
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
        <h1>User Trends by Month for Popular Courses (2024)</h1>
        <div style="display: flex; justify-content: flex-end; width: 100%; margin-bottom: 15px;">
                <a href="OrderAnalysis.jsp" class="order-analysis-btn">Order Analysis</a>
            </div>
        <%
            OrderDAO orderDAO = new OrderDAO();
            Calendar calendar = Calendar.getInstance();
            int currentYear = calendar.get(Calendar.YEAR);
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
        <div class="chart-container">
            <canvas id="userTrendsByMonthChart"></canvas>
        </div>
        <script>
            var userTrendsByMonthData = {
                labels: ['January', 'February', 'March', 'April', 'May', 'June', ' July', 'August', 'September', 'October ', 'November', 'December'],
                datasets: [
            <% 
                        // Duyệt qua từng subjectName và tạo dataset cho biểu đồ
                        for (Map.Entry<String, Map<String, Integer>> entry : userTrendsByMonthFormatted.entrySet()) {
                            String subjectName = entry.getKey();
                            Map<String, Integer> monthlyCounts = entry.getValue();
                            // Chọn màu cho từng dòng biểu đồ
                            String color = "rgba(" + (int)(Math.random() * 255) + "," + (int)(Math.random() * 255) + "," + (int)(Math.random() * 255) + ", 1)";
            %>
                    {
                        label: '<%= subjectName %>',
                        data: [
            <%= monthlyCounts.getOrDefault("1", 0) %>,
            <%= monthlyCounts.getOrDefault("2", 0) %>,
            <%= monthlyCounts.getOrDefault("3", 0) %>,
            <%= monthlyCounts.getOrDefault("4", 0) %>,
            <%= monthlyCounts.getOrDefault("5", 0) %>,
            <%= monthlyCounts.getOrDefault("6", 0) %>,
            <%= monthlyCounts.getOrDefault("7", 0) %>,
            <%= monthlyCounts.getOrDefault("8", 0) %>,
            <%= monthlyCounts.getOrDefault("9", 0) %>,
            <%= monthlyCounts.getOrDefault("10", 0) %>,
            <%= monthlyCounts.getOrDefault("11", 0) %>,
            <%= monthlyCounts.getOrDefault("12", 0) %>
                        ],
                        borderColor: '<%= color %>',
                        backgroundColor: '<%= color %>',
                        fill: false,
                        tension: 0.4
                    },
            <% } %>
                ]
            };

            var userTrendsByMonthChart = new Chart(document.getElementById('userTrendsByMonthChart'), {
                type: 'line',
                data: userTrendsByMonthData,
                options: {
                    responsive: true,
                    plugins: {
                        legend: {
                            position: 'top',
                        },
                        title: {
                            display: true,
                            text: 'User Trends by Month for Popular Courses (2024)'
                        }
                    },
                    scales: {
                        y: {
                            title: {
                                display: true,
                                text: 'Số lượng người dùng'
                            },
                            beginAtZero: true
                        },
                        x: {
                            title: {
                                display: true,
                                text: 'Tháng'
                            }
                        }
                    }
                }
            });
        </script>
    </body>
</html>

