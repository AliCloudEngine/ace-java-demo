package ace.demo.service;

import ace.demo.model.User;
import com.alibaba.appengine.api.cache.CacheService;
import com.alibaba.appengine.api.cache.CacheServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Simple demo of {@link com.alibaba.appengine.api.cache.CacheService}
 * 
 * @author ding.lid
 */
public class CacheServiceDemo extends HttpServlet {
    private static final long serialVersionUID = -2359366324351384788L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("Begin cache service test.");

        CacheService cacheService = CacheServiceFactory.getCacheService();

        // Test String
        final String key = "FooKey";
        final String value = "cache-value";

        resp.getWriter().printf("Save value %s to key %s.\n", key, value);
        cacheService.put(key, value);

        String get = (String) cacheService.get(key);
        resp.getWriter().printf("Read value %s to key %s.\n", key, get);

        if (value.equals(get)) {
            resp.getWriter().println("Cache service test ok!");
        } else {
            resp.getWriter().printf("Fail to get, expect %s, actual %s!\n", value, get);
        }

        // Test Pojo
        final User user = new User();
        user.setName("Shylock");
        user.setSex(true);
        user.setAge((byte) 20);
        user.setWeight(150);
        final String cacheKey = "UserKey";

        resp.getWriter().printf("Save value %s to key %s.\n", cacheKey, user);
        boolean success = cacheService.put(cacheKey, user);
        resp.getWriter().println("cache put result:" + success);

        User cacheUser = (User) cacheService.get(cacheKey);
        resp.getWriter().printf("Read value %s to key %s.\n", cacheKey, cacheUser);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
