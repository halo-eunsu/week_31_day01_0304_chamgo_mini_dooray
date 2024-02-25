package com.nhnacademy.springboot.taskapiserver.controller;

import com.nhnacademy.springboot.taskapiserver.domain.comment.Comment;
import com.nhnacademy.springboot.taskapiserver.domain.comment.CommentService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @PostMapping("/tasks/{taskId}/comments")
    public ResponseEntity<Comment> createComment(@PathVariable Long projectId,
                                        @PathVariable Long taskId,
                                        @RequestBody CommentRequest commentRequest) {
        Comment createdComment = commentService.createComment(projectId, taskId, commentRequest.getContent());
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }


    //todo: 2. Comment 수정 - PUT /projects/{projectId}/tasks/{taskId}/comments/{commentId}
    @PutMapping("/tasks/{taskId}/comments/{commentId}")
    public ResponseEntity<Comment> modifyComment(@PathVariable Long projectId,
                                                 @PathVariable Long taskId,
                                                 @PathVariable Long commentId,
                                                 @RequestBody CommentRequest commentRequest) {
        Comment updatedComment = commentService.modifyComment(projectId, taskId, commentId, commentRequest.getContent());
        return ResponseEntity.ok(updatedComment);
    }

    //todo: 3. Comment 삭제 - DELETE /projects/{projectId}/tasks/{taskId}/comments/{commentId}
    @DeleteMapping("/tasks/{taskId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long projectId,
                                              @PathVariable Long taskId,
                                              @PathVariable Long commentId) {
        commentService.deleteComment(projectId, taskId, commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
