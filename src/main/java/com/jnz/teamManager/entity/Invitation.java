package com.jnz.teamManager.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "invitation")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = Invitation.class
)
public class Invitation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    User user;

    @ManyToOne
    @JoinColumn(name = "userOwner_id", nullable = false, referencedColumnName = "id")
    User userOwner;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    Team teamId;

    String message;
}
