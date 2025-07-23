package com.hsf302.socialnetwork.service;

import com.hsf302.socialnetwork.enity.Message;

import java.util.List;

public interface IsMessageService {
    List<Message> findByConversationId(Long conversationId);
    Message sendMessage(Long conversationId, Long senderId, String text);

    public Message sendSystemMessage(Long conversationId, String text);

    public List<Message> searchInConversation(Long convId, String keyword);

}
