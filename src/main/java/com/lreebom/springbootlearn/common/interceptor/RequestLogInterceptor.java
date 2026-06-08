package com.lreebom.springbootlearn.common.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class RequestLogInterceptor implements HandlerInterceptor {

    private static final String START_TIME = "startTime";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute(START_TIME, System.currentTimeMillis());
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        Object startTime = request.getAttribute(START_TIME);
        if (startTime instanceof Long start) {
            long cost = System.currentTimeMillis() - start;
            log.info("request completed, method={}, uri={}, status={}, cost={}ms",
                    request.getMethod(),
                    request.getRequestURI(),
                    response.getStatus(),
                    cost);
        }
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
