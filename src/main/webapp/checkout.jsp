<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Check Out - Order Summary</title>
    <link rel="stylesheet" href="styles/main.css" type="text/css"/>
</head>
<body>

    <h1>Order Summary</h1>

    <p>Thank you for your order! Here are your order details:</p>

    <!-- Biến tích lũy tổng giá trị đơn hàng -->
    <c:set var="grandTotal" value="0" />

    <table>
        <thead>
            <tr>
                <th>Product Description</th>
                <th style="text-align: center;">Quantity</th>
                <th class="right">Price</th>
                <th class="right">Amount</th>
            </tr>
        </thead>
        <tbody>
            <!-- Duyệt từng sản phẩm trong giỏ hàng -->
            <c:forEach var="item" items="${sessionScope.cart.items}">
                <c:set var="grandTotal" value="${grandTotal + item.total}" />
                <tr>
                    <td>${item.product.description}</td>
                    <td style="text-align: center;">${item.quantity}</td>
                    <td class="right">${item.product.priceCurrencyFormat}</td>
                    <td class="right">${item.totalCurrencyFormat}</td>
                </tr>
            </c:forEach>
        </tbody>
        <tfoot>
            <!-- Dòng tổng giá của tất cả các sản phẩm -->
            <tr style="border-top: 2px solid #008080; font-weight: bold; background-color: #eef7f7;">
                <td colspan="3" style="text-align: right; text-transform: uppercase;">Total:</td>
                <td class="right" style="color: #c0392b; font-size: 15px;">
                    <fmt:setLocale value="en_US" />
                    <fmt:formatNumber value="${grandTotal}" type="currency" />
                </td>
            </tr>
        </tfoot>
    </table>

    <br>
    <form action="cart.jsp" method="get">
        <input type="submit" value="Return to Cart">
    </form>

</body>
</html>