package com.nhnacademy.springboot.taskapiserver.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    private String projectName;

    private String content;

    @ManyToOne
    @JoinColumn(name = "statusId")
    private Status status;

    @OneToMany(mappedBy = "project")
    private List<Member> members;

    @OneToMany(mappedBy = "project")
    private List<MileStone> mileStones;

    @OneToMany(mappedBy = "project")
    private Set<Tag> tags;
}
