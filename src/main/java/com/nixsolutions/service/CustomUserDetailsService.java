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

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override public UserDetails loadUserByUsername(String login)
            throws UsernameNotFoundException {
        User user = userService.findByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException(login + " username not found");
        }
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + "ADMIN");

        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(),
                true, true, true,
                true, Collections.singleton(authority));
    }

}
