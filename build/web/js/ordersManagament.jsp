<%-- 
    Document   : ordersManagament
    Created on : Nov 2, 2024, 12:23:52 AM
    Author     : TRUONG GIANG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="dao.OrderDAO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="dao.AccountDAO"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Orders Management</title>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500&display=swap" rel="stylesheet">
        <style>
            /* Basic styles */
            body {
                font-family: 'Roboto', sans-serif;
                background-color: #f5f5f5;
                color: #333;
                margin: 0;
                padding: 20px;
                display: flex;
                justify-content: center;
                align-items: center;
                min-height: 100vh;
            }

            .container {
                width: 80%;
                padding: 20px;
                background-color: white;
                box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
                border-radius: 8px;
                display: flex;
                flex-direction: column;
                align-items: center;
            }

            h1 {
                text-align: center;
                color: #0073e6;
                margin-bottom: 20px;
            }

            /* Dropdown styling */
            select {
                padding: 10px;
                border-radius: 5px;
                border: 1px solid #0073e6;
                background-color: #ffffff;
                font-size: 16px;
                margin-bottom: 20px;
                cursor: pointer;
                transition: border-color 0.3s;
            }

            select:hover {
                border-color: #005bb5;
            }

            /* Table styling */
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
                background-color: #ffffff;
            }

            th, td {
                padding: 12px 15px;
                text-align: center;
                border: 1px solid #ddd;
            }

            th {
                background-color: #007BFF;
                color: white;
                font-weight: 500;
            }

            tr:nth-child(even) {
                background-color: #f9f9f9;
            }

            .no-data {
                text-align: center;
                font-size: 1.2em;
                color: #666;
                margin-top: 20px;
            }

            /* Hover effect */
            tbody tr:hover {
                background-color: #e6f7ff;
                transition: background-color 0.3s;
            }

            /* Save button styling */
            .save-button {
                padding: 10px 20px;
                font-size: 16px;
                color: white;
                background-color: #4CAF50;
                border: none;
                cursor: pointer;
                border-radius: 5px;
                margin-top: 20px;
            }

            /* Pagination styling */
            .pagination {
                display: flex;
                justify-content: center;
                margin-top: 20px;
            }

            .pagination a {
                padding: 8px 16px;
                margin: 2px;
                border: 1px solid #ddd;
                border-radius: 5px;
                text-decoration: none;
                color: #333;
            }

            .pagination a.active {
                background-color: #007BFF;
                color: white;
            }
            /* Form container styling */
            form[action="ordersManagement"] {
                width: 100%;
                display: flex;
                justify-content: space-between;
                align-items: center;
                gap: 15px;
                margin-bottom: 15px;
                padding: 10px;
                background-color: #f9f9f9;
                border-radius: 8px;
                box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.1);
            }

            /* Label styling */
            form[action="ordersManagement"] label {
                font-weight: 500;
                font-size: 14px;
                color: #333;
            }

            /* Input field styling */
            form[action="ordersManagement"] input[type="text"] {
                padding: 8px;
                width: 180px;
                border-radius: 5px;
                border: 1px solid #ddd;
                font-size: 16px;
                transition: border-color 0.3s;
            }

            /* Input hover and focus effects */
            form[action="ordersManagement"] input[type="text"]:hover,
            form[action="ordersManagement"] input[type="text"]:focus {
                border-color: #0073e6;
                outline: none;
            }

            /* Button styling within the form */
            form[action="ordersManagement"] .save-button {
                padding: 8px 16px;
                font-size: 14px;
                color: white;
                background-color: #4CAF50;
                border: none;
                cursor: pointer;
                border-radius: 5px;
                transition: background-color 0.3s;
            }

            /* Button hover effect */
            form[action="ordersManagement"] .save-button:hover {
                background-color: #45a049;
            }

            /* Optional additional alignment for labels and inputs */
            form[action="ordersManagement"] div {
                display: flex;
                align-items: center;
                gap: 10px;
            }

        </style>
        <script>
            // Function to handle sort changes
            function handleSortChange() {
                var sortOption = document.getElementById("sortOption").value;
                var url = "ordersManagement?sortOption=" + sortOption;
                window.location.href = url;
            }
        </script>
    </head>
    <body>
        <div class="container">
            <jsp:include page="header.jsp" />
            <h1>Orders Management</h1>
            <form action="ordersManagement" method="get">
                <div>
                    <label for="searchOrderId">Search by Order ID:</label>
                    <input type="text" name="searchOrderId" id="searchOrderId">
                    <button type="submit" class="save-button">Search by Order ID</button>
                </div>
            </form>
            <form action="ordersManagement" method="get">
                <div>
                    <label for="searchOrderDate">Search by Order Date:</label>
                    <input type="text" name="searchOrderDate" id="searchOrderDate">
                    <label for="searchAccountId">Search by Account ID:</label>
                    <input type="text" name="searchAccountId" id="searchAccountId">
                    <button type="submit" class="save-button">Search by Date & Account ID</button>
                </div>
            </form>
            <div style="display: flex; justify-content: flex-end; width: 100%; margin-bottom: 15px;">
    <a href="OrderAnalysis.jsp" class="order-analysis-btn">Order Analysis</a>
