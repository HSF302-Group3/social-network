package com.hsf302.socialnetwork.service;

import com.hsf302.socialnetwork.enity.Conversation;
import com.hsf302.socialnetwork.enity.Users;

import java.util.List;
import java.util.Optional;

public interface IsConversationService {
    Conversation findById(Long id);
    Optional<Conversation> findPrivateConversationBetweenUsers(Long userId1, Long userId2);
    void save(Conversation conversation);
    Conversation createGroupConversation(String name, List<Long> memberIds, Users creator);
    List<Conversation> findByUserId(Long userId);

}
