<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Take Quiz</title>
        <style>
            .quiz-container {
                display: flex;
                flex-direction: column;
                align-items: center;
                margin-top: 20px;
            }

            .question {
                border: 1px solid #ccc;
                padding: 15px;
                margin-bottom: 10px;
                width: 100%;
                border-radius: 5px;
            }

            .question h3 {
                margin-top: 0;
            }

            .options {
                display: flex;
                flex-direction: column;
                gap: 10px;
            }

            .option {
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
                cursor: pointer;
            }

            .option:hover {
                background-color: #f0f0f0;
            }

            .submit-button {
                padding: 10px 20px;
                background-color: #4CAF50;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                margin-top: 20px;
            }

            .submit-button:hover {
                background-color: #45a049;
            }

            .score-container {
                position: absolute;
                top: 20px;
                right: 30px;
                background-color: floralwhite;
                padding: 20px;
                display: flex;
                flex-direction: column;
                align-items: center;
                justify-content: center;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                border-radius: 15px;
                color: white;
                width: 180px; /* Adjust width as needed */
                text-align: center;
            }
        </style>
    </head>
    <jsp:include page="header.jsp" />
    <body>
        <c:if test="${sessionScope.user.role_id == '2'}">
            <div class="quiz-container">
                <c:if test="${not empty errorMessage}">
                    <div class="error-message">${errorMessage}</div>
                </c:if>
                <h2>Take Quiz</h2>
                <form action="takequiz" method="post">
                    <input type="hidden" name="quiz_id" value="${quiz_id}">
                    <input type="hidden" name="learner_id" value="${sessionScope.user.account_id}">
                    <input type="hidden" name="subject_id" value="${subject_id}">
                    <input type="hidden" name="course_id" value="${course_id}">
                    <input type="hidden" name="chapter_id" value="${chapter_id}">
                    <input type="hidden" name="listQuestionId" value="${listQuestionId}">
                    <input type="hidden" name="listAnswer" value="${listAnswer}">

                    <c:if test="${listQ != null}">
                        <c:forEach items="${listQ}" var="q">
                            <div id="question" class="question">
                                <h3>${q.content}</h3>
                                <div class="options">
                                    <c:if test="${q.option1 != '' && q.option1 != null}">
                                        <label class="option">
                                            <input type="radio" name="question-${q.question_id}" value="1"> ${q.option1}
                                        </label>
                                    </c:if>
                                    <c:if test="${q.option2 != '' && q.option2 != null}">
                                        <label class="option">
                                            <input type="radio" name="question-${q.question_id}" value="2"> ${q.option2}
                                        </label>
                                    </c:if>
                                    <c:if test="${q.option3 != '' && q.option3 != null}">
                                        <label class="option">
                                            <input type="radio" name="question-${q.question_id}" value="3"> ${q.option3}
                                        </label>
                                    </c:if>
                                    <c:if test="${q.option4 != '' && q.option4 != null}">
                                        <label class="option">
                                            <input type="radio" name="question-${q.question_id}" value="4"> ${q.option4}
                                        </label>
                                    </c:if>
                                </div>
                            </div>
                        </c:forEach>
                        <button type="submit" id="submitBtn" class="submit-button">Submit Answer</button>
                    </c:if>
                </form>
            </div>
            <div class="score-container">
                <h2 style="color: black">Time Remaining</h2>
                <p id="timer" style="color: black"> </p>
            </div>
            <script>
                document.getElementById('submitBtn').addEventListener('click', function () {
                    if (confirm('Do you want to Submit Quiz')) {
                        submitForm();
                    }
                });

                function submitForm() {
                    const questions = document.querySelectorAll('.question');
                    questions.forEach(question => {
                        const options = question.nextElementSibling.querySelectorAll('input[type="radio"]');
                        let answered = false;
                        options.forEach(option => {
                            if (option.checked) {
                                answered = true;
                            }
                        });
                        if (!answered) {
                            const hiddenInput = document.createElement('input');
                            hiddenInput.type = 'hidden';
                            hiddenInput.name = 'question-' + options[0].name.split('-')[1];
                            hiddenInput.value = '0';
                            document.forms[0].appendChild(hiddenInput);
                        }
                    });
                    document.forms[0].submit();
                }

                function startTimer(duration, display) {
                    var timer = duration, minutes, seconds;
                    var interval = setInterval(function () {
                        minutes = parseInt(timer / 60, 10);
                        seconds = parseInt(timer % 60, 10);

                        minutes = minutes < 10 ? "0" + minutes : minutes;
                        seconds = seconds < 10 ? "0" + seconds : seconds;

                        display.textContent = minutes + ":" + seconds;

                        if (--timer < 0) {
                            clearInterval(interval);
                            submitForm(); // Automatically submit form when timer ends
                        }
                    }, 1000);
                }

                window.onload = function () {
                    var numQuestions = document.querySelectorAll('.question').length;
                    var timeLimit = 60 * numQuestions;
                    var display = document.querySelector('#timer');
                    startTimer(timeLimit, display);
                };
            </script>
        </c:if>
        <c:if test="${sessionScope.user.role_id != '2'}">    
            <div class="container">    
                <h2>You are not authorized to access this page.</h2>       
                <a href="lessonlist?subject_id=${subject_id}&course_id=${course_id}&chapter_id=${chapter_id}&learner_id=${learner_id}">
                    <button class="btn btn-primary">Back to Course Details</button>
                </a>
            </div>  
        </c:if>
    </body>
</html>
