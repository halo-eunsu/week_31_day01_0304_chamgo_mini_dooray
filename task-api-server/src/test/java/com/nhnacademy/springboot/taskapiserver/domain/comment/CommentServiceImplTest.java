package com.nhnacademy.springboot.taskapiserver.domain.comment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class CommentServiceImplTest {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentServiceImpl commentService;

    @Test
    void getComments() {

        commentService.getComments();
        verify(commentRepository).findAll();
    }

    @Test
    void getComment() {
        Long commentId = 1L;

        when(commentRepository.findById(commentId)).thenReturn(Optional.of(new Comment()));

        commentService.getComment(commentId);
        verify(commentRepository).findById(commentId);
    }

    @Test
    void deleteComment() {
        Long projectId = 1L;
        Long taskId = 1L;
        Long commentId = 1L;

        doNothing().when(commentRepository).deleteById(commentId);

        commentService.deleteComment(projectId, taskId, commentId);
        verify(commentRepository).deleteById(commentId);
    }

    @Test
    void modifyComment() {
        Long projectId = 1L;
        Long taskId = 1L;
        Long commentId = 1L;
        String content = "updated content";

        Comment updatedComment = new Comment();
        updatedComment.setContent(content);

        when(commentRepository.findById(commentId)).thenReturn(Optional.of(new Comment()));
        when(commentRepository.save(any(Comment.class))).thenReturn(updatedComment);

        Comment result = commentService.modifyComment(projectId, taskId, commentId, content);
        assertEquals(content, result.getContent());
    }

    @Test
    void createComment() {
        Long projectId = 1L;
        Long taskId = 1L;
        String content = "new comment";

        Comment newComment = new Comment();
        newComment.setContent(content);

        when(commentRepository.save(any(Comment.class))).thenReturn(newComment);

        Comment result = commentService.createComment(projectId, taskId, content);
        assertNotNull(result);
        assertEquals(content, result.getContent());
    }
}
