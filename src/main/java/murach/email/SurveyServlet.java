
//package murach.email;
//
//import java.io.IOException;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import murach.business.User;
//
//@WebServlet("/survey")
//public class SurveyServlet extends HttpServlet {
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        // 1. Lấy các tham số từ form request
//        String firstName = request.getParameter("firstName");
//        String lastName = request.getParameter("lastName");
//        String email = request.getParameter("email");
//        String heardFrom = request.getParameter("heardFrom");
//        String wantsUpdates = request.getParameter("wantsUpdates");
//        String contactVia = request.getParameter("contactVia");
//
//        // 2. Xử lý giá trị mặc định cho heardFrom và wantsUpdates theo chuẩn Murach
//        if (heardFrom == null) {
//            heardFrom = "NA";
//        }
//        if (wantsUpdates == null) {
//            wantsUpdates = "No";
//        } else {
//            wantsUpdates = "Yes";
//        }
//
//        // 3. Khởi tạo đối tượng User và gán dữ liệu
//        User user = new User();
//        user.setFirstName(firstName);
//        user.setLastName(lastName);
//        user.setEmail(email);
//        user.setHeardFrom(heardFrom);
//        user.setWantsUpdates(wantsUpdates);
//        user.setContactVia(contactVia);
//
//        // 4. Lưu User vào request scope
//        request.setAttribute("user", user);
//
//        // 5. Chuyển tiếp (forward) sang trang survey.jsp
//        String url = "/survey.jsp";
//        getServletContext()
//                .getRequestDispatcher(url)
//                .forward(request, response);
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        doPost(request, response);
//    }
//}
