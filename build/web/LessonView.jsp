<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Lesson View</title>
        <style>
            body {
                font-family: 'Roboto', sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f4f4f9;
                color: #333;
            }

            /* Main container for the page layout */
            .container {
                max-width: 1200px;
                margin: 0 auto;
                padding: 30px;
                display: flex;
                justify-content: space-between;
                flex-wrap: wrap;
                align-items: flex-start;
                background-color: #fff;
                box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
                border-radius: 10px;
            }

            /* Chapter list on the left side */
            .chapter-list {
                width: 20%;
                font-size: 16px;
                color: #555;
                line-height: 1.6;
                padding-right: 20px;
                box-sizing: border-box;
            }

            .chapter-list ul {
                list-style-type: none;
                padding: 0;
            }

            .chapter-list ul li {
                margin-bottom: 10px;
            }

            .chapter-list ul li a {
                text-decoration: none;
                color: #0073b1;
                font-weight: 500;
            }

            .chapter-list ul li a:hover {
                text-decoration: underline;
                color: #005f8d;
            }

            /* Lesson details on the right side */
            .lesson-details {
                width: 75%;
                font-size: 16px;
                color: #555;
                line-height: 1.6;
                padding-left: 20px;
                box-sizing: border-box;
            }

            /* Video player */
            .lesson-video {
                width: 100%;
                height: 500px;
                border: none;
                border-radius: 10px;
                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
                margin-bottom: 15px;
            }

            /* Lesson title */
            .lesson-title {
                font-size: 24px;
                font-weight: 600;
                color: #333;
                margin-top: 10px;
                margin-bottom: 20px;
            }

            /* Document and Description */
            .lesson-details .document {
                font-size: 18px;
                font-weight: bold;
                color: #0073b1;
                margin-bottom: 10px;
            }

            .lesson-details p {
                margin-bottom: 15px;
            }

            /* Responsive for tablet and smaller devices */
            @media (max-width: 1024px) {
                .chapter-list,
                .lesson-details {
                    width: 100%;
                }

                .lesson-video {
                    height: 350px;
                }

                .lesson-details {
                    padding-left: 0;
                    margin-top: 20px;
                }
            }

            /* Responsive for mobile phones */
            @media (max-width: 768px) {
                .lesson-video {
                    height: 250px;
                }
            }

            /* Pagination styling */
            .pagination {
                display: flex;
                justify-content: center;
                margin: 30px 0;
            }

            .pagination a {
                text-decoration: none;
                color: #fff;
                padding: 10px 15px;
                margin: 0 5px;
                background-color: #0073b1;
                border-radius: 5px;
                transition: background-color 0.3s ease;
            }

            .pagination a:hover {
                background-color: #005f8d;
            }

        </style>
    </head>
    <jsp:include page="header.jsp" />
    <body>

        <!-- Main content container -->
        <div class="container">
            <!-- Display lessons1 in the left side -->
            <c:if test="${not empty lessons1}">
                <div class="chapter-list">
                    <h3>Lessons by Chapter ID:</h3>
                    <ul>
                        <c:forEach items="${lessons1}" var="lesson">
                            <li>
                                <a href="lessonview?chapter_id=${lesson.chapter_id}&lesson_id=${lesson.lesson_id}">${lesson.lesson_name}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>

            <!-- Video and lesson details on the right side -->
            <c:forEach items="${lessons}" var="lesson">
                <div class="lesson-details">
                    <!-- Video player -->
                    <iframe class="lesson-video" src="${lesson.video}" title="YouTube video player" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>

                    <!-- Lesson details -->
                    <h2 class="lesson-title">${lesson.lesson_name}</h2>
                    <p class="document">Document: ${lesson.document}</p>
                    <p>${lesson.description}</p>

                    <!-- Add a button to update learner lesson status -->
                    <form action="lessonview" method="post">
                        <input type="hidden" name="lesson_name" value="${lesson.lesson_name}">
                        <input type="hidden" name="chapter_id" value="${lesson.chapter_id}">
                        <input type="hidden" name="lesson_id" value="${lesson.lesson_id}">
                        <button type="submit" name="updateStatus">Mark as Completed</button>
                    </form>​
                    <!-- Add a button to return to Lesson List -->
                    <!-- Display success message if lesson status is updated -->                  
                    <c:if test="${not empty successMessage}">                        
                        <p class="success-message">${successMessage}</p>                
                    </c:if>
                        <a href="lessonlist">Back to Lesson List</a>
                    </div>
            </c:forEach>
        </div>

        <!-- Pagination -->
        <div class="pagination">
            <c:forEach begin="1" end="${page}" var="i">
                <a href="lessonview?index=${i}">${i}</a>
            </c:forEach>
        </div>

    </body>
    <jsp:include page="TabsLesson.jsp" />  
</html>

