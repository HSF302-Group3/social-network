package com.hsf302.socialnetwork.service;

import com.hsf302.socialnetwork.dto.request.ChangePasswordRequest;
import com.hsf302.socialnetwork.dto.request.UserLoginRequest;
import com.hsf302.socialnetwork.dto.request.UserRegisterRequest;
import com.hsf302.socialnetwork.dto.request.UserUpdateRequest;
import com.hsf302.socialnetwork.enity.Users;


import java.util.List;

public interface UsersService {
    Users register(UserRegisterRequest request);
    Users findByUsername(String username);
    List<Users> findAll();
    Users updateProfileByUsername(String username, UserUpdateRequest request);
    void changePasswordByUsername(String username, ChangePasswordRequest request);
}

