package com.hsf302.socialnetwork.controller;

import com.hsf302.socialnetwork.enity.Users;
import com.hsf302.socialnetwork.enums.Role;
import com.hsf302.socialnetwork.repo.UserRepo;
import com.hsf302.socialnetwork.service.impl.UserService;
import jakarta.annotation.Priority;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public String user(org.springframework.ui.Model model, HttpSession session,@RequestParam(name = "keysearch",required = false) String search) {

        model.addAttribute("search", search);
        Users user = (Users) session.getAttribute("user");
        if(user == null) {
            return "redirect:/login";
        }

        model.addAttribute("fr", userService.allUser(user,search));
        return "friend";
    }

    @GetMapping("/friends")
    public String friended(Model model,HttpSession session,@RequestParam(name = "keysearch",required = false) String search) {
        Users user = (Users) session.getAttribute("user");
        if(user == null) {
            return "redirect:/login";
        }
        model.addAttribute("search", search);
        System.out.println(user);
        System.out.println(search+"search");
        model.addAttribute("unfr", userService.friendInvites(user,search));
        model.addAttribute("fr", userService.friends(user,search));
        return "friend";
    }

    @GetMapping("/addfriend/{id}")
    public String addfriend(HttpSession session, @PathVariable Long id) {
        Users user = (Users) session.getAttribute("user");
        if(user == null) {
            return "redirect:/login";
        }
        userService.addFriend(id, user);

        return "redirect:/user/friends";

    }

    @GetMapping("/updaterelation/{id}")
    public String updaterelation(HttpSession session, @RequestParam("status") boolean status,@PathVariable Long id) {
        Users user = (Users) session.getAttribute("user");
        if(user == null) {
            return "redirect:/login";
        }
        userService.updateRelation(user,id,status);
        return "redirect:/user/friends";
    }

    @GetMapping("/update")
    public String update(Model model,HttpSession session) {

        Users user = (Users) session.getAttribute("user");
        if(user == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);
          return "editProfile";
    }
    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("user") Users user, BindingResult bindingResult,Model model,HttpSession session) {

        Users account = (Users) session.getAttribute("user");
        if(account == null) {
            return "redirect:/login";
        }
        if(bindingResult.hasErrors()) {
            return "editProfile";
        }

        userService.saveAccount(user);
        session.setAttribute("user", userService.findById(user.getUserId()));
         return "redirect:/posts";
    }

    @GetMapping("/getAll")
    private String getALl(HttpSession session, Model model,@RequestParam(defaultValue ="true") boolean active,@RequestParam(required = false) String search) {


        model.addAttribute("search", search);
        model.addAttribute("active", active);
        Users user = (Users) session.getAttribute("user");
        if(user == null || !user.getRole().equals(Role.ADMIN)) {
            return "redirect:/login";
        }
        model.addAttribute("users", userService.getALlUsers(active,search));
        return "admind-users";
    }
    @GetMapping("/active/{id}")
    public String active(HttpSession session, @PathVariable Long id) {
        Users user = (Users) session.getAttribute("user");
        if(user == null || !user.getRole().equals(Role.ADMIN)) {
            return "redirect:/login";
        }
        userService.activeUser(id);
        return "redirect:/user/getAll";
    }
    @GetMapping("/inactive/{id}")
    public String inactive(HttpSession session, @PathVariable Long id) {
        Users user = (Users) session.getAttribute("user");
        if(user == null || !user.getRole().equals(Role.ADMIN)) {
            return "redirect:/login";
        }
        userService.inactiveUser(id);
        return "redirect:/user/getAll";
    }
}
