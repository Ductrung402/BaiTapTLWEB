<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/includes/header.jsp" />

    <h1>Download Registration</h1>
    <p>Please enter your name and email address to download our songs.</p>

    <form action="download" method="post">
        <input type="hidden" name="action" value="registerUser">
        
        <div class="form-group">
            <label>Email:</label>
            <input type="email" name="email" required>
        </div>
        <div class="form-group">
            <label>First Name:</label>
            <input type="text" name="firstName" required>
        </div>
        <div class="form-group">
            <label>Last Name:</label>
            <input type="text" name="lastName" required>
        </div>
        
        <input type="submit" value="Register" id="submit">
    </form>

<c:import url="/includes/footer.jsp" />