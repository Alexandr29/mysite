package com.nixsolutions.config;

import com.nixsolutions.users.User;
import com.nixsolutions.webservice.UserOperations;
import com.nixsolutions.webservice.UserOperationsImpl;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.security.access.SecurityConfig;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.ws.config.annotation.EnableWs;
import org.apache.cxf.Bus;
import org.apache.cxf.annotations.EndpointProperties;
import org.apache.cxf.annotations.SchemaValidation;
import org.apache.cxf.bus.spring.SpringBus;

import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import javax.xml.ws.Endpoint;


import java.util.Properties;

@EnableWebMvc
@EnableWs
@Configuration
@EnableTransactionManagement
@ImportResource({"classpath:META-INF/cxf/cxf.xml",
        "classpath:META-INF/cxf/cxf-servlet.xml"})
@ComponentScan({
        "com.nixsolutions.service.hibernate, com.nixsolutions.service.impl, "
                + "com.nixsolutions.service, com.nixsolutions.controller, com.nixsolutions.webservice" })
public class WebConfig implements WebMvcConfigurer {

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    @Bean
    public Endpoint endpoint() {
        Endpoint endpoint = new EndpointImpl(springBus(), userOperations());
        endpoint.publish("/users");
        return endpoint;
    }

    @Bean
    public UserOperations userOperations() {
        return new UserOperationsImpl();
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean
    public SessionFactory sessionFactory() {
        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(
                dataSource());
        builder.scanPackages("com.nixsolutions.service.impl")
                .addProperties(hibernateProperties());
        return builder.buildSessionFactory();
    }

    private Properties hibernateProperties() {
        Properties prop = new Properties();
        prop.put("hibernate.format_sql", "true");
        prop.put("hibernate.show_sql", "false");
        prop.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        return prop;
    }
    @Bean(name = "dataSource")
    public BasicDataSource dataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("org.h2.Driver");
        ds.setUrl("jdbc:h2:~/USER");
        ds.setUsername("test");
        ds.setPassword("test");
        return ds;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        return new HibernateTransactionManager(sessionFactory());
    }
}
