package com.distributed_system.term_project.chat.controller;

import com.distributed_system.term_project.chat.dto.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MessageController {

    // 특정 Broker로 메세지를 전달한다.
    private final SimpMessagingTemplate template;

    // Config에서 설정한 applicationDestinationPrefixes + @MessageMapping 경로가 병합
    @MessageMapping("/chat/message")
    public void enter(ChatMessage message) {
        if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
            message.updateMessage(message.getSender() + "님이 입장하셨습니다.");
        } else if (ChatMessage.MessageType.LEAVE.equals(message.getType())) {
            message.updateMessage(message.getSender() + "님이 퇴장하셨습니다.");
        }
        template.convertAndSend("/topic/chat/room/" + message.getRoomId(), message);
    }

}