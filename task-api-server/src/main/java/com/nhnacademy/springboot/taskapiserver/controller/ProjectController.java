package com.nhnacademy.springboot.taskapiserver.controller;

import com.nhnacademy.springboot.taskapiserver.domain.member.Member;
import com.nhnacademy.springboot.taskapiserver.domain.project.Project;
import com.nhnacademy.springboot.taskapiserver.domain.project.ProjectService;
import com.nhnacademy.springboot.taskapiserver.dto.ProjectDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    // 프로젝트 생성
    @PostMapping
    public ResponseEntity<Void> createProject(@RequestBody Project project) {
        projectService.createProject(project);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<Map<String, Object>> getProject(@PathVariable Long projectId) {
        Project project = projectService.getProjectById(projectId);
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("projectId", project.getProjectId());
        responseBody.put("projectName", project.getProjectName());

        return ResponseEntity.ok(responseBody);
    }

    // 프로젝트 수정
    @PutMapping("/{projectId}")
    public ResponseEntity<Void> editProject(@PathVariable Long projectId, @RequestBody ProjectDto projectEditDto) {
        projectEditDto.setProjectId(projectId);
        projectService.modifyProject(projectEditDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 프로젝트 삭제
    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long projectId) {
        projectService.deleteProject(projectId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 프로젝트에 멤버 추가
    @PostMapping("/{projectId}/members")
    @ResponseStatus(HttpStatus.CREATED)
    public void addProjectMember(@PathVariable Long projectId, @RequestBody Member member) {
        projectService.addProjectMember(projectId, member);
    }

    // 프로젝트에서 멤버 삭제
    @DeleteMapping("/{projectId}/members/{memberId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> deleteProjectMember(@PathVariable Long projectId, @PathVariable String memberId) {
        projectService.deleteProjectMember(projectId, memberId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}