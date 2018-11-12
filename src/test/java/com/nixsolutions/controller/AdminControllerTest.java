package com.nixsolutions.controller;

import com.nixsolutions.config.WebConfig;
import com.nixsolutions.config.WebSecurityConfig;
import com.nixsolutions.service.RoleService;
import com.nixsolutions.service.UserService;
import com.nixsolutions.service.hibernate.HibernateUtil;
import com.nixsolutions.service.impl.Role;
import com.nixsolutions.service.impl.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebAppConfiguration @RunWith(MockitoJUnitRunner.class) @ContextConfiguration(classes = {
        HibernateUtil.class, WebConfig.class,
        WebSecurityConfig.class }) public class AdminControllerTest {

    @Mock UserService userService;
    @Mock RoleService roleService;
    @InjectMocks AdminController controller;
    private MockMvc mockMvc;

    UserService mockUserService = mock(UserService.class);
    HttpServletRequest request = mock(HttpServletRequest.class);
    User user;
    Model model;
    @Before public void setUp() throws Exception {
        model = mock(Model.class);
        user = mock(User.class);
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setViewResolvers(viewResolver).build();
    }

    @Test public void createGet() throws Exception {

            this.mockMvc.perform(get("/create"))
                    .andExpect(forwardedUrl("/WEB-INF/views/create.jsp"))
                    .andExpect(model().attribute("roles", roleService.findAll()))
                    .andDo(print());
    }

    @Test public void createPost() {
    }

    @Test public void RegistrationGet() {
    }

    @Test public void registrationPost() {
    }

    @Test public void editGet() throws Exception {
        String login = "admin";
        when(mockUserService.findByLogin("login")).thenReturn(user);
        when(model.addAttribute("login",user.getLogin())).thenReturn(model);
        this.mockMvc.perform(get("/edit/?logintoedit=" + login))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin"))
                .andDo(print());

    }

    @Test public void editPost() {
    }

    @Test public void deleteGet() throws Exception {
        String login = "User3";

        this.mockMvc.perform(get("/delete/?logintodelete=" + login))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin")).andDo(print());
    }
}