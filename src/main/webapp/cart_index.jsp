<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!-- Gọi Header chung -->
<c:import url="/includes/header.jsp" />


    <h1>CD list</h1>

    <table>
        <tr>
            <th>Description</th>
            <th class="right">Price</th>
            <th>&nbsp;</th>
        </tr>

        
        <!-- Duyệt qua danh sách sản phẩm được truyền từ Servlet -->
        <c:forEach var="product" items="${products}">
            <tr>
                <td>${product.description}</td>
                <td class="right">${product.priceCurrencyFormat}</td>
                <td>
                    <form action="cart" method="post">
                        <input type="hidden" name="action" value="add">
                        <input type="hidden" name="productCode" value="${product.code}">
                        <input type="submit" value="Add To Cart">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

<!-- Gọi Footer chung -->
<c:import url="/includes/footer.jsp" />

