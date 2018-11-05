package com.nixsolutions.service;
import com.nixsolutions.service.hibernate.HibernateRoleDao;
import com.nixsolutions.service.hibernate.HibernateUserDao;
import com.nixsolutions.service.impl.Role;
import com.nixsolutions.service.impl.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private UserService userService;


    @Transactional
    @Override public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        System.out.println("load method");

         List<User> users = userService.findAll();
        System.out.println(users);

        User user = new HibernateUserDao().findByLogin(login);
        System.out.println(user + "i am here");
        if (user == null) {
            throw new UsernameNotFoundException(login + " username not found");
        }
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN");
        System.out.println(authority.getAuthority());
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(),
                true, true, true,
                true, Collections.singleton(authority));
    }
}
