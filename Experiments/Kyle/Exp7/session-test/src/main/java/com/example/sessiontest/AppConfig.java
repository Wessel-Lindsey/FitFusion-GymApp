package com.example.sessiontest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ActiveUserStore activeUserStore() {
        return new ActiveUserStore();
    }

}
