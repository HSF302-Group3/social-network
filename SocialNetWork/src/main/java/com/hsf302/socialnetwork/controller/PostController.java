package com.hsf302.socialnetwork.controller;

import com.hsf302.socialnetwork.enity.Post;
import com.hsf302.socialnetwork.enity.Users;
import com.hsf302.socialnetwork.repo.UserRepo;
import com.hsf302.socialnetwork.service.impl.PostService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {
  @Autowired
  private PostService postService;
@Autowired // de test sau khi login co roi thi se xoa no di
private UserRepo repo;
    @GetMapping
    public String getALL(Model model, HttpSession session, @RequestParam(name = "active",defaultValue = "true") boolean active) {
        Users users = (Users) session.getAttribute("user");

        // de test thoi tuong lai se bo no di
        if (users == null) {
            users = repo.findByUsername("hsf302");
            System.out.println(users.getUsername());
            session.setAttribute("user", users);
        }
        System.out.println("ac"+active);
        //
        model.addAttribute("user", users);
        model.addAttribute("posts",postService.getALlPostsByCurrentUser(users,active));
        return "posts";
    }
    @PostMapping("/save")
    public String createPost(@ModelAttribute("post") Post post, HttpSession session,@RequestParam(name = "file",required = false) List<MultipartFile> fileList) throws IOException {
        Users users = (Users) session.getAttribute("user");
        if (users == null) {
            users = repo.findByUsername("hsf302");
            System.out.println(users.getUsername());
        }

        postService.createPost(post,fileList,users);
        return "redirect:/posts";
    }

    @GetMapping("/create")
    public String create(Model model, HttpSession session) {
        Users users = (Users) session.getAttribute("user");
        if (users == null) {
            users = repo.findByUsername("hsf302");
            System.out.println(users.getUsername());
            model.addAttribute("user", users);
        }
        model.addAttribute("user", users);
        model.addAttribute("post", new Post());
        return "posts-form";
    }
    @GetMapping("/edit/{id}")
    public String edit(Model model, HttpSession session, @PathVariable Long id) {
        Users users = (Users) session.getAttribute("user");
        if (users == null) {
            users = repo.findByUsername("hsf302");
            System.out.println(users.getUsername());
            model.addAttribute("user", users);
        }
        model.addAttribute("user", users);
        model.addAttribute("post", postService.getPostById(id));
        return "posts-form";
    }

    @GetMapping("/delete/{id}")
    public String delete( @PathVariable Long id) {
       postService.deletePost(id);
       return "redirect:/posts";
    }
    @GetMapping("/active/{id}")
    public String active( @PathVariable Long id) {
       postService.activatePost(id);
       return "redirect:/posts";
    }

    @GetMapping("/friendPost")
    public String friendPost(Model model, HttpSession session) {
        Users users = (Users) session.getAttribute("user");

        System.out.println(users.getUsername());
        model.addAttribute("user", users);
        model.addAttribute("posts",postService.postOfFriend(users));
        return "posts";
    }

@GetMapping("/like/{id}")
    public String like(@PathVariable Long id,HttpSession session) {
        Users users = (Users) session.getAttribute("user");

        postService.likePost(users,id);
        return "redirect:/posts/friendPost";
  }
}
