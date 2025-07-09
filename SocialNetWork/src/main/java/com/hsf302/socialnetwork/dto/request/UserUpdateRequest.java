package com.hsf302.socialnetwork.dto.request;

import com.hsf302.socialnetwork.emums.Gender;
import lombok.Data;


@Data
public class UserUpdateRequest {
    private String name;
    private String phone;
    private String email;
    private Gender gender;
}
