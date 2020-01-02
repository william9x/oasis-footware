//package com.oasisvn.middleware.security.filters;
//
//import com.oasisvn.io.response.ErrorResponse;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//public class HeadersFilter implements Filter {
//
//    @Override
//    public void doFilter(ServletRequest request,
//                         ServletResponse response,
//                         FilterChain chain) throws IOException, ServletException {
//
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse res = (HttpServletResponse) response;
//
//        String contentType = req.getHeader("Content-Type");
//
//        if (null == contentType || false == contentType.equals("application/json")) {
//            res.sendError(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), ErrorResponse.UNSUPPORTED_MEDIA_TYPE.getErrorMessage());
//            return;
//        }
//    }
//}
