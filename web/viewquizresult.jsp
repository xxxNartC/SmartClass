<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quiz Result</title>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <style>
            .quiz-result-container {
                display: flex;
                flex-direction: column;
                align-items: center;
                margin-top: 20px;
            }
            .quiz-result-header {
                display: flex;
                justify-content: center;
                align-items: center;
                width: 100%;
                margin-bottom: 20px;
            }
            .quiz-result-header h2 {
                margin: 0 auto;
            }
            .quiz-table {
                width: 80%;
                margin-bottom: 20px;
                border-collapse: collapse;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            .quiz-table, .quiz-table th, .quiz-table td {
                border: 1px solid #ddd;
            }
            .quiz-table th, .quiz-table td {
                width: 50%;
                padding: 10px;
                text-align: left;
                word-wrap: break-word;
            }
            .quiz-table th {
                background-color: #f4f4f4;
                font-weight: bold;
            }
            .mark, .status {
                font-weight: bold;
                font-size: 18px;
            }
            .status-pass {
                color: green;
            }
            .status-not-pass {
                color: red;
            }
            .chart-and-rating-container {
                display: flex;
                width: 80%;
                margin-top: 15px;
                gap: 20px;
            }
            .chart-container, .feedback-form-container {
                flex: 1;
                padding: 20px;
                border: 1px solid #ddd;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                background-color: #f9f9f9;
            }
            .chart-container {
                display: flex;
                justify-content: center;
                align-items: center;
            }
            .feedback-title {
                font-size: 20px;
                color: #333;
                margin-bottom: 10px;
                text-align: center;
            }
            .feedback-form {
                width: 100%;
            }
            .rating input {
                display: none;
            }
            .rating label {
                font-size: 25px;
                color: #ddd;
                cursor: pointer;
                transition: transform 0.2s;
            }
            .rating label:hover,
            .rating input:checked ~ label {
                color: #FFC107;
                transform: scale(1.2);
            }
            .rating input:checked ~ label {
                color: #FFD700;
            }
            .rating input:checked ~ input:checked ~ label {
                color: #FFD700;
            }
            .feedback-label {
                margin-top: 15px;
                font-size: 16px;
                font-weight: 500;
                color: #555;
            }
            .feedback-textarea {
                width: 100%;
                border-radius: 8px;
                margin-top: 10px;
                padding: 10px;
                font-size: 14px;
                resize: vertical;
            }
            .feedback-buttons {
                display: flex;
                justify-content: space-between;
                margin-top: 20px;
            }
            .btn-submit {
                padding: 8px 16px;
                border-radius: 5px;
                font-weight: bold;
                cursor: pointer;
                transition: background-color 0.3s;
            }
            .btn-submit {
                background-color: #4CAF50;
                color: white;
            }
            .btn-submit:hover {
                background-color: #45a049;
            }
            .btn-back {
                background-color: #337ab7;
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                font-size: 16px;
                position: absolute;
                right: 0;
            }
            .btn-back:hover {
                background-color: #21618c;
            }
            .update-certificate-button {
                padding: 10px 20px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                font-size: 16px;
                background-color: #28a745;
                color: white;
                margin-top: 20px;
            }
            .update-certificate-button:hover {
                background-color: #218838;
            }
            .success-message {
                color: green;
                font-weight: bold;
                margin-top: 20px;
            }
        </style>
    </head>
    <jsp:include page="header.jsp" />
    <body>
        <c:if test="${sessionScope.user.role_id == '2'}">
            <div class="quiz-result-container">
                <div class="quiz-result-header">
                    <h2>Quiz Results</h2>
                    <a href="course-details?course_id=${course_id}&learner_id=${learner_id}">
                        <button class="btn-back">Back to Course Details</button>
                    </a>
                </div>
                <br>
                <c:if test="${not empty quizResults}">
                    <c:forEach items="${quizResults}" var="quizResult">
                        <!-- Quiz Result Table -->
                        <table class="quiz-table">
                            <tr>
                                <th>Quiz Name</th>
                                <td>${quizResult.quiz_name}</td>
                            </tr>
                            <tr>
                                <th>Chapter Name</th>
                                <td>${quizResult.chapter_name}</td>
                            </tr>
                            <tr>
                                <th>Mark</th>
                                <td class="mark">${quizResult.mark}</td>
                            </tr>
                            <tr>
                                <th>Status</th>
                                <td class="status ${quizResult.status == 1 ? 'status-pass' : 'status-not-pass'}">
                                    ${quizResult.status == 1 ? 'Pass' : 'Not Pass'}
                                </td>
                            </tr>
                        </table>
                    </c:forEach>

                    <!-- Chart and Rating Container -->
                    <div class="chart-and-rating-container">
                        <!-- Chart Container -->
                        <div class="chart-container">
                            <canvas id="learnerProgressChart"></canvas>
                        </div>

                        <!-- Feedback Form Section -->
                        <div class="feedback-form-container" id="formrate">
                            <h4 class="feedback-title">Rate this course</h4>
                            <form action="rateCourse" method="get" class="feedback-form">
                                <input type="hidden" name="subject_id" value="${subject_id}" />
                                <input type="hidden" name="course_id" value="${course_id}" />
                                <input type="hidden" name="learner_id" value="${sessionScope.user.account_id}" />

                                <!-- Rating stars -->
                                <div class="rating">
                                    <input type="radio" name="rating" id="star5" value="5" ${learnercourse.getRate() eq '5' ? 'checked="checked"' : ''}><label for="star5" title="5 stars">☆</label>
                                    <input type="radio" name="rating" id="star4" value="4" ${learnercourse.getRate() eq '4' ? 'checked="checked"' : ''}><label for="star4" title="4 stars">☆</label>
                                    <input type="radio" name="rating" id="star3" value="3" ${learnercourse.getRate() eq '3' ? 'checked="checked"' : ''}><label for="star3" title="3 stars">☆</label>
                                    <input type="radio" name="rating" id="star2" value="2" ${learnercourse.getRate() eq '2' ? 'checked="checked"' : ''}><label for="star2" title="2 stars">☆</label>
                                    <input type="radio" name="rating" id="star1" value="1" ${learnercourse.getRate() eq '1' ? 'checked="checked"' : ''}><label for="star1" title="1 star">☆</label>
                                </div>

                                <!-- Feedback text area -->
                                <label for="feedback" class="feedback-label">Feedback</label>
                                <textarea name="feedback" id="feedback" cols="30" rows="5" class="form-control feedback-textarea"></textarea>

                                <!-- Submit and close buttons -->
                                <div class="feedback-buttons">
                                    <button type="submit" id="submitformrate" class="btn-submit">Submit</button>
                                </div>
                            </form>
                        </div>
                    </div>
                    <c:if test="${showUpdateCertificateButton}">
                        <form action="updateCourseCertificate" method="post">
                            <input type="hidden" name="course_id" value="${course_id}">
                            <input type="hidden" name="learner_id" value="${learner_id}">
                            <button type="submit" class="update-certificate-button">View Accomplish</button>
                        </form>
                        <c:if test="${not empty message}">
                            <p class="success-message">${message}</p>
                        </c:if>
                    </c:if>
                </c:if>
                <c:if test="${empty quizResults}">
                    <p>No quiz results found for this learner.</p>
                </c:if>
            </div>

            <script>
                // Chart.js configuration
                const ctx = document.getElementById('learnerProgressChart').getContext('2d');
                const learnerProgressChart = new Chart(ctx, {
                    type: 'line',
                    data: {
                        labels: [
                <c:forEach items="${quizResults}" var="quizResult">
                            '${quizResult.chapter_name}',
                </c:forEach>
                        ],
                        datasets: [{
                                label: 'Learner Progress',
                                data: [
                <c:forEach items="${quizResults}" var="quizResult">
                    ${quizResult.mark},
                </c:forEach>
                                ],
                                borderColor: 'rgba(54, 162, 235, 1)',
                                        borderWidth: 2
                            }]
                    },
                    options: {
                        scales: {
                            y: {
                                beginAtZero: true,
                                max: 10
                            }
                        }
                    }
                });
            </script>
        </c:if>
        <c:if test="${sessionScope.user.role_id != '2'}">    
            <div class="container">    
                <h2>You are not authorized to access this page.</h2>       
                <a href="course-details?course_id=${course_id}&learner_id=${learner_id}">
                    <button class="btn btn-primary">Back to Course Details</button>
                </a>
            </div>  
        </c:if>
    </body>
</html>
