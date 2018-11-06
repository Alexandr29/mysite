package com.nixsolutions.config;
import com.nixsolutions.service.hibernate.HibernateUtil;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class ApplicationInitializer implements WebApplicationInitializer {

    public void onStartup(ServletContext container) throws ServletException {

        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(WebConfig.class);
        //ctx.register(WebSecurityConfig.class);
        ctx.register(HibernateUtil.class);
        //ctx.register(RestConfig.class);*/
        ctx.setServletContext(container);
        /*container.addListener(new ContextLoaderListener(ctx));*/

        ServletRegistration.Dynamic servlet = container.addServlet(
                "dispatcher", new DispatcherServlet(ctx));

        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");
    }
}
