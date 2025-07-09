package com.hsf302.socialnetwork.controller;

import com.hsf302.socialnetwork.dto.request.ChangePasswordRequest;
import com.hsf302.socialnetwork.dto.request.UserRegisterRequest;
import com.hsf302.socialnetwork.dto.request.UserUpdateRequest;
import com.hsf302.socialnetwork.dto.response.UserProfileResponse;
import com.hsf302.socialnetwork.enity.Users;
import com.hsf302.socialnetwork.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;



    @GetMapping
    public String loginPage() {
        return "login";
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterRequest request) {
        usersService.register(request);
        return ResponseEntity.ok("User registered successfully");
    }

    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> getProfile(Authentication authentication) {
        Users user = usersService.findByUsername(authentication.name());
        UserProfileResponse response = mapToResponse(user);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserProfileResponse>> getAllUsers() {


        List<Users> users = usersService.findAll();
        List<UserProfileResponse> responses = users.stream()
                .map(this::mapToResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProfile(Authentication authentication, @RequestBody UserUpdateRequest request) {
        usersService.updateProfileByUsername(authentication.name(), request);
        return ResponseEntity.ok("Profile updated");
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(Authentication authentication, @RequestBody ChangePasswordRequest request) {
        usersService.changePasswordByUsername(authentication.name(), request);
        return ResponseEntity.ok("Password changed");
    }

    private UserProfileResponse mapToResponse(Users user) {
        return UserProfileResponse.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .gender(user.getGender())
                .role(user.getRole())
                .active(user.isActive())
                .build();
    }
}

