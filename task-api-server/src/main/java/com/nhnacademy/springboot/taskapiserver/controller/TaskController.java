package com.nhnacademy.springboot.taskapiserver.controller;

import com.nhnacademy.springboot.taskapiserver.domain.project.Project;
import com.nhnacademy.springboot.taskapiserver.domain.task.Task;
import com.nhnacademy.springboot.taskapiserver.domain.task.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects/{projectId}")
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

//todo:    1. Task 등록 - POST /projects/{projectId}/tasks

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@PathVariable Long projectId,
                           @RequestBody Task task) {
        task.setProject(new Project(projectId));
        return taskRepository.save(task);
    }

    //todo:    2. Task 수정 - PUT /projects/{projectId}/tasks/{taskId}
    @PutMapping("/{taskId}")
    public Task updateTask(@PathVariable Long projectId, @PathVariable Long taskId, @RequestBody Task taskDetails) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalStateException("Task not found: " + taskId));

        task.setTitle(taskDetails.getTitle());
        task.setContent(taskDetails.getContent());
        // Set other fields if necessary
        return taskRepository.save(task);
    }

    //todo:    3. Task 삭제 - DELETE /projects/{projectId}/tasks/{taskId}
// Task 삭제
    @DeleteMapping("/{taskId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long projectId, @PathVariable Long taskId) {
        taskRepository.deleteById(taskId);
    }

    //todo:    4. Task 목록 조회 - GET /projects/{projectId}/tasks
    @GetMapping("/tasks")
    public List<Task> getAllTasks(@PathVariable Long projectId) {
        return taskRepository.findByProject_ProjectId(projectId);
    }

    //todo:    5. Task 내용 조회 - GET /projects/{projectId}/tasks/{taskId}
    @GetMapping("/{taskId}")
    public Task getTask(@PathVariable Long projectId, @PathVariable Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalStateException("없는 태그: " + taskId));
    }
}
