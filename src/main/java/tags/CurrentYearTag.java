package murach.tags;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.time.Year;

public class CurrentYearTag extends SimpleTagSupport {

    @Override
    public void doTag() throws JspException, IOException {
        // Lấy năm hiện tại từ hệ thống
        String currentYear = String.valueOf(Year.now().getValue());
        
        // Ghi thẳng ra file HTML/JSP
        JspWriter out = getJspContext().getOut();
        out.print(currentYear);
    }
}