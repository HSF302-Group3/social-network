package com.hsf302.socialnetwork.service;

import com.hsf302.socialnetwork.enity.Users;
import org.apache.catalina.User;

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
    List<Users> getALlUsers(boolean action ,String search);
    void activeUser(Long id);
    void inactiveUser(Long id);
}
