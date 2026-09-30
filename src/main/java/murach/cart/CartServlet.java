<<<<<<< HEAD
package murach.cart;

import java.io.IOException;
import java.util.List; // Nhớ import thư viện List
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import murach.business.Cart;
import murach.business.LineItem;
import murach.business.Product;
import murach.data.ProductDB;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext sc = this.getServletContext();
        
        // Lấy action hiện tại
        String action = request.getParameter("action");
        if (action == null) {
            action = "shop"; // SỬA: Mặc định lần đầu vào web phải là trang danh sách sản phẩm (shop)
        }

        String url = "/cart_index.jsp";
        
        // 1. CÁC HÀNH ĐỘNG ĐIỀU HƯỚNG TRANG
        if (action.equals("shop")) {
            // THÊM: Lấy danh sách sản phẩm từ MySQL truyền sang cart_index.jsp
            List<Product> products = ProductDB.selectProducts();
            request.setAttribute("products", products);
            
            url = "/cart_index.jsp";
        } 
        else if (action.equals("cart")) {
            url = "/cart.jsp"; 
        } 
        else if (action.equals("checkout")) {
            url = "/checkout.jsp";
        } 
        
        // 2. CÁC HÀNH ĐỘNG THAY ĐỔI DỮ LIỆU GIỎ HÀNG (THÊM, SỬA, XÓA)
        else if (action.equals("add") || action.equals("update") || action.equals("remove")) {
            String productCode = request.getParameter("productCode");
            String quantityString = request.getParameter("quantity");

            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null) {
                cart = new Cart();
            }

            int quantity;
            try {
                quantity = Integer.parseInt(quantityString);
                if (quantity < 0) {
                    quantity = 1;
                }
            } catch (NumberFormatException nfe) {
                quantity = 1;
            }

            Product product = ProductDB.getProduct(productCode);

            LineItem lineItem = new LineItem();
            lineItem.setProduct(product);
            lineItem.setQuantity(quantity);

            if (action.equals("add")) {
                cart.addItem(lineItem);    
            } else if (action.equals("update")) {
                if (quantity > 0) {
                    cart.updateItem(lineItem); 
                } else {
                    cart.removeItem(lineItem); 
                }
            } else if (action.equals("remove")) {
                cart.removeItem(lineItem);
            }

            session.setAttribute("cart", cart);
            url = "/cart.jsp";
        }

        sc.getRequestDispatcher(url).forward(request, response);
    }
=======
package murach.cart;

import java.io.IOException;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import murach.business.Cart;
import murach.business.LineItem;
import murach.business.Product;
import murach.data.ProductIO;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    // BƯỚC 5: Hỗ trợ GET bằng cách chuyển tiếp trực tiếp sang doPost
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext sc = this.getServletContext();
        
        // Lấy action hiện tại
        String action = request.getParameter("action");
        if (action == null) {
            action = "cart"; // Mặc định chuyển về trang cart
        }

        // Xử lý action
        String url = "/cart_index.jsp";
        if (action.equals("shop")) {
            url = "/cart_index.jsp"; // Quay lại trang mua sắm
        } else if (action.equals("cart")) {
            String productCode = request.getParameter("productCode");
            String quantityString = request.getParameter("quantity");

            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null) {
                cart = new Cart();
            }

            // Nếu tham số số lượng có tồn tại -> thêm/sửa/xóa sản phẩm
            int quantity;
            try {
                quantity = Integer.parseInt(quantityString);
                if (quantity < 0) {
                    quantity = 1;
                }
            } catch (NumberFormatException nfe) {
                quantity = 1;
            }

            String path = sc.getRealPath("/WEB-INF/products.txt");
            Product product = ProductIO.getProduct(productCode, path);

            LineItem lineItem = new LineItem();
            lineItem.setProduct(product);
            lineItem.setQuantity(quantity);

            if (quantity > 0) {
                cart.addItem(lineItem);
            } else if (quantity == 0) {
                cart.removeItem(lineItem);
            }

            session.setAttribute("cart", cart);
            url = "/cart.jsp";
        } else if (action.equals("checkout")) {
            url = "/checkout.jsp";
        }

        sc.getRequestDispatcher(url).forward(request, response);
    }
>>>>>>> b1afbd999bd42958f471e97f7700f611520e5d6a
}