<%-- 
    Document   : marketer
    Created on : Sep 26, 2024, 9:54:24 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.User"%>
<!DOCTYPE html>
<html>
    <head>
        <style>
            body {
                font-family: sans-serif;
            }
            h1 {
                color: #337ab7;
                text-align: center;
            }
            p {
                text-align: center;
                margin: 10px 0;
            }
            a {
                color: #337ab7;
                text-decoration: none;
            }
            a:hover {
                text-decoration: underline;
            }
            .container {
                width: 300px;
                margin: 0 auto;
                padding: 20px;
                border: 1px solid #ccc;
                border-radius: 5px;
                text-align: center;
            }
            p.error {
                color: red;
            }
        </style>
    </head>
    <body>
        <h1>Welcome</h1>
        <%
            User user = (User) session.getAttribute("user");
            if (user != null) {
                out.println("<p>Welcome, " + user.getFullname() + "!</p>");
                out.println("<p>You are logged in as a Marketer.</p>");
            } else {
                response.sendRedirect("login.jsp");
            }
        %>
    </body>
</html>
