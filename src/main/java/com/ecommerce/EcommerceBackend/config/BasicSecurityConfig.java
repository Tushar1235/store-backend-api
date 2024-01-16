package com.ecommerce.EcommerceBackend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/*@Configuration
@EnableWebSecurity*/
public class BasicSecurityConfig {
/*
    @Autowired
    private CustomerDetailService customerDetailService;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults())
                .csrf().disable();
        return http.build();
    }
    protected  void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customerDetailService).passwordEncoder(passwordEncoder());
    }
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }*/
}
