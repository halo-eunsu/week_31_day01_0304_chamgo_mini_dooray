package com.nhnacademy.springboot.taskapiserver.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    private String projectName;

    private String content;

    private StatusType status;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Task> tasks;

    @Getter
    public enum StatusType {
        ACTIVE,
        SLEEP,
        TERMINATION
    }
}
