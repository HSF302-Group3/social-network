package com.hsf302.socialnetwork.service;


import com.hsf302.socialnetwork.enity.Comment;
import com.hsf302.socialnetwork.enity.Post;
import com.hsf302.socialnetwork.enity.Users;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IsPostService {

    Post createPost(Post  post , List<MultipartFile> fileList,Users users) throws IOException;
   List<Post> getALlPostsByCurrentUser(Users user,boolean active);
   List<Post>  postOfFriend(Users user);
   Post getPostById(Long id);
   void updatePost(Post post,List<MultipartFile> fileList) throws IOException;
   void deletePost(Long id);
   void activatePost(Long id);
   void likePost(Users user, Long id);
   List<Comment> getComments(Long id);
}
