package com.hsf302.socialnetwork.config;

import com.hsf302.socialnetwork.enity.AddFriend;
import com.hsf302.socialnetwork.enums.Gender;
import com.hsf302.socialnetwork.enums.Role;
import com.hsf302.socialnetwork.enity.Image;
import com.hsf302.socialnetwork.enity.Post;
import com.hsf302.socialnetwork.enity.Users;
import com.hsf302.socialnetwork.repo.AddFriendRepo;
import com.hsf302.socialnetwork.repo.PostRepo;
import com.hsf302.socialnetwork.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.stereotype.Component;

@Component
public class DataInitrizer implements CommandLineRunner {
    @Autowired
    PostRepo postRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    AddFriendRepo addFriend;
    @Override
    public void run(String... args) throws Exception {



        Users users = new Users();
        users.setUsername("hsf302");
        users.setEmail("hsf302@gmail.com");
        users.setPassword("hsf302");
        users.setName("HSF302");
        users.setGender(Gender.F);
        users.setPhone("123456789");
        users.setAvatarUrl("https://www.comingsoon.net/wp-content/uploads/sites/3/gallery/avatar-the-way-of-water-character-posters/avatar-the-way-of-water-3.jpg");
        users.setRole(Role.ADMIN);



        Image i1 = new Image();
        i1.setImageUrl("https://tse3.mm.bing.net/th/id/OIP.x0kZwbyNYdWZWNCuIgSeBwHaE7?r=0&rs=1&pid=ImgDetMain&o=7&rm=3");
        Image i2 = new Image();
        i2.setImageUrl("https://static01.nyt.com/images/2022/12/18/arts/18box-avatar1/merlin_218275158_a7867bfd-31ee-4e89-a051-15f442c403c1-videoSixteenByNine3000.jpg");
        Image i3 = new Image();
        i3.setImageUrl("https://tse3.mm.bing.net/th/id/OIP.JiiX3DGnPD6Jw0Eea7vAAgHaHa?r=0&w=736&h=736&rs=1&pid=ImgDetMain&o=7&rm=3");
        Image i4 = new Image();
        i4.setImageUrl("https://tse3.mm.bing.net/th/id/OIP.JiiX3DGnPD6Jw0Eea7vAAgHaHa?r=0&w=736&h=736&rs=1&pid=ImgDetMain&o=7&rm=3");
        Image i5 = new Image();
        i5.setImageUrl("https://tse3.mm.bing.net/th/id/OIP.JiiX3DGnPD6Jw0Eea7vAAgHaHa?r=0&w=736&h=736&rs=1&pid=ImgDetMain&o=7&rm=3");
        Image i6 = new Image();
        i6.setImageUrl("https://tse3.mm.bing.net/th/id/OIP.JiiX3DGnPD6Jw0Eea7vAAgHaHa?r=0&w=736&h=736&rs=1&pid=ImgDetMain&o=7&rm=3");

        Post post = new Post();
        post.setContent("This is a post first");

        post.addImage(i1);
        post.addImage(i2);
        post.addImage(i3);
        post.addImage(i4);
        post.addImage(i5);
        post.addImage(i6);
        users.addPostCreate(post);



        Users users2 = new Users();
        users2.setUsername("user2");
        users2.setEmail("user2@gmail.com");
        users2.setPassword("hsf302");
        users2.setName("user2");
        users2.setGender(Gender.F);
        users2.setPhone("123456789");
        users2.setAvatarUrl("https://www.comingsoon.net/wp-content/uploads/sites/3/gallery/avatar-the-way-of-water-character-posters/avatar-the-way-of-water-3.jpg");
        users2.setRole(Role.USER);


        Image i12 = new Image();
        i12.setImageUrl("https://tse3.mm.bing.net/th/id/OIP.x0kZwbyNYdWZWNCuIgSeBwHaE7?r=0&rs=1&pid=ImgDetMain&o=7&rm=3");
        Image i22 = new Image();
        i22.setImageUrl("https://static01.nyt.com/images/2022/12/18/arts/18box-avatar1/merlin_218275158_a7867bfd-31ee-4e89-a051-15f442c403c1-videoSixteenByNine3000.jpg");
        Image i33 = new Image();
        i33.setImageUrl("https://tse3.mm.bing.net/th/id/OIP.JiiX3DGnPD6Jw0Eea7vAAgHaHa?r=0&w=736&h=736&rs=1&pid=ImgDetMain&o=7&rm=3");
        Image i44 = new Image();
        i44.setImageUrl("https://tse3.mm.bing.net/th/id/OIP.JiiX3DGnPD6Jw0Eea7vAAgHaHa?r=0&w=736&h=736&rs=1&pid=ImgDetMain&o=7&rm=3");
        Image i55 = new Image();
        i55.setImageUrl("https://tse3.mm.bing.net/th/id/OIP.JiiX3DGnPD6Jw0Eea7vAAgHaHa?r=0&w=736&h=736&rs=1&pid=ImgDetMain&o=7&rm=3");
        Image i66 = new Image();
        i6.setImageUrl("https://tse3.mm.bing.net/th/id/OIP.JiiX3DGnPD6Jw0Eea7vAAgHaHa?r=0&w=736&h=736&rs=1&pid=ImgDetMain&o=7&rm=3");

        Post post2 = new Post();
        post2.setContent("This is a post first");

        post2.addImage(i12);
        post2.addImage(i22);
        post2.addImage(i33);
        post2.addImage(i44);
        post2.addImage(i55);
        post2.addImage(i66);


        Post post3 = new Post();
        post3.setContent("This is a post first");

        users2.addPostCreate(post2);
        users2.addPostCreate(post3);



        Users users3 = new Users();
        users3.setUsername("user3");
        users3.setEmail("user3@gmail.com");
        users3.setPassword("hsf302");
        users3.setName("user3");
        users3.setGender(Gender.F);
        users3.setPhone("123456789");
        users3.setAvatarUrl("https://www.comingsoon.net/wp-content/uploads/sites/3/gallery/avatar-the-way-of-water-character-posters/avatar-the-way-of-water-3.jpg");
        users3.setRole(Role.USER);
        Users users4 = new Users();
        users4.setUsername("user4");
        users4.setEmail("user4@gmail.com");
        users4.setPassword("hsf302");
        users4.setName("user4");
        users4.setGender(Gender.F);
        users4.setPhone("123456789");
        users4.setAvatarUrl("https://www.comingsoon.net/wp-content/uploads/sites/3/gallery/avatar-the-way-of-water-character-posters/avatar-the-way-of-water-3.jpg");
        users4.setRole(Role.USER);
        Users users5 = new Users();
        users5.setUsername("user5");
        users5.setEmail("user5@gmail.com");
        users5.setPassword("hsf302");
        users5.setName("user5");
        users5.setGender(Gender.F);
        users5.setPhone("123456789");
        users5.setAvatarUrl("https://www.comingsoon.net/wp-content/uploads/sites/3/gallery/avatar-the-way-of-water-character-posters/avatar-the-way-of-water-3.jpg");
        users5.setRole(Role.USER);

        userRepo.save(users2);
        userRepo.save(users);
        AddFriend f = new AddFriend();
        f.setSendInvite(users);
        f.setReciveInvite(users2);
        f.setFriended(true);
        addFriend.save(f);

        userRepo.save(users3);
        userRepo.save(users4);
        userRepo.save(users5);
        userRepo.save(users2);
        userRepo.save(users);



    }
}
