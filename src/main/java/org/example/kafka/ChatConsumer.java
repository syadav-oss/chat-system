package org.example.kafka;

import org.example.domain.Message;
import org.example.repository.MessageRepository;
import org.example.service.WebSocketGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ChatConsumer {

    @Autowired
    WebSocketGateway webSocketGateway;

    @Autowired
    MessageRepository messageRepository;

    @KafkaListener(topics = "chat-messages", groupId = "chat-group")
    public void consume(Message message) {
        System.out.println("Kafka Consumer received: " + message.getContent());
        messageRepository.save(message);
        webSocketGateway.pushMessage(message.getReceiverId(), message);
    }
}