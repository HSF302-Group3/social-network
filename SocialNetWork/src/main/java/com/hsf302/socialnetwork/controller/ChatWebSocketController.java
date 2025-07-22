package com.hsf302.socialnetwork.controller;

import com.hsf302.socialnetwork.dto.MessageDTO;
import com.hsf302.socialnetwork.service.impl.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class ChatWebSocketController {
    @Autowired
    private MessageService messageService;

    @MessageMapping("/chat/{convId}/send")
    public void onMessage(@DestinationVariable Long convId, MessageDTO incoming
    ) {
        messageService.sendMessage(convId, incoming.getUserId(), incoming.getMessage());
    }
}
