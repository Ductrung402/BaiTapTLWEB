//package murach.controllers;
//
//import java.io.IOException;
//import java.sql.*;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import murach.data.ConnectionPool;
//import murach.data.SQLUtil;
//
//@WebServlet("/sqlGateway")
//public class SqlGatewayServlet extends HttpServlet {
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        
//        String sqlStatement = request.getParameter("sqlStatement");
//        String sqlResult = "";
//
//        try {
//            // Lấy kết nối từ Pool (giống hệt bài 12-1)
//            ConnectionPool pool = ConnectionPool.getInstance();
//            Connection connection = pool.getConnection();
//            
//            // Lần này ta dùng Statement (không phải PreparedStatement) vì câu lệnh SQL là linh hoạt do người dùng gõ
//            Statement statement = connection.createStatement();
//            
//            // Xóa khoảng trắng thừa và cắt lấy chữ đầu tiên xem là SELECT hay lệnh khác
//            sqlStatement = sqlStatement.trim();
//            if (sqlStatement.length() >= 6) {
//                String sqlType = sqlStatement.substring(0, 6).toUpperCase();
//                
//                if (sqlType.equals("SELECT")) {
//                    // Xử lý lệnh SELECT (Trả về dạng Bảng)
//                    ResultSet resultSet = statement.executeQuery(sqlStatement);
//                    sqlResult = SQLUtil.getHtmlTable(resultSet);
//                    resultSet.close();
//                } else {
//                    // Xử lý lệnh INSERT, UPDATE, DELETE (Trả về số hàng bị thay đổi)
//                    int i = statement.executeUpdate(sqlStatement);
//                    if (i == 0) {
//                        sqlResult = "<p>The statement executed successfully. No rows were affected.</p>";
//                    } else {
//                        sqlResult = "<p>" + i + " row(s) affected.</p>";
//                    }
//                }
//            }
//            statement.close();
//            pool.freeConnection(connection);
//            
//        } catch (SQLException e) {
//            // Nếu người dùng gõ sai cú pháp SQL, in lỗi ra màn hình cho họ thấy
//            sqlResult = "<p style='color:red;'>Error executing the SQL statement: <br>" 
//                      + e.getMessage() + "</p>";
//        }
//
//        // Lưu dữ liệu vào request để in ra màn hình
//        request.setAttribute("sqlResult", sqlResult);
//        request.setAttribute("sqlStatement", sqlStatement);
//
//        String url = "/AdminIndex.jsp";
//        getServletContext().getRequestDispatcher(url).forward(request, response);
//    }
//}