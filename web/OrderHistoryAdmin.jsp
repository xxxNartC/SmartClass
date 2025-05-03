<%-- 
    Document   : OrderHistoryAdmin
    Created on : 14 thg 10, 2024, 22:42:14
    Author     : bacht
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Order History Admin</title>
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

            h1 {
                text-align: center;
                font-size: 36px;
                margin-bottom: 40px;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 20px;
                font-size: 16px;
                border: 1px solid #000;
            }

            th, td {
                border: 1px solid #ddd;
                padding: 10px;
                text-align: center;
            }

            th {
                background-color: #e8e8e8;
                font-weight: bold;
            }

            .filter-section {
                display: flex;
                justify-content: space-between;
                margin-bottom: 20px;
            }

            .filter-section input, .filter-section select {
                padding: 5px;
                font-size: 16px;
                margin-right: 10px;
            }

            .filter-section button {
                padding: 5px 15px;
                background-color: #007bff;
                color: white;
                border: none;
                cursor: pointer;
            }

            .filter-section button:hover {
                background-color: #0056b3;
            }
        </style>
    </head>
    <body>
        <!-- Include phần menu giống với Pay.jsp -->
        <jsp:include page="header.jsp" />

        <div class="container">
            <h1>Đơn Hàng</h1>

            <div class="filter-section">
                <div>
                    <label for="orderId">Mã đơn hàng:</label>
                    <input type="text" id="orderId" placeholder="Enter order ID">
                    <button type="button">Search</button>
                </div>
                <div>
                    <label for="status">Trạng thái:</label>
                    <select id="status">
                        <option value="all">Xác nhận vs Chờ Xác Nhận</option>
                    </select>
                    <label for="date">Thời gian:</label>
                    <input type="date" id="date">
                    <button type="button">Search</button>
                </div>
            </div>

            <table>
                <thead>
                    <tr>
                        <th>Mã Đơn Hàng</th>
                        <th>Người mua</th>
                        <th>Môn Học</th>
                        <th>Ngày Mua</th>
                        <th>Số tiền</th>
                        <th>Trạng thái</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="order" items="${orderList}">
                    <tr>
                        <td>${order.orderId}</td>
                        <td>${order.accountName}</td>
                        <td>${order.subjectName}</td>
                        <td>${order.orderDate}</td>
                        <td>${order.totalMoney}</td>
                        <td>${order.paymentStatus}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
