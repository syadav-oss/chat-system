package org.example.service;

import org.example.domain.Message;
import org.example.domain.MessageStatus;

import java.util.List;

public interface ChatService {
    void sendMessage(String senderId, String receiverId, String content);
    List<Message> fetchConversation(String user1, String user2);
    void updateMessageStatus(String messageId, MessageStatus status);
}
