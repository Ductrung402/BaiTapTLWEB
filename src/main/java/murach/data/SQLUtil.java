package murach.data;

import java.sql.*;

public class SQLUtil {

    public static String getHtmlTable(ResultSet results) throws SQLException {
        StringBuilder htmlTable = new StringBuilder();
        
        // ResultSetMetaData chứa thông tin về cấu trúc của bảng (số cột, tên cột)
        ResultSetMetaData metaData = results.getMetaData();
        int columnCount = metaData.getColumnCount();

        htmlTable.append("<table border='1' cellpadding='5'>");
        
        // 1. Tạo hàng tiêu đề (Headers) chứa tên các cột
        htmlTable.append("<tr>");
        for (int i = 1; i <= columnCount; i++) {
            htmlTable.append("<th>").append(metaData.getColumnName(i)).append("</th>");
        }
        htmlTable.append("</tr>");

        // 2. Lặp qua từng hàng dữ liệu để in ra
        while (results.next()) {
            htmlTable.append("<tr>");
            for (int i = 1; i <= columnCount; i++) {
                htmlTable.append("<td>").append(results.getString(i)).append("</td>");
            }
            htmlTable.append("</tr>");
        }
        
        htmlTable.append("</table>");
        return htmlTable.toString();
    }
}