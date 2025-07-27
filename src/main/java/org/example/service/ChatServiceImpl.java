package org.example.service;

import org.example.domain.Message;
import org.example.domain.MessageStatus;
import org.example.kafka.ChatProducer;
import org.example.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements  ChatService{

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    WebSocketGateway webSocketGateway;

    @Autowired
    NotificationService notificationService;

    @Autowired
    ChatProducer chatProducer;

    @Override
    public void sendMessage(String senderId, String receiverId, String content) {
        Message message = new Message(senderId, receiverId, content, System.currentTimeMillis(), MessageStatus.SENT);
        chatProducer.sendMessage(message);
    }

    @Override
    public List<Message> fetchConversation(String user1, String user2) {
        return messageRepository.findBySenderIdAndReceiverIdOrReceiverIdAndSenderId(user1, user2, user1, user2);
    }


    @Override
    public void updateMessageStatus(String messageId, MessageStatus status) {
        messageRepository.findById(messageId).ifPresent(msg -> {
            msg.setStatus(status);
            messageRepository.save(msg);
        });
    }
}