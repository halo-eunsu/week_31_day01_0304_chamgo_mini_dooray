package com.nhnacademy.springboot.taskapiserver.entity;

import javax.persistence.*;

@Entity
public class MileStone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mileStoneId;
    private String mileStoneName;

    @ManyToOne
    @JoinColumn(name = "projectId")
    private Project project;
}
