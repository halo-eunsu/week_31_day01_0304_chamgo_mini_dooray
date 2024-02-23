package com.nhnacademy.springboot.taskapiserver.domain.comment;

import java.util.List;

public interface CommentService {

    List<Comment> getComments();

    Comment getComment(Long id);
    void deleteComment(Comment comment);

    void modifyCommentContent(Comment comment);

    Comment createComment(Comment comment);
}
