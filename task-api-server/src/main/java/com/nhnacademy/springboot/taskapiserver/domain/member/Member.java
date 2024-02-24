package com.nhnacademy.springboot.taskapiserver.domain.member;

import com.nhnacademy.springboot.taskapiserver.domain.AuthType;
import com.nhnacademy.springboot.taskapiserver.domain.project.Project;
import lombok.Getter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
public class Member {

    @Id
    private String memberId;

//    @ManyToOne
//    @JoinColumn(name = "projectId")
//    private Project project;

    @ManyToMany(mappedBy = "members")
    private Set<Project> projects = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private AuthType auth;
}
