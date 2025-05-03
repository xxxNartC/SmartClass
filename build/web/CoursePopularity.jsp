<%-- 
    Document   : CoursePopularity.jsp
    Created on : Nov 5, 2024, 12:00:26 AM
    Author     : TRUONG GIANG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<%@page import="dao.OrderDAO"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Course Popularity</title>
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
        <h1>Course Popularity</h1>
        <div style="display: flex; justify-content: flex-end; width: 100%; margin-bottom: 15px;">
                <a href="OrderAnalysis.jsp" class="order-analysis-btn">Order Analysis</a>
            </div>
        <%
            OrderDAO orderDAO = new OrderDAO();
            Map<String, Integer> coursePopularity = orderDAO.getCoursePopularity();
        %>
        <div class="chart-container">
            <canvas id="coursePopularityChart"></canvas>
        </div>
        <script>
            var coursePopularityData = {
                labels: [
            <%
                        for (String subjectName : coursePopularity.keySet()) {
                            out.print("'" + subjectName + "',");
                        }
            %>
                ],
                datasets: [{
                        label: 'Enrollments',
                        data: [
            <%
                            for (Integer enrollmentCount : coursePopularity.values()) {
                                out.print(enrollmentCount + ",");
                            }
            %>
                        ],
                        backgroundColor: [
                            'rgba(255, 99, 132, 0.2)',
                            'rgba(54, 162, 235, 0.2)',
                            'rgba(255, 206, 86, 0.2)',
                            'rgba(75, 192, 192, 0.2)',
                            'rgba(153, 102, 255, 0.2)',
                            'rgba(255, 159, 64, 0.2)'
                        ],
                        borderColor: [
                            'rgba(255, 99, 132, 1)',
                            'rgba(54, 162, 235, 1)',
                            'rgba(255, 206, 86, 1)',
                            'rgba(75, 192, 192, 1)',
                            'rgba(153, 102, 255, 1)',
                            'rgba(255, 159, 64, 1)'
                        ],
                        borderWidth: 1
                    }]
            };

            var coursePopularityChart = new Chart(document.getElementById('coursePopularityChart'), {
                type: 'bar',
                data: coursePopularityData,
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

