package com.nhnacademy.springboot.taskapiserver.controller;

import com.nhnacademy.springboot.taskapiserver.domain.comment.Comment;
import com.nhnacademy.springboot.taskapiserver.domain.comment.CommentRepository;
import com.nhnacademy.springboot.taskapiserver.domain.comment.CommentService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects/{projectId}")
public class CommentController {

    private final CommentService commentService;

    @Getter
    static class CommentRequest {
        private String content;
    }


//todo:  1. Comment 생성 - POST /projects/{projectId}/tasks/{taskId}/comments
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Comment createComment(@PathVariable ("projectId") Long projectId,
                                 @PathVariable("taskId")Long taskId,
                                 @RequestBody CommentRequest commentRequest){
        return null;
    }

//todo: 2. Comment 수정 - PUT /projects/{projectId}/tasks/{taskId}/comments/{commentId}
    @PutMapping("/task/{taskId}/comments/{commentId}")
    private ResultResponse modifyComment(@PathVariable("projectId") Long projectId,
                                         @PathVariable("taskId") Long taskId,
                                         @PathVariable("commentId") Long commentId,
                                         @RequestBody CommentRequest commentRequest){
        return null;

    }


//todo: 3. Comment 삭제 - DELETE /projects/{projectId}/tasks/{taskId}/comments/{commentId}
}
