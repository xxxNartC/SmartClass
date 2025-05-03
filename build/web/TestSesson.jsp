<%-- 
    Document   : TestSesson
    Created on : Oct 13, 2024, 2:56:57 PM
    Author     : TRUONG GIANG
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Info</title>
</head>
<body>

<%
    // Kiểm tra thông tin từ session
    if (session != null) {
        Integer role_id = (Integer) session.getAttribute("role_id");
        Integer account_id = (Integer) session.getAttribute("account_id");

        if (role_id != null && account_id != null) {
%>
            <h1>Welcome, User</h1>
            <p>Role ID: <%= role_id %></p>
            <p>Account ID: <%= account_id %></p>
<%
        } else {
%>
            <p>No role or account information available in the session.</p>
<%
        }
    } else {
%>
        <p>No active session found.</p>
<%
    }
%>

</body>
</html>

