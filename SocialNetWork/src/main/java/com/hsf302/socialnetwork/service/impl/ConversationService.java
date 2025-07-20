package com.hsf302.socialnetwork.service.impl;

import com.hsf302.socialnetwork.enity.Conversation;
import com.hsf302.socialnetwork.enity.Users;
import com.hsf302.socialnetwork.repo.ConversationRepository;
import com.hsf302.socialnetwork.repo.UserRepo;
import com.hsf302.socialnetwork.service.IsConversationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class ConversationService implements IsConversationService {
    @Autowired
    private ConversationRepository conversationRepository;
    @Autowired
    private UserRepo userRepo;

    @Override
    public Conversation findById(Long id) {
        return conversationRepository.findById(id).orElse(null);
    }

    @Override
    public Optional<Conversation> findPrivateConversationBetweenUsers(Long userId1, Long userId2) {
        return conversationRepository.findPrivateConversationBetweenUsers(userId1, userId2);
    }

    @Override
    public void save(Conversation conversation) {
        conversationRepository.save(conversation);
    }

    @Override
    @Transactional
    public Conversation createGroupConversation(String name, List<Long> memberIds, Users creator) {
        Conversation conv = new Conversation();
        conv.setName(name);
        conv.setType("GROUP");
        conv.setActive(true);

        Users managedCreator = userRepo.findById(creator.getUserId()).orElse(creator);
        // add creator + selected members
        Set<Users> participants = new HashSet<>();
        participants.add(managedCreator);
        for (Long id : memberIds) {
            userRepo.findById(id).ifPresent(participants::add);
        }
        conv.setUsers(participants);
        Conversation savedConv = conversationRepository.save(conv);
        for (Users user : participants) {
            userRepo.save(user);
        }
        return savedConv;
    }

    @Override
    public List<Conversation> findByUserId(Long userId) {
        return conversationRepository.findByUserId(userId);
    }
}
