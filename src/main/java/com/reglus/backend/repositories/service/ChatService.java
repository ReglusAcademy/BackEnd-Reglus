package com.reglus.backend.repositories.service;

import com.reglus.backend.model.entities.users.chat.ChatMessage;
import com.reglus.backend.model.entities.users.chat.Conversation;
import com.reglus.backend.model.entities.users.chat.Message;
import com.reglus.backend.repositories.chat.MessageRepository;
import com.reglus.backend.repositories.chat.ConversationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.reglus.backend.chat.MessageDTO;

@Service
public class ChatService {
    private final MessageRepository messageRepository;
    private final ConversationRepository conversationRepository;

    public ChatService(MessageRepository messageRepository, ConversationRepository conversationRepository) {
        this.messageRepository = messageRepository;
        this.conversationRepository = conversationRepository;
    }

    @Transactional
    public Conversation getOrCreatePrivateConversation(String user1Id, String user2Id) {
        Optional<Conversation> existingConversation = conversationRepository.findByParticipants(user1Id, user2Id);

        if (existingConversation.isPresent()) {
            return existingConversation.get();
        } else {
            Conversation newConversation = new Conversation(user1Id, user2Id);
            return conversationRepository.save(newConversation);
        }
    }

    @Transactional
    public Message saveChatMessage(ChatMessage chatMessage) {
        Conversation conversation = conversationRepository.findById(chatMessage.getConversationId())
                .orElseThrow(() -> new IllegalArgumentException("Conversa não encontrada para o ID: " + chatMessage.getConversationId()));

        Message messageToSave = new Message(chatMessage, conversation);
        Message savedMessage = messageRepository.save(messageToSave);

        conversation.setUpdatedAt(LocalDateTime.now());
        conversationRepository.save(conversation);

        return savedMessage;
    }

    public List<MessageDTO> getConversationHistory(String conversationId) {
        List<Message> messages = messageRepository.findByConversationConversationIdOrderByTimestampAsc(conversationId);
        return messages.stream()
                .map(MessageDTO::new)
                .collect(Collectors.toList());
    }

    public List<MessageDTO> getUnreadMessagesInConversation(String conversationId, String userId) {
        List<Message> unreadMessages = messageRepository.findUnreadMessagesInConversation(conversationId, userId);
        return unreadMessages.stream()
                .map(MessageDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void markMessagesAsRead(List<Long> messageIds, String readerId) {
        if (messageIds == null || messageIds.isEmpty()) {
            return;
        }

        List<Message> messagesToMark = messageRepository.findAllById(messageIds);

        if (messagesToMark.size() != messageIds.size()) {
            throw new IllegalArgumentException("Algumas mensagens não foram encontradas.");
        }

        if (messagesToMark.isEmpty()) {
            return;
        }

        Conversation conversation = messagesToMark.get(0).getConversation();
        if (conversation == null) {
            throw new IllegalArgumentException("Mensagem sem conversa associada.");
        }

        String conversationId = conversation.getConversationId();

        boolean isParticipant = (conversation.getUser1Id().equals(readerId) || conversation.getUser2Id().equals(readerId));
        if (!isParticipant) {
            throw new IllegalArgumentException("Usuário " + readerId + " não é participante da conversa " + conversationId + ".");
        }

        String conversationId2 = messagesToMark.get(0).getConversation().getConversationId();

        for (Message msg : messagesToMark) {
            if (!msg.getConversation().getConversationId().equals(conversationId2)) {
                throw new IllegalArgumentException("Erro: As mensagens fornecidas pertencem a conversas diferentes. Operação inválida.");
            }
        }

        List<Long> messageIdsToUpdate = messagesToMark.stream()
                .filter(msg -> !msg.getSenderId().equals(readerId) && msg.getStatus() != Message.MessageStatus.READ)
                .map(Message::getId)
                .collect(Collectors.toList());

        if (!messageIdsToUpdate.isEmpty()) {
            messageRepository.updateMessageStatus(messageIdsToUpdate, Message.MessageStatus.READ);
        }
    }

    public List<Conversation> getUserConversations(String userId) {
        return conversationRepository.findByUser1IdOrUser2IdOrderByUpdatedAtDesc(userId, userId);
    }
}