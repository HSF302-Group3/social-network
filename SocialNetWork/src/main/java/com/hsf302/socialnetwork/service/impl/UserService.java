package com.hsf302.socialnetwork.service.impl;

import com.hsf302.socialnetwork.enity.AddFriend;
import com.hsf302.socialnetwork.enity.Users;
import com.hsf302.socialnetwork.enums.Role;
import com.hsf302.socialnetwork.repo.AddFriendRepo;
import com.hsf302.socialnetwork.repo.AuthRepo;
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
    @Autowired
    AuthRepo authRepo;
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

        System.out.println(user.getUserId());
        System.out.println(id);
        AddFriend addFriends  = userRepo.findBySendInvitesAndReciveInvites(user.getUserId(),id);

         if(isFriend){
             addFriends.setFriended(isFriend);
             addFriendRepo.save(addFriends);
         }
         else {

             addFriendRepo.delete(addFriends);
         }


    }


    public Users authenticate(String email, String password) {
        Users account = authRepo.findByEmailAndPassword(email,password);
        return account;
    }


    public  void saveAccount(Users user) {
        authRepo.save(user);
    }

    @Override
    public Users findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public Users findById(Long id) {
        return userRepo.findById(id)
                .map(user -> {
                    user.getConversations().size();
                    return user;
                })
                .orElse(null);
    }

    @Override
    public void save(Users user) {
        userRepo.save(user);
    }

 public List<Users> getALlUsers(boolean active ,String search) {
          if(search == null || search.trim().isEmpty()) {
              return userRepo.findByActiveAndRoleIsNotLike(active, Role.ADMIN);
          }
          return userRepo.findByActiveAndNameContainingIgnoreCaseAndRoleIsNotLike(active,search,Role.ADMIN);
 }

    @Override
    public void activeUser(Long id) {
        Users user = findById(id);
        user.setActive(true);
        userRepo.save(user);

    }

    @Override
    public void inactiveUser(Long id) {
        Users user = findById(id);
        user.setActive(false);
        userRepo.save(user);
    }
    public Users findByEmail(String email) {
        return userRepo.findByEmail(email);
    }


}
