<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Subject</title>

        <style>
            /* CSS cho form chỉnh sửa môn học */
            body {
                background-color: #f4f4f4; /* Màu nền giống ảnh mẫu */
                font-family: 'Roboto', sans-serif; /* Font chữ đồng nhất */
            }

            .container {
                max-width: 600px;
                margin: 25px auto;
                background: #fff; /* Màu nền trắng cho form */
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            }

            h1 {
                font-size: 24px;
                font-weight: bold;
                text-align: center;
                margin-bottom: 20px;
                color: #333;
            }

            .form-group {
                margin-bottom: 15px;
            }

            .form-group label {
                display: block;
                font-weight: 500;
                margin-bottom: 5px;
                color: #333; /* Màu chữ đậm cho nhãn */
            }

            .form-control {
                width: 100%;
                padding: 10px;
                font-size: 16px;
                border: 1px solid #ddd;
                border-radius: 4px;
                background-color: #f9f9f9; /* Màu nền nhạt cho input */
                box-shadow: none;
            }

            .form-control:focus {
                border-color: #9c27b0; /* Màu viền tím khi focus */
                outline: none;
                box-shadow: 0 0 0 2px rgba(156, 39, 176, 0.2); /* Hiệu ứng focus nhẹ */
            }

            .btn {
                background-color: #42a5f5; /* Màu xanh nổi bật cho nút */
                color: #fff;
                border: none;
                padding: 10px 20px;
                border-radius: 4px;
                font-size: 16px;
                cursor: pointer;
                text-align: center;
                display: inline-block;
                margin-top: 10px;
            }

            .btn:hover {
                background-color: #1e88e5; /* Màu xanh đậm hơn khi hover */
            }

            .banner-area {
                background-color: #f4f4f4; /* Màu nền banner */
                padding: 20px 0;
                margin-bottom: 20px;
            }

            .banner-right {
                text-align: center;
            }

            .banner-right .btn {
                margin: 0 5px;
                background-color: #42a5f5;
                border: none;
            }

            .banner-right .btn:hover {
                background-color: #1e88e5;
            }

            .message {
                text-align: center;
                color: red;
                margin-bottom: 20px;
            }
        </style>

        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:400,500,700" />
    </head>

    <body>
        <section class="banner-area">
            <div class="container">
                <div class="row justify-content-center align-items-center">
                    <div class="col-lg-12 banner-right">
                        <h1 class="text-dark">
                            Edit Subject
                        </h1>
                        <a href="subjectlist" class="btn">Back to Subject List</a>
                        <a href="subjectmanage.jsp" class="btn">Back to Subject Manage</a>
                    </div>
                </div>
            </div>
        </section>

        <div class="container">
            <h1>Edit Subject</h1>
            <h1 class="message">${mess}</h1>
            <c:if test="${checkEnroll == false}">
                <form action="editsubject" method="post" class="edit-subject-form">
                    <input type="hidden" name="sid" value="${subject.subject_id}"/>
                    <div class="form-group">
                        <label for="subjectName">Name:</label>
                        <input value="${subject.subject_name}" type="text" class="form-control" id="subjectName" name="subjectname" required>
                    </div>
                    <div class="form-group">
                        <label for="description">Description:</label>
                        <textarea class="form-control" id="description" name="description" required>${subject.description}</textarea>
                    </div>
                    <div class="form-group">
                        <label for="image">Image :</label>
                        <input value="${subject.image}" class="form-control" id="image" name="image" required>
                    </div>
                    <div class="form-group">
                        <label for="price">Price:</label>
                        <input placeholder="Price > 0" value="${subject.price}" type="number" class="form-control" id="price" name="price" required>
                    </div>
                    <div class="form-group">
                        <label for="discount">Discount: </label>
                        <input placeholder="Discount needs to be 0-100%" value="${subject.discount}" type="number" class="form-control" id="discount" name="discount" required>
                    </div>
                    <div class="form-group">
                        <label for="category">Category:</label>
                        <select class="form-control" id="category" name="category">
                            <c:forEach items="${listCate}" var="o">
                                <option ${o.category_id == subject.cate.category_id ? 'selected' : ''} value="${o.category_id}">${o.category_name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <button type="submit" class="btn">Edit Subject</button>
                </form>
            </c:if>
        </div>
    </body>
</html>
