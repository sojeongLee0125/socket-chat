package com.distributed_system.term_project.socket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketMessageBrokerConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws/chat") // Client -> websocket 연결할 때 사용할 API 경로 설정
                .setAllowedOriginPatterns("*")
                .withSockJS(); // socket-js 라이브러리 사용
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 메시지 받을 때 관련 경로 설정, "/queue"는 1대1 메시징, "/topic"은 1대다 메시징
        registry.enableSimpleBroker("/queue", "/topic");
        // 메시지 보낼 때 관련 경로 설정
        registry.setApplicationDestinationPrefixes("/app");
    }

}
