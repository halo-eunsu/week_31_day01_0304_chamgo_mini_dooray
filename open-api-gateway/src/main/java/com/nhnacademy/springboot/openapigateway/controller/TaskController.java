package com.nhnacademy.springboot.openapigateway.controller;

import com.nhnacademy.springboot.openapigateway.domain.MilestoneSetDto;
import com.nhnacademy.springboot.openapigateway.domain.TagSetDto;
import com.nhnacademy.springboot.openapigateway.domain.Task;
import com.nhnacademy.springboot.openapigateway.domain.TaskEditDto;
import com.nhnacademy.springboot.openapigateway.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@Controller
@RequestMapping("/projects/{projectId}/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public String createTask(@PathVariable Long projectId, @Valid Task task) {
        taskService.createTask(projectId, task);
        return "redirect:" + UriComponentsBuilder.fromPath("/projects/{projectId}/tasks")
                                                 .buildAndExpand(projectId)
                                                 .toUriString();
    }

    @PutMapping("/{taskId}")
    public String editTask(@PathVariable Long projectId, @PathVariable Long taskId, @Valid TaskEditDto taskEditDto) {
        taskService.editTask(projectId, taskId, taskEditDto);
        return "redirect:" + UriComponentsBuilder.fromPath("/projects/{projectId}/tasks/{taskId}")
                                                 .buildAndExpand(projectId, taskId)
                                                 .toUriString();
    }

    @DeleteMapping("/{taskId}")
    public String deleteTask(@PathVariable Long projectId, @PathVariable Long taskId) {
        taskService.deleteTask(projectId, taskId);
        return "redirect:" + UriComponentsBuilder.fromPath("/projects/{projectId}/tasks")
                                                 .buildAndExpand(projectId)
                                                 .toUriString();
    }

    @GetMapping
    public String getTasks(@PathVariable Long projectId, Model model) {
        model.addAttribute("tasks", taskService.getTasks(projectId));
        return "tasks";
    }

    @GetMapping("/{taskId}")
    public String getTask(@PathVariable Long projectId, @PathVariable Long taskId, Model model) {
        model.addAttribute("task", taskService.getTask(projectId, taskId));
        return "taskDetail";
    }

    @PostMapping("/{taskId}/tags")
    public String setTags(@PathVariable Long projectId, @PathVariable Long taskId, @Valid TagSetDto tagSetDto){
        taskService.setTags(projectId, taskId, tagSetDto);
        return "redirect:" + UriComponentsBuilder.fromPath("/projects/{projectId}/tasks/{taskId}")
                                                 .buildAndExpand(projectId, taskId)
                                                 .toUriString();
    }

    @PostMapping("/{taskId}/milestone")
    public String setTags(@PathVariable Long projectId, @PathVariable Long taskId, @Valid MilestoneSetDto milestoneSetDto){
        taskService.setMilestone(projectId, taskId, milestoneSetDto);
        return "redirect:" + UriComponentsBuilder.fromPath("/projects/{projectId}/tasks/{taskId}")
                                                 .buildAndExpand(projectId, taskId)
                                                 .toUriString();
    }
}

