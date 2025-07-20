package com.hsf302.socialnetwork.repo;

import com.hsf302.socialnetwork.enity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByConversationIdOrderByCreateddateAsc(Long conversationId);
}
