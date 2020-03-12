package com.nixsolutions.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

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

//        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet());
//        dispatcher.setLoadOnStartup(1);
//        dispatcher.addMapping("/");
//
        ServletRegistration.Dynamic cxfServlet = servletContext
            .addServlet("cxf", new CXFServlet());
        cxfServlet.setLoadOnStartup(2);
        cxfServlet.addMapping("/soap/*");
    }
}