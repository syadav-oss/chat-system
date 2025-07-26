package org.example.service;

import org.springframework.stereotype.Service;

@Service
class NotificationServiceImpl implements NotificationService {
    public void notifyUser(String userId, String message) {
        System.out.println("Notify " + userId + ": " + message);
        // In a real-world app, trigger mobile push/web notifications here
    }
}
