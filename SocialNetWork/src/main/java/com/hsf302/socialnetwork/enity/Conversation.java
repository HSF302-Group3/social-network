package com.hsf302.socialnetwork.enity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;

    @OneToMany(mappedBy = "conversation")
    private List<Message> messages = new ArrayList<>();
    @ManyToMany(mappedBy = "conversations")
    private Set<Users> users = new HashSet<>();

    @Override
    public String toString() {
        return "Conversation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
    @Column(columnDefinition = "BIT DEFAULT 1")
    private boolean active = true;
}
