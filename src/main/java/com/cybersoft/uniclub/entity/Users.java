package com.cybersoft.uniclub.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Entity(name="users")
public class Users {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private String id;
    private String email;
    private String password;
    private LocalDateTime createDate;
    @ManyToOne
    @JoinColumn(name="id_role")
    private Roles roles;
    // bang nao giu khao ngoai thi them @ManyToOne va @JoinColumn
}
