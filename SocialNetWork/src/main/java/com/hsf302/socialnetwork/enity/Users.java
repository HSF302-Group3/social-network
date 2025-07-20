package com.hsf302.socialnetwork.enity;
import com.hsf302.socialnetwork.enums.Gender;
import com.hsf302.socialnetwork.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
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
@Builder
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @NotBlank(message = "Username must not be blank")
    @NotNull(message = "username must not be blank")
    private String username;
    @NotNull(message = "Name must not be blank")
    @NotBlank(message = "Name must not be blank")
    @Size(min = 5,max = 50,message ="Name must in range 5 to 50 " )
    @Pattern(regexp = "^([A-Z][A-Za-z0-9]*)(\\s[A-Z][A-Za-z0-9]*)*$",message = "Name start capital and each word gap space and do not have special character")
    private String name;
    @NotBlank(message = "Password must not be blank")
    private String password;
    @NotNull(message = "Email must not be blank")
    @NotBlank(message = "Email must not be blank")
    @Email(message = "Invalid email format")
    private String email;
    @NotBlank(message = "Phone number must not be blank")
    @Pattern(regexp = "^0\\d{9}$", message = "Phone number must be 10 digits and start 0")
    private String phone;
    @NotNull(message = "AvatarUrl must not be blank")
    @NotBlank(message = "AvatarUrl must not be blank")
    @Lob
    private String avatarUrl;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Role is required")
    private Role role;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Gender is required")
    private Gender gender;
    @Column(columnDefinition = "BIT DEFAULT 1")
    private boolean active = true;
    @CreationTimestamp
    private LocalDateTime created = LocalDateTime.now();

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Message> messages = new ArrayList<>();
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "User_Like_Post",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id")
    )
    private Set<Post> postsLike = new HashSet<>();

    @OneToMany(mappedBy = "usercreate", cascade = CascadeType.ALL)
    private List<Post> postcreate = new ArrayList<>();
    @ManyToMany
    @JoinTable(
            name = "User_Join_Conversation",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "conversation_id")
    )
    private Set<Conversation> conversations = new HashSet<>();

    public void addPostCreate(Post post) {
        postcreate.add(post);
        post.setUsercreate(this);
    }

    public void addLikePost(Post post) {

        postsLike.add(post);
        post.getUsers().add(this);
    }

    @OneToMany(mappedBy = "sendInvite")
    private List<AddFriend> sendInvites = new ArrayList<>();
    @OneToMany(mappedBy = "reciveInvite")
    private List<AddFriend> reciveInvites = new ArrayList<>();

    @Override
    public String toString() {
        return "Users{" +
                "created=" + created +
                ", active=" + active +
                ", gender=" + gender +
                ", role=" + role +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", userId=" + userId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users)) return false;
        Users other = (Users) o;
        return this.userId != null && this.userId.equals(other.userId);
    }

    @Override
    public int hashCode() {
        return userId != null ? userId.hashCode() : 0;
    }



}
