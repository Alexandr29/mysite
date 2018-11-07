package com.nixsolutions.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration @EnableWebSecurity public class WebSecurityConfig
        extends WebSecurityConfigurerAdapter {

    @Autowired @Qualifier("userDetailsService") private UserDetailsService userDetailsService;

    @Override protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/", "/login", "/registration")
                .permitAll()
                .antMatchers("/edit", "/admin", "/edit/*", "/create", "/remove")
                .hasAuthority("ADMIN").antMatchers("/user").hasAuthority("USER")
                .and().formLogin().loginPage("/login")
                .defaultSuccessUrl("/success").usernameParameter("login")
                .passwordParameter("password").and().logout()
                .logoutUrl("/logout").logoutSuccessUrl("/?logout=true")
                .permitAll().and().csrf().disable();
    }

}
