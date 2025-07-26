package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.domain.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
class WebSocketGatewayImpl implements WebSocketGateway {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketGatewayImpl.class.getName());
    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Autowired
    ObjectMapper mapper;


    public void connect(String userId) {
        System.out.println("User connected: " + userId);
    }

    public void disconnect(String userId) {
        sessions.remove(userId);
        System.out.println("User disconnected: " + userId);
    }

    public void registerSession(String userId, WebSocketSession session) {
        sessions.put(userId, session);
    }

    public void pushMessage(String receiverId, Message message) {
        WebSocketSession session = sessions.get(receiverId);
        if (session != null && session.isOpen()) {
            try {

                session.sendMessage(new TextMessage(mapper.writeValueAsString(message)));
            } catch (Exception e) {
                logger.error("error sending message", e);
            }
        }
    }
}
