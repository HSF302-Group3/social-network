package com.hsf302.socialnetwork.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConversationDTO {
    private Long id;
    private String name;
    private String type;
    private String lastMessage;
}
