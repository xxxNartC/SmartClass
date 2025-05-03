<%-- 
    Document   : Cart
    
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
      <jsp:include page="header.jsp" />
    <head>
        <meta charset="utf-8">
        <title>Cart</title>
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
                background-color: #f4f4f4;
                font-weight: bold;
            }
            .total {
                font-size: 18px;
                font-weight: bold;
                text-align: right;
                padding: 10px;
            }
            .checkout-btn {
                display: block;
                width: 150px;
                padding: 10px;
                background-color: #28a745;
                color: white;
                text-align: center;
                text-decoration: none;
                border-radius: 5px;
                margin: 0 auto;
                font-size: 18px;
            }
            .checkout-btn:hover {
                background-color: #218838;
            }
            hr {
                border: none;
                border-top: 2px solid #ccc;
                margin: 20px 0;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Giỏ hàng</h1>
            <table>
                <thead>
                    <tr>
                        <th>STT</th>
                        <th>Tên khóa học</th>
                        <th>Giá</th>
                        <th>Discount</th>
                        <th>Thao tác</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listSubject.items}" var="item" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>${item.getSubject().getSubject_name()}</td>
                            <td>${item.price}</td>
                            <td>${item.getSubject().getDiscount()}%</td>
                            <td>
<!--                                <form action="removeFromCart" method="post">
                                    <input type="hidden" name="subject_id" value="${item.getSubject().getSubject_id()}">
                                    <button type="submit" style="background-color: #ff4d4d; border: none; padding: 5px 10px; color: white; cursor: pointer;">Xóa</button>
                                </form>-->
                                <form action="process" method="post">
                                        <input type="hidden" name="id" value="${item.getSubject().getSubject_id()}">
                                    <button type="submit" style="background-color: #ff4d4d; border: none; padding: 5px 10px; color: white; cursor: pointer;">Xóa</button>                  
                                </form> 
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty listSubject}">
                        <tr>
                            <td colspan="4">Giỏ hàng của bạn trống.</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
            <div class="total">
                Tổng: <c:out value="${totalMoney}" /> đ
            </div>
            <div style="display: flex; justify-content: space-around">
<!--                <a href="pay.jsp" class="checkout-btn">Thanh toán</a>-->
                <a href="checkout" class="checkout-btn">Thanh toán qua Vnpay</a>
            </div>
        </div>
    </body>
</html>
