package com.example.technicaltask.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")

    private String name;
    @Column(name = "age")
    private int age;
    @Column(name = "user_group")
    private long group;
    @Column(name = "course")
    @Enumerated(EnumType.STRING)
    private Course course;
    @Column(name = "user_type")
    @Enumerated(EnumType.STRING)
    private UserType userType;
}
