<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Quiz Result</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f9;
                margin: 0;
                padding: 0;
                height: 100vh;
                display: flex;
                justify-content: center;
                align-items: center;
            }

            .container {
                display: flex;
                flex-direction: column;
                width: 80%;
                max-width: 900px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
                background-color: white;
                padding: 20px;
                margin-top: 20px;
                margin: auto;
            }

            .quiz-container {
                flex: 1;
                padding: 20px;
                background-color: #ffffff;
                border-radius: 8px;
            }

            h1 {
                text-align: center;
                margin-bottom: 20px;
            }

            .question {
                margin: 20px 0;
                font-size: 18px;
                font-weight: bold;
            }

            .options {
                display: flex;
                flex-direction: column;
                margin-bottom: 20px;
            }

            .options label {
                margin: 5px 0;
            }

            .options input[type="radio"] {
                margin-right: 10px;
            }

            .submit {
                width: 100%;
                padding: 10px;
                background-color: #5cb85c;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                font-size: 16px;
            }

            .submit:hover {
                background-color: #4cae4c;
            }

            .score-container {
                position: absolute;
                top: 20px;
                right: 20px;
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

            .score-container h2 {
                margin: 0;
                font-size: 20px;
            }

            .score-container p {
                margin: 5px 0;
                font-size: 16px;
            }

            #score {
                font-size: 24px;
                font-weight: bold;
            }

            .btn-back {
                background-color: #008CBA; /* Blue */
                color: white;
                border: none;
                padding: 10px 20px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                margin: 4px 2px;
                cursor: pointer;
                border-radius: 4px;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="quiz-container">

                <c:if test="${listQ != null}">
                    <c:forEach items="${listQ}" var="q">
                        <div id="question" class="question" style="color: ${q.isTrue=='false'?'red':''}">${q.content}</div>
                        <div class="options">
                            <!--Cac cau hoi da tra loi dung-->
                            <c:if test="${q.isTrue == 'true'}">

                                <c:if test="${q.option1 != '' && q.option1 != null}">
                                    <label style="color: ${(q.isTrue == 'true' && q.learnerAns == '1') ?'green':''}">
                                        <input ${q.learnerAns == '1' ? 'checked' : ''}
                                            disabled type="radio" name="question-${q.question_id}" value="1"> ${q.option1}
                                    </label>
                                </c:if>
                                <c:if test="${q.option2 != '' && q.option2 != null}">
                                    <label style="color: ${(q.isTrue == 'true' && q.learnerAns == '2') ?'green':''}">
                                        <input ${q.learnerAns == '2' ? 'checked' : ''}
                                            disabled type="radio" name="question-${q.question_id}" value="2"> ${q.option2}
                                    </label>
                                </c:if>
                                <c:if test="${q.option3 != '' && q.option3 != null}">
                                    <label style="color: ${(q.isTrue == 'true' && q.learnerAns == '3') ?'green':''}">
                                        <input ${q.learnerAns == '3' ? 'checked' : ''}
                                            disabled type="radio" name="question-${q.question_id}" value="3"> ${q.option3}
                                    </label>
                                </c:if>
                                <c:if test="${q.option4 != '' && q.option4 != null}">
                                    <label style="color: ${(q.isTrue == 'true' && q.learnerAns == '4') ?'green':''}">
                                        <input ${q.learnerAns == '4' ? 'checked' : ''}
                                            disabled type="radio" name="question-${q.question_id}" value="4"> ${q.option4}
                                    </label>
                                </c:if>

                            </c:if>

                            <!--Cac cau hoi da tra loi sai-->
                            <c:if test="${q.isTrue == 'false'}">
                                <c:if test="${q.option1 != '' && q.option1 != null}">
                                    <label style="color: ${q.learnerAns == '1'?'':''}; text-decoration: ${q.learnerAns == '1'?'line-through':''}">
                                        <input ${q.learnerAns == '1' ? 'checked' : ''}
                                            disabled type="radio" name="question-${q.question_id}" value="1"> 
                                        <span style="color: ${q.answer == '1' ? 'green' : ''}">${q.option1}</span>
                                    </label>
                                </c:if>
                                <c:if test="${q.option2 != '' && q.option2 != null}">
                                    <label style="color: ${q.learnerAns == '2'?'':''}; text-decoration: ${q.learnerAns == '2'?'line-through':''}">
                                        <input ${q.learnerAns == '2' ? 'checked' : ''}
                                            disabled type="radio" name="question-${q.question_id}" value="1"> 
                                        <span style="color: ${q.answer == '2' ? 'green' : ''}">${q.option2}</span>
                                    </label>
                                </c:if>
                                <c:if test="${q.option3 != '' && q.option3 != null}">
                                    <label style="color: ${q.learnerAns == '3'?'':''}; text-decoration: ${q.learnerAns == '3'?'line-through':''}">
                                        <input ${q.learnerAns == '3' ? 'checked' : ''}
                                            disabled type="radio" name="question-${q.question_id}" value="1"> 
                                        <span style="color: ${q.answer == '3' ? 'green' : ''}">${q.option3}</span>
                                    </label>
                                </c:if>
                                <c:if test="${q.option4 != '' && q.option4 != null}">
                                    <label style="color: ${q.learnerAns == '4'?'':''}; text-decoration: ${q.learnerAns == '4'?'line-through':''}">
                                        <input ${q.learnerAns == '4' ? 'checked' : ''}
                                            disabled type="radio" name="question-${q.question_id}" value="1"> 
                                        <span style="color: ${q.answer == '4' ? 'green' : ''}">${q.option4}</span>
                                    </label>
                                </c:if>
                            </c:if>
                        </div>
                    </c:forEach>

                </c:if>
            </div>
            <div class="score-container">
                <h2 style="color: black">MARK</h2>              
                <c:if test="${mark >= 5}">
                    <p id="score" style="color: green">${mark}</p><br>
                    <p id="score" style="color: green"> PASSED </p><br>
                </c:if>
                <c:if test="${mark < 5}">
                    <p id="score" style="color: red">${mark}</p><br>
                    <p id="score" style="color: red"> NOT PASSED </p><br>
                </c:if>


            </div>

            <c:if test="${mark >= 5}">
                <a href="lessonlist?subject_id=${subject_id}&course_id=${course_id}&chapter_id=${chapter_id}">
                    <button class="btn btn-back">Back to Lesson View</button>
                </a>
            </c:if>
            <c:if test="${mark < 5}">
                <a href="takequiz?chapter_id=${chapter_id}&learner_id=${sessionScope.user.account_id}&quiz_id=${quiz_id}&subject_id=${subject_id}&course_id=${course_id}">
                    <button class="btn btn-back">Try Again</button>
                </a>
                <a href="lessonlist?subject_id=${subject_id}&course_id=${course_id}&chapter_id=${chapter_id}" >
                    <button class="btn btn-back">Back to Lesson</button>
                </a>

            </c:if>
        </div>
    </body>
</html>

