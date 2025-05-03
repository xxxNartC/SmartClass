<%-- 
    Document   : sub_infor
    
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Subject Information</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f8f8f8;
            }

            .container {
                width: 80%;
                margin: 20px auto;
                background-color: #fff;
                padding: 20px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }

            .subject-info {
                display: flex;
                margin-bottom: 20px;
            }

            .subject-image {
                width: 300px;
                height: 300px;
                background-color: lightgray;
                text-align: center;
                line-height: 300px;
                font-size: 20px;
                color: #888;
            }

            .subject-details {
                margin-left: 20px;
                flex-grow: 1;
            }

            .subject-name {
                font-size: 28px;
                font-weight: bold;
                margin-bottom: 10px;
            }

            .favorites-count {
                margin-bottom: 10px;
            }

            .price {
                color: red;
                font-size: 24px;
                margin-bottom: 10px;
            }

            .discount {
                font-size: 14px;
                color: gray;
                text-decoration: line-through;
            }

            .created-date {
                font-size: 14px;
                color: #666;
                margin-bottom: 20px;
            }

            .add-to-cart button {
                background-color: #28a745;
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }

            .add-to-cart button:hover {
                background-color: #218838;
            }

            .description {
                margin-top: 20px;
            }

            .message {
                margin-top: 10px;
                font-size: 14px;
            }

            .success {
                color: green;
            }

            .error {
                color: red;
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp" />

        <div class="container">
            <div class="subject-info">
                <div class="subject-image">
                    <img src="${subject.image}" alt="Hình ảnh môn học" width="300" height="300">
                </div>
                <div class="subject-details">
                    <div class="subject-name">${subject.subject_name}</div>
                    <div class="favorites-count">Lượt yêu thích: ${subject.favorites_count}</div>
                    <div class="price">${subject.price}đ 
                        <span class="discount">${subject.discount}đ</span>
                    </div>
                    <div class="created-date">Ngày tạo: ${subject.created_date}</div>

                    <!-- Hiển thị thông báo đã mua nếu đã đăng nhập và đã mua -->
                    <c:choose>
                        <c:when test="${purchased}">
                            <div class="message success">
                                Bạn đã mua môn học này.
                            </div>
                        </c:when>
                        <c:otherwise>
                            <!-- Hiển thị nút Thêm vào giỏ hoặc yêu cầu đăng nhập nếu chưa đăng nhập -->
                            <div class="add-to-cart">
                                <form name="f" action="buy" method="post">
                                    <input type="hidden" value="${subject.subject_id}" name="id">
                                    <input type="hidden" name="source" value="sub_infor">
                                    <c:choose>
                                        <c:when test="${loggedIn}">
                                            <!-- Hiển thị nút Thêm vào giỏ nếu đã đăng nhập -->
                                            <button class="btn btn-primary add-to-cart" type="submit" onclick="show()" data-id="${subject.subject_id}">
                                                Thêm vào giỏ hàng
                                            </button>
                                        </c:when>
                                        <c:otherwise>
                                            <!-- Hiển thị yêu cầu đăng nhập nếu chưa đăng nhập -->
                                            <button class="btn btn-primary add-to-cart" type="button" onclick="promptLogin()">
                                                Thêm vào giỏ hàng
                                            </button>
                                        </c:otherwise>
                                    </c:choose>
                                </form>
                            </div>
                        </c:otherwise>
                    </c:choose>

                    <!-- Nút Xem Khóa học -->
                    <c:choose>
                        <c:when test="${purchased}">
                            <!-- Nếu khóa học đã được mua, liên kết đến trang xem khóa học -->
                            <a href="course?subject_id=${subject.subject_id}" class="btn btn-info">Xem Khóa học</a>
                        </c:when>
                        <c:otherwise>
                            <!-- Nếu khóa học chưa được mua, liên kết đến trang CourseNotBuy.jsp -->
                            <a href="notbuy?subject_id=${subject.subject_id}" class="btn btn-info">Xem Khóa học</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>


            <div class="description">
                <h3>Description:</h3>
                <p>${subject.description}</p>
            </div>
        </div>
        <script type="text/javascript">
            function show() {
                window.alert("Added to cart successfully!");
            }
        </script>

        <script type="text/javascript">
            function show() {
                window.alert("Add to card successfully!!!");
            }
        </script>
        <script>
            // Function to get the cart from the cookie
            function getCart() {
                const cookie = document.cookie.split('; ').find(row => row.startsWith('cart='));
                if (!cookie)
                    return [];

                const cartValue = cookie.split('=')[1]; // Example: "1:1/2:1/1:2"
                return cartValue.split('/').map(item => {
                    const [id, quantity] = item.split(':');
                    return {id: id.trim(), quantity: parseInt(quantity.trim(), 10)};
                });
            }

            // Function to update the "Add to Cart" button state
            function updateButtonState() {
                const cart = getCart(); // [{ id: '1', quantity: 1 }, { id: '2', quantity: 1 }]
                document.querySelectorAll('.add-to-cart').forEach(button => {
                    const productId = button.getAttribute('data-id');
                    const isAdded = cart.some(item => item.id === productId);

                    if (isAdded) {
                        button.textContent = 'Added';
                        button.disabled = true;
                    }
                });
            }

            // Call updateButtonState on page load
            document.addEventListener('DOMContentLoaded', updateButtonState);
        </script>
    </body>
</html>

