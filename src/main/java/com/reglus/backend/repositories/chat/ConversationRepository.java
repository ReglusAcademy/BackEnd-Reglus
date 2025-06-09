package com.reglus.backend.repositories.chat;

import com.reglus.backend.model.entities.users.chat.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, String> {
    @Query("SELECT c FROM Conversation c WHERE (c.user1Id = :userAId AND c.user2Id = :userBId) OR (c.user1Id = :userBId AND c.user2Id = :userAId)")
    Optional<Conversation> findByParticipants(String userAId, String userBId);
    List<Conversation> findByUser1IdOrUser2IdOrderByUpdatedAtDesc(String userId1, String userId2);
}