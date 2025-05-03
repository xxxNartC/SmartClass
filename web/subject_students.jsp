<%-- 
    Document   : subject_students
    Created on : 5 thg 11, 2024, 19:11:14
    Author     : bacht
--%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Subjects and Enrolled Students</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .container {
            width: 80%;
            margin: 0 auto;
        }
        h1 {
            text-align: center;
        }
        .search-bar {
            width: 100%;
            padding: 10px;
            margin-top: 10px;
            margin-bottom: 20px;
            font-size: 16px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: left;
        }
        th {
            background-color: #f4f4f4;
        }
    </style>
    <script>
        // JavaScript function to filter table rows based on search input
        function filterTable() {
            let input = document.getElementById("searchInput");
            let filter = input.value.toLowerCase();
            let table = document.getElementById("subjectStudentTable");
            let rows = table.getElementsByTagName("tr");

            // Loop through all table rows (except headers)
            for (let i = 1; i < rows.length; i++) {
                let subjectCell = rows[i].getElementsByTagName("td")[0];
                let studentCell = rows[i].getElementsByTagName("td")[1];
                if (subjectCell || studentCell) {
                    let subjectText = subjectCell.textContent || subjectCell.innerText;
                    let studentText = studentCell.textContent || studentCell.innerText;

                    // Show the row if either cell matches the filter
                    if (subjectText.toLowerCase().indexOf(filter) > -1 || 
                        studentText.toLowerCase().indexOf(filter) > -1) {
                        rows[i].style.display = "";
                    } else {
                        rows[i].style.display = "none";
                    }
                }       
            }
        }
    </script>
</head>
<body>
    <!-- Include header -->
    <jsp:include page="header.jsp" />
    <div class="container">
        <h1>Subjects and Enrolled Students</h1>
        
        <!-- Search bar -->
        <input type="text" id="searchInput" class="search-bar" onkeyup="filterTable()" placeholder="Search for subject or student name...">
        
        <table id="subjectStudentTable">
            <thead>
                <tr>
                    <th>Tên môn học</th>
                    <th>Tên học sinh</th>
                </tr>
            </thead>
            <tbody>
                <!-- Grouped subjects with students -->
                <c:forEach var="entry" items="${groupedSubjectStudentList}">
                    <tr>
                        <td>${entry.subjectName}</td>
                        <td>${entry.studentNames}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>

