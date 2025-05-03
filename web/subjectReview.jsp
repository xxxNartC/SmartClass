<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Subject Review</title>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
        <style>
            body {
                font-family: 'Roboto', sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 0;
            }

            .card-container {
                display: grid;
                grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
                gap: 20px;
                justify-content: center;
                margin: 20px auto;
                max-width: 1200px;
            }

            .card {
                background-color: #fff;
                border-radius: 8px;
                box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
                transition: transform 0.2s, box-shadow 0.2s;
                text-align: center;
                display: flex;
                flex-direction: column;
                justify-content: space-between;
                height: 100%;
            }

            .card:hover {
                transform: translateY(-5px);
                box-shadow: 0 8px 20px rgba(0, 0, 0, 0.2);
            }

            .card img {
                width: 100%;
                height: 200px;
                object-fit: cover;
            }

            .card-content {
                padding: 15px;
            }

            .card h3 {
                font-size: 20px;
                margin: 10px 0;
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
                padding: 10px 15px;
                margin: 4px;
                background-color: #007bff;
                color: white;
                border-radius: 4px
            }

            .pagination a:hover {
                background-color: #0069d9;
            }

            h1 {
                text-align: center;
                margin-bottom: 20px;
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
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 4px;
            }

            .search-container button {
                white-space: nowrap;
                padding: 10px 20px;
                background-color: #007bff;
                color: white;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }

            .search-container button:hover {
                background-color: #0069d9;
            }
        </style>
    </head>
    <body>

        <jsp:include page="header.jsp" />
        
<c:if test="${sessionScope.user.role_id == '3'}">
        <div class="container">
        <h1>Subject Review</h1>

        <!-- Form tìm kiếm -->
        <form action="subjectreview" method="post" class="search-form">
            <div class="search-container">
                <input type="text" class="form-control" id="search_subject" name="search_name" placeholder="Search here">
                <button type="submit" class="btn btn-primary">
                    <i class="fas fa-search"></i> Search
                </button>
            </div>
        </form>

        <div class="card-container">
            <c:forEach items="${listSub}" var="s">
                <div class="card">
                    <img src="${s.image}" alt="Course image">
                    <div class="card-content">
                        <h3>${s.subject_name}</h3>
                        <p>${s.description}</p>
                        <a href="responemanagement?subject_id=${s.subject_id}" class="inner-button">
                            <button class="btn btn-success">Comment Subject</button>
                        </a></br></br>
                    </div>
                </div>
            </c:forEach>
        </div>

        <!-- Phân trang -->
        <div class="pagination">
            <c:forEach begin="1" end="${page}" var="i">
                <a href="subjectreview?index=${i}&search_name=${search_name}"> ${i} </a>
            </c:forEach>
        </div>
</c:if>
    <c:if test="${sessionScope.user.role_id != '3'}">    
        <div class="container">    
            <h1>You are not authorized to access this page.</h1>       
            <a href="index.jsp"><input class="btn" type="submit" value="Back to Home"/></a>  
        </div>  
    </c:if>
    </body>
</html>

