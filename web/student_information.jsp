<%-- 
    Document   : student_information
    Created on : Oct 18, 2024, 8:11:06 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student Information</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    
    <jsp:include page="header.jsp" />

    <body>
        <div class="container mt-5">
            <div class="card">
                <div class="card-header bg-primary text-white">
                    <h3 class="card-title">Student Information</h3>
                </div>
                <div class="card-body">
                    <form>
                        <div class="mb-3">
                            <label for="username" class="form-label">Username</label>
                            <input class="form-control" id="username" value="${info.username}" readonly>
                        </div>
                        <div class="mb-3">
                            <label for="fullname" class="form-label">Fullname</label>
                            <input class="form-control" id="fullname" value="${info.fullname}" readonly>
                        </div>
                        <div class="mb-3">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" class="form-control" id="email" value="${info.email}" readonly>
                        </div>
                        <div class="mb-3">
                            <label for="dob" class="form-label">Date of Birth</label>
                            <input type="text" class="form-control" id="dob" value="${info.dob}" readonly>
                        </div>
                        <div class="mb-3">
                            <label for="phone" class="form-label">Phone Number</label>
                            <input type="tel" class="form-control" id="phone" value="${info.phone}" readonly>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <div class="container mt-5">
            <h3 class="mb-4">Assignment Information</h3>
            <table class="table table-bordered">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">Course Name</th>
                        <th scope="col">Status</th>
                        <th scope="col">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listAsm}" var="c">
                        <tr>
                            <td>${c.course_name}</td>
                            <td>${c.status}</td>
                            <td>
                                <a href="viewAssignmentSubmittedDetail?submitted_id=${c.submitted_id}&courseid=${courseid}" class="btn btn-primary">View Detail</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <!-- Bootstrap JS and dependencies -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

    </body>
</html>
