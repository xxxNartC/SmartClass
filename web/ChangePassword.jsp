<%-- 
    Document   : ChangePassword

--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.User"%>
<%
    User user = (User) session.getAttribute("user");
    
    if (user == null) {
        response.sendRedirect("login.jsp");
    }

    // Kiểm tra nếu có lỗi trong quá trình đổi mật khẩu
    String errorMessage = request.getParameter("error");
    String successMessage = request.getParameter("success");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Change Password</title>
        <style>
            .container {
                width: 300px;
                margin: 100px auto;
                padding: 20px;
                border: 1px solid #000;
                text-align: center;
                font-family: Arial, sans-serif;
            }
            h3 {
                font-size: 24px;
                margin-bottom: 20px;
            }
            input[type="password"] {
                width: 90%;
                padding: 5px;
                margin-bottom: 15px;
                border: 1px solid #ccc;
                font-size: 14px;
                text-align: left;
            }
            button[type="submit"] {
                padding: 5px 15px;
                background-color: #4CAF50;
                color: white;
                border: none;
                cursor: pointer;
                font-size: 14px;
                margin-top: 10px;
            }
            button[type="submit"]:hover {
                background-color: #45a049;
            }
            .error-message {
                color: red;
                font-size: 12px;
                margin-top: 10px;
            }
            .success-message {
                color: green;
                font-size: 12px;
                margin-top: 10px;
            }
            table {
                margin: 0 auto;
                text-align: left;
            }
            td {
                padding: 5px 0;
                text-align: right;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h3>Change Password</h3>
            <% if (errorMessage != null) { %>
                <p class="error-message"><%= errorMessage %></p>
            <% } %>
            <% if (successMessage != null) { %>
                <p class="success-message"><%= successMessage %></p>
            <% } %>
            <form action="<%= request.getContextPath() %>/changePassword" method="post">
                <table>
                    <tr>
                        <td>Enter password:</td>
                        <td><input type="password" name="currentPassword" required /></td>
                    </tr>
                    <tr>
                        <td>Enter new Password:</td>
                        <td><input type="password" name="newPassword" required /></td>
                    </tr>
                    <tr>
                        <td>Re-enter:</td>
                        <td><input type="password" name="confirmPassword" required /></td>
                    </tr>
                </table>
                <button type="submit">Submit</button>
            </form>
        </div>
    </body>
</html>