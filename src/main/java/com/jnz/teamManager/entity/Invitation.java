package com.jnz.teamManager.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "invitation")
public class Invitation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    User user;

    @ManyToOne
    @JoinColumn(name = "userOwner_id", nullable = false, referencedColumnName = "id")
    User userOwner;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id", nullable = false)
    Team teamId;

    String message;
}
