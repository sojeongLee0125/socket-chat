package com.distributed_system.term_project.chat.service;

import com.distributed_system.term_project.chat.dto.ChatRoom;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService {

    private Map<String, ChatRoom> chatRooms;

    //의존관게 주입 완료 후 실행
    // todo :의문정: 웹소켓에서의 동시성 제어도 필요할 것인가?
    @PostConstruct
    private void init() {
        chatRooms = new ConcurrentHashMap<>();
    }

    // 채팅방 리스트 최근 생성 순으로 반환
    public List<ChatRoom> findAllRooms() {
        return new ArrayList<>(chatRooms.values()).stream()
                .sorted(Comparator.comparing(ChatRoom::getCreatedAt).reversed())
                .collect(Collectors.toList());
    }

    //채팅방 하나 불러오기
    public ChatRoom findRoomById(String roomId) {
        return chatRooms.get(roomId);
    }

    //채팅방 생성
    public ChatRoom createRoom(String roomName) {
        ChatRoom chatRoom = ChatRoom.createRoom(roomName);
        chatRooms.put(chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }

}
