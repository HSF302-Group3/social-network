package com.hsf302.socialnetwork.service.impl;


import com.hsf302.socialnetwork.dto.request.ChangePasswordRequest;
import com.hsf302.socialnetwork.dto.request.UserRegisterRequest;
import com.hsf302.socialnetwork.dto.request.UserUpdateRequest;
import com.hsf302.socialnetwork.emums.Role;
import com.hsf302.socialnetwork.enity.Users;
import com.hsf302.socialnetwork.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService implements com.hsf302.socialnetwork.service.UsersService {

    private final UserRepo userRepo;
   // private final BCryptPasswordEncoder passwordEncoder ;


    @Override
    public Users register(UserRegisterRequest request) {
        if(userRepo.findByUsername(request.getUsername()).isPresent()){
            throw new RuntimeException("Username already exists");
        }
        if (userRepo.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        Users user = new Users();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setGender(request.getGender());
        user.setRole(Role.USER);
        user.setActive(true);
        return userRepo.save(user);
    }


    @Override
    public Users findByUsername(String username) {
        return userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<Users> findAll() {
        return userRepo.findAll();
    }

    @Override
    public Users updateProfileByUsername(String username, UserUpdateRequest request) {
        Users user = findByUsername(username);
        if (request.getName() != null) user.setName(request.getName());
        if (request.getEmail() != null) user.setEmail(request.getEmail());
        if (request.getPhone() != null) user.setPhone(request.getPhone());
        if (request.getGender() != null) user.setGender(request.getGender());
        return userRepo.save(user);
    }

    @Override
    public void changePasswordByUsername(String username, ChangePasswordRequest request) {
        Users user = findByUsername(username);

        userRepo.save(user);
    }
}

