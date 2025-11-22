package com.musicstore.bluevelvet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration // Indica que esta classe fornece beans de configuração para o Spring
public class SecurityConfig {

    /**
     * Define o BCryptPasswordEncoder como um bean.
     * O Spring o injetará automaticamente sempre que PasswordEncoder for necessário,
     * como no nosso UserServiceImpl.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}