package ru.netology.dao_with_hibernate.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
public class SecurityConfig {
    @Bean
    public UserDetailsService users() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        User.UserBuilder users = User.withDefaultPasswordEncoder();

        manager.createUser(
                users.username("user1")
                        .password("123")
                        .roles("READ")
                        .build()
        );
        manager.createUser(
                users.username("user2")
                        .password("123")
                        .roles("WRITE")
                        .build()
        );
        manager.createUser(
                users.username("user3")
                        .password("123")
                        .roles("DELETE")
                        .build()
        );
        manager.createUser(
                users.username("user4")
                        .password("123")
                        .authorities("user4_auth")
                        .build()
        );
        return manager;
    }


    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated()
                )
                .formLogin(withDefaults());
        return http.build();
    }

    /*@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        //.requestMatchers("/persons/read*").hasRole("READ")
                        //.requestMatchers("/persons/by-city*").hasRole("WRITE")
                        .anyRequest().authenticated()
                ).formLogin();
        return http.build();
    }*/
    /* @Bean
    @Order(1)
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/persons/read")
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().hasRole("WRITE")
                )
                .httpBasic(withDefaults());
        return http.build();
    }*/
}
