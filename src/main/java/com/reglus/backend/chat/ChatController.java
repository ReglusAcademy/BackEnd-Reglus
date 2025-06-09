package com.reglus.backend.chat;

import com.reglus.backend.model.entities.users.chat.*;
import com.reglus.backend.repositories.service.ChatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.reglus.backend.chat.MessageDTO;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatService chatService;

    public ChatController(SimpMessagingTemplate messagingTemplate, ChatService chatService) {
        this.messagingTemplate = messagingTemplate;
        this.chatService = chatService;
    }

    @MessageMapping("/privateMessage")
    public void sendPrivateMessage(@Payload ChatMessage chatMessage,
                                   SimpMessageHeaderAccessor headerAccessor) {
        Conversation conversation = chatService.getOrCreatePrivateConversation(
                chatMessage.getSenderId(),
                chatMessage.getReceiverId()
        );
        chatMessage.setConversationId(conversation.getConversationId());

        chatService.saveChatMessage(chatMessage);

        messagingTemplate.convertAndSendToUser(
                chatMessage.getReceiverId(), "/queue/private", chatMessage
        );

        messagingTemplate.convertAndSendToUser(
                chatMessage.getSenderId(), "/queue/private", chatMessage
        );
    }

    @MessageMapping("/joinChat")
    public void joinChat(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("userId", chatMessage.getSenderId());
        headerAccessor.getSessionAttributes().put("userName", chatMessage.getSenderName());

        System.out.println("Usuário " + chatMessage.getSenderName() + " (ID: " + chatMessage.getSenderId() + ") conectou-se ao chat.");
    }

    @GetMapping("/conversation/{user1Id}/{user2Id}")
    public ResponseEntity<Conversation> getOrCreatePrivateConversation(
            @PathVariable String user1Id,
            @PathVariable String user2Id) {
        try {
            if (user1Id == null || user1Id.isEmpty() || user2Id == null || user2Id.isEmpty() || user1Id.equals(user2Id)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            Conversation conversation = chatService.getOrCreatePrivateConversation(user1Id, user2Id);
            return new ResponseEntity<>(conversation, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/history/{conversationId}")
    public ResponseEntity<List<MessageDTO>> getConversationHistory(@PathVariable String conversationId) {
        List<MessageDTO> history = chatService.getConversationHistory(conversationId);
        return new ResponseEntity<>(history, HttpStatus.OK);
    }

    @GetMapping("/conversations/user/{userId}")
    public ResponseEntity<List<Conversation>> getUserConversations(@PathVariable String userId) {
        List<Conversation> conversations = chatService.getUserConversations(userId);
        return new ResponseEntity<>(conversations, HttpStatus.OK);
    }

    @PostMapping("/messages/read/{readerId}")
    public ResponseEntity<Void> markMessagesAsRead(
            @PathVariable String readerId,
            @RequestBody List<Long> messageIds) {
        try {
            chatService.markMessagesAsRead(messageIds, readerId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/messages/unread/{conversationId}/{userId}")
    public ResponseEntity<List<MessageDTO>> getUnreadMessagesInConversation(
                                                                             @PathVariable String conversationId,
                                                                             @PathVariable String userId) {
        List<MessageDTO> unreadMessages = chatService.getUnreadMessagesInConversation(conversationId, userId);
        return new ResponseEntity<>(unreadMessages, HttpStatus.OK);
    }
}