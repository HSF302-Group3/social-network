package com.hsf302.socialnetwork.controller;

import com.hsf302.socialnetwork.enity.Comment;
import com.hsf302.socialnetwork.enity.Post;
import com.hsf302.socialnetwork.enity.Users;
import com.hsf302.socialnetwork.repo.UserRepo;
import com.hsf302.socialnetwork.service.impl.CommentService;
import com.hsf302.socialnetwork.service.impl.PostService;
import com.hsf302.socialnetwork.service.impl.ReportService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
  @Autowired
  private CommentService commentService;
@Autowired // de test sau khi login co roi thi se xoa no di
private UserRepo repo;

@Autowired
private ReportService reportService;
    @GetMapping
    public String getALL(Model model, HttpSession session, @RequestParam(name = "active",defaultValue = "true") boolean active,

    @RequestParam(required = false  ,defaultValue = "0") int page
    ) {
        Users users = (Users) session.getAttribute("user");

        // de test thoi tuong lai se bo no di
        if (users == null) {
//            users = repo.findByUsername("hsf302");
//            System.out.println(users.getUsername());
            session.setAttribute("user", users);
            return "redirect:/login";
        }
        System.out.println("ac"+active);
        //
        Pageable pageable = PageRequest.of(page,1);//de test thoi chu sua lai la 3 -4 la ok
        model.addAttribute("user", users);
        model.addAttribute("posts",postService.getALlPostsByCurrentUser(users,active, pageable).getContent());
        model.addAttribute("paging",postService.getALlPostsByCurrentUser(users,active, pageable));
        model.addAttribute("currentPage", page);
        model.addAttribute("active", active);
        model.addAttribute("isYourPage", true);
        return "posts";
    }
    @PostMapping("/save")
    public String createPost(@ModelAttribute("post") Post post, HttpSession session,@RequestParam(name = "file",required = false) List<MultipartFile> fileList) throws IOException {
        Users users = (Users) session.getAttribute("user");
        if (users == null) {
          return "redirect:/login";
        }

        postService.createPost(post,fileList,users);
        return "redirect:/posts";
    }

    @GetMapping("/create")
    public String create(Model model, HttpSession session) {
        Users users = (Users) session.getAttribute("user");
        if (users == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", users);
        model.addAttribute("post", new Post());
        return "posts-form";
    }
    @GetMapping("/edit/{id}")
    public String edit(Model model, HttpSession session, @PathVariable Long id) {
        Users users = (Users) session.getAttribute("user");
        if (users == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", users);
        model.addAttribute("post", postService.getPostById(id));
        return "posts-form";
    }

    @GetMapping("/delete/{id}")
    public String delete( @PathVariable Long id, HttpSession session) {
        Users users = (Users) session.getAttribute("user");
        if (users == null) {
            return "redirect:/login";
        }
       postService.deletePost(id);
       return "redirect:/posts";
    }
    @GetMapping("/active/{id}")
    public String active( @PathVariable Long id,HttpSession session) {
        Users users = (Users) session.getAttribute("user");
        if (users == null) {
            return "redirect:/login";
        }
       postService.activatePost(id);
       return "redirect:/posts";
    }

    @GetMapping("/friendPost")
    public String friendPost(Model model, HttpSession session , @RequestParam(required = false  ,defaultValue = "0") int page ) {
        Users users = (Users) session.getAttribute("user");
        if (users == null) {
            return "redirect:/login";
        }
        Pageable pageable = PageRequest.of(page,1);
        model.addAttribute("posts",postService.postOfFriend(users,pageable).getContent());
        model.addAttribute("paging",postService.postOfFriend(users,pageable));
        model.addAttribute("currentPage", page);
        model.addAttribute("user", users);
        model.addAttribute("isYourPage", false);
        model.addAttribute("canreport",reportService.canreport(postService.postOfFriend(users,pageable).getContent(),users));
        return "posts";
    }

    @GetMapping("/like/{id}")
    public String like(@PathVariable Long id,HttpSession session) {

        Users users = (Users) session.getAttribute("user");
        if (users == null) {
            return "redirect:/login";
        }

        postService.likePost(users,id);
        return "redirect:/posts/friendPost";
  }
    @PostMapping("/comment/{id}")
    public String comment(@PathVariable Long id,HttpSession session, @RequestParam("content") String cmt ,@RequestParam(name = "image",required = false) List<MultipartFile> fileList) throws IOException {

        Users users = (Users) session.getAttribute("user");
        if (users == null) {
            return "redirect:/login";
        }
        Post post = postService.getPostById(id);
        commentService.addComment(post,users,cmt , fileList );
        return "redirect:/posts/friendPost";
    }
    @PostMapping("/reply/{id}")
    public String replyComment(@PathVariable Long id,HttpSession session, @RequestParam("replyContent") String cmt ,@RequestParam(name = "images",required = false) List<MultipartFile> fileList) throws IOException {
        Users users = (Users) session.getAttribute("user");
        if (users == null) {
            return "redirect:/login";
        }
        Comment comment = commentService.getReferenceById(id);
        Post post = comment.getPost();

        commentService.replyComment(users,cmt ,id,post, fileList);
        return "redirect:/posts/friendPost";
    }

    @GetMapping("/get/{id}")
    public String get(@PathVariable Long id, Model model) {
        Users users = (Users) model.getAttribute("user");
        if (users == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", users);
        model.addAttribute("post", postService.getPostById(id));
        return "Adminreport";
    }
}
