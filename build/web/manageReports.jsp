<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Manage Commment Reports</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f0f2f5;
            }

            h1, h2 {
                text-align: center;
                color: #333;
            }

            .message {
                text-align: center;
                margin-bottom: 20px;
                padding: 10px;
                border-radius: 5px;
            }

            .success {
                color: #155724;
                background-color: #d4edda;
                border: 1px solid #c3e6cb;
            }

            .error {
                color: #721c24;
                background-color: #f8d7da;
                border: 1px solid #f5c6cb;
            }

            table {
                width: 80%;
                margin: 0 auto 20px;
                border-collapse: collapse;
                box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
            }

            table, th, td {
                border: 1px solid #ddd;
            }

            th, td {
                padding: 10px;
                text-align: center;
            }

            th {
                background-color: #4CAF50;
                color: white;
            }

            tr:nth-child(even) {
                background-color: #f2f2f2;
            }

            tr:hover {
                background-color: #ddd;
            }

            form {
                margin: 0;
            }

            input[type="submit"] {
                padding: 5px 10px;
                border: none;
                background-color: #4CAF50;
                color: white;
                cursor: pointer;
                border-radius: 3px;
            }

            input[type="submit"]:hover {
                background-color: #45a049;
            }

            .pagination {
                text-align: center;
                margin: 20px 0;
            }

            .pagination button {
                padding: 5px 10px;
                border: 1px solid #ddd;
                background-color: #f0f2f5;
                cursor: pointer;
                margin: 0 2px;
            }

            .pagination button.active {
                background-color: #4CAF50;
                color: white;
            }

            .pagination button:hover {
                background-color: #ddd;
            }

            .search-box {
                text-align: center;
                margin: 10px 0;
            }

            .search-box input {
                padding: 10px;
                width: 200px;
                border: 1px solid #ddd;
                border-radius: 3px;
            }

            .filter-box {
                text-align: center;
                margin: 20px 0;
            }

            .filter-box select {
                padding: 10px;
                border: 1px solid #ddd;
                border-radius: 3px;
            }
            .button {
                display: inline-block;
                padding: 10px 20px;
                font-size: 14px;
                color: #fff;
                background-color: #007bff;
                border: none;
                border-radius: 4px;
                text-align: center;
                text-decoration: none;
                margin: 0px 56rem;
                margin-left: 0rem;
            }
            .button:hover {
                background-color: #0056b3;
            }
        </style>
    </head>
    <body>
        <h1>Manage Comment Reports</h1>
        <!--<h3>Pending Reports</h3>-->
        <div class="message">
            <c:if test="${not empty manageSuccess}">
                <span class="success">${manageSuccess}</span>
            </c:if>
            <c:if test="${not empty manageError}">
                <span class="error">${manageError}</span>
            </c:if>
        </div>

        <div id="pendingReportsSection">
            <h2>Pending Reports</h2>
                <div class="search-box">
                    <button onclick="window.location.href = 'index.jsp'" class="btn-back">Back Home</button>
                    
                </div>
            <table id="pendingReportsTable">
                <thead>
                    <tr>
                        <th>Report ID</th>
                        <th>Reporter Username</th>
                        <th>Reported Username</th>
                        <th>Report Type</th>
                        <th>Description</th>
                        <th>Date</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="report" items="${reports}">
                        <tr>
                            <td>${report.reportId}</td>
                            <td>${report.reporterUsername}</td>
                            <td>${report.reportedUsername}</td>
                            <td>${report.reportType}</td>
                            <td>${report.reportDescription}</td>
                            <td>${report.reportDate}</td>
                            <td>${report.status == 0 ? "Pending" : "Reviewed"}</td>
                            <td>
                                <form action="manageReports" method="post">
                                    <input type="hidden" name="account_id" value="${report.reportedAccountId}">
                                    <input type="hidden" name="report_id" value="${report.reportId}">
                                   
                                    <input type="submit" name="action" value="delete">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

    </body>
</html>
