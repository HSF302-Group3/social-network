package com.hsf302.socialnetwork.enity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    @Column(columnDefinition = "TEXT")
    private String comment;
    private Long  parentCommentId;
    @CreationTimestamp
    private LocalDateTime createdAt;


    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Image> imageComment  = new ArrayList<Image>();
    @ManyToOne
    @JoinColumn(name = "post_id")
    private  Post post;
    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;

   public void addComment(Image comment) {
        this.imageComment.add(comment);
        comment.setComment(this);
   }
   public void removeComment(Image comment) {
        this.imageComment.remove(comment);
   }
    @Override
    public String toString() {
        return "Comment{" +
                "parentCommentId=" + parentCommentId +
                ", comment='" + comment + '\'' +
                ", commentId=" + commentId +
                '}';
    }
}
