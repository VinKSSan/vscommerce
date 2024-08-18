package com.vinsaned.vscommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//para configurarmos componentes do sistema
@Configuration
public class WebSecurityConfig {
    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest()
                        .permitAll()
                );
        http
                .headers(h->h
                        .frameOptions(f->f.disable())
                );
        http.
                csrf(AbstractHttpConfigurer::disable)
        ;
        return  http.build();
    }
}
