<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!-- Gọi Header -->
<c:import url="/includes/header.jsp" />

<h1>Your Cart</h1>

<c:choose>
    <c:when test="${empty cart.items}">
        <p>Giỏ hàng của bạn đang trống.</p>
    </c:when>
    
    <c:otherwise>
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
                        <form action="cart" method="post">
                            <input type="hidden" name="action" value="update">
                            <input type="hidden" name="productCode" value="${item.product.code}">
                            <input type="text" name="quantity" value="${item.quantity}" size="2">
                            <input type="submit" value="Update">
                        </form>
                    </td>
                    <td>${item.product.description}</td>
                    <td>${item.product.priceCurrencyFormat}</td>
                    <td>${item.totalCurrencyFormat}</td>
                    <td>
                        <c:url var="removeUrl" value="cart">
                            <c:param name="action" value="remove" />
                            <c:param name="productCode" value="${item.product.code}" />
                        </c:url>
                        <a href="${removeUrl}">Remove Item</a>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <p><b>To change the quantity</b>, enter the new quantity and click the Update button.</p>
    </c:otherwise>
</c:choose>

<form action="cart" method="post">
    <input type="hidden" name="action" value="shop">
    <input type="submit" value="Continue Shopping">
</form>

<c:if test="${not empty cart.items}">
    <form action="cart" method="post">
        <input type="hidden" name="action" value="checkout">
        <input type="submit" value="Check Out">
    </form>
</c:if>

<!-- Gọi Footer -->
<c:import url="/includes/footer.jsp" />