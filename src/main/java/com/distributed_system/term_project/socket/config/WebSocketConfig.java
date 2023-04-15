package com.distributed_system.term_project.socket.config;

import com.distributed_system.term_project.socket.websocket.MyWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private MyWebSocketHandler myWebSocketHandler; // WebSocket 핸들러

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myWebSocketHandler, "/ws")// WebSocket 핸들러 등록 및 엔드포인트 설정
            //.addInterceptors(new HttpSessionHandshakeInterceptor())
            .setAllowedOrigins("*"); // CORS 설정 (필요에 따라 수정 가능)
    }
}
