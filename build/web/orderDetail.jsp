<%-- 
    Document   : orderDetail
    Created on : Nov 2, 2024, 5:51:01 PM
    Author     : TRUONG GIANG
--%>

<%@page import="model.OrderDetails"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Order Detail</title>
    <style>
        /* CSS styling */
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }
        .container {
            width: 50%;
            padding: 20px;
            background-color: white;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }
        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: left;
        }
        th {
            background-color: #007BFF;
            color: white;
            width: 40%;
        }
        td {
            background-color: #f9f9f9;
        }
        tr:hover td {
            background-color: #f1f1f1;
        }
        .not-found {
            text-align: center;
            color: #ff0000;
            font-size: 1.2em;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Order Detail</h1>
        <%
            OrderDetails orderDetails = (OrderDetails) request.getAttribute("orderDetails");
            if (orderDetails != null) {
        %>
        <table>
            <tr>
                <th>Order ID</th>
                <td><%= orderDetails.getOrderId() %></td>
            </tr>
            <tr>
                <th>Subject ID</th>
                <td><%= orderDetails.getSubjectId() %></td>
            </tr>
            <tr>
                <th>Subject Name</th>
                <td><%= orderDetails.getSubjectName() %></td>
            </tr>
            <tr>
                <th>Price</th>
                <td><%= orderDetails.getPrice() %></td>
            </tr>
            <tr>
                <th>Username</th>
                <td><%= orderDetails.getUsername() %></td>
            </tr>
            <tr>
                <th>Email</th>
                <td><%= orderDetails.getEmail() %></td>
            </tr>
            <tr>
                <th>Phone</th>
                <td><%= orderDetails.getPhone() %></td>
            </tr>
            <tr>
                <th>Order Date</th>
                <td><%= orderDetails.getOrderDate() %></td>
            </tr>
            <tr>
                <th>Total Money</th>
                <td><%= orderDetails.getTotalMoney() %></td>
            </tr>
            <tr>
                <th>Payment Status</th>
                <td><%= orderDetails.getPaymentStatus() %></td>
            </tr>
        </table>
        <%
            } else {
        %>
        <p class="not-found">Order not found.</p>
        <%
            }
        %>
    </div>
</body>
</html>

