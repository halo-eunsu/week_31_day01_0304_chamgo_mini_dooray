package com.nhnacademy.springboot.openapigateway.controller;

import com.nhnacademy.springboot.openapigateway.domain.Milestone;
import com.nhnacademy.springboot.openapigateway.domain.MilestoneEditDto;
import com.nhnacademy.springboot.openapigateway.service.MilestoneService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@Controller
@RequestMapping("/projects/{projectId}/milestones")
public class MilestoneController {

    private final MilestoneService milestoneService;

    public MilestoneController(MilestoneService milestoneService) {
        this.milestoneService = milestoneService;
    }

    @PostMapping
    public String createMilestone(@PathVariable Long projectId, @Valid Milestone milestone) {
        milestoneService.createMilestone(projectId, milestone);
        return "redirect:" + UriComponentsBuilder.fromPath("/projects/{projectId}")
                                                 .buildAndExpand(projectId)
                                                 .toUriString();
    }

    @PutMapping("/{milestoneId}")
    public String editMilestone(@PathVariable Long projectId, @PathVariable Long milestoneId, @Valid MilestoneEditDto milestoneEditDto) {
        milestoneService.editMilestone(projectId, milestoneId, milestoneEditDto);
        return "redirect:" + UriComponentsBuilder.fromPath("/projects/{projectId}")
                                                 .buildAndExpand(projectId)
                                                 .toUriString();
    }

    @DeleteMapping("/{milestoneId}")
    public String deleteMilestone(@PathVariable Long projectId, @PathVariable Long milestoneId) {
        milestoneService.deleteMilestone(projectId, milestoneId);
        return "redirect:" + UriComponentsBuilder.fromPath("/projects/{projectId}")
                                                 .buildAndExpand(projectId)
                                                 .toUriString();
    }
}
