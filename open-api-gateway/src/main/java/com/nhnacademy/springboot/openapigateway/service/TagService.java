package com.nhnacademy.springboot.openapigateway.service;

import com.nhnacademy.springboot.openapigateway.config.properties.TagServiceProperties;
import com.nhnacademy.springboot.openapigateway.domain.Tag;
import com.nhnacademy.springboot.openapigateway.domain.TagEditDto;
import com.nhnacademy.springboot.openapigateway.utils.HttpHeadersUtils;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TagService {
    private final RestTemplate restTemplate;

    private final TagServiceProperties tagServiceProperties;

    public TagService(RestTemplate restTemplate, TagServiceProperties tagServiceProperties) {
        this.restTemplate = restTemplate;
        this.tagServiceProperties = tagServiceProperties;
    }

    public void createTag(Long projectId, Tag tag) {
        HttpHeaders httpHeaders = HttpHeadersUtils.createJsonHeaders();
        HttpEntity<Tag> requestEntity = new HttpEntity<>(tag, httpHeaders);

        ResponseEntity<Void> exchange = restTemplate.exchange(tagServiceProperties.getAddress(),
                HttpMethod.POST,
                requestEntity,
                Void.class,
                projectId);

        if (exchange.getStatusCode() != HttpStatus.CREATED) {
            throw new RuntimeException("HTTP Status: " + exchange.getStatusCode());
        }
    }

    public void editTag(Long projectId, Long tagId, TagEditDto tagEditDto) {
        HttpHeaders httpHeaders = HttpHeadersUtils.createJsonHeaders();
        HttpEntity<TagEditDto> requestEntity = new HttpEntity<>(tagEditDto, httpHeaders);

        ResponseEntity<Void> exchange = restTemplate.exchange(tagServiceProperties.getAddress() + "/{tagId}",
                HttpMethod.PUT,
                requestEntity,
                Void.class,
                projectId,
                tagId);

        if (exchange.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("HTTP Status: " + exchange.getStatusCode());
        }
    }

    public void deleteTag(Long projectId, Long tagId) {
        HttpHeaders httpHeaders = HttpHeadersUtils.createJsonHeaders();
        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Void> exchange = restTemplate.exchange(tagServiceProperties.getAddress() + "/{tagId}",
                HttpMethod.DELETE,
                requestEntity,
                Void.class,
                projectId,
                tagId);

        if (exchange.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("HTTP Status: " + exchange.getStatusCode());
        }
    }
}
