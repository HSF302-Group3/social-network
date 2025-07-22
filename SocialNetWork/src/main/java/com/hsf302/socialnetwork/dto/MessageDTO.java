package com.hsf302.socialnetwork.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDTO {
    private Long userId;
    private String message;
    private String time;
    private Boolean system;
    private String avatarUrl;

    public MessageDTO() {
    }

    public MessageDTO(Long userId, String message, String time, Boolean system, String avatarUrl) {
        this.userId = userId;
        this.message = message;
        this.time = time;
        this.system = system;
        this.avatarUrl = avatarUrl;
    }
}
