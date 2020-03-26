//package com.nixsolutions.config;
//
//import org.springframework.context.annotation.Configuration;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class CorsFilter implements Filter {
//
//    public void init(FilterConfig filterConfig) throws ServletException {
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Origin", "*");
//        ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Methods", "GET, DELETE, OPTIONS, HEAD, PUT, POST");
//        ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Headers", "Content-Type");
//        ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Headers", "authorization");
//
//        HttpServletResponse resp = (HttpServletResponse) servletResponse;
//        if (request.getMethod().equals("OPTIONS")) {
//            resp.setStatus(HttpServletResponse.SC_ACCEPTED);
//            return;
//        }
//        filterChain.doFilter(request, servletResponse);
//    }
//}
