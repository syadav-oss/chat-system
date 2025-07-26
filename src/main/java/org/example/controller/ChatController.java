package org.example.controller;

import org.example.domain.Message;
import org.example.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/send")
    public void sendMessage(@RequestParam String senderId, @RequestParam String receiverId, @RequestParam String content) {
        chatService.sendMessage(senderId, receiverId, content);
    }

    @GetMapping("/conversation")
    public List<Message> getConversation(@RequestParam String user1, @RequestParam String user2) {
        return chatService.fetchConversation(user1, user2);
    }
}

