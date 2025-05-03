<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Course List</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/themify-icons/0.1.2/css/themify-icons.css" />
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">

        <style>
            .card-container {
                display: grid;
                grid-template-columns: repeat(3, 1fr); /* 3 cột */
                gap: 20px;
                justify-content: center; /* Căn giữa container */
                margin: 20px auto; /* Căn giữa trang với khoảng cách đều hai bên */
                max-width: 1200px; /* Đặt chiều rộng tối đa cho container để không kéo dài quá nhiều */
            }

            /* Thiết kế cho từng card */
            .card {
                background-color: #fff;
                border: 1px solid #ddd;
                border-radius: 8px;
                overflow: hidden;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                transition: transform 0.2s;
                text-align: center; /* Căn giữa nội dung văn bản */
                display: flex; /* Sử dụng flex để căn giữa nội dung */
                flex-direction: column; /* Sắp xếp các phần tử theo chiều dọc */
                justify-content: space-between; /* Căn đều khoảng cách giữa các phần tử */
                height: 100%; /* Đảm bảo thẻ chiếm toàn bộ chiều cao */
            }

            /* Hiệu ứng hover cho card */
            .card:hover {
                transform: translateY(-5px);
            }

            .card img {
                width: 100%;
                height: 370px;
                object-fit: cover;
            }

            /* Phần nội dung trong card */
            .card-content {
                padding: 15px;
                display: flex;
                flex-direction: column;
                align-items: center;
            }

            /* Tiêu đề của card */
            .card h3 {
                font-size: 18px;
                margin: 0 0 10px;
                color: #333;
            }

            /* Mô tả của card */
            .card p {
                font-size: 14px;
                color: #666;
                margin: 10px 0;
            }

            /* Giá của khóa học */
            .card .price {
                font-size: 16px;
                font-weight: bold;
                color: #ff5722;
                margin-top: 10px;
            }


            .pagination {
                display: flex; /* Sử dụng Flexbox để dễ dàng căn giữa */
                justify-content: center; /* Căn giữa các nút phân trang */
                margin-top: 20px;
            }

            .pagination a {
                display: inline-block;
                padding: 8px 16px;
                margin: 4px;
                background-color: #ddd;
                text-decoration: none;
                color: black;
                border-radius: 4px;
            }

            .pagination a:hover {
                background-color: #bbb;
            }

            h1 {

                text-align: center;
            }
            .search-container {
                display: flex; /* Đặt cả input và nút search trên cùng một hàng */
                justify-content: center; /* Căn giữa các phần tử trong container */
                gap: 10px; /* Khoảng cách giữa ô input và nút search */
                margin-bottom: 20px; /* Khoảng cách dưới của form */
            }

            /* Điều chỉnh chiều rộng của input */
            .search-container input {
                flex-grow: 1; /* Để input chiếm nhiều không gian nhất có thể */
                max-width: 300px; /* Giới hạn chiều rộng tối đa */
            }

            /* Điều chỉnh nút search */
            .search-container button {
                white-space: nowrap; /* Đảm bảo nút không bị xuống dòng */
            }
        </style>

    </head>
    <jsp:include page="header.jsp" />
    <body>

    </div>

</nav>


<h1 style="margin-top: 30px; margin-bottom: 20px">Course List</h1>
<!-- Courses Start -->
<div class="inner-wrap">
    <div class="row">
        <div class="col-12" style="margin-bottom: 16px">
            <form class="form-inline" style="float: right; display: flex" action="course" method="get">
                <input class="form-control mr-sm-2" type="search" placeholder="Search by username" aria-label="Search" name="name" value="${name}">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>
        </div>
    </div>
    <div class="row">
        <c:if test="${not empty data_course}">
            <c:forEach items="${data_course}" var="course">
                <div class="col-xl-6 col-lg-6 col-md-12 col-sm-12 mb-4">
                    <div class='inner-box'>
                        <div class="card shadow-sm">
                            <img src="img/coursera.png" class="card-img-top" alt="Image Course">
                            <div class="card-body">
                                <h5 class="value text-primary mb-3">${course.getCourse_name()}</h5>
                                <div class="card-text mb-2">
                                    <div class="inner-head">
                                        <i class="fa-solid fa-audio-description"></i>
                                        <span class="label">Description: </span>
                                    </div>
                                    <span class="value">${course.getDescription()}</span>
                                </div>
                                <div class="card-text mb-2">
                                    <div class="inner-head">
                                        <i class="fa-solid fa-book"></i>
                                        <span class="label">Subject:</span>
                                    </div>
                                    <span class="value">${course.getSubject_name()}</span>
                                </div>
                                <div class="card-text mb-2">
                                    <div class="inner-head">
                                        <i class="fa-solid fa-user-graduate"></i>
                                        <span>Lecturer</span>
                                    </div>
                                    <span class="value">${account.getFullname()}</span>
                                </div>
                                <div class="card-text mb-2">
                                    <div class="inner-head">
                                        <i class="ti-file"></i>
                                        <span class="label">Course No:</span>
                                    </div>
                                    <span class="value">${course.getCourse_no()}</span>
                                </div>
                                <a href="course-details?subject_id=${subject_id}&course_id=${course.getCourse_id()}" class="inner-button">
                                    <button class="btn btn-success">Join Now</button>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </c:if>
        <c:if test="${empty data_course}">
            <div class="col-12">
                <p>No courses found.</p>
            </div>
        </c:if>
    </div>
</div>
</div>
<!-- Courses End -->
</body>
</html>
