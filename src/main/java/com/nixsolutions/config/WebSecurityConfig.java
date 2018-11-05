package com.nixsolutions.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {



    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;
    @Bean
    public UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }
//    @Autowired
//    @Qualifier("customizeAuthenticationSuccessHandler")
//    CustomizeAuthenticationSuccessHandler SuccessHandler;
//    @Bean
//    public AuthenticationSuccessHandler successHandler() {
//        return super.;
//    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/registration/**","/", "/registration", "/login", "/register").permitAll()
                .antMatchers("/admin", "/edit", "/create", "/remove")
                .hasAuthority("ADMIN").antMatchers("/user").hasAuthority("USER")
                .and().logout()
                .logoutUrl("/logout").logoutSuccessUrl("/?logout=true")
                .permitAll().and().csrf().disable();
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/registration/**","/", "/registration", "/login", "/register").permitAll()
//                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
//                .anyRequest()
//                .authenticated().and().csrf().disable()
//                .formLogin().loginPage("/login")
//                .defaultSuccessUrl("/successful")
//                .usernameParameter("login")
//                .passwordParameter("password")
//                .and().logout().logoutUrl("*/logout").logoutSuccessUrl("/login");
//    }

}

