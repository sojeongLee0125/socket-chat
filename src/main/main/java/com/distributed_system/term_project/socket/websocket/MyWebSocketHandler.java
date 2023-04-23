package com.distributed_system.term_project.socket.websocket;



import java.util.HashSet;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
@Slf4j
@Component
public class MyWebSocketHandler extends TextWebSocketHandler {

    private Set<WebSocketSession> sessions = new HashSet<>();
    // 클라이언트와의 연결이 맺어진 경우
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);

        for (WebSocketSession s : sessions) {
            if (s.isOpen() && (!s.getId().equals(session.getId()))) {
                s.sendMessage(new TextMessage(session.getId()+ "유저가 접속했습니다."));
            }
        }
        TextMessage message = new TextMessage("환영합니다!");
        session.sendMessage(message);
    }

    // 클라이언트로부터 메시지를 수신한 경우
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String receivedMessage = message.getPayload();

        for (WebSocketSession s : sessions) {
            if (s.isOpen() && (!s.getId().equals(session.getId()))) {
                s.sendMessage(new TextMessage(receivedMessage));
            }

        }
    }

        // 클라이언트와의 연결이 끊어진 경우
        @Override
        public void afterConnectionClosed (WebSocketSession session, CloseStatus status) throws
        Exception {

            sessions.remove(session);

            for (WebSocketSession s : sessions) {
                if (s.isOpen()) {
                    s.sendMessage(new TextMessage(session.getId() + "유저의 접속이 끊어졌습니다."));
                }
            }
        }


}
