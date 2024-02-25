package com.nhnacademy.springboot.taskapiserver.domain.project;

import com.nhnacademy.springboot.taskapiserver.domain.member.Member;
import com.nhnacademy.springboot.taskapiserver.domain.task.Task;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    private String projectName;

    private String content;

    @Enumerated(EnumType.STRING)
    private StatusType status;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;

    @ManyToMany(mappedBy = "projects")
    private Set<Member> members = new HashSet<>();

    public Project(Long projectId) {
        this.projectId = projectId;
    }

    @Getter
    public enum StatusType {
        ACTIVE,
        SLEEP,
        TERMINATION
    }

    public void addMember(Member member) {
        members.add(member);
        member.getProjects().add(this);
    }
    public void removeMember(Member member) {
        members.remove(member);
        member.getProjects().remove(this);
    }
}
