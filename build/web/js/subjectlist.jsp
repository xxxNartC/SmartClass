<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Subject List</title>

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
<h1>Subject List</h1>

<!-- Form tìm kiếm -->
<form action="subjectlist" method="post" class="search-form">
    <div class="search-container">
        <input type="text" class="form-control" id="search_subject" name="search_name" placeholder="Search here">
        <button type="submit" class="btn btn-primary">Search</button>
    </div>
</form>

<!-- Display success message if available -->
<c:if test="${not empty sessionScope.successMessage}">
    <div class="alert alert-success" role="alert">
        ${sessionScope.successMessage}
        <c:remove var="successMessage" scope="session" />
    </div>
</c:if>

<div class="card-container">

    <c:forEach items="${listSub}" var="s">
        <div class="card">

            <img src="${s.image}" alt="Course image">
            <div class="card-content">

                <h3>${s.subject_name}</h3>

                <p>${s.description}</p>

                <div class="price">${s.price}đ</div>
            </div>
            <a href="course?subject_id=${s.subject_id}" class="inner-button">
                <button class="btn btn-success">View</button>
            </a>
            <c:if test="${sessionScope.account_id != null}">
                <form action="FavoriteSubject" method="post">
                    <input type="hidden" name="subject_id" value="${s.subject_id}">
                    <button type="submit" class="btn btn-primary">Add To My Favorite</button>
                </form>
            </c:if>
        </div>
    </c:forEach>
</div>

<!-- Phân trang -->
<div class="pagination">
    <c:forEach begin="1" end="${page}" var="i">
        <a href="subjectlist?index=${i}&search_name=${search_name}"> ${i} </a>
    </c:forEach>
</div>
</body>
</html>

