package com.nhnacademy.springboot.taskapiserver.entity;

import javax.persistence.*;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @ManyToOne
    @JoinColumn(name = "projectId")
    private Project project;



    @Enumerated(EnumType.STRING)
    private AuthType auth;
}
