package com.hsf302.socialnetwork.service;

import com.hsf302.socialnetwork.enity.Comment;
import com.hsf302.socialnetwork.enity.Post;
import com.hsf302.socialnetwork.enity.Users;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IsCommentService {
    void addComment(Post post , Users user, String cmt , List<MultipartFile> fileList) throws IOException;
    void replyComment( Users user, String reply , Long id , Post post ,List<MultipartFile> fileList) throws IOException;
    Comment getReferenceById(Long id);
}
