package org.example.service;

import org.example.domain.Message;
import org.example.domain.MessageStatus;
import org.example.kafka.ChatProducer;
import org.example.repository.MessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ChatServiceImplTest {

    @InjectMocks
    private ChatServiceImpl chatService;

    @Mock
    private MessageRepository messageRepository;

    @Mock
    ChatProducer chatProducer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendMessage_callsKafkaProducer() {
        chatService.sendMessage("alice", "bob", "Hey there");

        // We verify the producer sends the message
        verify(chatProducer, times(1)).sendMessage(any(Message.class));
        // Optionally capture the message and assert values
        ArgumentCaptor<Message> captor = ArgumentCaptor.forClass(Message.class);
        verify(chatProducer).sendMessage(captor.capture());
        Message sent = captor.getValue();
        assertEquals("alice", sent.getSenderId());
        assertEquals("bob", sent.getReceiverId());
        assertEquals("Hey there", sent.getContent());
        assertEquals(MessageStatus.SENT, sent.getStatus());
    }

    @Test
    void testFetchConversation_returnsCorrectList() {
        List<Message> mockMessages = List.of(
                new Message( "alice", "bob", "Hi", System.currentTimeMillis(), MessageStatus.SENT)
        );

        when(messageRepository.findBySenderIdAndReceiverIdOrReceiverIdAndSenderId("alice", "bob", "alice", "bob"))
                .thenReturn(mockMessages);

        List<Message> result = chatService.fetchConversation("alice", "bob");
        assertEquals(1, result.size());
        assertEquals("Hi", result.get(0).getContent());
    }

    @Test
    void testUpdateMessageStatus_updatesStatusCorrectly() {
        Message existingMessage = new Message("alice", "bob", "Hi Bob", System.currentTimeMillis(), MessageStatus.SENT);
        when(messageRepository.findById("id123")).thenReturn(Optional.of(existingMessage));

        chatService.updateMessageStatus("id123", MessageStatus.READ);

        assertEquals(MessageStatus.READ, existingMessage.getStatus());
        verify(messageRepository).save(existingMessage);
    }

    @Test
    void testUpdateMessageStatus_messageNotFound_doesNothing() {
        when(messageRepository.findById("unknown")).thenReturn(Optional.empty());
        chatService.updateMessageStatus("unknown", MessageStatus.READ);
        verify(messageRepository, never()).save(any());
    }
}