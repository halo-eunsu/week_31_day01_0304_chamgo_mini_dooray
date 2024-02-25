package com.nhnacademy.springboot.taskapiserver.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    private String projectName;

    private String content;

    private StatusType status;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Task> tasks;

    public enum StatusType {
        ACTIVE,
        SLEEP,
        TERMINATION
    }
}
