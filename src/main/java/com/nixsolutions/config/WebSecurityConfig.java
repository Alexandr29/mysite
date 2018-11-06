package com.nixsolutions.config;

import com.nixsolutions.service.SuccessHandler;
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


//    @Autowired
//    private SuccessHandler successHandler;
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth)
//            throws Exception {
//        auth.userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder());
//    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/","/login","/j_spring_security_check").access("hasRole('ANONYMOUS')")
                .antMatchers( "/edit", "/create", "/remove")
                .hasAuthority("ADMIN").antMatchers("/user").hasAuthority("USER")
                .and().formLogin().loginPage("/login")
                .defaultSuccessUrl("/success")
                .usernameParameter("login")
                .passwordParameter("password")
                .failureForwardUrl("/?error=true").permitAll().and().logout()
                .logoutUrl("/logout").logoutSuccessUrl("/?logout=true")
                .permitAll().and().csrf().disable();
    }
@Autowired
public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.
    userDetailsService(userDetailsService);
}
//@Override
//    protected void configure(HttpSecurity http) throws Exception {
//    http.authorizeRequests().antMatchers("/","/j_spring_security_check","/user","/admin").permitAll()
//            .and().csrf().disable();
//
//}




}
