package com.distributed_system.term_project.chat.service;

import com.distributed_system.term_project.chat.dto.ChatRoom;
import com.distributed_system.term_project.chat.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService {

    private final ChatRoomRepository repository;

    // 채팅방 리스트 최근 생성 순으로 반환
    public List<ChatRoom> findAllRooms() {
        return repository.findAllRooms();
    }

    //채팅방 하나 불러오기
    public ChatRoom findRoomById(String roomId) {
        return repository.findRoomById(roomId);
    }

    //채팅방 생성
    public ChatRoom createRoom(String roomName) {
        return repository.createRoom(roomName);
    }

}
