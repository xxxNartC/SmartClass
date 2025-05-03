<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Pay</title>

        <!-- Liên kết các style cần thiết -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

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

            .total {
                font-size: 18px;
                font-weight: bold;
                text-align: right;
                padding: 10px;
                margin-bottom: 40px;
            }

            .payment-section {
                display: flex;
                justify-content: space-between;
                align-items: flex-start;
                margin-bottom: 40px;
            }

            .payment-instructions {
                width: 60%;
                font-size: 18px;
                color: #000;
            }

            .payment-instructions h3 {
                color: #0056b3; /* Màu xanh dương đậm cho tiêu đề */
                font-size: 20px; /* Kích thước lớn hơn cho tiêu đề */
                font-weight: bold;
            }

            .bank-info {
                margin-top: 20px;
                font-weight: bold;
                line-height: 1.8em; /* Lùi dòng dưới dòng chủ tài khoản */
            }

            .bank-info p {
                color: #000; /* Màu đen cho thông tin tài khoản */
            }

            .qr-code {
                text-align: center;
                width: 30%;
                margin-right: 140px;
            }

            .qr-code img {
                width: 400px;
                height: 400px;
            }

            /* Kích thước lớn hơn cho nội dung chuyển khoản */
            .transfer-content {
                font-weight: bold;
                color: #0056b3;
                font-size: 20px; /* Cỡ chữ ngang với dòng "Hướng dẫn thanh toán" */
            }

            .transfer-details {
                color: red;
                font-size: 18px; /* Cỡ chữ ngang với các bước thanh toán */
                margin-top: 10px;
            }

            .complete-btn {
                display: block;
                width: 150px;
                padding: 10px;
                background-color: #28a745;
                color: white;
                text-align: center;
                text-decoration: none;
                border-radius: 5px;
                margin: 20px auto;
                font-size: 18px;
            }

            .complete-btn:hover {
                background-color: #218838;
            }

            /* Dòng phân cách */
            hr {
                border: none;
                border-top: 2px solid #ccc;
                margin: 20px 0;
            }

            .note {
                color: red;
                text-align: center;
                margin-top: 20px;
                font-size: 14px;
            }

            .footer-note {
                text-align: center;
                font-size: 12px;
                color: #333;
            }
        </style>
    </head>
    <body>

        <!-- Include phần menu giống với sub_infor.jsp -->
        <jsp:include page="header.jsp" />

        <div class="container">
            <h1>Đơn Hàng</h1>
            <table>
                <thead>
                    <tr>
                        <th>Mã Đơn Hàng</th>
                        <th>Tên Subject</th>
                        <th>Giá</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${cart}" var="item" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>${item.subjectId}</td> <!-- Hiển thị ID hoặc tên khóa học -->
                            <td>${item.price}</td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty cart}">
                        <tr>
                            <td colspan="3">Không có đơn hàng.</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>

            <!-- Dòng phân cách -->
            <hr />

            <div class="total">
                Tổng: <c:out value="${cartTotal}" /> đ
            </div>

            <!-- Phần chứa hướng dẫn thanh toán và mã QR hiển thị song song -->
            <div class="payment-section">
                <div class="payment-instructions">
                    <h3>Hướng dẫn thanh toán:</h3>
                    <p>Bước 1: Mở ứng dụng thanh toán</p>
                    <p>Bước 2: Chọn thanh toán và quét mã QR</p>
                    <p>Bước 3: Nhập đúng số tiền cần thanh toán</p>
                    <p>Bước 4: Hoàn thành các bước thanh toán theo hướng dẫn</p>

                    <div class="bank-info">
                        <p>Chủ tài khoản: LUU BACH TUNG</p>
                        <p>STK: 0000 0111726</p>
                        <p>Ngân hàng: TPBank</p>
                    </div>

                    <div class="transfer-content">
                        <p>Nội dung chuyển khoản:</p>
                    </div>

                    <div class="transfer-details">
                        <p>Mã Đơn Hàng_chuyen khoan</p> <!-- Màu đỏ và cỡ chữ ngang với các bước -->
                    </div>
                </div>

                <div class="qr-code">
                    <img src="img/qr_code.jpg" alt="QR Code"> <!-- Mã QR giả định -->
                </div>
            </div>

            <a href="#" onclick="document.getElementById('completeForm').submit();" class="complete-btn">Hoàn Thành</a>

            <form id="completeForm" action="completeOrder" method="POST" style="display: none;">
                <!-- Bạn có thể thêm các thông tin cần thiết tại đây nếu muốn gửi dữ liệu -->
            </form>


            <div class="note">
                **Lưu ý: Sau khi thanh toán thành công hãy ấn vào nút "Hoàn Thành" và xin bạn chờ đợi để hệ thống xử lý đơn hàng.**
            </div>

            <div class="footer-note">
                **Nếu có bất kỳ thắc mắc hay lỗi trong quá trình thanh toán xin hãy liên hệ với chúng tôi qua email: esmartlearnisp@gmail.com hoặc số điện thoại 0353362487.**
            </div>
        </div>

    </body>
</html>
