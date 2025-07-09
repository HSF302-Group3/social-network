package com.hsf302.socialnetwork.dto.request;

import com.hsf302.socialnetwork.emums.Gender;
import lombok.Data;


@Data
public class UserRegisterRequest {
    private String username;
    private String name;
    private String password;
    private String email;
    private String phone;
    private Gender gender;

}
