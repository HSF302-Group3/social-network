package com.hsf302.socialnetwork.service.impl;


import com.hsf302.socialnetwork.dto.request.ChangePasswordRequest;
import com.hsf302.socialnetwork.dto.request.UserRegisterRequest;
import com.hsf302.socialnetwork.dto.request.UserUpdateRequest;
import com.hsf302.socialnetwork.emums.Role;
import com.hsf302.socialnetwork.enity.Users;
import com.hsf302.socialnetwork.repository.UsersRepository;
import com.hsf302.socialnetwork.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder passwordEncoder ;


    @Override
    public Users register(UserRegisterRequest request) {
        if(usersRepository.findByUsername(request.getUsername()).isPresent()){
            throw new RuntimeException("Username already exists");
        }
        if (usersRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        Users user = new Users();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setGender(request.getGender());
        user.setRole(Role.USER);
        user.setActive(true);
        return usersRepository.save(user);
    }


    @Override
    public Users findByUsername(String username) {
        return usersRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    @Override
    public Users updateProfileByUsername(String username, UserUpdateRequest request) {
        Users user = findByUsername(username);
        if (request.getName() != null) user.setName(request.getName());
        if (request.getEmail() != null) user.setEmail(request.getEmail());
        if (request.getPhone() != null) user.setPhone(request.getPhone());
        if (request.getGender() != null) user.setGender(request.getGender());
        return usersRepository.save(user);
    }

    @Override
    public void changePasswordByUsername(String username, ChangePasswordRequest request) {
        Users user = findByUsername(username);
        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new RuntimeException("Old password is incorrect");
        }
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        usersRepository.save(user);
    }
}

