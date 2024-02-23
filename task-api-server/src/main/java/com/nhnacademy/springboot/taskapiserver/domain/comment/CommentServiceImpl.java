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
    public void deleteComment(Comment comment) {
        commentRepository.delete(comment);
    }

    @Override
    public void modifyCommentContent(Comment comment) {
        commentRepository.findById(comment.getCommentId()).ifPresent(
                targetComment -> {
                    targetComment.setContent(comment.getContent());
                    commentRepository.save(targetComment);
                });
    }

    @Override
    public Comment createComment(Comment comment) {
        if (commentRepository.existsById(comment.getCommentId())) {
            throw new IllegalArgumentException("id: " + comment.getCommentId() + "is already exist");
        }
        return commentRepository.save(comment);
    }
}
