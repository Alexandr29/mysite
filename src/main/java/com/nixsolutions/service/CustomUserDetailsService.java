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

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private static Logger logger = LoggerFactory
            .getLogger(CustomUserDetailsService.class);

    @Autowired
    private UserService userService;
    @Autowired
    private HibernateUserDao hibernateUserDao;

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String login) throws
            UsernameNotFoundException {
        User user = userService.findByLogin(login);
        Role role = new Role();
        if (user == null) {
            logger.error(login + " user not found");
            throw new UsernameNotFoundException(login + " username not found");
        }

        logger.info("User singed in " + user.getLogin() + " " + user.getRole_id());
        GrantedAuthority authority = new SimpleGrantedAuthority("USER");
        System.out.println(authority);

        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(),
                true, true, true,
                true, Collections.singleton(authority));
    }
}
