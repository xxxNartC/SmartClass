<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp" />
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Course Manage</title>
        <style>
            table {
                width: 80%; /* Set a fixed width for the table */
                margin: 0 auto; /* Center the table */
                border-collapse: collapse;
            }
            table, th, td {
                border: 1px solid black;
            }
            th, td {
                padding: 10px;
                text-align: left;
                word-wrap: break-word; /* Ensure long text wraps to the next line */
            }
            th {
                background-color: #ADD8E6; /* Light blue background for headers */
            }
            td {
                max-width: 300px; /* Limit the width of each cell */
            }
            .add-subject-btn, .add-home-btn {
                display: inline-block;
                margin-top: -18px;
                background-color: #ADD8E6;
                color: black;
                padding: 10px 20px;
                text-decoration: none;
                border-radius: 5px;
                font-size: 16px;
            }
            .add-subject-btn {
                float: right;
                margin-right: 1%;
            }
            .add-home-btn {
                float: left;
                margin-right: 1%;
            }
        </style>
        <link href="img/favicon.ico" rel="icon">
        <script src="https://code.jquery.com/jquery-3.7.1.min.js"
                integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
        crossorigin="anonymous"></script>
        <link href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/bootstrap.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css">
        <link href="https://fonts.googleapis.com/css2?family=Handlee&family=Nunito&display=swap" rel="stylesheet">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    </head>

    <body>

        <h1 style="text-align: center; margin-top: 16px">Course List Management</h1>

        <!-- Add Subject button -->
        <a href="subjectmanage" class="add-subject-btn" style="margin-bottom: 16px">Back</a>
        <a href="create-course?subjecId=${subject_id}" class="add-subject-btn" style="margin-bottom: 16px">Add Course</a>

        <!--Table hiển thị dữ liệu-->
        <div class="error" name="err">${err}</div>
        <div class="message" name="mess">${mess}</div>

        <table id="tablesubject" class="display" style="width:100%">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Course Name</th>
                    <th>Course No</th>
                    <th>Description</th>
                    <th>Image</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:set var="counter" value="1" />
                <c:forEach items="${data_course}" var="course">
                    <tr>
                        <td class="long-content">${counter}</td>
                        <td class="long-content">${course.getCourse_name()}</td>
                        <td class="long-content">${course.getCourse_no()}</td>
                        <td class="long-content">${course.getDescription()}</td>
                        <td><img src="img/${course.image}" alt="${course.getCourse_name()}" style="width: 50px"/></td>
                        <td>${course.status == true ? 'Active' : 'InActive'}</td>
                        <td>
                            <a href="manageChapter?course_id=${course.getCourse_id()}&subject_id=${subject_id}" class="chapter mr-2" title="Management Chapter">
                                <i class="fa-solid fa-eye material-icons"></i> View Chapter
                            </a>
                            <a href="addAssignment?courseid=${course.getCourse_id()}&subjectid=${subject_id}" class="blue-button">
                                <i class="material-icons">&#xE145;</i> Add Assignment
                            </a>
                            <a href="update-course?course_id=${course.getCourse_id()}" class="edit mr-2 update" title="Edit">
                                <i class="material-icons">&#xE254;</i> Edit
                            </a>
                            <c:if test="${course.status == true}">
                                <a href="delete-course?subject_id=${subject_id}&course_id=${course.getCourse_id()}" class="delete mr-2 delete" title="Delete">
                                    <i class="material-icons">&#xE872;</i> Delete
                                </a>
                            </c:if>
                        </td>
                    </tr>
                    <c:set var="counter" value="${counter + 1}" />
                </c:forEach>
            </tbody>
        </table>


        <c:if test="${not empty message}">
            <script>
                alert('${message}');
            </script>
        </c:if>
        <script type="text/javascript">
            // Kiểm tra thông báo thành công
            <c:if test="${not empty sessionScope.successMessage}">
                        alert("${sessionScope.successMessage}");
                <c:remove var="successMessage" scope="session" />
            </c:if>

            // Kiểm tra thông báo lỗi
            <c:if test="${not empty sessionScope.errorMessage}">
                        alert("${sessionScope.errorMessage}");
                <c:remove var="errorMessage" scope="session" />
            </c:if>
        </script>

        <!-- Các thư viện Javascript -->
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
    </body>
</html>
