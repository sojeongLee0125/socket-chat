package com.distributed_system.term_project.chat.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatMessage {

    private MessageType type;
    //채팅방 ID
    private String roomId;
    //보내는 사람
    private String sender;
    //내용
    private String message;

    public enum MessageType {
        ENTER, TALK, LEAVE
    }

    protected ChatMessage() {
    }

    @Builder
    public ChatMessage(MessageType type, String roomId, String sender, String message) {
        this.type = type;
        this.roomId = roomId;
        this.sender = sender;
        this.message = message;
    }

    public void updateMessage(String message) {
        this.message = message;
    }

}
