package com.nhnacademy.springboot.taskapiserver.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;
    private String title;

    @ManyToOne
    @JoinColumn(name = "projectId")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "mileStoneId")
    private MileStone mileStone;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    @OneToMany(mappedBy = "task")
    private List<Comment> comments;

    @ManyToMany
    @JoinTable(
            name = "TaskTags",
            joinColumns = @JoinColumn(name = "taskId"),
            inverseJoinColumns = @JoinColumn(name = "tagId")
    )
    private Set<Tag> tags;
}
