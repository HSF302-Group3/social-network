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
    AddFriendRepo addFriendRepo;

    @Autowired
    ConversationRepository conversationRepo;

    @Autowired
    MessageRepository messageRepo;

    @Override
    public void run(String... args) throws Exception {

        Users user1 = new Users();
        user1.setUsername("Tu");
        user1.setEmail("hsf302@gmail.com");
        user1.setPassword("123");
        user1.setName("Uong Thanh Tu");
        user1.setGender(Gender.F);
        user1.setPhone("0234567899");
        user1.setAvatarUrl("https://www.comingsoon.net/wp-content/uploads/sites/3/gallery/avatar-the-way-of-water-character-posters/avatar-the-way-of-water-3.jpg");
        user1.setRole(Role.USER);

        Image img1 = new Image();
        img1.setImageUrl("https://tse3.mm.bing.net/th/id/OIP.x0kZwbyNYdWZWNCuIgSeBwHaE7?r=0&rs=1&pid=ImgDetMain&o=7&rm=3");

        Image img2 = new Image();
        img2.setImageUrl("https://static01.nyt.com/images/2022/12/18/arts/18box-avatar1/merlin_218275158_a7867bfd-31ee-4e89-a051-15f442c403c1-videoSixteenByNine3000.jpg");

        Image img3 = new Image();
        img3.setImageUrl("https://tse3.mm.bing.net/th/id/OIP.JiiX3DGnPD6Jw0Eea7vAAgHaHa?r=0&w=736&h=736&rs=1&pid=ImgDetMain&o=7&rm=3");

        Post post1 = new Post();
        post1.setContent("This is the first post");

        post1.addImage(img1);
        post1.addImage(img2);
        post1.addImage(img3);
        user1.addPostCreate(post1);

        Users user2 = new Users();
        user2.setUsername("Quyet");
        user2.setEmail("user2@gmail.com");
        user2.setPassword("123");
        user2.setName("Duong Van Quyet");
        user2.setGender(Gender.F);
        user2.setPhone("0234567891");
        user2.setAvatarUrl("https://cdn.pixabay.com/photo/2025/05/16/07/22/arcades-9603171_1280.jpg");
        user2.setRole(Role.USER);

        Post post2 = new Post();
        post2.setContent("This is another first post");

        post2.addImage(img1);
        post2.addImage(img2);
        post2.addImage(img3);

        Post post3 = new Post();
        post3.setContent("Another post content");

        user2.addPostCreate(post2);
        user2.addPostCreate(post3);

        Users user3 = new Users();
        user3.setUsername("Hung");
        user3.setEmail("user3@gmail.com");
        user3.setPassword("123");
        user3.setName("Hung Mien Tay");
        user3.setGender(Gender.F);
        user3.setPhone("0234567892");
        user3.setAvatarUrl("https://www.comingsoon.net/wp-content/uploads/sites/3/gallery/avatar-the-way-of-water-character-posters/avatar-the-way-of-water-3.jpg");
        user3.setRole(Role.USER);

        Users user4 = new Users();
        user4.setUsername("Huy");
        user4.setEmail("user4@gmail.com");
        user4.setPassword("123");
        user4.setName("Tran Nhat Huy");
        user4.setGender(Gender.F);
        user4.setPhone("0234567893");
        user4.setAvatarUrl("https://www.comingsoon.net/wp-content/uploads/sites/3/gallery/avatar-the-way-of-water-character-posters/avatar-the-way-of-water-3.jpg");
        user4.setRole(Role.USER);

        Users user5 = new Users();
        user5.setUsername("Nha");
        user5.setEmail("user5@gmail.com");
        user5.setPassword("123");
        user5.setName("Thanh Nha");
        user5.setGender(Gender.F);
        user5.setPhone("0234567894");
        user5.setAvatarUrl("https://www.comingsoon.net/wp-content/uploads/sites/3/gallery/avatar-the-way-of-water-character-posters/avatar-the-way-of-water-3.jpg");
        user5.setRole(Role.USER);

        userRepo.saveAll(Arrays.asList(user1, user2, user3, user4, user5));

        AddFriend friendship1 = new AddFriend();
        friendship1.setSendInvite(user1);
        friendship1.setReciveInvite(user2);
        friendship1.setFriended(true);
        addFriendRepo.save(friendship1);

        // Private conversation between user1 and user2
        Conversation privateConv1 = new Conversation();
        privateConv1.setName(user2.getName());
        privateConv1.setType("PRIVATE");
        privateConv1.setActive(true);
        privateConv1.setUsers(new HashSet<>(Arrays.asList(user1, user2)));
        conversationRepo.save(privateConv1);

        Message msg1 = new Message();
        msg1.setConversation(privateConv1);
        msg1.setUsers(user1);
        msg1.setMessage("Hello, this is the first message!");
        msg1.setActive(true);
        msg1.setCreateddate(LocalDateTime.now().minusMinutes(15));

        Message msg2 = new Message();
        msg2.setConversation(privateConv1);
        msg2.setUsers(user2);
        msg2.setMessage("Hello, I have received your message.");
        msg2.setActive(true);
        msg2.setCreateddate(LocalDateTime.now().minusMinutes(10));

        messageRepo.saveAll(Arrays.asList(msg1, msg2));

        // Group conversation
        Conversation groupConv = new Conversation();
        groupConv.setName("Close Friends Group");
        groupConv.setType("GROUP");
        groupConv.setActive(true);
        groupConv.setUsers(new HashSet<>(Arrays.asList(user1, user3, user4, user5)));
        conversationRepo.save(groupConv);

        Message groupMsg1 = new Message();
        groupMsg1.setConversation(groupConv);
        groupMsg1.setUsers(user3);
        groupMsg1.setMessage("Hello everyone, let’s start the chat!");
        groupMsg1.setActive(true);
        groupMsg1.setCreateddate(LocalDateTime.now().minusHours(1));

        Message groupMsg2 = new Message();
        groupMsg2.setConversation(groupConv);
        groupMsg2.setUsers(user1);
        groupMsg2.setMessage("Okay, I’m ready.");
        groupMsg2.setActive(true);
        groupMsg2.setCreateddate(LocalDateTime.now().minusMinutes(55));

        messageRepo.saveAll(Arrays.asList(groupMsg1, groupMsg2));

        user1.setConversations(new HashSet<>(Arrays.asList(privateConv1, groupConv)));
        user2.setConversations(new HashSet<>(Arrays.asList(privateConv1)));
        user3.setConversations(new HashSet<>(Arrays.asList(groupConv)));
        user4.setConversations(new HashSet<>(Arrays.asList(groupConv)));
        user5.setConversations(new HashSet<>(Arrays.asList(groupConv)));

        userRepo.saveAll(Arrays.asList(user1, user2, user3, user4, user5));

        // More users
        Users user6 = new Users();
        user6.setUsername("user6");
        user6.setEmail("user6@gmail.com");
        user6.setPassword("123");
        user6.setName("Nguyen Van A");
        user6.setGender(Gender.M);
        user6.setPhone("0234567895");
        user6.setAvatarUrl("https://images.pexels.com/photos/32390716/pexels-photo-32390716.jpeg");
        user6.setRole(Role.USER);

        Users user7 = new Users();
        user7.setUsername("user7");
        user7.setEmail("user7@gmail.com");
        user7.setPassword("123");
        user7.setName("Tran Thi B");
        user7.setGender(Gender.F);
        user7.setPhone("0234567896");
        user7.setAvatarUrl("https://images.pexels.com/photos/33045/lion-wild-africa-african.jpg");
        user7.setRole(Role.USER);

        userRepo.saveAll(Arrays.asList(user6, user7));

        AddFriend friendship2 = new AddFriend();
        friendship2.setSendInvite(user1);
        friendship2.setReciveInvite(user6);
        friendship2.setFriended(true);
        addFriendRepo.save(friendship2);

        AddFriend friendship3 = new AddFriend();
        friendship3.setSendInvite(user1);
        friendship3.setReciveInvite(user7);
        friendship3.setFriended(true);
        addFriendRepo.save(friendship3);

        // Conversations with user6 and user7
        Conversation convWithUser6 = new Conversation();
        convWithUser6.setName(user6.getName());
        convWithUser6.setType("PRIVATE");
        convWithUser6.setActive(true);
        convWithUser6.setUsers(new HashSet<>(Arrays.asList(user1, user6)));
        conversationRepo.save(convWithUser6);

        Message msg3 = new Message();
        msg3.setConversation(convWithUser6);
        msg3.setUsers(user6);
        msg3.setMessage("Hello HSF302, nice to meet you!");
        msg3.setActive(true);
        msg3.setCreateddate(LocalDateTime.now().minusMinutes(30));
        messageRepo.save(msg3);

        Conversation convWithUser7 = new Conversation();
        convWithUser7.setName(user7.getName());
        convWithUser7.setType("PRIVATE");
        convWithUser7.setActive(true);
        convWithUser7.setUsers(new HashSet<>(Arrays.asList(user1, user7)));
        conversationRepo.save(convWithUser7);

        Message msg4 = new Message();
        msg4.setConversation(convWithUser7);
        msg4.setUsers(user7);
        msg4.setMessage("Hi, I'm Tran Thi B. We are now friends!");
        msg4.setActive(true);
        msg4.setCreateddate(LocalDateTime.now().minusMinutes(20));
        messageRepo.save(msg4);

        user1.setConversations(new HashSet<>(Arrays.asList(privateConv1, groupConv, convWithUser6, convWithUser7)));
        user6.setConversations(new HashSet<>(Arrays.asList(convWithUser6)));
        user7.setConversations(new HashSet<>(Arrays.asList(convWithUser7)));

        userRepo.saveAll(Arrays.asList(user1, user6, user7));

        // Final user
        Users user8 = new Users();
        user8.setUsername("testuser");
        user8.setEmail("testuser@gmail.com");
        user8.setPassword("123");
        user8.setName("Uong Van C");
        user8.setGender(Gender.M);
        user8.setPhone("0234567897");
        user8.setAvatarUrl("https://images.pexels.com/photos/771742/pexels-photo-771742.jpeg");
        user8.setRole(Role.ADMIN);

        userRepo.save(user8);

        AddFriend friendship4 = new AddFriend();
        friendship4.setSendInvite(user1);
        friendship4.setReciveInvite(user8);
        friendship4.setFriended(true);
        addFriendRepo.save(friendship4);

        userRepo.save(user8);
    }
}
