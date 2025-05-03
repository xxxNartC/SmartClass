<%-- 
    Document   : ReportAccount
    Created on : 5 thg 11, 2024, 11:10:10
    Author     : bacht
--%>
<%-- 
    Document   : ReportAccount
    Created on : 5 thg 11, 2024, 11:10:10
    Author     : bacht
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <style>
            body {
                font-family: Arial, sans-serif;
            }
            .container {
                width: 80%;
                margin: 0 auto;
                padding: 20px;
            }
            h1 {
                text-align: center;
                margin-bottom: 10px;
            }
            .error-message {
                color: red;
                text-align: center;
                margin-bottom: 20px;
                font-weight: bold;
            }
            .form-section, .reports-section {
                margin-bottom: 30px;
            }
            .form-section label, .form-section select, .form-section textarea, .form-section input[type="submit"] {
                display: block;
                width: 100%;
                margin-bottom: 10px;
                font-size: 16px;
            }
            .form-section select, .form-section textarea, .form-section input[type="submit"] {
                padding: 8px;
                border: 1px solid #ccc;
                border-radius: 5px;
            }
            .form-section input[type="submit"] {
                background-color: #007bff;
                color: white;
                cursor: pointer;
            }
            .form-section input[type="submit"]:hover {
                background-color: #0056b3;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }
            table, th, td {
                border: 1px solid #ddd;
                padding: 8px;
                text-align: center;
            }
            th {
                background-color: #f2f2f2;
            }
            .actions a {
                margin: 0 5px;
                color: #007bff;
                text-decoration: none;
            }
            .actions a:hover {
                text-decoration: underline;
            }
            /* Status Colors */
            .status-pending {
                color: orange;
                font-weight: bold;
            }
            .status-reject {
                color: red;
                font-weight: bold;
            }
            .status-approve {
                color: green;
                font-weight: bold;
            }
        </style>
    </head>
    <body>

        <!-- Include header -->
        <jsp:include page="header.jsp" />
        <div class="container">
            <h1>Report Account</h1>

            <!-- Error Message Display Below Header -->
            <c:if test="${not empty errorMessage}">
                <div class="error-message">${errorMessage}</div>
            </c:if>

            <!-- Form Section -->
            <div class="form-section">
                <h2>${editMode ? 'Edit Report' : 'Submit a New Report'}</h2>
                <form action="report" method="post">
                    <!-- Hidden field to hold report ID during edit mode -->
                    <c:if test="${editMode}">
                        <input type="hidden" name="reportId" value="${reportId}" />
                    </c:if>

                    <label for="accountToReport">Account to Report:</label>
                    <input type="text" id="accountToReport" name="accountToReport" required value="${accountToReport}" />

                    <label for="reason">Reason for Report:</label>
                    <select id="reason" name="reason">
                        <option value="Spam" ${reason == 'Spam' ? 'selected' : ''}>Spam</option>
                        <option value="Fraud" ${reason == 'Fraud' ? 'selected' : ''}>Fraud</option>
                        <option value="Other" ${reason == 'Other' ? 'selected' : ''}>Other</option>
                    </select>

                    <label for="details">Details:</label>
                    <textarea id="details" name="details" rows="4" placeholder="Provide additional details..." required>${details}</textarea>

                    <!-- Show Save button in edit mode and Submit in new mode -->
                    <c:choose>
                        <c:when test="${editMode}">
                            <input type="submit" value="Save" />
                            <a href="report" class="button">Cancel</a> <!-- Link to cancel and reload the page -->
                        </c:when>
                        <c:otherwise>
                            <input type="submit" value="Submit Report" />
                        </c:otherwise>
                    </c:choose>
                </form>
            </div>

            <!-- Your Reports Section -->
            <div class="reports-section">
                <h2>Your Reports</h2>
                <table>
                    <thead>
                        <tr>
                            <th>Account</th>
                            <th>Reason</th>
                            <th>Status</th>
                            <th>Date Submitted</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="report" items="${reports}">
                            <tr>
                                <td>${report.reportedAccountName}</td>
                                <td>${report.reportType}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${report.status == 0}">
                                            <span class="status-pending">Pending</span>
                                        </c:when>
                                        <c:when test="${report.status == 1}">
                                            <span class="status-reject">Rejected</span>
                                        </c:when>
                                        <c:when test="${report.status == 2}">
                                            <span class="status-approve">Approved</span>
                                        </c:when>
                                    </c:choose>
                                </td>
                                <td>${report.reportDate}</td>
                                <td class="actions">
                                    <a href="report?action=edit&id=${report.reportId}">Edit</a> 
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

        </div>
    </body>
</html>

