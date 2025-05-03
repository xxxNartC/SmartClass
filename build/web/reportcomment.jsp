<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Report Comment</title>
        <link rel="stylesheet" href="css/linearicons.css" />
        <link rel="stylesheet" href="css/bootstrap.css" />
        <link rel="stylesheet" href="css/magnific-popup.css" />
        <link rel="stylesheet" href="css/owl.carousel.css" />
        <link rel="stylesheet" href="css/nice-select.css">
        <link rel="stylesheet" href="css/hexagons.min.css" />
        <link rel="stylesheet" href="css/main.css" />
        <link rel="stylesheet" href="css/style.css">
        <style>
/*            header{
                background-color: #222222;
            }
            body {
                font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
                background-color: #f0f2f5;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
                padding: 0 20px;
            }*/

            .report-container {
                background-color: #ffffff;
                padding: 40px;
                border-radius: 12px;
                box-shadow: 0 6px 10px rgba(0, 0, 0, 0.1);
                width: 100%;
                max-width: 500px;
                box-sizing: border-box;
                text-align: center;
            }

            .report-container h2 {
                margin-top: 0;
                margin-bottom: 30px;
                font-size: 28px;
                color: #333333;
            }

            label {
                display: block;
                margin-bottom: 12px;
                font-weight: 600;
                color: #555555;
                text-align: left;
            }

            input[type="text"],
            select {
                width: 100%;
                padding: 14px;
                margin-bottom: 20px;
                border: 1px solid #dddddd;
                border-radius: 6px;
                box-sizing: border-box;
                font-size: 16px;
                color: #333333;
            }

            input[type="text"]:focus,
            select:focus {
                border-color: #007bff;
                outline: none;
                box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
            }

            .hidden {
                display: none;
            }

            .back-button {
                background-color: #007bff;
                color: white;
                padding: 12px 24px;
                border: none;
                border-radius: 6px;
                cursor: pointer;
                margin-bottom: 30px;
                text-decoration: none;
                display: inline-block;
            }

            .back-button:hover {
                background-color: #0056b3;
            }

            button[type="submit"] {
                background-color: #28a745;
                color: white;
                padding: 12px 24px;
                border: none;
                border-radius: 6px;
                cursor: pointer;
            }

            button[type="submit"]:hover {
                background-color: #218838;
            }

            .error {
                color: red;
                font-size: 14px;
                margin-top: -10px;
                margin-bottom: 20px;
            }

            .input-group {
                text-align: left;
            }

        </style>
    </head>

  <jsp:include page="header.jsp" />
    <body>

    </div>

</nav>
<div class="report-container" style="margin: 0 auto; margin-top: 30px">
            <c:choose>
                <c:when test="${not empty sessionScope.user and sessionScope.user.role_id == 2}">
                    <a href="lessonview?chapter_id=${chapter_id}&lesson_id=${lesson_id}" class="back-button">Back To Lesson View</a>
                </c:when>
            </c:choose>
            <h2>Report Comment</h2>
            <form id="reportComment" method="Post">
                <div class="input-group">
                    <label for="reportedAccountInfo">Reported Account Information:</label>
                    <input type="text" id="reportedAccountInfo" name="reportedAccountInfo" value="${commentlesson.getFullname()}" readonly/>
                </div>

                <div class="input-group">
                    <label for="report_description">Report Description:</label>
                    <input type="text" id="report_description" name="report_description" value="${commentlesson.getComment()}" readonly/>
                </div>

                <div class="input-group">
                    <label for="report_type">Report Type:</label>
                    <select id="report_type" name="report_type" >
                        <option value="spam">Spam</option>
                        <option value="abusive">Abusive Content</option>
                    </select>
                </div>

                <div id="otherTypeContainer" class="input-group" style="display: none;">
                    <label for="other_type">Please specify:</label>
                    <input type="text" id="other_type" name="other_type" placeholder="Specify other report type">
                    <div name="errreportcomment" class="error" id="errreportcomment"></div>
                </div>

                <input type="text" id="account_id" name="account_id" class="hidden" value="${commentlesson.getAccountId()}">
                <input type="text" id="comment_id" name="comment_id" class="hidden" value="${comment_id}">
                <input type="text" id="subject_id" name="subject_id" class="hidden" value="${subject_id}">
                <input type="text" id="chapter_id" name="chapter_id" class="hidden" value="${chapter_id}">
                <input type="text" id="lesson_id" name="lesson_id" class="hidden" value="${lesson_id}">

                <button type="submit" id="submitreport" onclick="validate(event)" style="margin-top: 24px">Submit Report</button>
            </form>
        </div>

            
        <script>


            function validate(event) {
                const submitreport = document.querySelector("#submitreport").value.trim();
                const report_type = document.querySelector("#report_type").value; 
                    let reportComment = document.querySelector("#reportComment");
                    reportComment.submit(); 
            }
        </script>
    </body>
</html>
