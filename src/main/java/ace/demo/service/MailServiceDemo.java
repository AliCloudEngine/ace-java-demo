package ace.demo.service;

import com.alibaba.appengine.api.mail.MailMessage;
import com.alibaba.appengine.api.mail.MailService;
import com.alibaba.appengine.api.mail.MailServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Simple demo of {@link com.alibaba.appengine.api.mail.MailService}
 *
 * @author ding.lid
 */
public class MailServiceDemo extends HttpServlet {
    private static final long serialVersionUID = -2359366322435138488L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("Begin mail service test.");

        MailService mailService = MailServiceFactory.getMailService();
        mailService.send(new MailMessage("your_from@gmail.com", "your_to@gmail.com", "Hello", "From Mail Service"));

        resp.getWriter().println("Finish mail service test.");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
