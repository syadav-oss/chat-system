# 💬 Chat System - WebSocket Based

A basic real-time chat backend using **Spring Boot**, **WebSocket**, and **H2 in-memory DB**.

## ✅ Features

- Real-time messaging using WebSocket
- In-memory persistence using H2 and Spring Data JPA
- Message status updates (SENT, DELIVERED, READ)
- User presence tracking (online/offline)
- Notification service stub (for push/email/etc.)

## 🛠️ Tech Stack

- Java 17+
- Spring Boot
- Spring WebSocket
- Spring Data JPA
- H2 Database
- Postman / Hoppscotch (for testing)

## 🚀 How to Run

```bash
git clone https://github.com/syadav-oss/chat-system.git
cd chat-system
mvn clean install
mvn spring-boot:run
ws://localhost:8080/ws/chat?userId=alice
