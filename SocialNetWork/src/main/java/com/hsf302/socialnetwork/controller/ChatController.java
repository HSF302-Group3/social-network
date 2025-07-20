package com.hsf302.socialnetwork.controller;

import com.hsf302.socialnetwork.enity.Conversation;
import com.hsf302.socialnetwork.enity.Message;
import com.hsf302.socialnetwork.enity.Users;
import com.hsf302.socialnetwork.service.impl.ConversationService;
import com.hsf302.socialnetwork.service.impl.MessageService;
import com.hsf302.socialnetwork.service.impl.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/chat")
public class ChatController {

    private final ConversationService conversationService;
    private final MessageService messageService;
    private final UserService userService;

    public ChatController(ConversationService conversationService,
                          MessageService messageService,
                          UserService userService) {
        this.conversationService = conversationService;
        this.messageService = messageService;
        this.userService = userService;
    }

    @GetMapping
    public String chatHome(Model model, HttpSession session) {
        Users currentUser = (Users) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        }
        List<Conversation> conversations = conversationService.findByUserId(currentUser.getUserId());
        model.addAttribute("conversations", conversations);

        // Lấy conversation đầu tiên làm mặc định
        Conversation currentConversation = conversations.stream().findFirst().orElse(null);
        model.addAttribute("currentConversation", currentConversation);

        if (currentConversation != null) {
            List<Message> messages = messageService.findByConversationId(currentConversation.getId());
            model.addAttribute("messages", messages);
        }

        model.addAttribute("currentUser", currentUser);

        return "chat";
    }

    @GetMapping("/{conversationId}")
    public String chatDetail(@PathVariable Long conversationId, Model model, HttpSession session) {
        Users currentUser = (Users) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        }
        List<Conversation> conversations = conversationService.findByUserId(currentUser.getUserId());

        Conversation currentConversation = conversationService.findById(conversationId);
        List<Message> messages = messageService.findByConversationId(conversationId);


        if(currentConversation != null && currentConversation.getType().equalsIgnoreCase("Private")) {

            currentConversation.getUsers().forEach(user -> {
                if(!user.getUserId().equals(currentUser.getUserId())) {
                    model.addAttribute("frim", user.getAvatarUrl());
                }

            });

        }else {
            model.addAttribute("frim", "https://cdn-icons-png.flaticon.com/512/6387/6387947.png");
        }
        model.addAttribute("conversations", conversations);
        model.addAttribute("currentConversation", currentConversation);
        model.addAttribute("messages", messages);
        model.addAttribute("currentUser", currentUser);

        return "chat";
    }

    @PostMapping("/send")
    public String sendMessage(@RequestParam Long conversationId,
                              @RequestParam String message,
                              HttpSession session) {
        Users currentUser = (Users) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        }
        messageService.sendMessage(conversationId, currentUser.getUserId(), message);
        return "redirect:/chat/" + conversationId;
    }

    @GetMapping("/search")
    public String searchFriends(@RequestParam(required = false) String keyword, Model model, HttpSession session) {
        Users currentUser = (Users) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        }
        // Tìm bạn bè phù hợp (bạn implement trong userService)
        List<Users> friends = userService.friends(currentUser, keyword);

        List<Conversation> conversations = conversationService.findByUserId(currentUser.getUserId());
        Conversation currentConversation = conversations.stream().findFirst().orElse(null);
        List<Message> messages = currentConversation != null
                ? messageService.findByConversationId(currentConversation.getId())
                : Collections.emptyList();

        model.addAttribute("currentUser", currentUser);
        model.addAttribute("conversations", conversations);
        model.addAttribute("currentConversation", currentConversation);
        model.addAttribute("messages", messages);

        model.addAttribute("friends", friends);
        model.addAttribute("keyword", keyword);

        return "chat";
    }

    @GetMapping("/start/{friendUserId}")
    public String startConversation(@PathVariable Long friendUserId, HttpSession session) {
        Users currentUser = (Users) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        }
        Users friend = userService.findById(friendUserId);

        currentUser = userService.findById(currentUser.getUserId());

        if (friend == null) {
            // Xử lý nếu không tìm thấy user
            return "redirect:/chat";
        }

        // Kiểm tra xem đã có conversation PRIVATE giữa currentUser và friend chưa
        Optional<Conversation> existingConv = conversationService
                .findPrivateConversationBetweenUsers(currentUser.getUserId(), friendUserId);

        Conversation conversation;

        if (existingConv.isPresent()) {
            conversation = existingConv.get();
        } else {
            // Nếu chưa có, tạo conversation mới
            conversation = new Conversation();
            conversation.setName("Chat với " + friend.getName());
            conversation.setType("PRIVATE");
            conversation.setActive(true);

            conversation.setUsers(new HashSet<>(Arrays.asList(currentUser, friend)));
            conversationService.save(conversation);
            currentUser.getConversations().add(conversation);
            friend.getConversations().add(conversation);
            userService.save(currentUser);
            userService.save(friend);
        }
        Users fresh = userService.findById(currentUser.getUserId());
        session.setAttribute("user", fresh);
        return "redirect:/chat/" + conversation.getId();
    }

    @GetMapping("/new-group")
    public String newGroupForm(Model model, HttpSession session) {
        Users currentUser = (Users) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        }
        // Lấy danh sách bạn bè (giả sử userService.friends(...) trả về friend list)
        List<Users> friends = userService.friends(currentUser, null);
        model.addAttribute("friends", friends);
        return "chat-new-group";  // Thymeleaf template
    }

    // Xử lý submit tạo nhóm
    @PostMapping("/create-group")
    public String createGroup(@RequestParam String name, @RequestParam(value = "members", required = false) List<Long> memberIds, HttpSession session
    ) {
        if (memberIds == null) {
            memberIds = new ArrayList<>();
        }
        Users currentUser = (Users) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        }
        Conversation newGroup = conversationService.createGroupConversation(name, memberIds, currentUser);
        Users refreshedUser = userService.findById(currentUser.getUserId());
        refreshedUser.getConversations().add(newGroup);
        userService.save(refreshedUser);
        for (Long memberId : memberIds) {
            Users member = userService.findById(memberId);
            member.getConversations().add(newGroup);
            userService.save(member);
        }
        session.setAttribute("user", refreshedUser);
        return "redirect:/chat/" + newGroup.getId();
    }


}
