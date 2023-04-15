package com.distributed_system.term_project.chat.controller;

import com.distributed_system.term_project.chat.dto.ChatRoom;
import com.distributed_system.term_project.chat.service.ChatService;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatRoomController {

    private final ChatService chatService;

    /**
     * 채팅방 리스트 화면 조회
     *
     * @param model : 컨트롤러에서 데이터를 생성해 이를 View에 전달하는 역할, HashMap 구조
     */
    @GetMapping("/main")
    public String getRoomListPage(Model model) {
        return "/chat/room";
    }

    /**
     * 채팅방 입장 후 화면 조회
     *
     * @param model
     * @param roomId
     */
    @GetMapping("/enter/{roomId}")
    public String getRoomDetailPage(Model model, @PathVariable String roomId) {
        model.addAttribute("roomId", roomId);
        return "/chat/roomdetail";
    }

    /**
     * 채팅방 리스트 데이터 조회
     */
    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoom> getAllRooms() {
        return chatService.findAllRooms();
    }

    /**
     * 채팅방 신규 생성
     *
     * @param roomName : 신규 생성 방 이름
     */
    @PostMapping("/rooms")
    @ResponseBody
    public ChatRoom createRoom(@RequestParam String roomName) {
        return chatService.createRoom(roomName);
    }

    /**
     * 특정 채팅방 데이터 조회
     *
     * @param roomId
     */
    @GetMapping("/rooms/{roomId}")
    @ResponseBody
    public ChatRoom getRoomInfo(@PathVariable String roomId) {
        ChatRoom room = chatService.findRoomById(roomId);
        return room;
    }

}
