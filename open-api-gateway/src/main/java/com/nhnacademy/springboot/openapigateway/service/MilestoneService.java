package com.nhnacademy.springboot.openapigateway.service;

import com.nhnacademy.springboot.openapigateway.config.properties.MilestoneServiceProperties;
import com.nhnacademy.springboot.openapigateway.domain.Milestone;
import com.nhnacademy.springboot.openapigateway.domain.MilestoneEditDto;
import com.nhnacademy.springboot.openapigateway.utils.HttpHeadersUtils;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MilestoneService {
    private final RestTemplate restTemplate;

    private final MilestoneServiceProperties milestoneServiceProperties;

    public MilestoneService(RestTemplate restTemplate, MilestoneServiceProperties milestoneServiceProperties) {
        this.restTemplate = restTemplate;
        this.milestoneServiceProperties = milestoneServiceProperties;
    }

    // 마일스톤 추가
    public void createMilestone(Long projectId, Milestone milestone) {
        HttpHeaders httpHeaders = HttpHeadersUtils.createJsonHeaders();
        HttpEntity<Milestone> requestEntity = new HttpEntity<>(milestone, httpHeaders);

        ResponseEntity<Void> exchange = restTemplate.exchange(milestoneServiceProperties.getAddress(),
                HttpMethod.POST,
                requestEntity,
                Void.class,
                projectId);

        if (exchange.getStatusCode() != HttpStatus.CREATED) {
            throw new RuntimeException("HTTP Status: " + exchange.getStatusCode());
        }
    }

    // 마일스톤 수정
    public void editMilestone(Long projectId, Long milestoneId, MilestoneEditDto milestoneEditDto) {
        HttpHeaders httpHeaders = HttpHeadersUtils.createJsonHeaders();
        HttpEntity<MilestoneEditDto> requestEntity = new HttpEntity<>(milestoneEditDto, httpHeaders);

        ResponseEntity<Void> exchange = restTemplate.exchange(milestoneServiceProperties.getAddress() +"{milestoneId}",
                HttpMethod.PUT,
                requestEntity,
                Void.class,
                projectId,
                milestoneId);

        if (exchange.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("HTTP Status: " + exchange.getStatusCode());
        }
    }

    // 마일스톤 삭제
    public void deleteMilestone(Long projectId, Long milestoneId) {
        HttpHeaders httpHeaders = HttpHeadersUtils.createJsonHeaders();
        HttpEntity<MilestoneEditDto> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Void> exchange = restTemplate.exchange(milestoneServiceProperties.getAddress() +"/{milestoneId}",
                HttpMethod.DELETE,
                requestEntity,
                Void.class,
                projectId,
                milestoneId);

        if (exchange.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("HTTP Status: " + exchange.getStatusCode());
        }
    }

}
