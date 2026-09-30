package murach.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import murach.business.Product;

public class ProductDB {

    // 1. Lấy một sản phẩm theo mã
    public static Product getProduct(String productCode) {
        // ĐÃ THÊM allowPublicKeyRetrieval=true VÀO ĐÂY
        String dbUrl = "jdbc:mysql://localhost:3306/murach?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String dbUser = "root";
        String dbPassword = "1234"; 

        String query = "SELECT ProductCode, ProductDescription, ProductPrice " +
                       "FROM Product " +
                       "WHERE ProductCode = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, productCode);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Product p = new Product();
                p.setCode(rs.getString("ProductCode"));
                p.setDescription(rs.getString("ProductDescription"));
                p.setPrice(rs.getDouble("ProductPrice"));
                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Nếu lỗi kết nối/SQL sẽ hiện ở đây
        }
        return null;
    }

    // 2. Lấy tất cả sản phẩm cho trang chủ
    public static List<Product> selectProducts() {
        String dbUrl = "jdbc:mysql://localhost:3306/murach?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String dbUser = "root";
        String dbPassword = "1234";

        String query = "SELECT ProductCode, ProductDescription, ProductPrice FROM Product";
        List<Product> products = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {

            while (rs.next()) {
                Product p = new Product();
                p.setCode(rs.getString("ProductCode"));
                p.setDescription(rs.getString("ProductDescription"));
                p.setPrice(rs.getDouble("ProductPrice"));
                products.add(p);
            }
            
            // In ra ngay để kiểm tra
            System.out.println(">>> SỐ LƯỢNG SẢN PHẨM LẤY ĐƯỢC TỪ DB: " + products.size());
            
        } catch (SQLException e) {
            e.printStackTrace(); // Nếu câu lệnh SQL lỗi, nó sẽ in đống lỗi đỏ ở đây thay vì dòng trên
        }
        
        return products;
    }
}