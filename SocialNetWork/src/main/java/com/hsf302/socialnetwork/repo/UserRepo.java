package com.hsf302.socialnetwork.repo;

import com.hsf302.socialnetwork.enity.AddFriend;
import com.hsf302.socialnetwork.enity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepo extends JpaRepository<Users, Long> {
    Users findByUsername(String username);

    @Query("""
            
                  select u from Users u where (:search is null  or lower(u.name) like lower(concat('%',:search,'%') ) ) and u.userId in 
                  (
                  select  uu.reciveInvite.userId from AddFriend uu where uu.sendInvite =:user  and uu.friended = :status 
                  )
                  or u.userId in (
                  select  uu.sendInvite.userId from AddFriend uu where uu.reciveInvite =:user  and uu.friended = :status 
                  )
                                  
               """)
    List<Users> getALlFriendByStatus(Users user,boolean status,String search);

    @Query("""
            
                  select u from Users u where 
                  u.userId != :userId 
            
                  and (u.userId not in  ( select  ad.sendInvite.userId from AddFriend  ad where  ad.reciveInvite.userId =:userId)
                  and u.userId not in ( select  ad.reciveInvite.userId from AddFriend  ad where  ad.sendInvite.userId =:userId ))
                   and (:search is null  or lower(u.name) like lower(concat('%',:search,'%') ) )
                  order by u.created desc
            """)
    List<Users> findAllByUserIdNot(Long userId,String search);

@Query("""
     select u from AddFriend u where  u.sendInvite.userId = :userreID and u.reciveInvite.userId = :usersedId
""")
    AddFriend findBySendInvitesAndReciveInvites(Long usersedId, Long userreID);


    @Query("""
            
                  select u from Users u where (:search is null  or lower(u.name) like lower(concat('%',:search,'%') ) )
                               and u.userId in 
                  (
                  select  uu.sendInvite.userId from AddFriend uu where uu.reciveInvite =:user  and uu.friended = false 
                  )
                 
            """)
    List<Users> getALlInvitedFriendByStatus(Users user,String search);
}
