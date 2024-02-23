package com.nhnacademy.springboot.taskapiserver.controller;

import com.nhnacademy.springboot.taskapiserver.domain.task.Task;
import com.nhnacademy.springboot.taskapiserver.domain.task.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects/{projectId}/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@PathVariable Long projectId, @RequestBody Task task) {
        Task createdTask = taskService.registerTask(projectId, task);
        return ResponseEntity.ok(createdTask);
    }


    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long projectId, @PathVariable Long taskId, @RequestBody Task task) {
        Task updatedTask = taskService.modifyTask(projectId, taskId, task);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long projectId, @PathVariable Long taskId) {
        taskService.deleteTask(projectId, taskId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(@PathVariable Long projectId) {
        List<Task> tasks = taskService.getTasksByProjectId(projectId);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTask(@PathVariable Long projectId, @PathVariable Long taskId) {
        Optional<Task> taskOptional = taskService.getTaskByProjectIdAndTaskId(projectId, taskId);
        if (taskOptional.isPresent()) {
            return ResponseEntity.ok(taskOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
