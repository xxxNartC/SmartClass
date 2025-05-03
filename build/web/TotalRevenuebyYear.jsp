<%-- 
    Document   : TotalRevenuebyYear
    Created on : Nov 5, 2024, 12:02:38 AM
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
        <title>Total Revenue by Year</title>
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
        <h1>Total Revenue by Year</h1>
        <div style="display: flex; justify-content: flex-end; width: 100%; margin-bottom: 15px;">
                <a href="OrderAnalysis.jsp" class="order-analysis-btn">Order Analysis</a>
            </div>
        <%
            OrderDAO orderDAO = new OrderDAO();
            Calendar calendar = Calendar.getInstance();
            int currentYear = calendar.get(Calendar.YEAR);
            Map<String, Double> totalRevenueByMonth = orderDAO.getTotalRevenueByMonth(String.valueOf(currentYear));
        %>
        <div class="chart-container">
            <canvas id="totalRevenueByYearChart"></canvas>
        </div>
        <script>
            var totalRevenueByYearData = {
                labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
                datasets: [{
                        label: 'Total Revenue (VND)',
                        data: [
            <%
                        for (int i = 1; i <= 12; i++) {
                            out.print(totalRevenueByMonth.getOrDefault(String.valueOf(i), 0.0) + ",");
                        }
            %>
                        ],
                        backgroundColor: 'rgba(153, 102, 255, 0.2)',
                        borderColor: 'rgba(153, 102, 255, 1)',
                        borderWidth: 1
                    }]
            };

            var totalRevenueByYearChart = new Chart(document.getElementById('totalRevenueByYearChart'), {
                type: 'bar',
                data: totalRevenueByYearData,
                options: {
                    scales: {
                        y: {
                            beginAtZero: true,
                            title: { display: true, text: 'Revenue (VND)' }
                        },
                        x: {
                            title: { display: true, text: 'Month' }
                        }
                    }
                }
            });
        </script>
    </body>
</html>

