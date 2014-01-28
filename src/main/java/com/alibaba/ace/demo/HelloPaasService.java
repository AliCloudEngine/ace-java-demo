package com.alibaba.ace.demo;

import com.alibaba.appengine.api.cache.CacheService;
import com.alibaba.appengine.api.cache.CacheServiceFactory;
import com.alibaba.appengine.api.fetchurl.FetchUrlService;
import com.alibaba.appengine.api.fetchurl.FetchUrlServiceFactory;
import com.alibaba.appengine.api.store.StoreService;
import com.alibaba.appengine.api.store.StoreServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ding.lid
 */
public class HelloPaasService extends HttpServlet {
    private static final long serialVersionUID = -2359366324351384788L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // CacheService
        resp.getWriter().println("Begin cache service test.");
        CacheService cacheService = CacheServiceFactory.getCacheService();
        String key = "key1";
        String value = "cache-value";
        cacheService.put(key, value);
        String get = (String) cacheService.get(key);
        if (value.equals(key)) {
            resp.getWriter().println("Cache service test ok!");
        } else {
            resp.getWriter().printf("Fail to get, expect %s, actual %s!\n", value, get);
        }

        // StoreService
        resp.getWriter().println("Begin store service test.");
        StoreService storeService = StoreServiceFactory.getStoreService();
        String testText = "TestTest";
        storeService.saveTextFile(testText, "/test.txt");
        String getText = storeService.getTextFile("/test.txt");
        if (testText.equals(getText)) {
            resp.getWriter().println("Store service test ok!");
        } else {
            resp.getWriter().printf("Fail to get, expect %s, actual %s!\n", testText, getText);
        }

        // FetchUrlService
        FetchUrlService fetchUrlService = FetchUrlServiceFactory.getFetchUrlService();
        String url = "http://www.taobao.com";
        String body = fetchUrlService.get(url);
        resp.getWriter().printf("Fetch to %s: %s...\n", url, body.substring(256));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
