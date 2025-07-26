package org.example.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Objects;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {
    @Autowired
    WebSocketGateway gateway;

    @Autowired
    ChatService chatService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String userId = Objects.requireNonNull(session.getUri()).getQuery().split("=")[1];
        gateway.registerSession(userId, session);
        gateway.connect(userId);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String userId = Objects.requireNonNull(session.getUri()).getQuery().split("=")[1];
        gateway.disconnect(userId);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {
        String payload = textMessage.getPayload();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(payload);

        String senderId = jsonNode.get("senderId").asText();
        String receiverId = jsonNode.get("receiverId").asText();
        String content = jsonNode.get("content").asText();

        chatService.sendMessage(senderId, receiverId, content);
    }

}
