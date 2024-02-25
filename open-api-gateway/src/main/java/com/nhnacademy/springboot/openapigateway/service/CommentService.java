package com.nhnacademy.springboot.openapigateway.service;

import com.nhnacademy.springboot.openapigateway.config.properties.CommentServiceProperties;
import com.nhnacademy.springboot.openapigateway.domain.Comment;
import com.nhnacademy.springboot.openapigateway.domain.CommentEditDto;
import com.nhnacademy.springboot.openapigateway.utils.HttpHeadersUtils;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CommentService {

    private final RestTemplate restTemplate;

    private final CommentServiceProperties commentServiceProperties;

    public CommentService(RestTemplate restTemplate, CommentServiceProperties commentServiceProperties) {
        this.restTemplate = restTemplate;
        this.commentServiceProperties = commentServiceProperties;
    }

    public void createComment(Long projectId, Long taskId, Comment comment) {
        HttpHeaders httpHeaders = HttpHeadersUtils.createJsonHeaders();
        HttpEntity<Comment> requestEntity = new HttpEntity<>(comment, httpHeaders);

        ResponseEntity<Void> exchange = restTemplate.exchange(commentServiceProperties.getAddress(),
                HttpMethod.POST,
                requestEntity,
                Void.class,
                projectId,
                taskId);

        if (exchange.getStatusCode() != HttpStatus.CREATED) {
            throw new RuntimeException("HTTP Status: " + exchange.getStatusCode());
        }
    }

    public void editComment(Long projectId, Long taskId, Long commentId, CommentEditDto commentEditDto) {
        HttpHeaders httpHeaders = HttpHeadersUtils.createJsonHeaders();
        HttpEntity<CommentEditDto> requestEntity = new HttpEntity<>(commentEditDto, httpHeaders);

        ResponseEntity<Void> exchange = restTemplate.exchange(commentServiceProperties.getAddress() + "/{commentId}",
                HttpMethod.PUT,
                requestEntity,
                Void.class,
                projectId,
                taskId,
                commentId);

        if (exchange.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("HTTP Status: " + exchange.getStatusCode());
        }
    }

    public void deleteComment(Long projectId, Long taskId, Long commentId) {
        HttpHeaders httpHeaders = HttpHeadersUtils.createJsonHeaders();
        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Void> exchange = restTemplate.exchange(commentServiceProperties.getAddress() + "/{commentId}",
                HttpMethod.DELETE,
                requestEntity,
                Void.class,
                projectId,
                taskId,
                commentId);

        if (exchange.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("HTTP Status: " + exchange.getStatusCode());
        }
    }
}
