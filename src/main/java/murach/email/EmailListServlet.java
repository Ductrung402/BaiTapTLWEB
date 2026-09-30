package murach.email;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.mail.MessagingException; // Thêm thư viện bắt lỗi gửi mail
import murach.business.User;
import murach.data.UserDB;
import murach.util.MailUtil;        // Import tiện ích MailUtil

@WebServlet("/emailList")
public class EmailListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy năm hiện tại và đặt vào request attribute 
        GregorianCalendar currentDate = new GregorianCalendar();
        int currentYear = currentDate.get(Calendar.YEAR);
        request.setAttribute("currentYear", currentYear);
        String url = "/index.jsp";

        // get current action
        String action = request.getParameter("action");
        if (action == null) {
            action = "join"; // default action
        }
        log("EmailListServlet action = " + action);

        // perform action and set URL to appropriate page
        if (action.equals("join")) {
            url = "/EmailListIndex.jsp"; // the "join" page
        } 
        else if (action.equals("add")) {
            // get parameters from the request
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");

            // store data in User object and save User object in database
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            
            // Kiểm tra xem email đã tồn tại chưa để tránh lỗi trùng lặp, sau đó insert
            if (!UserDB.emailExists(email)) {
                UserDB.insert(user);
            }

            // TÍCH HỢP JAVA MAIL (Bài 14-1): Gửi email chào mừng/xác nhận
            String to = email;
            String from = "announcement@murach.com";
            String subject = "Welcome to our email list";
            String body = "Dear " + firstName + ",\n\n" +
                          "Thanks for joining our email list. " +
                          "We'll keep you posted on our latest news and products.\n\n" +
                          "Best regards,\n" +
                          "Murach Music Admin";
            
            try {
                MailUtil.sendMail(to, from, subject, body, false);
            } catch (MessagingException e) {
                // In log lỗi ra console nếu cấu hình mail server hoặc mật khẩu bị sai
                log("Unable to send email: " + e.getMessage());
            }

            // set User object in request object and set URL
            request.setAttribute("user", user);
            url = "/thanks.jsp"; // the "thanks" page
        }

        // forward request and response objects to specified URL
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
}