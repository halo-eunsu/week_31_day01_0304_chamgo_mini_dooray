package com.nhnacademy.springboot.taskapiserver.domain.taskTags;

import com.nhnacademy.springboot.taskapiserver.domain.tag.Tag;
import com.nhnacademy.springboot.taskapiserver.domain.task.Task;

import javax.persistence.*;

@Entity
public class TaskTags {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskTagId;

    @ManyToOne
    @JoinColumn(name = "taskId")
    private Task task;

    @ManyToOne
    @JoinColumn(name = "tagId")
    private Tag tag;


}
