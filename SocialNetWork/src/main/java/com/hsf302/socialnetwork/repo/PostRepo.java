package com.hsf302.socialnetwork.repo;

import com.hsf302.socialnetwork.enity.Post;
import com.hsf302.socialnetwork.enity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PostRepo extends JpaRepository<Post, Long> {
    List<Post> findAllByUsercreate(Users usercreate);

    List<Post> findAllByUsercreateAndActive(Users usercreate, boolean active);


    @Query("""
           select p from Post p where p.usercreate = :usercreate and p.active = :active
           and p.id not in (select r.post.id from Report  r where r.banded = true)
           order by p.createdAt desc
           
        
""")
    Page<Post> findAllByUsercreateAndActiveAndCreatedAtOrderByCreatedAtDesc(Users usercreate, boolean active, Pageable page);


    @Query("""
                       select p from Post p where    p.active = :active
            
                       and p.usercreate = :usercreate 
                      and not exists (
                       select u from Post p2 join p2.users u
                      where p2 = p and u = :like
                      )
                       order by p.createdAt desc
            
            """)
    List<Post> findAllByUsercreateAndActiveAndCreatedAtOrderByCreatedAtDescAndNotLike(Users usercreate, boolean active,Users like);

    @Query("""
                       select p from Post p where    p.active = true   and p.id not in (select r.post.id from Report  r where r.banded = true)
            
                       and (p.usercreate.userId in(
                        select  uu.reciveInvite.userId from AddFriend uu where uu.sendInvite =:user  and uu.friended = true 
                        )
                        or p.usercreate.userId in (
                        select  uu.sendInvite.userId from AddFriend uu where uu.reciveInvite =:user  and uu.friended = true 
                        ))
            
            """)
    Page<Post> getPostOfFriend(Users user,Pageable pageable);
}
