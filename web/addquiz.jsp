<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Quiz</title>
        <!-- Flaticon Font -->
        <link href="template course_detail/lib/flaticon/font/flaticon.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="template course_detail/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <link href="template course_detail/lib/lightbox/css/lightbox.min.css" rel="stylesheet">
        <link type="text/css" href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css" rel="stylesheet">
        <!-- Customized Bootstrap Stylesheet -->
        <link href="template course_detail/css/style.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="css/style.css"/>
        <link rel="stylesheet" href="css/stylemanagementcourse.css"/>


        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f9f9f9;
                color: #333;
                margin: 0;
                padding: 0;
            }

            .container {
                max-width: 600px;
                margin: 40px auto;
                padding: 20px;
                background-color: #fff;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }

            h1.text-center {
                font-size: 1.8rem;
                font-weight: bold;
                color: #007bff;
                text-align: center;
                margin-bottom: 20px;
            }

            .err {
                color: #dc3545;
                font-size: 0.9rem;
                font-weight: bold;
                text-align: center;
            }

            label {
                font-weight: bold;
                color: #555;
                margin-bottom: 5px;
                display: inline-block;
            }

            input[type="text"], select {
                width: 100%;
                padding: 10px;
                margin: 5px 0 15px 0;
                border: 1px solid #ddd;
                border-radius: 4px;
                box-sizing: border-box;
                transition: all 0.3s ease;
            }

            input[type="text"]:focus, select:focus {
                border-color: #007bff;
                box-shadow: 0 0 5px rgba(0, 123, 255, 0.2);
                outline: none;
            }

            .btn {
                display: inline-block;
                width: 100%;
                padding: 12px;
                font-size: 1rem;
                font-weight: bold;
                color: #fff;
                background-color: #007bff;
                border: none;
                border-radius: 4px; /* Bo tròn góc */
                cursor: pointer;
                transition: background-color 0.3s ease, transform 0.2s ease;
                margin-top: 15px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* Đổ bóng */
            }

            .btn:hover {
                background-color: #0056b3;
                transform: translateY(-2px); /* Hiệu ứng di chuyển nhẹ khi hover */
            }


            .btn-success {
                background-color: #28a745;
                border-radius: 4px; /* Bo tròn góc */
            }

            .btn-success:hover {
                background-color: #218838;
                transform: translateY(-2px);
            }

            /* Button for Navigation */
            .nav-btn {
                padding: 12px;
                font-size: 0.9rem;
                color: #fff;
                background-color: #007bff;
                border: none;
                border-radius: 4px; /* Bo tròn giống với input */
                text-transform: uppercase;
                text-align: center;
                display: block; /* Đặt nút thành dạng khối để mỗi nút ở trên một dòng */
                width: 100%; /* Đặt chiều rộng cho nút chiếm hết container */
                margin-top: 10px;
                text-decoration: none;
                transition: background-color 0.3s ease, transform 0.2s ease;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            }

            .nav-btn:hover {
                background-color: #0056b3;
                transform: translateY(-1px);
            }

            /* Styling for error messages */
            .err, .success {
                font-size: 0.9rem;
                padding: 10px;
                margin-bottom: 15px;
                border-radius: 4px;
            }

            .err {
                background-color: #f8d7da;
                color: #721c24;
                border: 1px solid #f5c6cb;
            }

            .success {
                background-color: #d4edda;
                color: #155724;
                border: 1px solid #c3e6cb;
            }

            .cancel-btn {
                background-color: #FF0000;
                color: #fff;
                padding: 12px;
                font-size: 0.9rem;
                border: none;
                border-radius: 4px;
                text-transform: uppercase;
                text-align: center;
                display: block;
                width: 100%;
                margin-top: 10px;
                text-decoration: none;
                transition: background-color 0.3s ease, transform 0.2s ease;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            }

            .cancel-btn:hover {
                background-color: #cc0000;
                transform: translateY(-1px);
            }

        </style>




    </head>
    <body>
        <input type="hidden" name="chapter_id" value="${chapterid}" readonly>
        <input type="hidden" name="subject_id" value="${subjectid}" readonly>
        <input type="hidden" name="course_id" value="${courseid}" readonly>

        <div class="container">
            <h1 class="text-center">${action == 'add' ? 'Add Quiz' : 'Update Quiz'}</h1>
            <c:if test="${not empty mess}">
                <h1 style="color: green">${mess}</h1>
            </c:if>

            <c:if test="${canAdd == false}">
                <h1 style="color: red">Have Error</h1>
                <p class="err">
                    The Question List is not enough to create a quiz (at least 10 questions including 4 easy questions, 4 medium questions, and 2 hard questions).<br />
                    Please add questions to the Question List!!!!
                </p>
            </c:if>
            <c:if test="${canAdd == true}">
                <form action="addquiz" method="post">
                    <input type="hidden" name="action" value="${action}" />
                    <input type="hidden" name="chapter_id" value="${chapterid}" readonly>
                    <input type="hidden" name="subject_id" value="${subjectid}" readonly>
                    <input type="hidden" name="course_id" value="${courseid}" readonly>
                    <label for="quiz_name">Quiz Name</label>
                    <input type="text" name="quiz_name" placeholder="Input name here" value="${detailQuiz.name}" required>
                    <label for="chapter_no">Number of Questions:</label>
                    <select name="no_question">
                        <option ${detailQuiz.no_question == '10' ? 'selected' : ''} value="10">10</option>
                        <option ${detailQuiz.no_question == '15' ? 'selected' : ''} style="display: ${displayOption15 == true ? '' : 'none'}" value="15">15</option>
                        <option ${detailQuiz.no_question == '20' ? 'selected' : ''} style="display: ${displayOption20 == true ? '' : 'none'}" value="20">20</option>

                    </select>
                    <button type="submit" class="btn-success">${action == 'add' ? 'Add Quiz' : 'Update Quiz'}</button>
                </form>
            </c:if>

            <a href="questionlist?sid=${subjectid}" class="nav-btn">Go to Question List</a>
            <a href="manageLesson?course_id=${courseid}&subject_id=${subjectid}&chapter_id=${chapterid}" class="cancel-btn">Cancel</a>
        </div>
    </body>


</html>



