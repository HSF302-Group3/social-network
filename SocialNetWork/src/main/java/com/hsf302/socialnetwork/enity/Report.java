package com.hsf302.socialnetwork.enity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Table
@Entity
@Getter
@Setter
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String reason;
    @ManyToOne()
    @JoinColumn(name = "UserReport_ID")
    private Users user;
    @ManyToOne()
    @JoinColumn(name = "PostReport_ID")
    private Post post;
    @Column(columnDefinition = "BIT DEFAULT 0")
    private boolean banded;
    @CreationTimestamp
    private LocalDateTime createdAt = LocalDateTime.now();

    public Report() {}

      public Report(String reason) {
        this.reason = reason;
      }
   public void add(Post post, Users users)
   {
       post.getReports().add(this);
       this.post = post;
       this.user = users;
       post.getUsers().add(user);
   }
}
