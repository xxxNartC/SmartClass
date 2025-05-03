<%-- 
    Document   : ForgetPassword5
    Created on : 27 thg 9, 2024, 19:10:19
    Author     : bacht
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Password Reset Notification</title>
        <style>
            .container {
                width: 300px;
                margin: 100px auto;
                border: 1px solid #000;
                padding: 20px;
                text-align: center;
                font-family: Arial, sans-serif;
            }

            h2 {
                font-size: 24px;
                margin-bottom: 10px;
                color: #4CAF50;
            }

            p {
                font-size: 14px;
                margin-bottom: 20px;
                color: #333;
            }

            /* Thiết kế nút */
            .button {
                padding: 10px 20px;
                background-color: #4CAF50;
                color: white;
                border: none;
                cursor: pointer;
                font-size: 14px;
                text-decoration: none;
                display: inline-block;
                border-radius: 5px;
            }

            .button:hover {
                background-color: #45a049;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Password Reset Successful</h2>
            <p>Your new password has been sent to your email. Please check your inbox and use the new password to log in.</p>
            <a href="<%= request.getContextPath() %>/login.jsp" class="button">Go to Login Page</a>
        </div>
    </body>
</html>
