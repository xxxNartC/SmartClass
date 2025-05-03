<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
    <jsp:include page="header.jsp" />  
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Course Assignment Management</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

        <style>
            .pagination {
                display: flex;
                justify-content: center;
                padding: 10px 0;
            }
            .pagination a {
                color: black;
                padding: 8px 16px;
                text-decoration: none;
                transition: background-color .3s;
                border: 1px solid #ddd;
                margin: 0 4px;
                border-radius: 4px;
            }
            .pagination a.active {
                background-color: #4CAF50;
                color: white;
                border: 1px solid #4CAF50;
            }
            .pagination a:hover:not(.active) {
                background-color: #ddd;
            }
            .table th, .table td {
                vertical-align: middle;
            }
            .status-pending {
                color: #FFC107; /* Bootstrap yellow */
                font-size: 0.9em;
            }
            .status-reject {
                color: #DC3545; /* Bootstrap red */
                font-size: 0.9em;
            }
            .status-passed {
                color: #28A745; /* Bootstrap green */
                font-size: 0.9em;
            }
        </style>
    </head>
    <body class="bg-light">
        <div class="container mt-5">
            <div class="text-center mb-4">
                <h1 class="display-4">Manage Course Assignments</h1>
            </div>

            <c:if test="${listSubject != null}">
                <div class="row">
                    <c:forEach items="${listSubject}" var="s">
                        <div class="col-md-4 mb-4">
                            <div class="card h-100">
                                <div class="card-body">
                                    <h5 class="card-title">${s.subject_name}</h5>
                                    <a href="listCourseAssignment?subjectid=${s.subject_id}" class="btn btn-primary">Go to Courses</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:if>

            <c:if test="${listCourse != null}">
                <div class="row">
                    <c:forEach items="${listCourse}" var="c">
                        <div class="col-md-4 mb-4">
                            <div class="card h-100">
                                <div class="card-body">
                                    <h5 class="card-title">${c.course_name}</h5>
                                    <a href="listCourseAssignment?courseid=${c.course_id}" class="btn btn-primary">Go to view submitted assignments</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:if>

            <c:if test="${listAsmSubmitted != null}">
                <form id="f" action="listCourseAssignment" method="get" class="mb-4">
                    <div class="input-group search-bar">
                        Status:
                        <select onchange="submitForm()" name="status" class="form-select">
                            <option ${status == 'all' ? 'selected' : ''} value="all">All</option>
                            <option ${status == 'pending' ? 'selected' : ''} value="pending">Pending</option>
                            <option ${status == 'passed' ? 'selected' : ''} value="passed">Passed</option>
                            <option ${status == 'reject' ? 'selected' : ''} value="reject">Reject</option>
                        </select>
                        <input type="hidden" name="courseid" value="${courseid}"/>
                    </div>
                </form>

                <div class="row">
                    <c:forEach items="${listAsmSubmitted}" var="sub">
                        <div class="col-md-4 mb-4">
                            <div class="card h-100">
                                <div class="card-body">
                                    <h5 class="card-title">
                                        <a href="viewstudentinfo?sid=${sub.learner_id}&subjectId=${subjectId}&courseid=${courseid}">Learner id: ${sub.learner_id}</a> 
                                        Status: <span class="status-${sub.status.toLowerCase()}">${sub.status}</span>
                                    </h5>
                                    <a href="viewAssignmentSubmittedDetail?submitted_id=${sub.submitted_id}&courseid=${courseid}" class="btn btn-primary">View detail</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>

                <div class="pagination">
                    <c:forEach var="i" begin="1" end="${totalPages}">
                        <a href="listCourseAssignment?courseid=${courseid}&p=${i}&status=${status}" class="${i == currentPage ? 'active' : ''}">${i}</a>
                    </c:forEach>
                </div>
            </c:if>
            <button onclick="goBack()" class="formbold-btn btn btn-secondary"> Back </button>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXlLvAwCEt3Gv9Gz87GX4nKCkQqAwNLHJJQGcFUU8O8yDN/4pQ60y1QQB1Sl" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWIY6ZgDMmPBZ+YFfnC1bbVQU4BU8I1v7tft/tV7fRXewp5oBS0b2HN6jJ" crossorigin="anonymous"></script>
        <script>
            function submitForm() {
                var form = document.getElementById('f');
                form.submit();
            }
            function goBack() {
                window.history.back();
            }
        </script>
    </body>
</html>
