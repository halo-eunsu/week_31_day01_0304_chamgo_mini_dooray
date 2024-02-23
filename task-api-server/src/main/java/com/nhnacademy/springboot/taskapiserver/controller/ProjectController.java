package com.nhnacademy.springboot.taskapiserver.controller;

import com.nhnacademy.springboot.taskapiserver.domain.project.Project;
import com.nhnacademy.springboot.taskapiserver.domain.project.ProjectService;
import com.nhnacademy.springboot.taskapiserver.domain.member.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final MemberService memberService;

    public ProjectController(ProjectService projectService, MemberService memberService) {
        this.projectService = projectService;
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        Project createdProject = projectService.createProject(project);
        return ResponseEntity.ok(createdProject);
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<Project> updateProjectStatus(@PathVariable Long projectId, @RequestBody Project.StatusType status) {
        Project updatedProject = projectService.updateProjectStatus(projectId, status);
        return ResponseEntity.ok(updatedProject);
    }

    @PostMapping("/{projectId}/members")
    public ResponseEntity<Void> addMemberToProject(@PathVariable Long projectId, @RequestBody String memberId) {
        memberService.addMemberToProject(projectId, memberId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{projectId}/members/{memberId}")
    public ResponseEntity<Void> removeMemberFromProject(@PathVariable Long projectId, @PathVariable String memberId) {
        memberService.removeMemberFromProject(projectId, memberId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<Project> getProject(@PathVariable Long projectId) {
        Project project = projectService.getProjectById(projectId);
        return ResponseEntity.ok(project);
    }
}
