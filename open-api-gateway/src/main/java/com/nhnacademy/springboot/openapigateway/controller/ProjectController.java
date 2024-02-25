package com.nhnacademy.springboot.openapigateway.controller;

import com.nhnacademy.springboot.openapigateway.domain.Member;
import com.nhnacademy.springboot.openapigateway.domain.Project;
import com.nhnacademy.springboot.openapigateway.domain.ProjectEditDto;
import com.nhnacademy.springboot.openapigateway.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/{projectId}")
    public String getProject(@PathVariable Long projectId, Model model) {
        model.addAttribute("project", projectService.getProject(projectId));
        return "projectDetail";
    }

    @PostMapping
    public String createProject(@Valid Project project, HttpSession session) {
        projectService.createProject(project);
        String loginId = (String) session.getAttribute("loginId");
        return "redirect:" + UriComponentsBuilder.fromPath("/members/{loginId}/projects")
                                                 .buildAndExpand(loginId)
                                                 .toUriString();
    }

    @DeleteMapping("/{projectId}")
    public String deleteProject(@PathVariable Long projectId, HttpSession session) {
        projectService.deleteProject(projectId);
        String loginId = (String) session.getAttribute("loginId");
        return "redirect:" + UriComponentsBuilder.fromPath("/members/{loginId}/projects")
                                                 .buildAndExpand(loginId)
                                                 .toUriString();
    }

    @PutMapping("/{projectId}")
    public String editProject(@PathVariable Long projectId,  @Valid ProjectEditDto projectEditDto, HttpSession session) {
        projectService.editProject(projectId, projectEditDto);
        String loginId = (String) session.getAttribute("loginId");
        return "redirect:" + UriComponentsBuilder.fromPath("/members/{loginId}/projects")
                                                 .buildAndExpand(loginId)
                                                 .toUriString();
    }

    @PostMapping("/{projectId}/members")
    public String addProjectMember(@PathVariable Long projectId, @Valid Member member) {
        projectService.addProjectMember(projectId, member);
        return "redirect:" + UriComponentsBuilder.fromPath("/projects/{projectId}/members")
                                                 .buildAndExpand(projectId)
                                                 .toUriString();
    }

    @DeleteMapping("/{projectId}/members/{memberId}")
    public String deleteProjectMember(@PathVariable Long projectId, @PathVariable String memberId) {
        projectService.deleteProjectMember(projectId,memberId);
        return "redirect:"+ UriComponentsBuilder.fromPath("/projects/{projectId}/members/{memberId}")
                                                .buildAndExpand(projectId, memberId)
                                                .toUriString();
    }

    @GetMapping("/{projectId}/members")
    public String getProjectMembers(@PathVariable Long projectId, Model model) {
        model.addAttribute("members", projectService.getProjectMembers(projectId));
        return "projectMembers";
    }
}
