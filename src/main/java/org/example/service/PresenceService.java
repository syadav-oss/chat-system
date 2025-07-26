package org.example.service;

public interface PresenceService {
    void setUserOnline(String userId);
    void setUserOffline(String userId);
    boolean isUserOnline(String userId);
    long getLastSeen(String userId);
}
