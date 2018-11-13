package com.nixsolutions.service;

import com.nixsolutions.service.impl.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements
        org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserService userService;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String s)
            throws UsernameNotFoundException {
        User user = userService.findByLogin(s);
        org.springframework.security.core.userdetails.User.UserBuilder builder = null;
        if (user != null) {
            builder = org.springframework.security.core.userdetails.User
                    .withUsername(s);
            builder.password(user.getPassword());
            String authorities;
            if (user.getRoleId().equals(1L)) {
                authorities = "ADMIN";
            } else {
                authorities = "USER";
            }
            builder.authorities(authorities);
        } else {
            builder.authorities("ANONYMOUS");
        }
        return builder.build();

    }
}
