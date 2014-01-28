package com.alibaba.ace.demo;

import com.alibaba.appengine.api.cache.CacheService;
import com.alibaba.appengine.api.cache.CacheServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ding.lid
 */
public class CacheServiceDemo extends HttpServlet {
    private static final long serialVersionUID = -2359366324351384788L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("Begin cache service test.");

        CacheService cacheService = CacheServiceFactory.getCacheService();

        final String key = "FooKey";
        final String value = "cache-value";

        resp.getWriter().printf("Save value %s to key %s.\n", key, value);
        cacheService.put(key, value);

        String get = (String) cacheService.get(key);
        resp.getWriter().printf("Read value %s to key %s.\n", key, get);

        if (value.equals(key)) {
            resp.getWriter().println("Cache service test ok!");
        } else {
            resp.getWriter().printf("Fail to get, expect %s, actual %s!\n", value, get);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
