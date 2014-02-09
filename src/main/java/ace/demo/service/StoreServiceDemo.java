package ace.demo.service;

import com.alibaba.appengine.api.store.StoreService;
import com.alibaba.appengine.api.store.StoreServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Simple demo of {@link com.alibaba.appengine.api.store.StoreService}
 *
 * @author ding.lid
 */
public class StoreServiceDemo extends HttpServlet {
    private static final long serialVersionUID = -235936632435138478L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("Begin store service test.");
        StoreService storeService = StoreServiceFactory.getStoreService();

        final String testText = "FooText";
        final String path = "/test.txt";

        resp.getWriter().printf("Save text %s to file %s.\n", testText, path);
        storeService.saveTextFile(testText, path);

        String getText = storeService.getTextFile(path);
        resp.getWriter().printf("Read text %s from file %s.\n", getText, path);

        if (testText.equals(getText)) {
            resp.getWriter().println("Store service test ok!");
        } else {
            resp.getWriter().printf("Fail to get, expect %s, actual %s!\n", testText, getText);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
