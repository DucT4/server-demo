package com.cybersoft.uniclub.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name="roles")

@Data
public class Roles {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private  int id;
    private String name;
    private LocalDateTime createDate;
    @OneToMany(mappedBy = "roles")
    private List<Users> users;

   //
}

