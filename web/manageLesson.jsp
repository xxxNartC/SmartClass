<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Lesson</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/themify-icons/0.1.2/css/themify-icons.css" />
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">

        <style>
            .search-container {
                display: flex;
                justify-content: center;
                gap: 10px;
                margin-bottom: 20px;
            }

            .search-container input {
                flex-grow: 1;
                max-width: 300px;
            }

            .search-container button {
                white-space: nowrap;
            }

            /* Center table headers and add space */
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

            /* Center the main heading */
            h1 {
                text-align: center;
                margin-top: 30px;
                margin-bottom: 30px;
            }

            /* Add spacing between the rows in the table */
            tr {
                margin-bottom: 20px;
            }
        </style>

    </head>
    <jsp:include page="header.jsp" />
    <body>
        <c:if test="${sessionScope.user.role_id == '3'}">
        </div>

    </nav>

    <h1>Lesson Manage</h1>
    <!-- Courses Start -->
    <div class="inner-wrap">
        <div class="row">
            <div class="col-12">
                <div class="card shadow-sm">
                    <div class="card-body">
                        <form action="manageLesson" method="get">
                            <input type="hidden" name="course_id" value="${course_id}">
                            <input type="hidden" name="subject_id" value="${subject_id}">
                            <input type="hidden" name="chapter_id" value="${chapter_id}">
                            <div class="search-container">
                                <input type="text" class="form-control" placeholder="Search here" name="searchLessonName" value="${searchLessonName}">
                                <button class="btn btn-primary" type="submit">Search</button>
                            </div>
                        </form>
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th scope="col">Lesson No</th>
                                    <th scope="col">Lesson Name</th>
                                    <th scope="col">Video</th>
                                    <th scope="col">Description</th>
                                    <th scope="col">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${lessons}" var="lesson">
                                    <tr>
                                        <td>${lesson.getLesson_no()}</td>
                                        <td>${lesson.getLesson_name()}</td>
                                        <td>
                                            <iframe width="80%" height="300" src="${lesson.video}" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>
                                        </td>
                                        <td>${lesson.getDescription()}</td>
                                        <td>
                                            <a href="editLesson?lesson_id=${lesson.getLesson_id()}&course_id=${course.getCourse_id()}&subject_id=${subject_id}&chapter_id=${chapter.getChapter_id()}" class="btn btn-primary">Edit</a>
                                            <a href="#" onclick="confirmDelete('${lesson.getLesson_id()}', '${course.getCourse_id()}', '${subject_id}', '${chapter.getChapter_id()}')" class="btn btn-danger">Delete</a>                                    
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <div class="d-flex justify-content-center my-3">
                            <a href="addLesson?course_id=${course.getCourse_id()}&subject_id=${subject_id}&chapter_id=${chapter.getChapter_id()}" class="btn btn-primary mx-2">Add Lesson</a>
                            <a href="manageChapter?course_id=${course.getCourse_id()}&subject_id=${subject_id}" class="btn btn-primary mx-2">Back to Manage Chapter</a>
                            <a href="addquiz?chapterid=${chapter.getChapter_id()}&subjectid=${subject_id}&courseid=${course.getCourse_id()}" class="btn btn-primary mx-2">Add Quiz</a>
                        </div>
                        <nav aria-label="Page navigation example">                
                            <ul class="pagination justify-content-center">                
                                <c:forEach begin="1" end="${numPages}" var="i">                  
                                    <li class="page-item <c:if test="${i == currentPage}">active</c:if>">             
                                        <a class="page-link" href="manageLesson?course_id=${course_id}&subject_id=${subject_id}&chapter_id=${chapter_id}&page=${i}&searchLessonName=${searchLessonName}">${i}</a>                 
                                    </li>                  
                                </c:forEach>              
                            </ul>           
                        </nav>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script>
        function confirmDelete(lesson_id, course_id, subject_id, chapter_id) {
            if (confirm("Are you sure you want to delete this lesson?")) {
                window.location.href = "manageLesson?action=delete&lesson_id=" + lesson_id + "&course_id=" + course_id + "&subject_id=" + subject_id + "&chapter_id=" + chapter_id;
            }
        }
    </script>
</c:if>
<c:if test="${sessionScope.user.role_id != '3'}">    
    <div class="container">    
        <h1>You are not authorized to access this page.</h1>       
        <a href="index.html"><input class="btn" type="submit" value="Back to Home"/></a>  
    </div>  
</c:if>
</body>
</html>
