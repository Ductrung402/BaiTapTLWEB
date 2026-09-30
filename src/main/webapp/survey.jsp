<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="/includes/header.html" %>

    <!-- Logo Murach -->
    <img src="images/Logo.jpg" alt="Murach Logo" width="100">

    <h1>Thanks for taking our survey!</h1>

    <p>Here is the information that you entered:</p>

    <div class="form-group">
        <label><b>Email:</b></label>
        <span>${user.email}</span>
    </div>
    
    <div class="form-group">
        <label><b>First Name:</b></label>
        <span>${user.firstName}</span>
    </div>
    
    <div class="form-group">
        <label><b>Last Name:</b></label>
        <span>${user.lastName}</span>
    </div>
    
    <div class="form-group">
        <label><b>Heard From:</b></label>
        <span>${user.heardFrom}</span>
    </div>
    
    <div class="form-group">
        <label><b>Updates:</b></label>
        <span>${user.wantsUpdates}</span>
    </div>

    <%-- Yêu cầu Bước 8: Dùng thẻ JSTL <c:if> để ẩn dòng Contact Via nếu wantsUpdates khác "Yes" --%>
    <c:if test="${user.wantsUpdates == 'Yes'}">
        <div class="form-group">
            <label><b>Contact Via:</b></label>
            <span>${user.contactVia}</span>
        </div>
    </c:if>

    <p>To enter another survey, click the Return button below.</p>

    <form action="index.html" method="get">
        <input type="submit" value="Return" id="submit">
    </form>

<%@ include file="/includes/footer.jsp" %>