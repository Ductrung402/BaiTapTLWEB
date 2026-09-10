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

    <h1>Cookies</h1>
    <p>Here's a list of the cookies for this session:</p>

    <table>
        <tr>
            <th>Cookie Name</th>
            <th>Cookie Value</th>
        </tr>
  
        <c:forEach var="c" items="${cookie}">
            <tr>
                <td>${c.value.name}</td>
                <td>${c.value.value}</td>
            </tr>
        </c:forEach>
    </table>

    <p><a href="download?action=deleteCookies">Delete all persistent cookies</a></p>
    <p><a href="download?action=viewAlbums">Return to album list</a></p>

</body>
</html>