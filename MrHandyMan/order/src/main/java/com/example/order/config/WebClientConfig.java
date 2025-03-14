package com.example.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public WebClient inventoryWebClient() {
        return webClientBuilder().baseUrl("http://localhost:9084/api/v1").build();
    }

    @Bean
    public WebClient productWebClient() {
        return webClientBuilder().baseUrl("http://localhost:9086/api/v1").build();
    }
}
