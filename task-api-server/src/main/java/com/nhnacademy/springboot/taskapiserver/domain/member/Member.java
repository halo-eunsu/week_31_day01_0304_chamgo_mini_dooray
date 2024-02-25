package com.nhnacademy.springboot.taskapiserver.domain.member;

import com.nhnacademy.springboot.taskapiserver.domain.AuthType;
import com.nhnacademy.springboot.taskapiserver.domain.project.Project;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
public class Member {

    @Id
    private String memberId;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "member_project", // 새로운 중간 테이블 이름
            joinColumns = @JoinColumn(name = "memberId"),
            inverseJoinColumns = @JoinColumn(name = "projectId")
    )
    private Set<Project> projects = new HashSet<>();

//    @ManyToMany(cascade = {
//            CascadeType.PERSIST,
//            CascadeType.MERGE
//    })
//    @JoinTable(
//            name = "project_member", // 이름 수정
//            joinColumns = @JoinColumn(name = "memberId"),
//            inverseJoinColumns = @JoinColumn(name = "projectId")
//    )
//    private Set<Project> projects = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private AuthType auth;
}
