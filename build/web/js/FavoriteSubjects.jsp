<%-- 
    Document   : FavoriteSubjects
    Created on : Oct 27, 2024, 11:33:21 PM
    Author     : TRUONG GIANG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Favorite Subjects</title>
    <style>
        /* CSS styling */
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 80%;
            margin: auto;
            padding: 20px;
            background-color: white;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: center;
        }
        th {
            background-color: #007BFF;
            color: white;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        .filter-button, #categorySelect, #sortBySelect {
            margin: 10px 0;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }
        .subject-image {
            width: 100px;
            height: 100px;
            object-fit: cover;
        }
        .subject-link {
            text-decoration: none;
            color: #007BFF;
        }
        .subject-link:hover {
            text-decoration: underline;
        }
    </style>
    <script>
        // JavaScript cho bộ lọc văn bản
        function filterTable() {
            const filter = document.getElementById("filterInput").value.toUpperCase();
            const table = document.getElementById("subjectsTable");
            const rows = table.getElementsByTagName("tr");

            for (let i = 1; i < rows.length; i++) {
                const cells = rows[i].getElementsByTagName("td");
                let found = false;

                for (let j = 0; j < cells.length; j++) {
                    if (cells[j].textContent.toUpperCase().indexOf(filter) > -1) {
                        found = true;
                        break;
                    }
                }
                rows[i].style.display = found ? "" : "none";
            }
        }

        // JavaScript cho bộ lọc danh mục
        function filterByCategory() {
            const selectedCategory = document.getElementById("categorySelect").value;
            const table = document.getElementById("subjectsTable");
            const rows = table.getElementsByTagName("tr");

            for (let i = 1; i < rows.length; i++) {
                const categoryCell = rows[i].getElementsByTagName("td")[4]; // Assuming category is in the 5th column (index 4)
                if (selectedCategory === "All" || categoryCell.textContent === selectedCategory) {
                    rows[i].style.display = "";
                } else {
                    rows[i].style.display = "none";
                }
            }
        }

        // JavaScript cho sắp xếp theo ngày thêm
        function sortByAddedDate() {
            const sortBy = document.getElementById("sortBySelect").value;
            const searchTerm = document.getElementById("filterInput").value;
            const categoryName = document.getElementById("categorySelect").value;
            window.location.href = "FavoriteSubject?searchTerm=" + searchTerm + "&sortBy=" + sortBy + "&categoryName=" + categoryName;
        }
        
        // JavaScript cho sắp xếp theo số lượng yêu thích
        function sortByFavoritesCount() {
            const sortBy = document.getElementById("sortByFavoritesCountSelect").value;
            const searchTerm = document.getElementById("filterInput").value;
            const categoryName = document.getElementById("categorySelect").value;
            window.location.href = "FavoriteSubject?searchTerm=" + searchTerm + "&sortBy=" + sortBy + "&categoryName=" + categoryName;
        }
    </script>
</head>
<body>
    <%@include file="header.jsp" %>
<div class="container">
    <c:if test="${not empty sessionScope.successMessage}">
        <div class="alert alert-success" role="alert">
            ${sessionScope.successMessage}
            <c:remove var="successMessage" scope="session" />
        </div>
    </c:if>
    <h1>Favorite Subjects</h1>
    
    <!-- Bộ lọc danh mục -->
    <label for="categorySelect">Filter by Category:</label>
    <select id="categorySelect" onchange="filterByCategory()" class="filter-button">
        <option value="All">All</option>
        <option value="Information Technology">Information Technology</option>
        <option value="Economic">Economic</option>
        <option value="Languages">Languages</option>
    </select>

    <!-- Bộ lọc bằng văn bản -->
    <form action="FavoriteSubject" method="get">
        <input type="text" id="filterInput" name="searchTerm" onkeyup="filterTable()" placeholder="Search by name..." class="filter-button">
        <button type="submit" class="filter-button">Search</button>
    </form>

    <!-- Sắp xếp theo ngày thêm -->
    <label for="sortBySelect">Sort by Added Date:</label>
    <select id="sortBySelect" onchange="sortByAddedDate()" class="filter-button">
        <option value="">None</option>
        <option value="addedDateDESC">Newest First</option>
        <option value="addedDateASC">Oldest First</option>
    </select>
    
    <!-- Sắp xếp theo số lượng yêu thích -->
    <label for="sortByFavoritesCountSelect">Sort by Favorites Count:</label>
    <select id="sortByFavoritesCountSelect" onchange="sortByFavoritesCount()" class="filter-button">
        <option value="">None</option>
        <option value="favoritesCountDESC">Most Popular First</option>
        <option value="favoritesCountASC">Least Popular First</option>
    </select>

    <table id="subjectsTable">
        <thead>
            <tr>
                <th>Subject ID</th>
                <th>Subject Name</th>
                <th>Description</th>
                <th>Image</th>
                <th>Category Name</th>
                <th>Favorites Count</th>
                <th>Added Date</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="subject" items="${favoriteSubjects}">
                <tr>
                    <td>${subject.subjectId}</td>
                    <td><a href="course?subject_id=${subject.subjectId}" class="subject-link">${subject.subjectName}</a></td>
                    <td>${subject.description}</td>
                    <td><img src="${subject.image}" alt="${subject.subjectName}" class="subject-image"></td>
                    <td>${subject.categoryName}</td>
                    <td>${subject.favoritesCount}</td>
                    <td>${subject.addedDate}</td>
                    <td>
                        <form action="FavoriteSubject" method="post">
                            <input type="hidden" name="favoriteId" value="${subject.favoriteId}">
                            <button type="submit" class="btn btn-danger">Delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
