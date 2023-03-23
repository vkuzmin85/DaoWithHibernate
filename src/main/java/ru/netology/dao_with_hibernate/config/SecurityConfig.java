package ru.netology.dao_with_hibernate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user1").password(encoder().encode("123")).authorities("by_city")
                .and()
                .withUser("user2").password(encoder().encode("123")).authorities("by_city", "by_name_surname", "by_age");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .and()
                .authorizeRequests()
                .antMatchers("/persons/by-age").hasAuthority("by_age")
                .and()
                .authorizeRequests()
                .antMatchers("/persons/by-city").hasAuthority("by_city")
                .and()
                .authorizeRequests()
                .antMatchers("/persons/by-name-surname").hasAuthority("by_name_surname")
                .and()
                .authorizeRequests().anyRequest().authenticated();
    }
}
