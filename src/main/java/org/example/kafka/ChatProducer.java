package org.example.kafka;

import org.example.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ChatProducer {

    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;

    public void sendMessage(Message message) {
        kafkaTemplate.send(Constants.CHAT_TOPIC, message);
        System.out.println("Kafka Producer sent message: " + message.getContent());
    }
}