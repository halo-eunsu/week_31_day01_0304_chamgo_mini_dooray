package com.nhnacademy.springboot.taskapiserver.domain.comment;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;


    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> getComments() {
        return commentRepository.findAll();
    }

    @Override
    public Comment getComment(Long id) {
        return commentRepository.findById(id)
                .orElse(null);
    }

    @Override
    public void deleteComment(Long projectId, Long taskId, Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public Comment modifyComment(Long projectId, Long taskId, Long commentId, String content) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found: " + commentId));
        comment.setContent(content);
        return commentRepository.save(comment);
    }

    @Override
    public Comment createComment(Long projectId, Long taskId, String content) {
        Comment comment = new Comment();
        comment.setContent(content);
        return commentRepository.save(comment);
    }
}
