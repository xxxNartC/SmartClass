<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Request Role Change</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f9;
                margin: 0;
                padding: 0;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }
            .container {
                background: #fff;
                padding: 20px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                border-radius: 8px;
                width: 400px;
            }
            h1 {
                text-align: center;
                color: #333;
                margin-bottom: 20px;
            }
            label {
                display: block;
                margin-bottom: 8px;
                color: #333;
            }
            select, textarea, input[type="text"], input[type="email"], input[type="date"], input[type="tel"] {
                width: 100%;
                padding: 10px;
                margin-bottom: 20px;
                border: 1px solid #ddd;
                border-radius: 4px;
                font-size: 16px;
            }
            textarea {
                resize: vertical;
            }
            .btn {
                width: 100%;
                padding: 10px;
                border: none;
                border-radius: 4px;
                background-color: #337ab7;
                color: white;
                font-size: 16px;
                cursor: pointer;
            }
            .btn:hover {
                background-color: #21618c;
            }
            .btn2 {
                width: 100%;
                padding: 10px;
                border: none;
                border-radius: 4px;
                background-color: #f44336;
                color: white;
                font-size: 16px;
                cursor: pointer;
            }
            .btn2:hover {
                background-color: #e53935;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Become a part of us</h1>
            <form action="submitRoleChangeRequest" method="post">
                <label for="role">Select new role:</label>
                <select name="role" id="role" required>
                    <option value="3">Lecturer</option>
                    <option value="4">Marketer</option>
                </select>
                <label for="content">Content:</label>
                <textarea name="content" id="content" rows="4" maxlength="180" required></textarea>
                <label style="color: #4CAF50"for="notify"> ${Message} </label>
                <button type="submit" class="btn">Submit Request</button>
            </form>
            <a href="index.jsp"><button type="button" class="btn2">Cancel</button></a>
        </div>
    </body>
</html>
