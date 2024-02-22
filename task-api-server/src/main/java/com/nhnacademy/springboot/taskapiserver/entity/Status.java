package com.nhnacademy.springboot.taskapiserver.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statusId;
    private String statusName;

    @OneToMany(mappedBy = "status")
    private List<Project> projects;
}
