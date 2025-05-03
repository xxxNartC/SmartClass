<%-- 
    Document   : OrderHistory
    Created on : 14 thg 10, 2024, 22:27:58
    Author     : bacht
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="header.jsp" /> <!-- Thanh menu vẫn giữ nguyên vị trí -->
        <meta charset="utf-8">
        <title>Lịch Sử Đơn Hàng</title>
        <style>
            /* Căn chỉnh tổng thể */
            body {
                font-family: Arial, sans-serif;
                background-color: #f9f9f9;
                margin: 0;
            }

            /* Căn giữa tiêu đề */
            h1 {
                text-align: center;
                color: #333;
                margin-bottom: 20px;
            }

            /* Phần chứa bảng với khoảng lề 2 bên */
            .table-container {
                width: 90%; /* Bảng chiếm 90% độ rộng trang */
                margin: 20px auto; /* Căn giữa bảng và thêm khoảng cách bên trên */
                background-color: #fff;
                padding: 20px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                border-radius: 8px;
            }

            /* Thanh tìm kiếm và bộ lọc */
            .search-container {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 20px;
            }

            .search-container input[type="text"] {
                padding: 8px;
                width: 60%;
                border: 1px solid #ddd;
                border-radius: 4px;
            }

            .search-container select {
                padding: 8px;
                border: 1px solid #ddd;
                border-radius: 4px;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 10px;
            }

            th, td {
                border: 1px solid #ddd;
                padding: 10px;
                text-align: center;
            }

            th {
                background-color: #f2f2f2;
                color: #333;
            }

            /* Đổi màu cho cột trạng thái */
            .status-fail {
                color: #d9534f; /* Màu đỏ */
                font-weight: bold;
            }
            .status-pending {
                color: #f0ad4e; /* Màu vàng */
                font-weight: bold;
            }
            .status-success {
                color: #5cb85c; /* Màu xanh */
                font-weight: bold;
            }
        </style>
        <script>
            // Hàm lọc danh sách đơn hàng theo tên môn học và trạng thái
            function filterOrders() {
                const searchInput = document.getElementById('searchInput').value.toLowerCase();
                const statusFilter = document.getElementById('statusFilter').value;
                const rows = document.querySelectorAll('#orderTable tbody tr');

                rows.forEach(row => {
                    const subjectName = row.querySelector('.subject-name').textContent.toLowerCase();
                    const status = row.querySelector('.status').textContent.trim();

                    const matchesSearch = subjectName.includes(searchInput);
                    const matchesStatus = (statusFilter === 'all') || (status === statusFilter);

                    if (matchesSearch && matchesStatus) {
                        row.style.display = '';
                    } else {
                        row.style.display = 'none';
                    }
                });
            }
        </script>
    </head>
    <body>
        <div class="table-container">
            <h1>Danh Sách Đơn Hàng</h1>
            <!-- Thanh tìm kiếm và bộ lọc trạng thái -->
            <div class="search-container">
                <input type="text" id="searchInput" placeholder="Tìm kiếm tên môn học..." onkeyup="filterOrders()" />
                <select id="statusFilter" onchange="filterOrders()">
                    <option value="all">Tất cả trạng thái</option>
                    <option value="pending">Pending</option>
                    <option value="Fail">Fail</option>
                    <option value="Success">Success</option>
                </select>
            </div>

            <table id="orderTable">
                <thead>
                    <tr>
                        <th>Môn Học</th>
                        <th>Ngày Mua</th>
                        <th>Giá</th>
                        <th>Tổng Tiền</th>
                        <th>Trạng Thái</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="order" items="${orderList}">
                        <tr>
                            <td class="subject-name">${order.subjectName}</td>
                            <td>${order.orderDate}</td>
                            <td>${order.price}</td>
                            <td>${order.totalMoney}</td>
                            <td class="status">
                                <span class="<c:choose>
                                          <c:when test='${order.paymentStatus == "Fail"}'>status-fail</c:when>
                                          <c:when test='${order.paymentStatus == "pending"}'>status-pending</c:when>
                                          <c:when test='${order.paymentStatus == "Success"}'>status-success</c:when>
                                      </c:choose>">
                                    ${order.paymentStatus}
                                </span>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
