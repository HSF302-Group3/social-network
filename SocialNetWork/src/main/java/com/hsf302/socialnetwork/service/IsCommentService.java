package com.hsf302.socialnetwork.service;

import com.hsf302.socialnetwork.enity.Comment;
import com.hsf302.socialnetwork.enity.Post;
import com.hsf302.socialnetwork.enity.Users;

import java.io.IOException;

public interface IsCommentService {
    void addComment(Post post , Users user, String cmt ) throws IOException;
    void replyComment( Users user, String reply , Long id, Post post) throws IOException;
    Comment getReferenceById(Long id);
}
