package com.nhnacademy.springboot.taskapiserver.controller;

import com.nhnacademy.springboot.taskapiserver.domain.member.Member;
import com.nhnacademy.springboot.taskapiserver.domain.project.Project;
import com.nhnacademy.springboot.taskapiserver.domain.project.ProjectService;
import com.nhnacademy.springboot.taskapiserver.dto.ProjectDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    // 프로젝트 생성
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProject(@RequestBody Project project) {
        projectService.createProject(project);
    }

    // 프로젝트 조회
    @GetMapping("/{projectId}")
    public Project getProject(@PathVariable Long projectId) {
        return projectService.getProjectById(projectId);
    }

    // 프로젝트 수정
    @PutMapping("/{projectId}")
    @ResponseStatus(HttpStatus.OK)
    public void editProject(@PathVariable Long projectId, @RequestBody ProjectDto projectEditDto) {
        projectEditDto.setProjectId(projectId);
        projectService.modifyProject(projectEditDto);
    }

    // 프로젝트 삭제
    @DeleteMapping("/{projectId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProject(@PathVariable Long projectId) {
        projectService.deleteProject(projectId);
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
    public void deleteProjectMember(@PathVariable Long projectId, @PathVariable String memberId) {
        projectService.deleteProjectMember(projectId, memberId);
    }
}