package com.nhnacademy.springboot.taskapiserver.entity;

import com.nhnacademy.springboot.taskapiserver.domain.AuthType;

import javax.persistence.*;

@Entity
public class Member {
    @Id
    private String memberId;

    @ManyToOne
    @JoinColumn(name = "projectId")
    private Project project;

    @Enumerated(EnumType.STRING)
    private AuthType auth;
}
