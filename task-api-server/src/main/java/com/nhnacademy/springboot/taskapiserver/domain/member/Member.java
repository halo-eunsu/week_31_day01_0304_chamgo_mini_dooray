package com.nhnacademy.springboot.taskapiserver.domain.member;

import com.nhnacademy.springboot.taskapiserver.domain.AuthType;
import com.nhnacademy.springboot.taskapiserver.domain.project.Project;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
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
