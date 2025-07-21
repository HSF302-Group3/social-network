package com.hsf302.socialnetwork.service;

import com.hsf302.socialnetwork.enity.Users;
import org.apache.catalina.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IsUserService {


    void addFriend(Long friendI, Users user);
    List<Users> friends(Users user,String search);
    List<Users>  allUser(Users user,String search);
    List<Users> friendInvites(Users user,String search);
    void updateRelation(Users user,Long id , boolean isFriend);
    Users findByUsername(String username);
    Users findById(Long id);
    void save(Users user);
    Page<Users> getALlUsers(boolean action , String search, Pageable pageable);
    void activeUser(Long id);
    void inactiveUser(Long id);
}
