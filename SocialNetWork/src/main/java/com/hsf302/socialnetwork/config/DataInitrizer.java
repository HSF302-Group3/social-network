package com.hsf302.socialnetwork.config;

import com.hsf302.socialnetwork.enity.*;
import com.hsf302.socialnetwork.enums.Gender;
import com.hsf302.socialnetwork.enums.Role;
import com.hsf302.socialnetwork.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitrizer implements CommandLineRunner {
    @Autowired
    PostRepo postRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    AddFriendRepo addFriend;

    @Autowired
    ConversationRepository conversationRepo;

    @Autowired
    MessageRepository messageRepo;

    @Override
    public void run(String... args) throws Exception {

        Users users = new Users();
        users.setUsername("hsf302");
        users.setEmail("hsf302@gmail.com");
        users.setPassword("123");
        users.setName("Hsf302");
        users.setGender(Gender.F);
        users.setPhone("0234567899");
        users.setAvatarUrl("https://www.comingsoon.net/wp-content/uploads/sites/3/gallery/avatar-the-way-of-water-character-posters/avatar-the-way-of-water-3.jpg");
        users.setRole(Role.USER);


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
        users2.setName("Hsf302 User");
        users2.setGender(Gender.F);
        users2.setPhone("0234567891");
        users2.setAvatarUrl("https://cdn.pixabay.com/photo/2025/05/16/07/22/arcades-9603171_1280.jpg");
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
        users3.setName("User3");
        users3.setGender(Gender.F);
        users3.setPhone("0234567892");
        users3.setAvatarUrl("https://www.comingsoon.net/wp-content/uploads/sites/3/gallery/avatar-the-way-of-water-character-posters/avatar-the-way-of-water-3.jpg");
        users3.setRole(Role.USER);
        Users users4 = new Users();
        users4.setUsername("User4");
        users4.setEmail("user4@gmail.com");
        users4.setPassword("123");
        users4.setName("User4");
        users4.setGender(Gender.F);
        users4.setPhone("0234567893");
        users4.setAvatarUrl("https://www.comingsoon.net/wp-content/uploads/sites/3/gallery/avatar-the-way-of-water-character-posters/avatar-the-way-of-water-3.jpg");
        users4.setRole(Role.USER);
        Users users5 = new Users();
        users5.setUsername("User5");
        users5.setEmail("user5@gmail.com");
        users5.setPassword("123");
        users5.setName("User5");
        users5.setGender(Gender.F);
        users5.setPhone("0234567894");
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

        // Tạo conversation 1: private chat giữa users (hsf302) và users2
        Conversation conv1 = new Conversation();
        conv1.setName(users2.getName());
        conv1.setType("PRIVATE");
        conv1.setActive(true);

        Set<Users> participants1 = new HashSet<>();
        participants1.add(users);
        participants1.add(users2);
        conv1.setUsers(participants1);

        conversationRepo.save(conv1);

        Message m1 = new Message();
        m1.setConversation(conv1);
        m1.setUsers(users);
        m1.setMessage("Chào bạn, đây là tin nhắn đầu tiên!");
        m1.setActive(true);
        m1.setCreateddate(LocalDateTime.now().minusMinutes(15));

        Message m2 = new Message();
        m2.setConversation(conv1);
        m2.setUsers(users2);
        m2.setMessage("Chào bạn, mình đã nhận được tin nhắn rồi.");
        m2.setActive(true);
        m2.setCreateddate(LocalDateTime.now().minusMinutes(10));

        messageRepo.save(m1);
        messageRepo.save(m2);

        Conversation conv2 = new Conversation();
        conv2.setName("Nhóm bạn bè thân");
        conv2.setType("GROUP");
        conv2.setActive(true);

        Set<Users> participants2 = new HashSet<>();
        participants2.add(users);
        participants2.add(users3);
        participants2.add(users4);
        participants2.add(users5);
        conv2.setUsers(participants2);

        conversationRepo.save(conv2);

        Message mg1 = new Message();
        mg1.setConversation(conv2);
        mg1.setUsers(users3);
        mg1.setMessage("Chào mọi người, nhóm chúng ta bắt đầu trò chuyện nhé!");
        mg1.setActive(true);
        mg1.setCreateddate(LocalDateTime.now().minusHours(1));

        Message mg2 = new Message();
        mg2.setConversation(conv2);
        mg2.setUsers(users);
        mg2.setMessage("Ok nhé, mình đã sẵn sàng.");
        mg2.setActive(true);
        mg2.setCreateddate(LocalDateTime.now().minusMinutes(55));

        messageRepo.save(mg1);
        messageRepo.save(mg2);

        // Cuối cùng lưu lại user để cập nhật mối quan hệ conversation
        users.setConversations(new HashSet<>(Arrays.asList(conv1, conv2)));
        userRepo.save(users);

        users2.setConversations(new HashSet<>(Arrays.asList(conv1)));
        userRepo.save(users2);

        users3.setConversations(new HashSet<>(Arrays.asList(conv2)));
        userRepo.save(users3);

        users4.setConversations(new HashSet<>(Arrays.asList(conv2)));
        userRepo.save(users4);

        users5.setConversations(new HashSet<>(Arrays.asList(conv2)));
        userRepo.save(users5);

        Users users6 = new Users();
        users6.setUsername("user6");
        users6.setEmail("user6@gmail.com");
        users6.setPassword("hsf302");
        users6.setName("Nguyen Van A");
        users6.setGender(Gender.M);
        users6.setPhone("0234567895");
        users6.setAvatarUrl("https://images.pexels.com/photos/32390716/pexels-photo-32390716.jpeg");
        users6.setRole(Role.USER);

        Users users7 = new Users();
        users7.setUsername("user7");
        users7.setEmail("user7@gmail.com");
        users7.setPassword("hsf302");
        users7.setName("Tran Thi B");
        users7.setGender(Gender.F);
        users7.setPhone("0234567896");
        users7.setAvatarUrl("https://images.pexels.com/photos/33045/lion-wild-africa-african.jpg");
        users7.setRole(Role.USER);

        userRepo.save(users6);
        userRepo.save(users7);

        AddFriend f2 = new AddFriend();
        f2.setSendInvite(users);
        f2.setReciveInvite(users6);
        f2.setFriended(true);
        addFriend.save(f2);

        AddFriend f3 = new AddFriend();
        f3.setSendInvite(users);
        f3.setReciveInvite(users7);
        f3.setFriended(true);
        addFriend.save(f3);

        Conversation conv3 = new Conversation();
        conv3.setName(users6.getName());
        conv3.setType("PRIVATE");
        conv3.setActive(true);

        Set<Users> participants3 = new HashSet<>();
        participants3.add(users);
        participants3.add(users6);
        conv3.setUsers(participants3);
        conversationRepo.save(conv3);

        Message m3 = new Message();
        m3.setConversation(conv3);
        m3.setUsers(users6);
        m3.setMessage("Chào HSF302, rất vui được kết bạn với bạn!");
        m3.setActive(true);
        m3.setCreateddate(LocalDateTime.now().minusMinutes(30));
        messageRepo.save(m3);

        Conversation conv4 = new Conversation();
        conv4.setName(users7.getName());
        conv4.setType("PRIVATE");
        conv4.setActive(true);

        Set<Users> participants4 = new HashSet<>();
        participants4.add(users);
        participants4.add(users7);
        conv4.setUsers(participants4);
        conversationRepo.save(conv4);

        Message m4 = new Message();
        m4.setConversation(conv4);
        m4.setUsers(users7);
        m4.setMessage("Hi, mình là Trần Thị B. Chúng ta đã là bạn bè rồi nhé!");
        m4.setActive(true);
        m4.setCreateddate(LocalDateTime.now().minusMinutes(20));
        messageRepo.save(m4);

        users.setConversations(new HashSet<>(Arrays.asList(conv1, conv2, conv3, conv4)));
        userRepo.save(users);

        users6.setConversations(new HashSet<>(Arrays.asList(conv3)));
        userRepo.save(users6);

        users7.setConversations(new HashSet<>(Arrays.asList(conv4)));
        userRepo.save(users7);

        // Thêm vào cuối method run(), sau khi save users7
        Users users8 = new Users();
        users8.setUsername("testuser");
        users8.setEmail("testuser@gmail.com");
        users8.setPassword("hsf302");
        users8.setName("Uong Van C");
        users8.setGender(Gender.M);
        users8.setPhone("0234567897");
        users8.setAvatarUrl("https://images.pexels.com/photos/771742/pexels-photo-771742.jpeg");
        users8.setRole(Role.USER);

        userRepo.save(users8);

        AddFriend f4 = new AddFriend();
        f4.setSendInvite(users);
        f4.setReciveInvite(users8);
        f4.setFriended(true);
        addFriend.save(f4);

        userRepo.save(users8);
    }
}
