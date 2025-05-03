<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lesson List</title>
        <style>
            .card-container {
                display: grid;
                grid-template-columns: repeat(3, 1fr);
                gap: 20px;
                justify-content: center;
                margin: 20px auto;
                max-width: 1200px;
            }
            .card {
                background-color: #fff;
                border: 1px solid #ddd;
                border-radius: 8px;
                overflow: hidden;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                transition: transform 0.2s;
                text-align: center;
                display: flex;
                flex-direction: column;
                justify-content: space-between;
                height: 100%;
            }
            .card:hover {
                transform: translateY(-5px);
            }
            .card img {
                width: 100%;
                height: 370px;
                object-fit: cover;
            }
            .card-content {
                padding: 15px;
                display: flex;
                flex-direction: column;
                align-items: center;
            }
            .card h3 {
                font-size: 18px;
                margin: 0 0 10px;
                color: #333;
            }
            .card p {
                font-size: 14px;
                color: #666;
                margin: 10px 0;
            }
            .card .price {
                font-size: 16px;
                font-weight: bold;
                color: #ff5722;
                margin-top: 10px;
            }
            .pagination {
                display: flex;
                justify-content: center;
                margin-top: 20px;
            }
            .pagination a {
                display: inline-block;
                padding: 8px 16px;
                margin: 4px;
                background-color: #ddd;
                text-decoration: none;
                color: black;
                border-radius: 4px;
            }
            .pagination a:hover {
                background-color: #bbb;
            }
            h1 {
                text-align: center;
            }
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
            .add-lesson-btn {
                display: inline-block;
                margin-top: -60px;
                margin-right: 15%;
                float: right;
                background-color: #ADD8E6;
                color: black;
                padding: 10px 20px;
                text-decoration: none;
                border-radius: 5px;
                font-size: 16px;
            }

        </style>
    </head>
    <body>  
        <jsp:include page="header.jsp" />
        <h1>Lesson List</h1>

        <!-- Form tìm kiếm -->
        <form action="lessonlist" method="get" class="search-form">
            <div class="search-container">
                <input type="text" class="form-control" id="search_lesson" name="search_name" placeholder="Search" value="${search_name}">
                <button type="submit" class="btn btn-primary">Search</button>
                <input type="hidden" name="chapter_id" value="${chapter_id}">
                <input type="hidden" name="course_id" value="${course_id}">
                <input type="hidden" name="subject_id" value="${subject_id}">
                <input type="hidden" name="learner_id" value="${learner_id}">
                <input type="hidden" name="account_id" value="${account_id}">
            </div>

        </form>



        <!-- Hiển thị danh sách bài học và bài kiểm tra -->
        <div class="card-container">
            <c:forEach items="${listLes}" var="l">
                <div class="card">
                    <iframe width="100%" height="370" src="${l.video}" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>
                    <div class="card-content">
                        <h3><a href='lessonview?chapter_id=${l.chapter_id}&lesson_id=${l.lesson_id}' class="nav-item nav-link">${l.lesson_name}</a></h3>
                <p>${l.description}</p>
                    </div>
                </div>
            </c:forEach>
        </div>
        <!-- Display quizzes at the end of the chapter -->
        <c:forEach items="${listQuiz}" var="q">
            <!-- Update the href to include all necessary parameters -->
            <c:if test="${q.chapter_id == chapter_id}">
                <div class="search-container">
                    <c:choose>
                        <c:when test="${q.status == 'passed'}">
                            <button style="background: #f1f1f1; color: #28a745" class="btn btn-primary" disabled>You have passed this quiz!</button>
                            <a href="viewquizresult?chapter_id=${chapter_id}&learner_id=${sessionScope.user.account_id}&quiz_id=${q.quiz_id}&subject_id=${subject_id}&course_id=${course_id}">                  
                                <button style="background: #f1f1f1; color: black" class="btn btn-primary">View Quiz Result</button>              
                            </a>
                        </c:when>
                        <c:when test="${q.status == 'not pass'}">
                            <a href="takequiz?chapter_id=${chapter_id}&learner_id=${sessionScope.user.account_id}&quiz_id=${q.quiz_id}&subject_id=${subject_id}&course_id=${course_id}">
                                <button style="background: #f1f1f1; color: red" class="btn btn-primary">You have not passed this quiz. Try again!</button>
                            </a>
                            <a href="viewquizresult?chapter_id=${chapter_id}&learner_id=${sessionScope.user.account_id}&quiz_id=${q.quiz_id}&subject_id=${subject_id}&course_id=${course_id}">                          
                                <button style="background: #f1f1f1; color: black" class="btn btn-primary">View Quiz Result</button>            
                            </a>
                        </c:when>
                        <c:otherwise>
                            <a href="takequiz?chapter_id=${chapter_id}&learner_id=${sessionScope.user.account_id}&quiz_id=${q.quiz_id}&subject_id=${subject_id}&course_id=${course_id}">
                                <button style="background: #f1f1f1; color: black" class="btn btn-primary">Quiz is ready! Take the quiz now.</button>
                            </a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </c:if>
        </c:forEach>
        <div class="search-container">
            <a  href="course-details?subject_id=${subject_id}&course_id=${course_id}" ><input class="btn btn-primary" type="submit" value="Back to Course Details"/></a>
        </div>
        <!-- Phân trang -->
        <div class="pagination">
            <c:forEach begin="1" end="${page}" var="i">
                <a href="lessonlist?index=${i}&search_name=${search_name}&chapter_id=${chapter_id}&course_id=${course_id}&subject_id=${subject_id}&learner_id=${learner_id}&account_id=${account_id}">${i}</a>
            </c:forEach>
        </div>
    </body>
</html>

