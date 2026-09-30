<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/includes/header.html" %>

    <h1>Downloads</h1>
    <!-- Thay thế tiêu đề cứng bằng EL lấy từ sessionScope.product -->
<h2>${sessionScope.product.description}</h2>

<p>Hi ${sessionScope.user.firstName}, here are your download links:</p>

<!-- Trong đường dẫn tải link MP3, lấy product.code -->
<a href="/musicStore/sound/${sessionScope.product.code}/filter.mp3">MP3</a>

    <p><a href="download?action=viewAlbums">View list of albums</a></p>
    <p><a href="download?action=viewCookies">View All Cookies</a></p>

<%@ include file="/includes/footer.jsp" %>