package com.hsf302.socialnetwork.controller;

import com.hsf302.socialnetwork.enity.Users;
import com.hsf302.socialnetwork.repo.UserRepo;
import com.hsf302.socialnetwork.service.impl.UserService;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public String user(org.springframework.ui.Model model, HttpSession session,@RequestParam(name = "keysearch",required = false) String search) {
        Users user = (Users) session.getAttribute("user");
        model.addAttribute("fr", userService.allUser(user,search));
        return "friend";
    }

    @GetMapping("/friends")
    public String friended(Model model,HttpSession session,@RequestParam(name = "keysearch",required = false) String search) {
        Users user = (Users) session.getAttribute("user");
        System.out.println(user);
        System.out.println(search+"search");
        model.addAttribute("unfr", userService.friendInvites(user,search));
        model.addAttribute("fr", userService.friends(user,search));
        return "friend";
    }

    @GetMapping("/addfriend/{id}")
    public String addfriend(HttpSession session, @PathVariable Long id) {
        Users user = (Users) session.getAttribute("user");
        userService.addFriend(id, user);

        return "redirect:/user/friends";

    }

    @GetMapping("/updaterelation/{id}")
    public String updaterelation(HttpSession session, @RequestParam("status") boolean status,@PathVariable Long id) {
        Users user = (Users) session.getAttribute("user");
        userService.updateRelation(user,id,status);
        return "redirect:/user/friends";
    }
}
