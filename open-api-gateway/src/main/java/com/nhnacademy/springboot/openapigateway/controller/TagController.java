package com.nhnacademy.springboot.openapigateway.controller;

import com.nhnacademy.springboot.openapigateway.domain.Tag;
import com.nhnacademy.springboot.openapigateway.domain.TagEditDto;
import com.nhnacademy.springboot.openapigateway.service.TagService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@Controller
@RequestMapping("/projects/{projectId}/tags")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping
    public String createTag(@PathVariable Long projectId, @Valid Tag tag) {
        tagService.createTag(projectId, tag);
        return "redirect:" + UriComponentsBuilder.fromPath("/project/{projectId}")
                                                 .buildAndExpand(projectId)
                                                 .toUriString();
    }

    @PutMapping("/{tagId}")
    public String editTag(@PathVariable Long projectId, @PathVariable Long tagId, @Valid TagEditDto tagEditDto) {
        tagService.editTag(projectId, tagId, tagEditDto);
        return "redirect:" + UriComponentsBuilder.fromPath("/project/{projectId}")
                                                 .buildAndExpand(projectId)
                                                 .toUriString();
    }

    @DeleteMapping("/{tagId}")
    public String deleteTag(@PathVariable Long projectId, @PathVariable Long tagId) {
        tagService.deleteTag(projectId,tagId);
        return "redirect:" + UriComponentsBuilder.fromPath("/project/{projectId}")
                                                 .buildAndExpand(projectId)
                                                 .toUriString();
    }
}
