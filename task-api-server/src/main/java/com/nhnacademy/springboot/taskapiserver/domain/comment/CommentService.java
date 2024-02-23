package com.nhnacademy.springboot.taskapiserver.domain.comment;

import com.nhnacademy.springboot.taskapiserver.domain.member.Member;
import com.nhnacademy.springboot.taskapiserver.domain.tag.Tag;
import org.springframework.scheduling.config.Task;

import java.util.List;

public interface CommentService {

    List<Comment> getComments();

    void updateComment(String content);

    Comment getComment(Long id);
    void deleteComment(Comment comment);

    void modifyCommentContent(Comment comment);

    Comment createComment(Comment comment);
}
