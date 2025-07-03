package com.e.hika.filter;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;

public class LoggingFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    private static final Integer LOGGING_MAXLENGTH = 10240;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse rsp = (HttpServletResponse) response;

        // 該方案可解決問題;
        if ("/login".equals(req.getServletPath())) {
            filterChain.doFilter(request, response);
            return;
        }

//        if (request.getContentType() != null && request.getContentType().toLowerCase().startsWith("multipart/")) {
//            filterChain.doFilter(request, response);
//            return;
//        }

        CachedBodyHttpServletRequest cacheRequest = new CachedBodyHttpServletRequest(req);
        ResponseCaptureWrapper responseWrapper = new ResponseCaptureWrapper(rsp);
        printRequestLog(cacheRequest);
        long startTime = System.currentTimeMillis();

        filterChain.doFilter(cacheRequest, responseWrapper);
// 沒有效果
//        responseWrapper.flushBuffer();
        long endTime = System.currentTimeMillis();
        byte[] contentAsByteArray = responseWrapper.getContentAsByteArray();
        printReseponseLog(cacheRequest, responseWrapper, endTime - startTime);
        rsp.getOutputStream().write(contentAsByteArray);
        rsp.getOutputStream().flush();

    }

    private void printReseponseLog(CachedBodyHttpServletRequest cacheRequest, ResponseCaptureWrapper responseWrapper, long time) {
        String responseContant = new String(responseWrapper.getContentAsByteArray(), StandardCharsets.UTF_8);
        logger.info("請求日志：| 接口： {} | 方法： {} | 狀態碼： {} | 耗時 {}ms | 相應内容： {}",
                cacheRequest.getRequest(),
                cacheRequest.getMethod(),
                responseWrapper.getStatus(),
                time,
                responseContant
        );
    }


    private void printRequestLog(HttpServletRequest request) throws IOException {
        if (!(request instanceof CachedBodyHttpServletRequest wrapper)) {
            throw new IllegalStateException("request is not wrapped with ContentCachingRequestWrapper");
        }

        Iterator<String> stringIterator = wrapper.getHeaderNames().asIterator();
        ArrayList<String> headers = new ArrayList<>();

        while (stringIterator.hasNext()) {
            String name = stringIterator.next();
            String header = wrapper.getHeader(name);
            headers.add(name + ":" + header);
        }

        logger.info("Request URI: {}", request.getRequestURI());
        logger.info("Request Method: {}", request.getMethod());
        logger.info("Request Headers: {}", headers);

        String cacheBody = wrapper.getCacheBody();
        if (cacheBody != null && cacheBody.length() > 0) {
            if (cacheBody.length() > LOGGING_MAXLENGTH) {
                cacheBody = cacheBody.substring(0, LOGGING_MAXLENGTH) + "...";
                logger.info(cacheBody);
            } else {
                logger.info("Request Body: {}", cacheBody);
            }
        }

    }


    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
