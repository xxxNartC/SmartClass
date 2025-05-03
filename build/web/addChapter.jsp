<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Chapter</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/themify-icons/0.1.2/css/themify-icons.css" />
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    </head>
    <jsp:include page="header.jsp" />
    <body>
        <c:if test="${sessionScope.user.role_id == '3'}">
            <div class="container">
                <div class="card" style="margin-top: 30px; padding: 20px;">
                    <div class="card-body">
                        <h1 class="card-title">Add Chapter</h1>
                        <c:if test="${not empty mess}">
                            <small class="text-danger">${mess}</small>
                        </c:if>
                        <c:if test="${not empty error}">
                            <small class="text-danger">${error}</small>
                        </c:if>
                        <form action="addChapter" method="POST">
                            <input type="hidden" name="course_id" value="${course_id}">
                            <input type="hidden" name="subject_id" value="${subject_id}">

                            <div class="form-group">
                                <label for="chapter_no">Chapter No:</label>
                                <input type="text" class="form-control" id="chapter_no" name="chapter_no" required>
                                <c:if test="${not empty chapter_no_error}">
                                    <small class="text-danger">${lesson_no_error}</small>
                                </c:if>
                            </div>

                            <div class="form-group">
                                <label for="chapter_name">Chapter Name:</label>
                                <input type="text" class="form-control" id="chapter_name" name="chapter_name" required>
                                <c:if test="${not empty chapter_name_error}">
                                    <small class="text-danger">${lesson_name_error}</small>
                                </c:if>
                            </div>

                            <div class="form-group">
                                <label for="description">Chapter Description:</label>
                                <textarea class="form-control" id="description" name="description" rows="3" required></textarea>
                                <c:if test="${not empty description_error}">
                                    <small class="text-danger">${description_error}</small>
                                </c:if>
                            </div>
                            <button type="submit" class="btn btn-primary">Add Chapter</button>
                            <a href="manageChapter?course_id=${course_id}&subject_id=${subject_id}" class="btn btn-primary">Back to Chapter Manage</a>
                        </form>
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${sessionScope.user.role_id != '3'}">    
            <div class="container">    
                <h1>You are not authorized to access this page.</h1>       
                <a href="index.html"><input class="btn" type="submit" value="Back to Home"/></a>  
            </div>  
        </c:if>

    </body>

</html>