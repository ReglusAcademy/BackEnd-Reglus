package com.reglus.backend.repositories.chat;

import com.reglus.backend.model.entities.users.chat.Message;
import com.reglus.backend.model.entities.users.chat.Message.MessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByConversationConversationIdOrderByTimestampAsc(String conversationId);

    @Query("SELECT m FROM Message m WHERE m.conversation.conversationId = :conversationId AND m.senderId <> :userId AND m.status <> 'READ' ORDER BY m.timestamp ASC")
    List<Message> findUnreadMessagesInConversation(@Param("conversationId") String conversationId, @Param("userId") String userId);

    @Modifying
    @Query("UPDATE Message m SET m.status = :status WHERE m.id IN :messageIds")
    void updateMessageStatus(@Param("messageIds") List<Long> messageIds, @Param("status") MessageStatus status);
}