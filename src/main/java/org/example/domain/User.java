package org.example.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
class User {
    String userId = UUID.randomUUID().toString();       // UUID
    String userName;     // Unique name
    String email;
    String phoneNumber;
    boolean isOnline;
    long lastSeen;
}