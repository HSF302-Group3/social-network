package com.hsf302.socialnetwork.enity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class AddFriend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "BIT DEFAULT 0")
    private boolean friended= false;
    @ManyToOne
    @JoinColumn(name = "usersendinvite_id")
    private Users sendInvite;
    @ManyToOne
    @JoinColumn(name = "userrecieve_id")
    private Users reciveInvite;


}
