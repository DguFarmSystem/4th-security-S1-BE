package com.farmsystem.sprout.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.SslProvider;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;

@Configuration
public class ApiConfig {
    
    @Value("${api.library.base-url:https://librsv.dongguk.edu}")
    private String libraryBaseUrl;
    
    @Bean
    public WebClient libraryWebClient() {
        try {
            // SSL 인증서 검증을 무시하는 SslContext 생성
            SslContext sslContext = SslContextBuilder
                .forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();
            
            // HttpClient에 SSL 설정 적용
            HttpClient httpClient = HttpClient.create()
                .secure(spec -> spec.sslContext(sslContext));
            
            return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .baseUrl(libraryBaseUrl)
                .build();
                
        } catch (Exception e) {
            throw new RuntimeException("WebClient 설정 중 오류 발생", e);
        }
    }
} 