package com.distributed_system.term_project.chat.dto;

import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
public class ChatRoom {
    private String roomId;
    private String roomName;
    private String createdAt;

    // WebSocketSession - Spring Websocket Connection 이 맺어진 세션
    private Set<WebSocketSession> sessions = new HashSet<>();

    protected ChatRoom() {
    }

    private ChatRoom(String roomName) {
        this.roomId = UUID.randomUUID().toString();
        this.roomName = roomName;
        this.createdAt = new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm:ss").format(new Date());
    }

    public static ChatRoom createRoom(String roomName) {
        return new ChatRoom(roomName);
    }

}
