<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Murach's Java Servlets and JSP</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 30px; }
        textarea { width: 100%; max-width: 500px; height: 100px; padding: 10px; }
        table { border-collapse: collapse; margin-top: 20px; }
        th { background-color: #f2f2f2; }
    </style>
</head>
<body>
    <h1>The SQL Gateway</h1>
    <p>Enter an SQL statement and click Execute.</p>
    
    <form action="sqlGateway" method="post">
        <!-- Nếu form bị lỗi và load lại, nó sẽ giữ nguyên câu lệnh cũ mà người dùng đã gõ -->
        <textarea name="sqlStatement" required>${sqlStatement != null ? sqlStatement : 'SELECT * FROM User'}</textarea>
        <br><br>
        <input type="submit" value="Execute">
    </form>
    
    <br>
    <h2>SQL Result:</h2>
    <!-- Khu vực hiển thị bảng HTML hoặc dòng thông báo (lỗi/thành công) -->
    <div>
        ${sqlResult}
    </div>
</body>
</html>