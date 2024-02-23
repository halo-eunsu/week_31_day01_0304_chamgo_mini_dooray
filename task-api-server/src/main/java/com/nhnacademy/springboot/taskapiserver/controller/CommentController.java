package com.nhnacademy.springboot.taskapiserver.controller;

import com.nhnacademy.springboot.taskapiserver.domain.comment.Comment;
import com.nhnacademy.springboot.taskapiserver.domain.comment.CommentRepository;
import com.nhnacademy.springboot.taskapiserver.domain.comment.CommentService;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects/{projectId}")
public class CommentController {

    private final CommentRepository commentRepository;

    public CommentController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }


    @Getter
    static class CommentResponse {
        private Long projectId;
        private Long taskId;
    }

//todo:  1. Comment 생성 - POST /projects/{projectId}/tasks/{taskId}/comments
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Comment createComment(@PathVariable ("projectId") Long projectId,
                                 @PathVariable("taskId")Long taskId,
                                 @RequestBody Comment comment){

        return commentRepository.save(comment);
    }

//todo: 2. Comment 수정 - PUT /projects/{projectId}/tasks/{taskId}/comments/{commentId}

//todo: 3. Comment 삭제 - DELETE /projects/{projectId}/tasks/{taskId}/comments/{commentId}
}
