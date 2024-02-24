package com.nhnacademy.springboot.taskapiserver.domain.comment;

import com.nhnacademy.springboot.taskapiserver.domain.member.Member;
import com.nhnacademy.springboot.taskapiserver.domain.tag.Tag;
import org.springframework.scheduling.config.Task;

import java.util.List;

public interface CommentService {

    List<Comment> getComments();

    Comment getComment(Long id);
    void deleteComment(Long projectId, Long taskId, Long commentId);

    Comment modifyComment(Long projectId, Long taskId, Long commentId, String content);

    Comment createComment(Long projectId, Long taskId, String content);
}
