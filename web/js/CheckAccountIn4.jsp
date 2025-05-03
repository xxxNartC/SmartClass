<%-- 
    Document   : CheckAccountIn4
    Created on : Nov 4, 2024, 2:00:19 AM
    Author     : TRUONG GIANG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Account"%>
<%@page import="model.OrderDetails"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account Information</title>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500&display=swap" rel="stylesheet">
        <style>
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
                max-width: 600px;
                background-color: white;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            }

            h1 {
                text-align: center;
                color: #0073e6;
                margin-bottom: 20px;
            }

            p {
                font-size: 16px;
                line-height: 1.6;
                margin: 10px 0;
            }

            .info-label {
                font-weight: 500;
                color: #0073e6;
            }

            .no-data {
                text-align: center;
                font-size: 1.2em;
                color: #666;
                margin-top: 20px;
            }
        </style>
    </head>
    <body>        
        <div class="container">
           
            <h1>Account Information</h1>
            <%
                Account account = (Account) request.getAttribute("account");
                OrderDetails orderDetails = (OrderDetails) request.getAttribute("orderDetails");
                if (account != null) {
            %>
            <p><span class="info-label">Account ID:</span> <%= account.getAccount_id() %></p>
            <p><span class="info-label">Username:</span> <%= account.getUsername() %></p>
            <p><span class="info-label">Full Name:</span> <%= account.getFullname() %></p>
            <p><span class="info-label">Email:</span> <%= account.getEmail() %></p>
            <p><span class="info-label">Date of Birth:</span> <%= account.getDob() %></p>
            <p><span class="info-label">Phone:</span> <%= account.getPhone() %></p>
            <%
                } else {
            %>
            <p class="no-data">Account information not found.</p>
            <%
                }
                if (orderDetails != null) {
            %>
            <h2>Order Details</h2>
            <p><span class="info-label">Order ID:</span> <%= orderDetails.getOrderId() %></p>
            <p><span class="info-label">Subject ID:</span> <%= orderDetails.getSubjectId() %></p>
            <p><span class="info-label">Subject Name:</span> <%= orderDetails.getSubjectName() %></p>
            <p><span class="info-label">Price:</span> <%= orderDetails.getPrice() %></p>
            <p><span class="info-label">Order Date:</span> <%= orderDetails.getOrderDate() %></p>
            <p><span class="info-label">Total Money:</span> <%= orderDetails.getTotalMoney() %></p>
            <p><span class="info-label">Payment Status:</span> <%= orderDetails.getPaymentStatus() %></p>
            <%
                }
            %>
        </div>
    </body>
</html>
