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
        // 모의 객체를 사용한 테스트 로직 작성
        commentService.getComments();
        verify(commentRepository).findAll(); // findAll 메서드가 호출되었는지 검증
    }

    @Test
    void getComment() {
        Long commentId = 1L;
        // 특정 조건을 지정하여 모의 객체의 행동을 정의할 수 있습니다.
        when(commentRepository.findById(commentId)).thenReturn(Optional.of(new Comment()));

        commentService.getComment(commentId);
        verify(commentRepository).findById(commentId); // findById 메서드가 주어진 ID로 호출되었는지 검증
    }

    @Test
    void deleteComment() {
        Long projectId = 1L;
        Long taskId = 1L;
        Long commentId = 1L;

        doNothing().when(commentRepository).deleteById(commentId);

        commentService.deleteComment(projectId, taskId, commentId);
        verify(commentRepository).deleteById(commentId); // deleteById 메서드가 주어진 ID로 호출되었는지 검증
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
        assertEquals(content, result.getContent()); // 수정된 코멘트의 내용이 예상대로 변경되었는지 검증
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
        assertNotNull(result); // 생성된 코멘트가 null이 아닌지 검증
        assertEquals(content, result.getContent()); // 생성된 코멘트의 내용이 예상대로인지 검증
    }
}
