package com.distributed_system.term_project.chat.repository;

import com.distributed_system.term_project.chat.dto.ChatRoom;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class ChatRoomMemoryRepository implements ChatRoomRepository {

    private static final Map<String, ChatRoom> chatRooms = new ConcurrentHashMap<>();

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