package com.nixsolutions.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider());
    }

    @Autowired
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/view/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //    @Override
    //    protected void configure(HttpSecurity http) throws Exception {
    //        http
    //                .authorizeRequests()
    //                .antMatchers("/registration/**", "/registration", "/login","/", "/register").permitAll()
    //                .antMatchers("/admin").access("hasRole('ROLE_ADMIN')")
    //                .anyRequest()
    //                .authenticated().and().csrf().disable()
    //                .formLogin().loginPage("/login")
    //                .defaultSuccessUrl("/j_spring_security_check")
    //                .usernameParameter("j_username")
    //                .passwordParameter("j_password")
    //                // TODO */logout
    //                .and().logout().logoutUrl("*/logout").logoutSuccessUrl("/login");
    //    }

    //    @Override
    //    protected void configure(AuthenticationManagerBuilder auth)
    //            throws Exception {
    //        auth.userDetailsService(userDetailsService)
    //                .passwordEncoder(passwordEncoder());
    //    }
    //


    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin","/", "/edit", "/create", "/remove")
                .hasAuthority("ADMIN").antMatchers("/user").hasAuthority("USER")
                .and().formLogin().loginPage("/")
                .defaultSuccessUrl("/j_spring_security_check")
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                .failureForwardUrl("/?error=true").permitAll().and().logout()
                .logoutUrl("/logout").logoutSuccessUrl("/?logout=true")
                .permitAll().and().csrf().disable();
    }


}
