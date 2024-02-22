package com.nhnacademy.springboot.taskapiserver.domain.mileStone;

import com.nhnacademy.springboot.taskapiserver.domain.project.Project;

import javax.persistence.*;

@Entity
public class MileStone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mileStoneId;

    @ManyToOne
    @JoinColumn(name = "projectId")
    private Project project;

    private String mileStoneName;
}
