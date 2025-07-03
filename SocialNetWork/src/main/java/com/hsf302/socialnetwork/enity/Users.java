package com.hsf302.socialnetwork.enity;

import com.hsf302.socialnetwork.emums.Gender;
import com.hsf302.socialnetwork.emums.Role;
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
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;
    private String name;
    private String password;
    private String email;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(columnDefinition = "BIT DEFAULT 1")
    private boolean active;
    @CreationTimestamp
    private LocalDateTime created;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Message> messages = new ArrayList<>();


    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "User_Like_Post",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id")
    )
    private Set<Post> postsLike = new HashSet<>();

    @OneToMany(mappedBy = "usercreate")
    private List<Post> postcreate = new ArrayList<>();
    @ManyToMany
    @JoinTable(
            name = "User_Join_Conversation",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "conversation_id")
    )
    private Set<Conversation> conversations = new HashSet<>();

    public Users(Long userId, String username, String name, String password, String email, String phone, Role role, Gender gender, boolean active, LocalDateTime created) {
        this.userId = userId;
        this.username = username;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.gender = gender;
        this.active = active;
        this.created = created;
    }
}
