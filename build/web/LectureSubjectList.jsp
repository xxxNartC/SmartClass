<%-- 
    Document   : LectureSubjectList
    Created on : 24 thg 10, 2024, 20:50:42
    Author     : bacht
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> <!-- Add this line -->
<jsp:include page="header.jsp" />
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Lecturer Subject List</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 20px;
            }
            table {
                width: 80%; /* Adjust the width of the table */
                border-collapse: collapse;
                margin: 20px auto; /* Center the table */
            }
            th, td {
                border: 1px solid #ddd;
                padding: 10px;
                text-align: left;
                vertical-align: top;
            }
            th {
                background-color: #f2f2f2;
            }
            .course-list {
                margin: 0;
                padding: 0;
                list-style-type: none;
            }
            .course-list li {
                padding-left: 10px;
                margin-bottom: 2px;
            }
            /* Style for centering the header */
            .page-header {
                text-align: center;
                margin-top: 20px;
                font-size: 24px;
                font-weight: bold;
                background-color: transparent; /* Đảm bảo nền là trong suốt */
                padding: 0; /* Bỏ padding nếu có */
                border: none; /* Bỏ border nếu có */
            }


            /* Style for the search input */
            .search-container {
                text-align: center;
                margin-bottom: 20px;
            }
            .search-input {
                width: 50%; /* Set the width of the search input */
                padding: 10px;
                border: 1px solid #ddd;
                border-radius: 5px;
                font-size: 16px;
            }
        </style>
    </head>
    <body>
        <h1 style="text-align: center;">List of Instructor's Subject</h1>

        <!-- Search Container -->
        <div class="search-container">
            <input type="text" id="searchInput" class="search-input" placeholder="Search by Lecturer Name, Subject Name, or Course Name" onkeyup="filterTable()">
        </div>

        <!-- Lecturer Table -->
        <table id="lecturerTable">
            <thead>
                <tr>
                    <th>Lecturer Name</th>
                    <th>Subject Name</th>
                    <th>Course Names</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="lecturer" items="${lecturers}">
                    <c:forEach var="subject" items="${lecturer.subjects}" varStatus="status">
                        <tr>
                            <!-- Show the lecturer name only once for each lecturer -->
                            <c:if test="${status.first}">
                                <td rowspan="${fn:length(lecturer.subjects)}">${lecturer.fullname}</td>
                            </c:if>
                            <td>${subject.subject_name}</td>
                            <td>
                                <ul class="course-list">
                                    <c:forEach var="course" items="${subject.coursesList}">
                                        <li>${course}</li>
                                        </c:forEach>
                                </ul>
                            </td>
                        </tr>
                    </c:forEach>
                </c:forEach>
            </tbody>
        </table>

        <script>
            function filterTable() {
                const input = document.getElementById("searchInput");
                const filter = input.value.toLowerCase();
                const table = document.getElementById("lecturerTable");
                const tr = table.getElementsByTagName("tr");

                // Tạo một đối tượng để theo dõi các giảng viên đã được hiển thị
                const lecturerDisplayStatus = {};

                // Đầu tiên, ẩn tất cả các hàng
                for (let i = 1; i < tr.length; i++) { // Bắt đầu từ 1 để bỏ qua hàng tiêu đề
                    tr[i].style.display = "none"; // Ẩn tất cả các hàng
                }

                // Sau đó, lặp lại để kiểm tra điều kiện tìm kiếm
                for (let i = 1; i < tr.length; i++) { // Bắt đầu từ 1 để bỏ qua hàng tiêu đề
                    const tdLecturer = tr[i].getElementsByTagName("td")[0];
                    const tdSubject = tr[i].getElementsByTagName("td")[1];
                    const tdCourses = tr[i].getElementsByTagName("td")[2];

                    const lecturerName = tdLecturer ? tdLecturer.textContent.toLowerCase() : "";
                    const subjectName = tdSubject ? tdSubject.textContent.toLowerCase() : "";
                    const courseNames = tdCourses ? Array.from(tdCourses.getElementsByTagName("li")).map(li => li.textContent.toLowerCase()).join(" ") : "";

                    // Kiểm tra xem tên giảng viên, tên môn học hoặc tên khóa học có chứa chuỗi tìm kiếm không
                    const isMatch = lecturerName.includes(filter) || subjectName.includes(filter) || courseNames.includes(filter);

                    // Nếu có ít nhất một hàng của giảng viên khớp với tiêu chí tìm kiếm
                    if (isMatch) {
                        lecturerDisplayStatus[lecturerName] = true; // Đánh dấu giảng viên này là đã hiển thị
                    }
                }

                // Lặp lại lần nữa để hiển thị tất cả các hàng của giảng viên đã được đánh dấu
                for (let i = 1; i < tr.length; i++) { // Bắt đầu từ 1 để bỏ qua hàng tiêu đề
                    const tdLecturer = tr[i].getElementsByTagName("td")[0];
                    const lecturerName = tdLecturer ? tdLecturer.textContent.toLowerCase() : "";

                    // Nếu giảng viên đã được đánh dấu là cần hiển thị
                    if (lecturerDisplayStatus[lecturerName]) {
                        tr[i].style.display = ""; // Hiển thị hàng
                    }
                }
            }
        </script>

    </body>
</html>