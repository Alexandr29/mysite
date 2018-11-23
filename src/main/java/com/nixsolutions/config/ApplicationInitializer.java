package com.nixsolutions.config;

import com.sun.jersey.spi.spring.container.servlet.SpringServlet;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import java.util.EnumSet;

public class ApplicationInitializer implements WebApplicationInitializer {

    public void onStartup(ServletContext container) {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        ContextLoaderListener contextLoaderListener = new ContextLoaderListener(
                context);
        container.addListener(contextLoaderListener);
        context.register(WebConfig.class);
        context.register(WebSecurityConfig.class);
        context.setServletContext(container);
        ServletRegistration.Dynamic servlet = container
                .addServlet("dispatcher", new DispatcherServlet(context));
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");

        ServletRegistration.Dynamic wwebserviceDispatcher = container.addServlet(
                "jersey-serlvet", new SpringServlet());
        wwebserviceDispatcher.addMapping("/rest/*");
    }
}
