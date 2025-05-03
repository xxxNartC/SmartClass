<%-- 
    Document   : MySubjectList
    Created on : 4 thg 11, 2024, 21:15:12
    Author     : bacht
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Subject List</title>
        <style>
            .card-container {
                display: grid;
                grid-template-columns: repeat(3, 1fr); /* 3 columns */
                gap: 20px;
                justify-content: center; /* Center the container */
                margin: 20px auto; /* Center the page with even spacing on both sides */
                max-width: 1200px; /* Set a maximum width for the container */
            }
            /* Design for each card */
            .card {
                background-color: #fff;
                border: 1px solid #ddd;
                border-radius: 8px;
                overflow: hidden;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                transition: transform 0.2s;
                text-align: center; /* Center the text content */
                display: flex; /* Use flex to center the content */
                flex-direction: column; /* Arrange elements in a column */
                justify-content: space-between; /* Space out the elements */
                height: 100%; /* Ensure the card takes full height */
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
            .card .price {
                font-size: 16px;
                font-weight: bold;
                color: #ff5722;
                margin-top: 10px;
            }
            h1 {
                text-align: center;
            }
        </style>
    </head>
    <jsp:include page="header.jsp" />
    <body>
        <h1>My Subject List</h1>
        <div class="card-container">
            <c:forEach items="${purchasedSubjects}" var="subject">
                <div class="card">
                    <img src="${subject.image}" alt="Subject image">
                    <div class="card-content">
                        <h3>${subject.subject_name}</h3>
                    
                    </div>
                    <div>
                        <a href="course?subject_id=${subject.subject_id}" class="inner-button">
                            <button class="btn btn-success">View</button>
                        </a>
                    </div>   
                </div>
            </c:forEach>
        </div>
    </body>
</html>
