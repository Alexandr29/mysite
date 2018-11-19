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
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.isNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebAppConfiguration
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {HibernateUtil.class, WebConfig.class,
        WebSecurityConfig.class })
public class AdminControllerTest {

    @Mock
    UserService userService;
    @Mock
    RoleService roleService;
    @InjectMocks
    AdminController controller;

    private MockMvc mockMvc;
    @Mock
    User user;
    Model model;

    @Before
    public void setUp() throws Exception {
        model = mock(Model.class);
        user = mock(User.class);
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setViewResolvers(viewResolver).build();
    }

    @Test
    public void succesCreateUser() throws Exception {
        doNothing().when(userService).create(any());
        when(userService.findByLogin(any())).thenReturn(null);
        mockMvc.perform(post("/create")
                .param("login", "login")
                .param("password", "password")
                .param("passwordagain", "password")
                .param("email", "email@mail.com")
                .param("firstName", "firstName")
                .param("lastName", "lastName")
                .param("birthday", "1997-04-29")
                .param("role", "1"))
                .andExpect(forwardedUrl("/WEB-INF/views/admin.jsp"))
        .andDo(print());
        verify(userService, times(1)).create(any());
        verify(userService, times(2)).findAll();
        verifyNoMoreInteractions(userService);
    }
    @Test
    public void loginAlreadyExistTest() throws Exception {
        List<User> users = new ArrayList<>();
        user = new User();
        user.setLogin("login2");
        users.add(user);
        when(userService.findAll()).thenReturn(users);
        mockMvc.perform(post("/create")
                .param("login", "login2")
                .param("password", "password")
                .param("passwordagain", "password")
                .param("email", "email@mail.com")
                .param("firstName", "firstName")
                .param("lastName", "lastName")
                .param("birthday", "1997-04-29")
                .param("role", "1"))
                .andExpect(forwardedUrl("/WEB-INF/views/create.jsp"))
                .andDo(print());
        verify(userService, never()).create(any());
        verify(userService, times(1)).findAll();
        verifyNoMoreInteractions(userService);
    }


    @Test
    public void createGet() throws Exception {

            this.mockMvc.perform(get("/create"))
                    .andExpect(forwardedUrl("/WEB-INF/views/create.jsp"))
                    .andExpect(model().attribute("roles", roleService.findAll()))
                    .andDo(print());
    }

    @Test
    public void createPost() throws Exception {
        this.mockMvc.perform(post("/create")
                .param("passwordagain","111111")
                .param("password","111111"))
                .andExpect(forwardedUrl("/WEB-INF/views/admin.jsp"))
                .andDo(print());
    }
    @Test
    public void passwordNotEqualsTest() throws Exception {
        this.mockMvc.perform(post("/create")
                .param("passwordagain","1111")
                .param("password","111111"))
                .andExpect(forwardedUrl("/WEB-INF/views/create.jsp"))
                .andDo(print());
    }
    @Test
    public void editGet() throws Exception {
        String login = "admin";
        user.setLogin(login);
        when(userService.findByLogin(anyString())).thenReturn(user);
        this.mockMvc.perform(get("/edit"))
                .andExpect(forwardedUrl("/WEB-INF/views/edit.jsp"))
                .andDo(print());
    }

    @Test
    public void editPost() throws Exception {
        String login = "admin";
        user.setLogin(login);
        when(userService.findByLogin(anyString())).thenReturn(user);
        this.mockMvc.perform(get("/edit/?logintoedit=" + login))
                .andExpect(forwardedUrl("/WEB-INF/views/edit.jsp"))
                .andDo(print());
    }

    @Test
    public void deleteGet() throws Exception {
        String login = "User3";

        this.mockMvc.perform(get("/delete/?logintodelete=" + login))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin")).andDo(print());
    }
    @Test
    public void showRegistration() throws Exception {
        this.mockMvc.perform(get("/registration"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/views/Registration.jsp")).andDo(print());
    }
    @Test
    public void Registration() throws Exception {
        doNothing().when(userService).create(any());
        when(userService.findByLogin(any())).thenReturn(null);
        mockMvc.perform(post("/registration")
                .param("login", "login")
                .param("password", "password")
                .param("passwordagain", "password")
                .param("email", "email@mail.com")
                .param("firstName", "firstName")
                .param("lastName", "lastName")
                .param("birthday", "1997-04-29")
                .param("role", "1"))
                .andExpect(forwardedUrl("/WEB-INF/views/login.jsp"))
                .andDo(print());
        verify(userService, times(1)).create(any());
        verify(userService, times(1)).findAll();
        verifyNoMoreInteractions(userService);
    }
    @Test
    public void wrongRegistration() throws Exception {
        doNothing().when(userService).create(any());
        when(userService.findByLogin(any())).thenReturn(null);
        mockMvc.perform(post("/registration")
                .param("login", "l")
                .param("password", "password")
                .param("passwordagain", "password2")
                .param("email", "email@mail.com")
                .param("firstName", "firstName")
                .param("lastName", "lastName")
                .param("birthday", "1997-04-29")
                .param("role", "1"))
                .andExpect(forwardedUrl("/WEB-INF/views/Registration.jsp"))
                .andDo(print());
        verify(userService, never()).create(any());
    }
    @Test
    public void loginAlreadyInUseRegistration() throws Exception {
        List<User> users = new ArrayList<>();
        user = new User();
        user.setLogin("login");
        users.add(user);
        when(userService.findAll()).thenReturn(users);
        mockMvc.perform(post("/registration")
                .param("login", "login")
                .param("password", "password")
                .param("passwordagain", "password2")
                .param("email", "email@mail.com")
                .param("firstName", "firstName")
                .param("lastName", "lastName")
                .param("birthday", "1997-04-29")
                .param("role", "1"))
                .andExpect(forwardedUrl("/WEB-INF/views/Registration.jsp"))
                .andDo(print());
        verify(userService, never()).create(any());
    }

    @Test
    public void succesEditUser() throws Exception {
        user.setId(2L);
        doNothing().when(userService).create(any());
        when(userService.findByLogin(any())).thenReturn(user);
        mockMvc.perform(post("/edit")
                .param("login", "login")
                .param("password", "password")
                .param("passwordagain", "password")
                .param("email", "email@mail.com")
                .param("firstName", "firstName")
                .param("lastName", "lastName")
                .param("birthday", "1997-04-29")
                .param("role", "1"))
                .andExpect(forwardedUrl("/WEB-INF/views/admin.jsp"))
                .andDo(print());
        verify(userService, times(1)).update(any());
        verify(userService, times(1)).findByLogin(anyString());
    }
    @Test
    public void passwordNotEqualsEditUser() throws Exception {
        user.setId(2L);
        doNothing().when(userService).create(any());
        when(userService.findByLogin(any())).thenReturn(user);
        mockMvc.perform(post("/edit")
                .param("login", "login")
                .param("password", "passwor")
                .param("passwordagain", "password")
                .param("email", "email@mail.com")
                .param("firstName", "firstName")
                .param("lastName", "lastName")
                .param("birthday", "1997-04-29")
                .param("role", "1"))
                .andExpect(forwardedUrl("/WEB-INF/views/edit.jsp"))
                .andDo(print());
        verify(userService, never()).update(any());
        verify(userService, times(1)).findByLogin(anyString());
    }

}