package ace.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Simple demo of Log Service
 *
 * @author lei.yaol
 * @author ding.lid
 */
public class LogServiceDemo extends HttpServlet {
    private static final long serialVersionUID = -1120930759438832012L;
    private static Logger logger = LoggerFactory.getLogger(LogServiceDemo.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // NOTE:
        // 注意在工程的Log4J的配置文件中配置上ACE的Ace4jLogAppender，Ace4jLogAppender的配置不要调整。
        // Ace4jLogAppender输出ACE会收集并显示到ACE的控制台上。
        // 详见本工程中的Log4J配置文件 log4j.properties

        resp.getWriter().println("Begin ols test!");

        // Test Logging
        logger.debug("log debug: Hello Ace!");
        logger.warn("log warn: Hello Ace!");
        logger.info("log info: Hello Ace!");
        logger.error("log error: Hello Ace!");

        // Test std output
        System.out.println("system out: Hello Ace!");
        System.err.println("system err: Hello Ace!");

        resp.getWriter().println("You can see log content on ACE console!");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