</div>

            <%
                String sortOption = request.getParameter("sortOption") != null ? request.getParameter("sortOption") : "orderDate_desc";
                int currentPage = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
                int pageSize = 5; // Number of orders per page
                
                // Get the total number of orders
                int totalOrders = ((List<Order>) request.getAttribute("orders")).size();
                int totalPages = (int) Math.ceil((double) totalOrders / pageSize);
                
                // Calculate the starting and ending indices for the current page
                int startIndex = (currentPage - 1) * pageSize;
                int endIndex = Math.min(currentPage * pageSize, totalOrders);
                
                // Create a sublist of orders for the current page
                List<Order> orders = new ArrayList<>();
                if (startIndex < endIndex) {
                    orders = ((List<Order>) request.getAttribute("orders")).subList(startIndex, endIndex);
                }
            %>
            <select name="sortOption" id="sortOption" onchange="handleSortChange()">
                <optgroup label="Sort by Order Date">
                    <option value="orderDate_desc" <%= "orderDate_desc".equals(sortOption) ? "selected" : "" %>>Newest First</option>
                    <option value="orderDate_asc" <%= "orderDate_asc".equals(sortOption) ? "selected" : "" %>>Oldest First</option>
                </optgroup>
                <optgroup label="Sort by Order ID">
                    <option value="orderId_asc" <%= "orderId_asc".equals(sortOption) ? "selected" : "" %>>Ascending</option>
                    <option value="orderId_desc" <%= "orderId_desc".equals(sortOption) ? "selected" : "" %>>Descending</option>
                </optgroup>
                <optgroup label="Sort by Total Money">
                    <option value="totalMoney_asc" <%= "totalMoney_asc".equals(sortOption) ? "selected" : "" %>>Ascending</option>
                    <option value="totalMoney_desc" <%= "totalMoney_desc".equals(sortOption) ? "selected" : "" %>>Descending</option>
                </optgroup>
            </select>


            <%
                if (orders != null && !orders.isEmpty()) {
            %>
            <form action="ordersManagement" method="post">
                <table>
                    <thead>
                        <tr>
                            <th>Order ID</th>
                            <th>Account ID</th>
                            <th>Order Date</th>
                            <th>Total Money</th>
                            <th>Payment Status</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (Order order : orders) {
                        %>
                        <tr>
                            <td><%= order.getOrderId() %></td>
                            <td><a href="ordersManagement?accountId=<%= order.getAccountId() %>"><%= order.getAccountId() %></a></td>
                            <td><%= order.getOrderDate() != null ? order.getOrderDate() : "N/A" %></td>
                            <td><%= order.getTotalMoney() != null ? order.getTotalMoney() : "N/A" %></td>
                            <td>
                                <select name="paymentStatus_<%= order.getOrderId() %>">
                                    <option value="Success" <%= "Success".equals(order.getPaymentStatus()) ? "selected" : "" %>>Success</option>
                                    <option value="Pending" <%= "Pending".equals(order.getPaymentStatus()) ? "selected" : "" %>>Pending</option>
                                    <option value="Cancelled" <%= "Cancelled".equals(order.getPaymentStatus()) ? "selected" : "" %>>Cancelled</option>
                                </select>
                            </td>
                            <td><a href="ordersManagement?orderId=<%= order.getOrderId() %>">View</a></td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
                <button type="submit" name="action" value="saveAll" class="save-button">Save</button>
            </form>
            <%
                } else {
            %>
            <div class="no-data">No orders found.</div>
            <%
                }
            %>

            <%
                // Pagination
                if (totalPages > 1) {
            %>
            <div class="pagination">
                <%
                    // Previous page link
                    if (currentPage > 1) {
                %>
                <a href="ordersManagement?page=<%= currentPage - 1 %>&sortOption=<%= sortOption %>">&laquo;</a>
                <%
                    }
                    // Page links
                    for (int i = 1; i <= totalPages; i++) {
                %>
                <a href="ordersManagement?page=<%= i %>&sortOption=<%= sortOption %>" <%= i == currentPage ? "class='active'" : "" %>><%= i %></a>
                <%
                    }
                    // Next page link
                    if (currentPage < totalPages) {
                %>
                <a href="ordersManagement?page=<%= currentPage + 1 %>&sortOption=<%= sortOption %>">&raquo;</a>
                <%
                    }
                %>
            </div>
            <%
                }
            %>
        </div>
    </body>
</html>

