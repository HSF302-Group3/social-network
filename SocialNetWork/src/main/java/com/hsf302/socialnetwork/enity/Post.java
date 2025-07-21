package com.hsf302.socialnetwork.enity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String content;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Column(columnDefinition = "BIT DEFAULT 1")
    private boolean active = true;
    @OneToMany(mappedBy = "post" ,cascade = CascadeType.ALL,orphanRemoval = true )
    private List<Image> imagePost = new ArrayList<>();
    @OneToMany(mappedBy = "post" ,cascade = CascadeType.ALL )
    private List<Comment> comments = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "usercreate_id")
    private Users usercreate;
    @ManyToMany(mappedBy = "postsLike")
    private Set<Users> users = new HashSet<>();

    @OneToMany(mappedBy = "post",fetch = FetchType.EAGER)
    private List<Report> reports = new ArrayList<>();

    @Override
    public String toString() {
        return "Post{" +
                "active=" + active +
                ", createdAt=" + createdAt +
                ", content='" + content + '\'' +
                ", id=" + id +
                '}';
    }

    public void addImage(Image image) {
        imagePost.add(image);
        image.setPost(this);
    }
    public void removeImage(Image image) {
        imagePost.remove(image);
    }

    public void addReport(Report report) {
        reports.add(report);
        report.setPost(this);
    }
}
