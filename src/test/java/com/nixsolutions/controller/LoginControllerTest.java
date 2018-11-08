package com.nixsolutions.controller;

import com.nixsolutions.config.WebConfig;
import com.nixsolutions.config.WebSecurityConfig;
import com.nixsolutions.service.hibernate.HibernateUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration @RunWith(MockitoJUnitRunner.class) @ContextConfiguration(classes = {
        HibernateUtil.class, WebConfig.class,
        WebSecurityConfig.class }) public class LoginControllerTest {
    @InjectMocks LoginController controller;

    HttpSession session = mock(HttpSession.class);

    private MockMvc mockMvc;

    @Before public void setUp() throws Exception {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setViewResolvers(viewResolver).build();
    }

    @Test public void showLoginPage() throws Exception {
        this.mockMvc.perform(get("/")).andExpect(status().isOk())
                .andExpect(forwardedUrl("login")).andDo(print());
    }

    @Test public void login() throws Exception {
        this.mockMvc.perform(get("/success")).andExpect(status().isOk())
                .andExpect(forwardedUrl("login")).andDo(print());

    }

    @Test public void logout() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        this.mockMvc.perform(get("?logout=true")).andExpect(status().isOk())
                .andExpect(forwardedUrl("login")).andDo(print());
        assertNull(authentication);
    }

    @Test public void showUsersTable() {
    }

}