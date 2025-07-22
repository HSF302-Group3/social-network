package com.hsf302.socialnetwork.service.impl;

import com.hsf302.socialnetwork.dto.MessageDTO;
import com.hsf302.socialnetwork.enity.Conversation;
import com.hsf302.socialnetwork.enity.Message;
import com.hsf302.socialnetwork.enity.Users;
import com.hsf302.socialnetwork.repo.ConversationRepository;
import com.hsf302.socialnetwork.repo.MessageRepository;
import com.hsf302.socialnetwork.repo.UserRepo;
import com.hsf302.socialnetwork.service.IsMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class MessageService implements IsMessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private ConversationRepository conversationRepository;
    @Autowired
    private UserRepo userRepository;
    @Autowired
    private SimpMessagingTemplate ws;

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

        Message saved = messageRepository.save(message);

        var dto = new MessageDTO(
                saved.getUsers().getUserId(),
                saved.getMessage(),
                saved.getCreateddate().format(DateTimeFormatter.ofPattern("HH:mm")),
                false,
                saved.getUsers() != null ? saved.getUsers().getAvatarUrl() : null
        );

        ws.convertAndSend("/topic/conversation/" + conversationId, dto);


        return saved;
    }

    public Message sendSystemMessage(Long conversationId, String text) {
        Conversation conv = conversationRepository.findById(conversationId)
                .orElseThrow(() -> new RuntimeException("Conversation not found"));
        Message message = new Message();
        message.setConversation(conv);
        message.setUsers(null);
        message.setMessage(text);
        message.setSystem(true);
        message.setCreateddate(LocalDateTime.now());
        message.setActive(true);

        Message saved = messageRepository.save(message);

        var dto = new MessageDTO(
                saved.getUsers().getUserId(),
                saved.getMessage(),
                saved.getCreateddate().format(DateTimeFormatter.ofPattern("HH:mm")),
                true,
                saved.getUsers() != null ? saved.getUsers().getAvatarUrl() : null
        );

        ws.convertAndSend("/topic/conversation/" + conversationId, dto);

        return saved;
    }

}
