package com.hsf302.socialnetwork.service.impl;

import com.hsf302.socialnetwork.enity.AddFriend;
import com.hsf302.socialnetwork.enity.Users;
import com.hsf302.socialnetwork.repo.AddFriendRepo;
import com.hsf302.socialnetwork.repo.UserRepo;
import com.hsf302.socialnetwork.service.IsUserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IsUserService {

    @Autowired
    private AddFriendRepo addFriendRepo;
    @Autowired
    private UserRepo userRepo;
    @Override
    public void addFriend(Long friendI, Users user) {
        Users recive = userRepo.findById(friendI).get();
        Users users = userRepo.findByUsername(user.getUsername());
        AddFriend a  = new AddFriend();
        a.setSendInvite(users);
        users.getSendInvites().add(a);

        a.setReciveInvite(recive);
        recive.getReciveInvites().add(a);

        addFriendRepo.save(a);
        userRepo.save(users);
        userRepo.save(recive);

    }

    @Override
    public List<Users> friends(Users user,String search) {
        List<Users>  friend = null;
        if(search != null && search.trim().isEmpty()) {
            friend   = userRepo.getALlFriendByStatus(user,true,null);
            return friend;
        }
        return userRepo.getALlFriendByStatus(user,true,search);


    }

    @Override
    public List<Users> allUser(Users user,String search) {

        if(search != null && search.trim().isEmpty()) {
            return userRepo.findAllByUserIdNot(user.getUserId(),search);
        }
        return userRepo.findAllByUserIdNot(user.getUserId(),search);

    }

    @Override
    public List<Users> friendInvites(Users user,String search) {



        List<Users> list = null;
         if( search != null && search.trim().isEmpty())
         {
             list = userRepo.getALlInvitedFriendByStatus(user,null);
             return list;
         }

        return userRepo.getALlInvitedFriendByStatus(user,search);

    }

    @Override
    public void updateRelation(Users user, Long id, boolean isFriend) {
        AddFriend addFriends = userRepo.findBySendInvitesAndReciveInvites(user.getUserId(),id);
         if(isFriend){

             addFriends.setFriended(isFriend);
             addFriendRepo.save(addFriends);
         }
         else {
             addFriendRepo.delete(addFriends);
         }


    }
}
