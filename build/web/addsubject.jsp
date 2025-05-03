<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Subject</title>

        <style>
            /* CSS cho form thêm môn học */
            body {
                background-color: #f4f4f4;
                font-family: 'Roboto', sans-serif;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
            }

            .container {
                display: flex;
                flex-direction: column;
                justify-content: center;
                align-items: center;
                width: 100%;
            }

            .banner-area {
                text-align: center;
                margin-bottom: 20px;
            }

            .banner-area h1 {
                color: #000000; /* Màu đen cho chữ Add Subject */
                font-size: 2rem;
            }

            .banner-area .btn {
                margin: 10px;
                padding: 10px 20px;
                font-size: 1rem;
                background-color: #5DADE2; /* Màu xanh nước biển nhạt */
                color: white;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }

            .banner-area .btn:hover {
                background-color: #85C1E9; /* Màu xanh nước biển sáng hơn cho hiệu ứng hover */
            }

            .add-subject-form {
                background-color: white;
                padding: 30px;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                max-width: 600px;
                width: 100%;
                text-align: left;
            }

            .form-group {
                margin-bottom: 20px;
            }

            .form-group label {
                display: block;
                margin-bottom: 8px;
                color: #333;
            }

            .form-control {
                width: 100%;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 4px;
                background-color: #f9f9f9;
                font-size: 1rem;
                color: #333;
            }

            .form-control:focus {
                border-color: #4A90E2; /* Màu xanh nước biển nhạt cho viền khi focus */
                outline: none;
            }

            .btn-primary {
                background-color: #4A90E2; /* Màu xanh nước biển nhạt */
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                width: 100%;
            }

            .btn-primary:hover {
                background-color: #5DADE2; /* Màu xanh nước biển sáng hơn cho hiệu ứng hover */
            }

            .error-message {
                color: red;
                text-align: center;
                margin-bottom: 15px;
            }
        </style>

        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <link rel="shortcut icon" href="img/fav.png" />
        <link href="https://fonts.googleapis.com/css?family=Playfair+Display:900|Roboto:400,400i,500,700" rel="stylesheet" />
    </head>

    <body>
        <c:if test="${sessionScope.user.role_id == '3'}">
        
        <div class="container">
            <section class="banner-area">
                <h1>Add Subject</h1>
                <a href="subjectlist"><input class="btn" type="submit" value="Back to Subject List"/></a>
                <a href="subjectmanage.jsp"><input class="btn" type="submit" value="Back to Subject Manage"/></a>
            </section>

            <h1 class="error-message">${mess}</h1>

            <form action="addsubject" method="post" class="add-subject-form">
                <div class="form-group">
                    <label for="subjectName">Name:</label>
                    <input value="${param.subjectname}" type="text" class="form-control" id="subjectName" name="subjectname" required>
                </div>

                <div class="form-group">
                    <label for="description">Description:</label>
                    <textarea class="form-control" id="description" name="description" required>${param.description}</textarea>
                </div>

                <div class="form-group">
                    <label for="image">Image:</label>
                    <input value="${param.image}" class="form-control" id="image" name="image" required>
                </div>

                <div class="form-group">
                    <label for="price">Price:</label>
                    <input placeholder="Price > 0" value="${param.price}" type="number" class="form-control" id="price" name="price" required>
                </div>

                <div class="form-group">
                    <label for="discount">Discount:</label>
                    <input placeholder="Discount need to 0->100%" value="${param.discount}" type="number" class="form-control" id="discount" name="discount" required>
                </div>

                <div class="form-group">
                    <label for="category">Category:</label>
                    <select class="form-control" id="category" name="category">
                        <c:forEach items="${listCate}" var="o">
                            <option value="${o.category_id}"> ${o.category_name} </option>
                        </c:forEach>
                    </select>
                </div>

                <button type="submit" class="btn btn-primary">ADD</button>
            </form>
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
