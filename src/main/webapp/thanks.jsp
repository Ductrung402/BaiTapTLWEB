<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Murach's Java Servlets and JSP</title>
    <link rel="stylesheet" href="styles/main.css" type="text/css"/>
</head>
<body>
    <h1>Thanks for joining our email list</h1>
    <p>Here is the information that you entered:</p>
    
    <!-- Sử dụng Expression Language (EL) để in thông tin từ đối tượng User -->
    <label>Email:</label>
    <span>${user.email}</span><br>
    
    <label>First Name:</label>
    <span>${user.firstName}</span><br>
    
    <label>Last Name:</label>
    <span>${user.lastName}</span><br>
    
    <p>To enter another email address, click on the link below:</p>
    <!-- Trở lại trang đăng ký -->
    <p><a href="EmailListIndex.jsp">Return and enter another email address</a></p>
</body>
</html>