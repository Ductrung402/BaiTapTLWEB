<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Murach's Java Servlets and JSP</title>
    <link rel="stylesheet" href="styles/main.css" type="text/css"/>
</head>
<body>

    <h1>Your Cart</h1>

    <table>
        <tr>
            <th>Quantity</th>
            <th>Description</th>
            <th>Price</th>
            <th>Amount</th>
            <th>&nbsp;</th>
        </tr>

        <c:forEach var="item" items="${cart.items}">
            <tr>
                <td>
                    <!-- Nút Update vẫn giữ nguyên method POST để so sánh với URL rewriting -->
                    <form action="cart" method="post">
                        <input type="hidden" name="action" value="cart">
                        <input type="hidden" name="productCode" value="${item.product.code}">
                        <input type="text" name="quantity" value="${item.quantity}" size="2">
                        <input type="submit" value="Update">
                    </form>
                </td>
                <td>${item.product.description}</td>
                <td>${item.product.priceCurrencyFormat}</td>
                <td>${item.totalCurrencyFormat}</td>
                <td>
                    <!-- BƯỚC 3: Dùng URL Rewriting thay cho form & hidden fields -->
                    <a href="cart?action=cart&amp;productCode=${item.product.code}&amp;quantity=0">Remove Item</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <p><b>To change the quantity</b>, enter the new quantity and click the Update button.</p>

    <form action="cart" method="post">
        <input type="hidden" name="action" value="shop">
        <input type="submit" value="Continue Shopping">
    </form>

    <form action="cart" method="post">
        <input type="hidden" name="action" value="checkout">
        <input type="submit" value="Check Out">
    </form>

</body>
</html>