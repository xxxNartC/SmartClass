<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Accomplishment</title>
        <style>
            body {
                font-family: sans-serif;
            }
            h1 {
                color: #337ab7;
                text-align: center;
            }
            table {
                width: 80%;
                margin: 0 auto;
                border-collapse: collapse;
            }
            th, td {
                padding: 10px;
                text-align: left;
            }
            th {
                background-color: #337ab7;
                color: white;
            }
            tr:nth-child(even) {
                background-color: #f2f2f2;
            }
            .container {
                width: 80%;
                margin: 0 auto;
                padding: 20px;
                border: 1px solid #ccc;
                border-radius: 5px;
            }
            .back-to-home-btn {
                display: inline-block;
                margin-top: 20px;
                background-color: #ADD8E6;
                color: black;
                padding: 10px 20px;
                text-decoration: none;
                border-radius: 5px;
                font-size: 16px;
            }
            .share-linkedin {
                background-color: #007bb5;
                color: white;
                padding: 5px 10px;
                text-decoration: none;
                border-radius: 3px;
                font-size: 14px;
                display: inline-block;
                margin-left: 10px;
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp" />

        <div class="container">
            <!-- Kiểm tra session để đảm bảo người dùng đã đăng nhập -->
            <%
                
                if (session.getAttribute("account_id") == null) {
                    response.sendRedirect("login.jsp");  // Chuyển hướng đến trang đăng nhập nếu chưa đăng nhập
                    return;
                }
            %>

            <h1>My Accomplishment</h1>
            
            <h2>Course Certificates</h2>
            <c:if test="${not empty completedCourses}">
                <table border="1">
                    <tr>
                        <th>Course Name</th>
                        <th>Share</th>
                    </tr>
                    <c:forEach items="${completedCourses}" var="course">
                        <tr>
                            <td><a href='ceftificate?courseName=${course}' class="nav-item nav-link">${course}</a></td>
                            <td>
                                <a href="#" class="share-linkedin" onclick="shareCourseOnLinkedIn('${course}')">Share on LinkedIn</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
            <c:if test="${empty completedCourses}">
                <p>You have not completed any courses yet.</p>
            </c:if>

            <h2>Subject Certificates</h2>
            <c:if test="${not empty completedSubjects}">
                <table border="1">
                    <tr>
                        <th>Subject Name</th>
                        <th>Share</th>
                    </tr>
                    <c:forEach items="${completedSubjects}" var="subject">
                        <tr>
                            <td><a href='ceftificate?subjectName=${subject}' class="nav-item nav-link">${subject}</a></td>
                            <td>
                                <a href="#" class="share-linkedin" onclick="shareSubjectOnLinkedIn('${subject}')">Share on LinkedIn</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
            <c:if test="${empty completedSubjects}">
                <p>You have not completed any subjects yet.</p>
            </c:if>

            <a href="index.jsp" class="back-to-home-btn">Back to Home</a>
        </div>
        
        <script>
            // Function to share each course on LinkedIn
            function shareCourseOnLinkedIn(courseName) {
                var shareUrl = "https://www.linkedin.com/sharing/share-offsite/?url=https://www.example.com/my-accomplishments&title=I completed the course: " + encodeURIComponent(courseName);
                window.open(shareUrl, '_blank', 'width=600,height=400');
            }

            // Function to share each subject on LinkedIn
            function shareSubjectOnLinkedIn(subjectName) {
                var shareUrl = "https://www.linkedin.com/sharing/share-offsite/?url=https://www.example.com/my-accomplishments&title=I completed the subject: " + encodeURIComponent(subjectName);
                window.open(shareUrl, '_blank', 'width=600,height=400');
            }
        </script>
    </body>
</html>

