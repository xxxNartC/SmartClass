<%-- 
    Document   : OrderVolume.jsp
    Created on : Nov 5, 2024, 12:00:49 AM
    Author     : TRUONG GIANG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dao.OrderDAO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Volume</title>
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
        <h1>Order Volume</h1>
        <%
            OrderDAO orderDAO = new OrderDAO();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date currentDate = new Date();
            String today = formatter.format(currentDate);
            String yesterday = formatter.format(new Date(currentDate.getTime() - (24 * 60 * 60 * 1000)));
            
            int orderVolumeToday = orderDAO.getOrderVolume(today, today);
            int orderVolumeYesterday = orderDAO.getOrderVolume(yesterday, yesterday);
        %>
        <div class="chart-container">
            <canvas id="orderVolumeChart"></canvas>
        </div>
        <script>
            var orderVolumeData = {
                labels: ['Today', 'Yesterday'],
                datasets: [{
                        label: 'Order Volume',
                        data: [<%= orderVolumeToday %>, <%= orderVolumeYesterday %>],
                        backgroundColor: [
                            'rgba(54, 162, 235, 1)',
                            'rgba(255, 159, 64, 1)'
                        ],
                        borderColor: [
                            'rgba(54, 162, 235, 1)',
                            'rgba(255, 159, 64, 1)'
                        ],
                        borderWidth: 1
                    }]
            };

            var orderVolumeChart = new Chart(document.getElementById('orderVolumeChart'), {
                type: 'bar',
                data: orderVolumeData,
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

