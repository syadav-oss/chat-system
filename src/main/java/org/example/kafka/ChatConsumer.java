package org.example.kafka;

import org.example.domain.Message;
import org.example.repository.MessageRepository;
import org.example.service.WebSocketGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

@Service
public class ChatConsumer {

    @Autowired
    WebSocketGateway webSocketGateway;

    @Autowired
    MessageRepository messageRepository;

//    @KafkaListener(topics = "chat-messages", groupId = "chat-group")
    public void consume(Message message) {
//        System.out.println("Kafka Consumer received: " + message.getContent());
        messageRepository.save(message);
        webSocketGateway.pushMessage(message.getReceiverId(), message);
    }

    public void temp() {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b-a);
        List<Integer> list = new ArrayList<>();
        pq.add(1);
        pq.add(2);
        int [] arr = new int[5];
        Integer[] arrr = new Integer[]{};
        Integer.compare(1,2);
        Arrays.sort(arr);
    }
}