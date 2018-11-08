package com.nixsolutions.controller;

import com.nixsolutions.config.WebConfig;
import com.nixsolutions.config.WebSecurityConfig;
import com.nixsolutions.service.UserService;
import com.nixsolutions.service.hibernate.HibernateUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration @RunWith(MockitoJUnitRunner.class) @ContextConfiguration(classes = {
        HibernateUtil.class, WebConfig.class,
        WebSecurityConfig.class }) public class AdminControllerTest {

    @InjectMocks AdminController controller;
    private MockMvc mockMvc;

    HttpServletRequest request = mock(HttpServletRequest.class);

    @Before public void setUp() throws Exception {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setViewResolvers(viewResolver).build();
    }

    @Test public void createGet() {
    }

    @Test public void createPost() {
    }

    @Test public void RegistrationGet() {
    }

    @Test public void registrartonPost() {
    }

    @Test public void editGet() {
    }

    @Test public void editPost() {
    }

    @Test public void deleteGet() throws Exception {

        String login = "User3";
        request.setAttribute("logintodelete", login);
        this.mockMvc.perform(get("/delete/?logintodelete=" + login))
                .andExpect(status().isOk()).andExpect(redirectedUrl("/admin"))
                .andDo(print());
    }
}