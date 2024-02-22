package com.nhnacademy.springboot.taskapiserver.entity;

import com.nhnacademy.springboot.taskapiserver.domain.StatusType;

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

}
