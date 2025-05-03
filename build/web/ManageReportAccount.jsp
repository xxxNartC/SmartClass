<%-- 
    Document   : ManageReportAccount
    Created on : 5 thg 11, 2024, 11:10:24
    Author     : bacht
--%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Manage Account Reports</title>
        <style>
            .btn-back {
                background-color: #337ab7;
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 4px;
                font-size: 16px;
                cursor: pointer;
                text-align: center;
            }
            .btn-back:hover {
                background-color: #21618c;
            }
            .search-bar {
                display: flex;
                justify-content: center;
                margin-bottom: 20px;
            }
            .search-bar input {
                padding: 10px;
                border: 1px solid #ddd;
                border-radius: 4px;
                width: 300px;
                margin-right: 10px;
            }
            .search-bar button {
                padding: 10px 20px;
                border: none;
                border-radius: 4px;
                background-color: #337ab7;
                color: white;
                cursor: pointer;
            }
            .search-bar button:hover {
                background-color: #21618c;
            }
            .btn-restore {
                background-color: #007bff;
            }
            .btn-approve {
                background-color: #4CAF50;
            }
            .btn-reject {
                background-color: #f44336;
            }
            body {
                font-family: Arial, sans-serif;
            }
            .container {
                width: 90%;
                margin: 0 auto;
            }
            h1 {
                text-align: center;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }
            th, td {
                padding: 10px;
                border: 1px solid #ccc;
                text-align: center;
            }
            .action-buttons {
                display: flex;
                gap: 10px;
                justify-content: center;
            }
        </style>
        <script>
            function filterReports() {
                const input = document.getElementById('searchInput').value.toLowerCase();
                const rows = document.querySelectorAll('tbody tr');

                rows.forEach(row => {
                    const accountName = row.querySelector('td:nth-child(2)').innerText.toLowerCase();
                    const reportedByName = row.querySelector('td:nth-child(3)').innerText.toLowerCase();
                    
                    if (accountName.includes(input) || reportedByName.includes(input)) {
                        row.style.display = '';
                    } else {
                        row.style.display = 'none';
                    }
                });
            }
        </script>
    </head>
    <body>
        <div class="container">
            <h1>Manage Account Reports</h1>
            <button onclick="window.location.href = 'index.jsp'" class="btn-back">Back Home</button>

            <!-- Search bar for Account and Reported By filtering -->
            <div class="search-bar">
                <input type="text" id="searchInput" onkeyup="filterReports()" placeholder="Search by Account or Reported By">
            </div>

            <table>
                <thead>
                    <tr>
                        <th>STT</th>
                        <th>Account</th>
                        <th>Reported By</th>
                        <th>Reason</th>
                        <th>Status</th>
                        <th>Date</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="report" items="${reportDisplayList}">
                        <tr>
                            <td>${report.reportId}</td>
                            <td>${report.reportedAccountName}</td>
                            <td>${report.reportByName}</td>
                            <td>${report.reportDescription}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${report.status == 0}">Pending</c:when>
                                    <c:when test="${report.status == 1}">Rejected</c:when>
                                    <c:when test="${report.status == 2}">Approved</c:when>
                                    <c:otherwise>Unknown</c:otherwise>
                                </c:choose>
                            </td>
                            <td>${report.reportDate}</td>
                            <td class="action-buttons">
                                <form action="UpdateReportServlet" method="post">
                                    <input type="hidden" name="reportId" value="${report.reportId}">
                                    <c:choose>
                                        <c:when test="${report.status == 0}">
                                            <button type="submit" name="action" value="approve" class="btn btn-approve">Approve</button>
                                            <button type="submit" name="action" value="reject" class="btn btn-reject">Reject</button>
                                        </c:when>
                                        <c:when test="${report.status == 1}">
                                            <button type="submit" name="action" value="approve" class="btn btn-approve">Approve</button>
                                        </c:when>
                                        <c:when test="${report.status == 2}">
                                            <button type="submit" name="action" value="restore" class="btn btn-restore">Restore</button>
                                        </c:when>
                                    </c:choose>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>


