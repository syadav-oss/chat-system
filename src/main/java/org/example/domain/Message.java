package org.example.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
public class Message {
    @Id
    String messageId = UUID.randomUUID().toString();    // UUID
    String senderId;
    String receiverId;
    String content;

    public Message(String senderId, String receiverId, String content, long timestamp, MessageStatus status) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
        this.timestamp = timestamp;
        this.status = status;
    }

    public Message() {}

    long timestamp;
    MessageStatus status; // SENT, DELIVERED, READ
}
