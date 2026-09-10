package murach.email;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import murach.business.User;
import murach.data.UserDB;

@WebServlet("/emailList")
public class EmailListServlet extends HttpServlet {

    // Đoạn mã doGet ở Page 2 được thêm vào đây
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
            url = "/index.jsp"; // the "join" page
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
            UserDB.insert(user);

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