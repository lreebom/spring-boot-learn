package com.lreebom.springbootlearn.common.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

@Slf4j
@Component
public class RequestLogInterceptor implements HandlerInterceptor {

    private static final String START_TIME = "startTime";
    private static final String TRACE_ID = "traceId";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String traceId = UUID.randomUUID().toString().replace("-", "");
        MDC.put(TRACE_ID, traceId);
        request.setAttribute(START_TIME, System.currentTimeMillis());
        request.setAttribute(TRACE_ID, traceId);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        Object startTime = request.getAttribute(START_TIME);
        Object traceId = request.getAttribute(TRACE_ID);
        if (startTime instanceof Long start) {
            long cost = System.currentTimeMillis() - start;
            log.info("request completed, method={}, uri={}, status={}, cost={}ms, traceId={}",
                    request.getMethod(),
                    request.getRequestURI(),
                    response.getStatus(),
                    cost,
                    traceId);
        }
        MDC.remove(TRACE_ID);
    }
}
