package com.hsf302.socialnetwork.service.impl;

import com.hsf302.socialnetwork.enity.Conversation;
import com.hsf302.socialnetwork.enity.Message;
import com.hsf302.socialnetwork.enity.Users;
import com.hsf302.socialnetwork.repo.ConversationRepository;
import com.hsf302.socialnetwork.repo.MessageRepository;
import com.hsf302.socialnetwork.repo.UserRepo;
import com.hsf302.socialnetwork.service.IsMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService implements IsMessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private ConversationRepository conversationRepository;
    @Autowired
    private UserRepo userRepository;

    @Override
    public List<Message> findByConversationId(Long conversationId) {
        return messageRepository.findByConversationIdOrderByCreateddateAsc(conversationId);
    }

    @Override
    public Message sendMessage(Long conversationId, Long senderId, String text) {
        Conversation conversation = conversationRepository.findById(conversationId)
                .orElseThrow(() -> new RuntimeException("Conversation not found"));
        Users sender = userRepository.findById(senderId)
                .orElseThrow(() -> new RuntimeException("Sender user not found"));

        Message message = new Message();
        message.setConversation(conversation);
        message.setUsers(sender);
        message.setMessage(text);
        message.setActive(true);
        message.setCreateddate(LocalDateTime.now());

        return messageRepository.save(message);
    }
}
