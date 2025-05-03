<%-- 
    Document   : subjectCard
    Created on : 4 thg 11, 2024, 22:17:11
    Author     : bacht
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:forEach items="${purchasedSubjects}" var="subject">
    <div class="card">
        <img src="${subject.image}" alt="Subject image">
        <div class="card-content">
            <h3>${subject.subject_name}</h3>
            <div class="price">${subject.price}đ</div>
        </div>
        <div>
            <form action="course" method="get">
                <input type="hidden" name="subject_id" value="${subject.subject_id}">
                <button type="submit" class="btn btn-success">View Course</button>
            </form>
        </div>   
    </div>
</c:forEach>

    </body>
</html>
