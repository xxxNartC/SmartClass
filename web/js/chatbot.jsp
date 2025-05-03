<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Queue" %>
<%@ page import="java.util.LinkedList" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chatbot Interface</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            padding: 20px;
        }
        .chat-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            max-width: 600px;
            margin: auto;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .chat-box {
            border: 1px solid #ddd;
            padding: 10px;
            border-radius: 5px;
            height: 300px;
            overflow-y: auto;
            margin-bottom: 10px;
        }
        .message {
            margin: 5px 0;
        }
        .user {
            color: blue;
        }
        .bot {
            color: green;
        }
        input[type="text"] {
            width: calc(100% - 80px);
            padding: 10px;
            margin-right: 5px;
        }
        button {
            padding: 10px;
        }
    </style>
</head>
<body>
    <div class="chat-container">
        <h2>Chat with GeminiBot</h2>
        <div class="chat-box">
            <% 
                Queue<String> history = (Queue<String>) request.getAttribute("conversationHistory");
                if (history != null) {
                    for (String message : history) {
                        if (message.startsWith("User:")) {
            %>
                            <div class="message user"><%= message %></div>
            <%
                        } else {
            %>
                            <div class="message bot"><%= message %></div>
            <%
                        }
                    }
                }
            %>
        </div>
        <form method="post" action="GeminiChatbot">
            <input type="text" name="question" placeholder="Ask something..." required>
            <button type="submit">Send</button>
        </form>

        <%-- Hiển thị lỗi nếu có --%>
        <%
            String error = (String) request.getAttribute("error");
            if (error != null) {
        %>
            <div style="color: red;"><%= error %></div>
        <%
            }
        %>
    </div>
</body>
</html>

