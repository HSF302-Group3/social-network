package com.hsf302.socialnetwork.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConversationEventDTO {
    private String type;
    private ConversationDTO data;
}
