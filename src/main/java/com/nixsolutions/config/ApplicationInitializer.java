package com.nixsolutions.config;

import com.sun.jersey.spi.spring.container.servlet.SpringServlet;
import org.apache.cxf.BusFactory;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.ws.transport.http.MessageDispatcherServlet;

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

        CXFServlet cxfServlet = new CXFServlet();
        BusFactory.setDefaultBus(cxfServlet.getBus());
        ServletRegistration.Dynamic dynamic = container.addServlet("cxf", cxfServlet);

        dynamic.setLoadOnStartup(2);
        dynamic.addMapping("/soap/*");

    }
}
