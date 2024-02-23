package com.nhnacademy.springboot.taskapiserver.controller;

import com.nhnacademy.springboot.taskapiserver.domain.task.TaskRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projects/{projectId}")
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

//todo:    1. Task 등록 - POST /projects/{projectId}/tasks
//todo:    2. Task 수정 - PUT /projects/{projectId}/tasks/{taskId}
//todo:    3. Task 삭제 - DELETE /projects/{projectId}/tasks/{taskId}
//todo:    4. Task 목록 조회 - GET /projects/{projectId}/tasks
//todo:    5. Task 내용 조회 - GET /projects/{projectId}/tasks/{taskId}
}
