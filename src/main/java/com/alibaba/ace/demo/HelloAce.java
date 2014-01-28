package com.alibaba.ace.demo;

import com.alibaba.ace.demo.model.User;
import com.aliyun.openservices.ace4j.sdk.cache.api.CacheService;
import com.aliyun.openservices.ace4j.sdk.spi.Ace4jCache;
import com.aliyun.openservices.ace4j.sdk.spi.Ace4jOSS;
import com.aliyun.openservices.oss.OSS;
import com.aliyun.openservices.oss.model.Bucket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author lei.yaol
 * @author ding.lid
 */
public class HelloAce extends HttpServlet {
    private static final long serialVersionUID = -1120930759438832012L;
    private Logger logger = LoggerFactory.getLogger(HelloAce.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Test Logging
        logger.debug("log debug: Hello Ace!");
        logger.warn("log warn: Hello Ace!");
        logger.info("log info: Hello Ace!");
        logger.error("log error: Hello Ace!");

        // Test std output
        System.out.println("system out: Hello Ace!");
        System.err.println("system err: Hello Ace!");

        resp.getWriter().println("Begin ols test:");
        resp.getWriter().println("Hello Ace!");

        // Test OSS
        try {
            resp.getWriter().println("=================================");
            resp.getWriter().println("Begin oss test:");
            String bucketName = "javademobucket";
//        	OSSBucketClient ossClient = Ace4jOSS.getFactory().createOSSBucketClient();
//        	String uniqueBucketName = ossClient.genUniqueBucketName(bucketName);
//        	Bucket bucket = ossClient.getOSS().createBucket(uniqueBucketName);
            OSS oss = Ace4jOSS.getFactory().create("dcn9PeTVFmRVY9ep", "AOVqswJP9Yjd7tIbWmqR58yYvXGp16");
            resp.getWriter().println("create oss:" + oss);
            Bucket bucket = oss.createBucket(bucketName);
            resp.getWriter().println("create bucket:" + bucket.getName());
        } catch (Exception e) {
            resp.getWriter().println("test oss error:" + toString(e));
            logger.error("test oss error:" + e.getMessage(), e);
        }

        // Test OCS
        try {
            resp.getWriter().println("=================================");
            resp.getWriter().println("Begin ocs test:");

            CacheService cacheClient = Ace4jCache.getFactory().create("HelloAce4Java");
            User user = genUser();
            final String cacheKey = "user.bob.pan";
            boolean success = cacheClient.put(cacheKey, user, -1);
            resp.getWriter().println("cache put result:" + success);
            User cacheUser = (User) cacheClient.get(cacheKey);
            resp.getWriter().println("cache get result:" + cacheUser);
        } catch (Exception e) {
            resp.getWriter().println("test ocs error :" + toString(e));
            logger.error("test ocs error:" + e.getMessage(), e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private User genUser() {
        User user = new User();
        user.setName("bob.pan");
        user.setSex(true);
        user.setAge((byte) 20);
        user.setWeight(150);
        return user;
    }

    private static String toString(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter writer = new PrintWriter(sw);
        t.printStackTrace(writer);
        writer.flush();
        return sw.toString();
    }
}
