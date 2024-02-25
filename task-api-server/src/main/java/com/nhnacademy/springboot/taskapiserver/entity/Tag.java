package com.nhnacademy.springboot.taskapiserver.entity;

import javax.persistence.*;

@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;

    @ManyToOne
    @JoinColumn(name = "projectId")
    private Project project;

    private String tagName;
}
