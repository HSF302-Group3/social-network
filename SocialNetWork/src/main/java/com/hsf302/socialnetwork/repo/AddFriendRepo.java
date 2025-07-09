package com.hsf302.socialnetwork.repo;

import com.hsf302.socialnetwork.enity.AddFriend;
import com.hsf302.socialnetwork.enity.Users;
import com.hsf302.socialnetwork.service.IsUserService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddFriendRepo extends JpaRepository<AddFriend, Long> {


}
