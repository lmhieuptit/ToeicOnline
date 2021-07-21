package com.fsoft.ez.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class HttpsHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean
                    preHandle(HttpServletRequest request, HttpServletResponse response,
                        Object handler)
                        throws Exception {

        String requestedPort = request.getHeader("x-forwarded-proto");

        if (!StringUtils.isEmpty(requestedPort) && requestedPort.equals("http")) { // This will
                                                                                   // still
                                                                                   // allow requests
                                                                                   // on :8080
            response.sendRedirect(
                "https://" + request.getServerName() + request.getRequestURI()
                    + (request.getQueryString() != null ? "?" + request.getQueryString() : ""));
            return false;
        }
        return true;
    }

}