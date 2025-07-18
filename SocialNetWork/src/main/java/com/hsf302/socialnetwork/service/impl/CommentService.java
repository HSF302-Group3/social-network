package com.hsf302.socialnetwork.service.impl;

import com.hsf302.socialnetwork.Util.CloudiaryUtil;
import com.hsf302.socialnetwork.enity.Comment;
import com.hsf302.socialnetwork.enity.Image;
import com.hsf302.socialnetwork.enity.Post;
import com.hsf302.socialnetwork.enity.Users;
import com.hsf302.socialnetwork.repo.CommentRepo;
import com.hsf302.socialnetwork.service.IsCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CommentService implements IsCommentService {
    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    CloudiaryUtil cloudiaryUtil;

    public void addComment(Post post , Users user, String cmt ) throws IOException {
        Comment comment = new Comment();
        comment.setPost(post);
        comment.setUsers(user);
        comment.setComment(cmt);



        commentRepo.save(comment);
    }

    public List<Comment> getComments(Post post) {
        return commentRepo.findByPostId(post.getId());
    }

    public void replyComment( Users user, String reply , Long id , Post post) throws IOException {
        Comment comment = new Comment();
        comment.setUsers(user);
        comment.setParentCommentId(id);
        comment.setPost(post);
        comment.setComment(reply);
        commentRepo.save(comment);
    }
}
