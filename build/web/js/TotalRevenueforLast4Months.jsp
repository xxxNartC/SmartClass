<%-- 
    Document   : TotalRevenueforLast4Months
    Created on : Nov 5, 2024, 12:02:17 AM
    Author     : TRUONG GIANG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<%@page import="dao.OrderDAO"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Total Revenue for Last 4 Months</title>
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
        <h1>Total Revenue for Last 4 Months</h1>
        <%
            OrderDAO orderDAO = new OrderDAO();
            Map<String, Double> totalRevenueLast4Months = orderDAO.getTotalRevenueLast4Months();
        %>
        <div class="chart-container">
            <canvas id="totalRevenueLast4MonthsChart"></canvas>
        </div>
        <script>
            var totalRevenueLast4MonthsData = {
                labels: [
            <%
                for (String month : totalRevenueLast4Months.keySet()) {
                    out.print("'" + month + "',");
                }
            %>
        ],
        datasets: [{
            label: 'Total Revenue (VND)',
            data: [
                <%
                    for (Double revenue : totalRevenueLast4Months.values()) {
                        out.print(revenue + ",");
                    }
                %>
            ],
            borderColor: 'rgba(153, 102, 255, 1)',
            backgroundColor: 'rgba(153, 102, 255, 0.2)',
            borderWidth: 1
        }]
    };

    var totalRevenueLast4MonthsChart = new Chart(document.getElementById('totalRevenueLast4MonthsChart'), {
        type: 'bar',
        data: totalRevenueLast4MonthsData,
        options: {
            responsive: true,
            plugins: {
                legend: { position: 'top' },
                title: { display: true, text: 'Total Revenue for Last 4 Months (VND)' }
            },
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

