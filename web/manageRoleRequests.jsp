<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Manage Role Change Requests</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f9;
                margin: 0;
                padding: 0;
            }
            .container {
                width: 80%;
                margin: 20px auto;
                background: #fff;
                padding: 20px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            h2 {
                text-align: center;
                color: #333;
            }
            .header-bar {
                display: flex;
                align-items: center;
                justify-content: space-between;
                margin-bottom: 20px;
            }
            .search-bar {
                display: flex;
                justify-content: center;
                flex: 1;
            }
            .search-bar input {
                padding: 10px;
                border: 1px solid #ddd;
                border-radius: 4px;
                width: 300px;
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
            table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 20px;
            }
            table, th, td {
                border: 1px solid #ddd;
            }
            th, td {
                padding: 12px;
                text-align: left;
            }
            th {
                background-color: #f4f4f4;
                color: #333;
            }
            tr:nth-child(even) {
                background-color: #f9f9f9;
            }
            tr:hover {
                background-color: #f1f1f1;
            }
            .action-buttons {
                display: flex;
                gap: 10px;
            }
            .btn-approve {
                background-color: #2196F3;
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }
            .btn-approve:hover {
                background-color: #1E88E5;
            }
            .btn-reject {
                background-color: #f44336;
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }
            .btn-reject:hover {
                background-color: #e53935;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Manage Role Change Requests</h2>
            <div class="header-bar">
                <div class="search-bar">
                    <form action="manageRoleRequests" method="get">
                        <input type="text" name="search" placeholder="Search by username, fullname, or email">
                        <button type="submit">Search</button>
                    </form>
                </div>
            </div>
            <table id="roleRequestsTable">
                <thead>
                    <tr>
                        <th>Username</th>
                        <th>Fullname</th>
                        <th>Email</th>
                        <th>DOB</th>
                        <th>Phone</th>
                        <th>Current Role</th>
                        <th>Requested Role</th>
                        <th>Content</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody id="roleRequestsTbody">
                    <c:forEach var="request" items="${roleChangeRequests}">
                        <tr>
                            <td>${request.username}</td>
                            <td>${request.fullname}</td>
                            <td>${request.email}</td>
                            <td>${request.dob}</td>
                            <td>${request.phone}</td>
                            <td>${request.current_role_name}</td>
                            <td>${request.requested_role_name}</td>
                            <td>${request.content}</td>
                            <td>
                                <div class="action-buttons">
                                    <form action="approveRoleChange" method="post">
                                        <input type="hidden" name="account_id" value="${request.account_id}" />
                                        <input type="hidden" name="new_role_id" value="${request.requested_role_id}" />
                                        <button type="submit" class="btn btn-approve">Approve</button>
                                    </form>
                                    <form action="rejectRoleChange" method="post">
                                        <input type="hidden" name="account_id" value="${request.account_id}" />
                                        <button type="submit" class="btn btn-reject">Reject</button>
                                    </form>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <button onclick="window.location.href = 'index.jsp'" class="btn-back">Back
 Home</button>
<a href="ManageReportController" class="button">Manage Report</a> <!-- New button to manage reports -->
        </div>
    </body>
</html>

