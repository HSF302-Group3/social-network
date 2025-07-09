package com.hsf302.socialnetwork.service.impl;

import com.hsf302.socialnetwork.Util.CloudiaryUtil;

import com.hsf302.socialnetwork.enity.*;

import com.hsf302.socialnetwork.repo.PostRepo;
import com.hsf302.socialnetwork.repo.UserRepo;
import com.hsf302.socialnetwork.service.IsPostService;
import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class PostService implements IsPostService {

    @Autowired
    CloudiaryUtil cloudiaryUtil;
    @Autowired
    PostRepo postRepo;
    @Autowired
    UserRepo userRepo;

    @Override
    public Post createPost(Post post, List<MultipartFile> fileList, Users users) throws IOException {


        Users currentUser = userRepo.findByUsername(users.getUsername());
        post.getImagePost().clear();
        if (fileList != null && fileList.size() > 0) {


            for (MultipartFile file : fileList) {
                if (!file.isEmpty()) {
                    Image image = new Image();
                    image.setImageUrl(cloudiaryUtil.uploadImage(file));
                    post.addImage(image);
                }

            }

        }


        if (post.getId() != null) {
            post.setUsercreate(users);
            postRepo.save(post);
        } else {
            currentUser.addPostCreate(post);
            userRepo.save(currentUser);
        }

        return post;
    }


    @Override
    public List<Post> getALlPostsByCurrentUser(Users users, boolean active) {

        List<Post> posts = postRepo.findAllByUsercreateAndActiveAndCreatedAtOrderByCreatedAtDesc(users, active);
//        System.out.println(posts.size()+"post size");
        return posts;
    }

    @Override
    public List<Post> postOfFriend(Users user) {

        List<Post> posts = postRepo.getPostOfFriend(user);
        return posts;
    }

    @Override
    public Post getPostById(Long id) {

        Post post = postRepo.findById(id).get();
        return post;
    }

    @Override
    public void updatePost(Post post, List<MultipartFile> fileList) throws IOException {

        if (fileList != null && fileList.size() > 0) {
            post.getImagePost().clear();
            for (MultipartFile file : fileList) {
                Image image = new Image();
                image.setImageUrl(cloudiaryUtil.uploadImage(file));
                post.addImage(image);
            }
        }
        postRepo.save(post);
    }


    @Override
    public void deletePost(Long id) {
        Post post = postRepo.findById(id).get();
        post.setActive(false);
        postRepo.save(post);


    }

    @Override
    public void activatePost(Long id) {
        Post post = postRepo.findById(id).get();
        post.setActive(true);
        postRepo.save(post);
    }

    @Override
    public void likePost(Users user, Long id) {

       Post post = postRepo.findById(id).get();

        Users users =  userRepo.findByUsername(user.getUsername());
        if(post.getUsers().contains(users)) {

            post.getUsers().remove(users);
            users.getPostsLike().remove(post);
            userRepo.save(users);
        }
        else {
            users.addLikePost(post);
            userRepo.save(users);
        }


    }

    @Override
    public List<Comment> getComments(Long id) {

        Post post = postRepo.findById(id).get();
        return post.getComments();
    }
}
