package com.distributed_system.term_project.socket.interceptor;

import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

public class MyHttpSessionHandShakeInterceptor extends HttpSessionHandshakeInterceptor {

    //@Override
//    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
//        WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
//       HttpSession session = getSession(request);
//    }
//
//    private HttpSession getSession(ServerHttpRequest request){
//
//    }
}
