package org.example.service;

import org.example.domain.Message;
import org.springframework.web.socket.WebSocketSession;

public interface WebSocketGateway {
    void connect(String userId);
    void disconnect(String userId);
    void pushMessage(String receiverId, Message message);
    void registerSession(String userId, WebSocketSession session);
}
