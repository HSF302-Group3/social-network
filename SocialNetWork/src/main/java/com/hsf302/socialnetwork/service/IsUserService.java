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
}
