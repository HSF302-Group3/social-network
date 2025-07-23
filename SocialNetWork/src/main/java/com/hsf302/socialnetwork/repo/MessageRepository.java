package com.hsf302.socialnetwork.repo;

import com.hsf302.socialnetwork.enity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByConversationIdOrderByCreateddateAsc(Long conversationId);

    List<Message> findByConversationIdAndSystemFalseOrderByCreateddateAsc(Long conversationId);

    List<Message> findByConversationIdAndSystemFalseAndMessageContainingIgnoreCaseOrderByCreateddateAsc(Long conversationId, String keyword);

    Page<Message> findByConversationId(Long convId, Pageable pageable);

    Page<Message> findByConversationIdAndSystemFalseAndMessageContainingIgnoreCase(
            Long convId, String kw, Pageable p);

    List<Message> findTop8ByConversationIdAndCreateddateBeforeOrderByCreateddateDesc(
            Long convId, LocalDateTime before);

    List<Message> findTop8ByConversationIdOrderByCreateddateDesc(Long convId);

}
