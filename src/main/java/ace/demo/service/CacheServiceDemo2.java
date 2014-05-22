package ace.demo.service;

import com.alibaba.appengine.api.cache.CacheService;
import com.alibaba.appengine.api.cache.CacheServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Advanced demo of {@link com.alibaba.appengine.api.cache.CacheService}
 *
 * @author ding.lid
 */
public class CacheServiceDemo2 extends HttpServlet {
    private static final long serialVersionUID = -2359366324351384788L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("Begin advanced cache service test.");

        CacheService cacheService = CacheServiceFactory.getCacheService();

        // prepare value 
        String key = "advanced";
        boolean success = cacheService.put(key, 1L);
        if (!success) {
            resp.getWriter().printf("Fail to put, key %s\n", key);
        }

        // check value
        Long get = (Long) cacheService.get(key);
        resp.getWriter().printf("Read value %s to key %s.\n", key, get);
        if (Long.valueOf(1).equals(get)) {
            resp.getWriter().println("Cache service test put ok!");
        } else {
            resp.getWriter().printf("Fail to get, expect %s, actual %s!\n", 1, get);
        }

        // test putIfUntouched
        CacheService.IdentifiableValue identifiable = cacheService.getIdentifiable(key);
        success = cacheService.putIfUntouched(key, identifiable, 10L, 1000);
        if (!success) {
            resp.getWriter().printf("Fail to putIfUntouched, key %s\n", key);
            return;
        }

        // test increment
        Long increment = cacheService.increment(key, 1);
        if (Long.valueOf(11).equals(increment)) {
            resp.getWriter().printf("increment key %s, value %s!\n", key, increment);
        } else {
            resp.getWriter().printf("Fail to increment key %s, value %s!\n", key, increment);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
