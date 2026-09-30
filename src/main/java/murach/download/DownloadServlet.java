package murach.download;

import java.io.IOException;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import murach.business.Product;
import murach.data.ProductIO;
import murach.business.User;
import murach.data.UserDB;        // Đổi từ UserIO sang UserDB
import murach.business.Download;  // Thêm Entity Download
import murach.data.DownloadDB;    // Thêm thao tác DB cho Download
import murach.util.CookieUtil;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String action = request.getParameter("action");
        if (action == null) {
            action = "viewAlbums";
        }

        String url = "/index.jsp";
        if (action.equals("viewAlbums")) {
            url = "/index.jsp";
        } else if (action.equals("checkUser")) {
            url = checkUser(request, response);
        } else if (action.equals("viewCookies")) {
            url = "/viewCookies.jsp";
        } else if (action.equals("deleteCookies")) {
            url = deleteCookies(request, response);
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String action = request.getParameter("action");
        String url = "/index.jsp";

        if (action.equals("registerUser")) {
            url = registerUser(request, response);
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    private String checkUser(HttpServletRequest request, HttpServletResponse response) {
        String productCode = request.getParameter("productCode");
        HttpSession session = request.getSession();

        // Lấy Product từ file và lưu vào Session
        ServletContext sc = this.getServletContext();
        String productPath = sc.getRealPath("/WEB-INF/products.txt");
        Product product = ProductIO.getProduct(productCode, productPath);
        session.setAttribute("product", product);

        User user = (User) session.getAttribute("user");
        String url;

        // Nếu Session chưa có user, kiểm tra Cookie
        if (user == null) {
            Cookie[] cookies = request.getCookies();
            String emailAddress = CookieUtil.getCookieValue(cookies, "userEmail");

            if (emailAddress == null || emailAddress.isEmpty()) {
                url = "/register.jsp";
            } else {
                // Dùng JPA (UserDB) thay vì UserIO đọc file txt
                user = UserDB.selectUser(emailAddress);
                
                if (user != null) {
                    session.setAttribute("user", user);
                    url = "/" + productCode + "_download.jsp";
                } else {
                    url = "/register.jsp"; // Cookie có email nhưng DB không có
                }
            }
        } else {
            url = "/" + productCode + "_download.jsp";
        }
        
        // GHI NHẬN LƯỢT TẢI: Nếu user hợp lệ và chuẩn bị sang trang download
        if (url.endsWith("_download.jsp")) {
            Download download = new Download();
            download.setUser(user);
            download.setProductCode(productCode);
            DownloadDB.insert(download); // Gọi JPA lưu vào DB
        }

        return url;
    }

    private String registerUser(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        User user = new User();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        // Lưu vào Database bằng JPA (Chỉ insert nếu email chưa tồn tại để tránh lỗi trùng khóa chính)
        if (!UserDB.emailExists(email)) {
            UserDB.insert(user);
        }

        // Lưu vào Session
        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        // Tạo Cookie
        Cookie c = new Cookie("userEmail", email);
        c.setMaxAge(60 * 60 * 24 * 365 * 3);
        c.setPath("/");
        response.addCookie(c);

        // Lấy đối tượng Product từ session để dựng URL
        Product product = (Product) session.getAttribute("product");
        String productCode = product.getCode();
        String url = "/" + productCode + "_download.jsp";
        
        // GHI NHẬN LƯỢT TẢI: Khi đăng ký thành công và chuyển tới trang download
        Download download = new Download();
        download.setUser(user);
        download.setProductCode(productCode);
        DownloadDB.insert(download); // Gọi JPA lưu vào DB

        return url;
    }

    private String deleteCookies(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }
        HttpSession session = request.getSession();
        session.invalidate();
        return "/index.jsp";
    }
}