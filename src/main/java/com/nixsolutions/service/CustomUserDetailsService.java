package com.nixsolutions.service;
import com.nixsolutions.service.hibernate.HibernateRoleDao;
import com.nixsolutions.service.hibernate.HibernateUserDao;
import com.nixsolutions.service.impl.Role;
import com.nixsolutions.service.impl.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;


    @Transactional
    @Override public UserDetails loadUserByUsername(String s)
            throws UsernameNotFoundException {

        User user = userService.findByLogin(s);
        org.springframework.security.core.userdetails.User.UserBuilder builder;
        if (user != null) {

            builder = org.springframework.security.core.userdetails.User
                    .withUsername(s);
            builder.password(user.getPassword());
            String authorities = "ADMIN";
                    //user.getRole().getName();

            System.out.println(authorities);
            builder.authorities(authorities);
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
        return builder.build();

    }
}
