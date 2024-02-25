package com.nhnacademy.springboot.taskapiserver.controller;

import com.nhnacademy.springboot.taskapiserver.domain.project.Project;
import com.nhnacademy.springboot.taskapiserver.domain.project.ProjectService;
import com.nhnacademy.springboot.taskapiserver.domain.task.Task;
import com.nhnacademy.springboot.taskapiserver.domain.task.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects/{projectId}")
public class TaskController {

    private final TaskRepository taskRepository;
    private final ProjectService projectService;

    public TaskController(TaskRepository taskRepository, ProjectService projectService) {
        this.taskRepository = taskRepository;
        this.projectService = projectService;
    }

    //todo:    1. Task 등록 - POST /projects/{projectId}/tasks
    @PostMapping
    public ResponseEntity<Task> createTask(@PathVariable Long projectId,
                                           @RequestBody Task task) {
        Project project = projectService.getProjectById(projectId);
        if (project == null) {
            return ResponseEntity.notFound().build();
        }

        task.setProject(project);
        Task createdTask = taskRepository.save(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);

    }

    //todo:    2. Task 수정 - PUT /projects/{projectId}/tasks/{taskId}
    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long projectId, @PathVariable Long taskId, @RequestBody Task taskDetails) {
        return taskRepository.findById(taskId).map(task -> {
            task.setTitle(taskDetails.getTitle());
            task.setContent(taskDetails.getContent());
            Task updatedTask = taskRepository.save(task);
            return ResponseEntity.ok(updatedTask);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //todo:    3. Task 삭제 - DELETE /projects/{projectId}/tasks/{taskId}
    public ResponseEntity<Void> deleteTask(@PathVariable Long projectId, @PathVariable Long taskId) {
        if (taskRepository.existsById(taskId)) {
            taskRepository.deleteById(taskId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    //todo:    4. Task 목록 조회 - GET /projects/{projectId}/tasks
    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getAllTasks(@PathVariable Long projectId) {
        List<Task> tasks = taskRepository.findByProject_ProjectId(projectId);
        return ResponseEntity.ok(tasks);
    }

    //todo:    5. Task 내용 조회 - GET /projects/{projectId}/tasks/{taskId}
    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTask(@PathVariable Long projectId, @PathVariable Long taskId) {
        return taskRepository.findById(taskId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
