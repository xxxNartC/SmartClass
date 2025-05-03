<%-- 
    Document   : AnalysisRerport
    Created on : Nov 5, 2024, 12:26:04 AM
    Author     : TRUONG GIANG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="dao.OrderDAO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="controller.common.GeminiChatbot1"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Analysis Report</title>
        <style>
            body {
                font-family: 'Arial', sans-serif;
                margin: 0;
                padding: 20px;
                background-color: #f9f9f9;
                color: #333;
            }

            .report-container {
                max-width: 800px;
                margin: 0 auto;
                padding: 20px;
                background-color: #fff;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                border-radius: 8px;
            }

            h1 {
                text-align: center;
                color: #333;
                font-size: 2em;
                margin-bottom: 30px;
            }

            .section-title {
                font-size: 1.5em;
                color: #555;
                margin-top: 30px;
                margin-bottom: 10px;
                border-bottom: 2px solid #ddd;
                padding-bottom: 5px;
            }

            .report-data {
                font-size: 1em;
                line-height: 1.6;
                margin-top: 10px;
            }

            .highlight {
                font-weight: bold;
                color: #007BFF;
            }

            .key-info {
                font-size: 1.2em;
                color: #FF5722;
                font-weight: bold;
                margin-bottom: 20px;
            }

            .evaluation {
                background-color: #e7f3fe;
                border-left: 4px solid #2196F3;
                padding: 15px;
                margin-bottom: 20px;
                font-size: 1.1em;
                line-height: 1.6;
            }

            ul {
                list-style-type: disc;
                padding-left: 20px;
            }

            /* Table styling for detailed data */
            table {
                width: 100%;
                margin-top: 20px;
                border-collapse: collapse;
            }

            th, td {
                padding: 10px;
                border: 1px solid #ddd;
                text-align: left;
            }

            th {
                background-color: #f0f0f0;
                font-weight: bold;
            }

            tr:nth-child(even) {
                background-color: #f9f9f9;
            }
        </style>
    </head>
    <body>
        <div class="report-container">
            <h1>Order Analysis Report</h1>
            <div style="display: flex; justify-content: flex-end; width: 100%; margin-bottom: 15px;">
                <a href="OrderAnalysis.jsp" class="order-analysis-btn">Order Analysis</a>
            </div>
            <%
                OrderDAO orderDAO = new OrderDAO();

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

                // Get total revenue for the last 4 months
                Map<String, Double> totalRevenueLast4Months = orderDAO.getTotalRevenueLast4Months();

                // Get total revenue for the current year
                Map<String, Double> totalRevenueByYear = orderDAO.getTotalRevenueByYear(String.valueOf(currentYear));

                // Generate report and evaluation using GeminiChatbot1
                String evaluation = GeminiChatbot1.generateReport(
                        orderVolumeToday,
                        orderVolumeYesterday,
                        orderVolumeByMonth,
                        userTrends,
                        userTrendsByMonth,
                        totalRevenueLast4Months,
                        totalRevenueByYear
                );
            %>
            
            <div class="evaluation">
                <h2>Overall Evaluation</h2>
                <p><%= evaluation.replaceAll("\n", "<br>") %></p>
            </div>
            
            <div class="report-data">
                <div class="key-info">
                    Order Volume Today: <span class="highlight"><%= orderVolumeToday %></span><br>
                    Order Volume Yesterday: <span class="highlight"><%= orderVolumeYesterday %></span>
                </div>
                
                <div class="section-title">Monthly Order Volume (<%= currentYear %>)</div>
                <table>
                    <thead>
                        <tr>
                            <th>Month</th>
                            <th>Order Volume</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (Map.Entry<String, Integer> entry : orderVolumeByMonth.entrySet()) {
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

                

                <div class="section-title">User Trends by Month for the Last 4 Months</div>
                <div class="report-data">
                    <ul>
                        <% for (Map.Entry<String, Map<Integer, Integer>> trend : userTrendsByMonth.entrySet()) { %>
                            <li><span class="highlight"><%= trend.getKey() %></span>: <%= trend.getValue().toString() %></li>
                        <% } %>
                    </ul>
                </div>

                <div class="section-title">Total Revenue for the Last 4 Months</div>
                <div class="report-data">
                    <ul>
                        <% for (Map.Entry<String, Double> revenue : totalRevenueLast4Months.entrySet()) { %>
                            <li><span class="highlight"><%= revenue.getKey() %></span>: $<%= revenue.getValue() %></li>
                        <% } %>
                    </ul>
                </div>

                <div class="section-title">Total Revenue by Year (<%= currentYear %>)</div>
                <div class="report-data">
                    <ul>
                        <% for (Map.Entry<String, Double> revenue : totalRevenueByYear.entrySet()) { %>
                            <li><span class="highlight"><%= revenue.getKey() %></span>: $<%= revenue.getValue() %></li>
                        <% } %>
                    </ul>
                </div>
            </div>
        </div>
    </body>
</html>

