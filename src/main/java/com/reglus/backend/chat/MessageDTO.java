package com.reglus.backend.chat;

import com.reglus.backend.model.entities.users.chat.*;
import java.time.LocalDateTime;

public class MessageDTO {
    private Long id;
    private String conversationId;
    private String senderId;
    private String senderName;
    private String content;
    private LocalDateTime timestamp;
    private String type;
    private String status;

    public MessageDTO() {}

    public MessageDTO(Message message) {
        this.id = message.getId();
        this.conversationId = message.getConversation() != null ? message.getConversation().getConversationId() : null;
        this.senderId = message.getSenderId();
        this.senderName = message.getSenderName();
        this.content = message.getContent();
        this.timestamp = message.getTimestamp();
        this.type = message.getType().name();
        this.status = message.getStatus().name();
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getConversationId() { return conversationId; }
    public void setConversationId(String conversationId) { this.conversationId = conversationId; }
    public String getSenderId() { return senderId; }
    public void setSenderId(String senderId) { this.senderId = senderId; }
    public String getSenderName() { return senderName; }
    public void setSenderName(String senderName) { this.senderName = senderName; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}