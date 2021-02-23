package com.costa.luiz.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/delete/**").hasRole("MANAGER")
                .antMatchers("/add").hasRole("MANAGER")
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll().and() // Sent to browser
                .logout().permitAll(); // Allow everyone does the logout
    }

    @Bean
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        final UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user")
                .roles("USER").build();

        final UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .roles("USER", "MANAGER").build();
        return new InMemoryUserDetailsManager(user, admin);
    }
}
