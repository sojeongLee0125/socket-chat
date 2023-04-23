package com.distributed_system.term_project.chat.repository;

import com.distributed_system.term_project.chat.dto.ChatRoom;

import java.util.List;

public interface ChatRoomRepository {

    List<ChatRoom> findAllRooms();

    ChatRoom findRoomById(String roomId);

    ChatRoom createRoom(String roomName);

}
