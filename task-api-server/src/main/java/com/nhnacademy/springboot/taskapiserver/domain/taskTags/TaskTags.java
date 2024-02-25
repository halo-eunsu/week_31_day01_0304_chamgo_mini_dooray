package com.nhnacademy.springboot.taskapiserver.domain.taskTags;

import com.nhnacademy.springboot.taskapiserver.domain.tag.Tag;
import com.nhnacademy.springboot.taskapiserver.domain.task.Task;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
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
