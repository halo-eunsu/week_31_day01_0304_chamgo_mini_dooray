package com.nhnacademy.springboot.openapigateway.controller;

import com.nhnacademy.springboot.openapigateway.domain.Comment;
import com.nhnacademy.springboot.openapigateway.domain.CommentEditDto;
import com.nhnacademy.springboot.openapigateway.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@Controller
@RequestMapping("/projects/{projectId}/tasks/{taskId}/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public String createComment(@PathVariable Long projectId, @PathVariable Long taskId, @Valid Comment comment) {
        commentService.createComment(projectId, taskId, comment);
        return "redirect:" + UriComponentsBuilder.fromPath("/projects/{projectId}/tasks/{taskId}")
                                                 .buildAndExpand(projectId, taskId)
                                                 .toUriString();
    }

    @PutMapping("/{commentId}")
    public String editComment(@PathVariable Long projectId, @PathVariable Long taskId, @PathVariable Long commentId, @Valid CommentEditDto commentEditDto) {
        commentService.editComment(projectId, taskId, commentId, commentEditDto);
        return "redirect:" + UriComponentsBuilder.fromPath("/projects/{projectId}/tasks/{taskId}")
                                                 .buildAndExpand(projectId, taskId)
                                                 .toUriString();
    }

    @DeleteMapping("/{commentId}")
    public String deleteComment(@PathVariable Long projectId, @PathVariable Long taskId, @PathVariable Long commentId) {
        commentService.deleteComment(projectId,taskId,commentId);
        return "redirect:" + UriComponentsBuilder.fromPath("/projects/{projectId}/tasks/{taskId}")
                                                 .buildAndExpand(projectId, taskId)
                                                 .toUriString();
    }
}
