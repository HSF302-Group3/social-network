package com.hsf302.socialnetwork.controller;


import com.hsf302.socialnetwork.enity.Users;
import com.hsf302.socialnetwork.enums.Role;
import com.hsf302.socialnetwork.service.impl.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String login() {
        return "login";
    }
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/doLogin")
    public String doLogin(@RequestParam String email, @RequestParam String password, Model model, RedirectAttributes redirectAttributes, HttpSession session) {
        Users account = userService.authenticate(email, password);
        if(account == null || !account.isActive()) {
            redirectAttributes.addFlashAttribute("error", "Invalid email or password");
            return "redirect:/login";
        }

        if(!account.isActive()) {
            redirectAttributes.addFlashAttribute("error", "Account is not active");
            return "redirect:/login";
        }
        session.setAttribute("user", account);
        if(account.getRole().equals(Role.USER)) {
            return "redirect:/posts";
        }
        if(account.getRole().equals(Role.ADMIN)) {
            return "redirect:/user/getAll";
        }
        return "redirect:/posts";

    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
    


}
