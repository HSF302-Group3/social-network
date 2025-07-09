package com.hsf302.socialnetwork.dto.response;

import com.hsf302.socialnetwork.emums.Gender;
import com.hsf302.socialnetwork.emums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileResponse {
    private Long userId;
    private String username;
    private String name;
    private String email;
    private String phone;
    private Gender gender;
    private Role role;
    private boolean active;
}
