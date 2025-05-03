<%@page import="model.QuizResult"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View All Quiz Results</title>
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
                width: 80%;
                margin-bottom: 20px;
            }
            .quiz-result-header h2 {
                margin: 0;
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
            .search-container {
                display: flex;
                align-items: center;
                margin-bottom: 10px;
                width: 20%;
            }
            .search-input {
                padding: 8px;
                border: 1px solid #ccc;
                border-radius: 4px;
                margin-right: 10px;
                flex: 1;
            }
            .filter-select {
                padding: 8px;
                border: 1px solid #ccc;
                border-radius: 4px;
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <div class="quiz-result-container">
            <div class="quiz-result-header">
                <h2>Quiz Results</h2>
                <a href="manageChapter?course_id=${course_id}&subject_id=${subject_id}">
                    <button class="btn-back">Back to Course Details</button>
                </a>
            </div>
            <div class="search-container">
                <input type="text" class="search-input" id="search-input" placeholder="Search...">
                <select class="filter-select" id="status-filter">
                    <option value="">All Status</option>
                    <option value="1">Pass</option>
                    <option value="0">Not Pass</option>
                </select>
            </div>
            <c:if test="${not empty allQuizResults}">
                <c:forEach items="${allQuizResults}" var="quizResult">
                    <!-- Quiz Result Table -->
                    <table class="quiz-table" data-chapter-name="${quizResult.chapter_name}" data-quiz-name="${quizResult.quiz_name}" data-user-name="${quizResult.username}" data-status="${quizResult.status}">
                        <tr>
                            <th>Quiz Name</th>
                            <td>${quizResult.quiz_name}</td>
                        </tr>
                        <tr>
                            <th>Chapter Name</th>
                            <td>${quizResult.chapter_name}</td>
                        </tr>
                        <tr>
                            <th>Learner User_name</th>
                            <td>${quizResult.username}</td>
                        </tr>
                        <tr>
                            <th>Learner Full_name</th>
                            <td>${quizResult.fullname}</td>
                        </tr>
                        <tr>
                            <th>Learner Email</th>
                            <td>${quizResult.email}</td>
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
            </c:if>
            <c:if test="${empty allQuizResults}">
                <p>No quiz results found for this course.</p>
            </c:if>
        </div>
        <script>
            const searchInput = document.getElementById('search-input');
            const statusFilter = document.getElementById('status-filter');
            const quizResultTables = document.querySelectorAll('.quiz-table');

            searchInput.addEventListener('input', () => {
                const searchTerm = searchInput.value.toLowerCase();
                quizResultTables.forEach(table => {
                    const chapterName = table.dataset.chapterName.toLowerCase();
                    const quizName = table.dataset.quizName.toLowerCase();
                    const userName = table.dataset.userName.toLowerCase();
                    const status = table.dataset.status;
                    const isMatch = chapterName.includes(searchTerm) || quizName.includes(searchTerm) || userName.includes(searchTerm);
                    const isStatusMatch = statusFilter.value === '' || status === statusFilter.value;
                    table.style.display = isMatch && isStatusMatch ? 'table' : 'none';
                });
            });

            statusFilter.addEventListener('change', () => {
                const selectedStatus = statusFilter.value;
                quizResultTables.forEach(table => {
                    const status = table.dataset.status;
                    const isStatusMatch = selectedStatus === '' || status === selectedStatus;
                    const searchTerm = searchInput.value.toLowerCase();
                    const chapterName = table.dataset.chapterName.toLowerCase();
                    const quizName = table.dataset.quizName.toLowerCase();
                    const userName = table.dataset.userName.toLowerCase();
                    const isMatch = chapterName.includes(searchTerm) || quizName.includes(searchTerm) || userName.includes(searchTerm);
                    table.style.display = isMatch && isStatusMatch ? 'table' : 'none';
                });
            });
        </script>
    </body>
</html>
