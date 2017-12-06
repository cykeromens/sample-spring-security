package com.zf.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


/**
 * The WebSecurityConfig class is annotated with @EnableWebSecurity to enable Spring Security’s web security support and
 * provide the Spring MVC integration. It also extends WebSecurityConfigurerAdapter and overrides a couple of its methods to
 * set some specifics of the web security configuration.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * The configure(HttpSecurity) method defines which URL paths should be secured and which should not.
     * Specifically, the "/" and "/home" paths are configured to not require any authentication.
     * All other paths must be authenticated
     * @param http HttpSecurity
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    /**
     * it sets up an in-memory user store with a single user.
     * That user is given a username of "user", a password of "password", and a role of "USER".
     * @param auth AuthenticationManagerBuilder
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication() //Here I am using an in memory authentication replace with userDetailsService(T t)
                .withUser("user").password("password").roles("USER");
    }
}
