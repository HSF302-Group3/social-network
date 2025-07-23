package com.hsf302.socialnetwork.repo;

import com.hsf302.socialnetwork.enity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    @Query("SELECT c FROM Conversation c JOIN c.users u " +
            "WHERE c.type = 'PRIVATE' " +
            "GROUP BY c.id, c.active, c.name, c.type,c.createdBy " +
            "HAVING COUNT(u) = 2 AND SUM(CASE WHEN u.userId IN (:userId1, :userId2) THEN 1 ELSE 0 END) = 2")
    Optional<Conversation> findPrivateConversationBetweenUsers(@Param("userId1") Long userId1, @Param("userId2") Long userId2);

    @Query("SELECT DISTINCT c FROM Conversation c JOIN c.users u WHERE u.userId = :userId AND c.active = true ORDER BY c.id DESC")
    List<Conversation> findByUserId(@Param("userId") Long userId);
}
