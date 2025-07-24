package com.hsf302.socialnetwork.service.impl;

import com.hsf302.socialnetwork.util.CloudiaryUtil;
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

    public void addComment(Post post , Users user, String cmt ,List<MultipartFile> fileList) throws IOException {
        Comment comment = new Comment();
        comment.setPost(post);
        comment.setUsers(user);
        comment.setComment(cmt);
        if (fileList != null && fileList.size() > 0) {
            for (MultipartFile file : fileList) {
                if (!file.isEmpty()) {
                    Image image = new Image();
                    image.setImageUrl(cloudiaryUtil.uploadImage(file));

                    image.setComment(comment);

                    comment.addImageComment(image);
                }
            }
        }
        System.out.println("Tổng ảnh gắn kèm bình luận: " + comment.getImageComment().size());
        commentRepo.save(comment);
    }


    public List<Comment> getComments(Post post) {
        return commentRepo.findByPostId(post.getId());
    }

    public void replyComment( Users user, String reply , Long id , Post post ,List<MultipartFile> fileList) throws IOException {
        Comment comment = new Comment();
        comment.setUsers(user);
        comment.setParentCommentId(id);
        comment.setPost(post);
        comment.setComment(reply);

        if (fileList != null && fileList.size() > 0) {
            for (MultipartFile file : fileList) {
                if (!file.isEmpty()) {
                    Image image = new Image();
                    image.setImageUrl(cloudiaryUtil.uploadImage(file));

                    image.setComment(comment);

                    comment.addImageComment(image);
                }
            }
        }
        commentRepo.save(comment);
    }
    public Comment getReferenceById(Long id) {
        return commentRepo.getReferenceById(id);
    }
}
