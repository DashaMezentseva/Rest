package com.nixsolutions.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


//public class AppInitializer implements WebApplicationInitializer{
//
//    private static final String DISPATCHER = "dispatcher";
//
//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//
//        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
//        ctx.register(WebConfig.class);
//        servletContext.addListener(new ContextLoaderListener(ctx));
//        ServletRegistration.Dynamic servlet = servletContext.addServlet(DISPATCHER, new DispatcherServlet(ctx));
//        servlet.addMapping("/");
//        servlet.setLoadOnStartup(1);
//
//    }
//}

@Order(Ordered.HIGHEST_PRECEDENCE)
public class AppInitializer
    implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext)
        throws ServletException {

        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();

        servletContext.addListener(new ContextLoaderListener(context));
        servletContext.setInitParameter(
            "contextConfigLocation", "com.nixsolutions");
    }
}