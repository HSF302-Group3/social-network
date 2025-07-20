package com.hsf302.socialnetwork.enity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Table
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "NVARCHAR(255)")
    private String message;
    @CreationTimestamp
    private LocalDateTime createddate;
    @ManyToOne
    @JoinColumn(name = "usersend_id")
    private Users users;
    @ManyToOne
    @JoinColumn(name = "conversation_id")
    private Conversation conversation;

    @Override
    public String toString() {
        return "Message{" +
                "createddate=" + createddate +
                ", message='" + message + '\'' +
                ", id=" + id +
                '}';
    }
    @Column(columnDefinition = "BIT DEFAULT 1")
    private boolean active = true;
}
