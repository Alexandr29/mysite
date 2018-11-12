package com.nixsolutions.controller;

import com.nixsolutions.config.SpringSecurityInitializer;
import com.nixsolutions.config.WebConfig;
import com.nixsolutions.config.WebSecurityConfig;
import com.nixsolutions.service.UserService;
import com.nixsolutions.service.hibernate.HibernateUtil;
import com.nixsolutions.service.impl.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.http.HttpSession;

import java.security.Principal;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebAppConfiguration @RunWith(MockitoJUnitRunner.class) @ContextConfiguration(classes = {
        WebConfig.class, SpringSecurityInitializer.class,
        WebSecurityConfig.class }) public class LoginControllerTest {

    @Mock UserService userService;
    @Mock User user;
    @Mock Principal principal;

    @InjectMocks LoginController controller;

    HttpSession session = mock(HttpSession.class);

    private MockMvc mockMvc;
    Authentication authentication;

    @Before public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setViewResolvers(viewResolver).build();
    }

    @Test public void showLoginPage() throws Exception {
        this.mockMvc.perform(get("/")).andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/views/login.jsp")).andDo(print());
    }

    @Test public void login() throws Exception {
        when(userService.findByLogin(anyString())).thenReturn(user);
        Principal mockPrincipal = Mockito.mock(Principal.class);
        Mockito.when(mockPrincipal.getName()).thenReturn("admin");
        this.mockMvc.perform(get("/success").
                principal(mockPrincipal)).andExpect(redirectedUrl("/admin"))
                .andDo(print());
    }

    @Test public void admin() throws Exception {
        this.mockMvc.perform(get("/admin")).andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/views/admin.jsp"))
                .andExpect(model().attribute("users", userService.findAll()))
                .andDo(print());
    }

    @Test public void showUsersTable() {
    }

}