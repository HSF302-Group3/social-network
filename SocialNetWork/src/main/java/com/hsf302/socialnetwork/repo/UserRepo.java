package com.hsf302.socialnetwork.repo;

import com.hsf302.socialnetwork.enity.AddFriend;
import com.hsf302.socialnetwork.enity.Users;
import com.hsf302.socialnetwork.enums.Role;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.EntityGraph;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

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


    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user_conversations (user_id, conversation_id) VALUES (:userId, :conversationId)",
            nativeQuery = true)
    void addConversationToUser(@Param("userId") Long userId, @Param("conversationId") Long conversationId);
    @EntityGraph(attributePaths = {"conversations", "conversations.users", "conversations.messages"})
    @Query("SELECT u FROM Users u WHERE u.userId = :userId")
    Optional<Users> findByIdWithConversations(@Param("userId") Long userId);

    List<Users> findByActive(boolean active);

    List<Users> findByActiveAndNameContainingIgnoreCase(boolean active, @NotNull(message = "Name must not be blank") @NotBlank(message = "Name must not be blank") @Size(min = 5,max = 50,message ="Name must in range 5 to 50 " ) @Pattern(regexp = "^([A-Z][A-Za-z0-9]*)(\\s[A-Z][A-Za-z0-9]*)*$",message = "Name start capital and each word gap space and do not have special character") String name);

    List<Users> findByActiveAndRoleNotContaining(boolean active, @NotNull(message = "Role is required") Role role);

    List<Users> findByActiveAndNameContainingIgnoreCaseAndRoleNotContaining(boolean active, @NotNull(message = "Name must not be blank") @NotBlank(message = "Name must not be blank") @Size(min = 5,max = 50,message ="Name must in range 5 to 50 " ) @Pattern(regexp = "^([A-Z][A-Za-z0-9]*)(\\s[A-Z][A-Za-z0-9]*)*$",message = "Name start capital and each word gap space and do not have special character") String name, @NotNull(message = "Role is required") Role role);

    List<Users> findByActiveAndRoleIsNotLike(boolean active, @NotNull(message = "Role is required") Role role);

    List<Users> findByActiveAndNameContainingIgnoreCaseAndRoleIsNotLike(boolean active, @NotNull(message = "Name must not be blank") @NotBlank(message = "Name must not be blank") @Size(min = 5,max = 50,message ="Name must in range 5 to 50 " ) @Pattern(regexp = "^([A-Z][A-Za-z0-9]*)(\\s[A-Z][A-Za-z0-9]*)*$",message = "Name start capital and each word gap space and do not have special character") String name, @NotNull(message = "Role is required") Role role);
}
