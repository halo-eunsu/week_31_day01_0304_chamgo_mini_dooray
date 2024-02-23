package com.nhnacademy.springboot.openapigateway.service;

import com.nhnacademy.springboot.openapigateway.config.properties.ProjectServiceProperties;
import com.nhnacademy.springboot.openapigateway.domain.Member;
import com.nhnacademy.springboot.openapigateway.domain.Project;
import com.nhnacademy.springboot.openapigateway.domain.ProjectEditDto;
import com.nhnacademy.springboot.openapigateway.utils.HttpHeadersUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service

public class ProjectService {

    private final RestTemplate restTemplate;

    private final ProjectServiceProperties projectServiceProperties;

    public ProjectService(RestTemplate restTemplate, ProjectServiceProperties projectServiceProperties) {
        this.restTemplate = restTemplate;
        this.projectServiceProperties = projectServiceProperties;
    }

    // 프로젝트 보기
    public Project getProject(Long projectId) {
        HttpHeaders httpHeaders = HttpHeadersUtils.createJsonHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Project> exchange = restTemplate.exchange(projectServiceProperties.getAddress() + "/{projectId}",
                HttpMethod.GET,
                requestEntity,
                Project.class,
                projectId);

        if (exchange.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("HTTP Status: " + exchange.getStatusCode());
        }
        return exchange.getBody();
    }

    // 프로젝트 만들기
    public void createProject(Project project) {
        HttpHeaders httpHeaders = HttpHeadersUtils.createJsonHeaders();
        HttpEntity<Project> requestEntity = new HttpEntity<>(project, httpHeaders);

        ResponseEntity<Void> exchange = restTemplate.exchange(projectServiceProperties.getAddress(),
                HttpMethod.POST,
                requestEntity,
                Void.class);
        if (exchange.getStatusCode() != HttpStatus.CREATED) {
            throw new RuntimeException("HTTP Status: " + exchange.getStatusCode());
        }
    }

    // 프로젝트 삭제
    public void deleteProject(Long projectId) {
        HttpHeaders httpHeaders = HttpHeadersUtils.createJsonHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Void> exchange = restTemplate.exchange(projectServiceProperties.getAddress() + "/{projectId}",
                HttpMethod.DELETE,
                requestEntity,
                Void.class,
                projectId);

        if (exchange.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("HTTP Status: " + exchange.getStatusCode());
        }
    }

    // 프로젝트명, 내용, 상태 수정
    public void editProject(ProjectEditDto projectEditDto) {
        HttpHeaders httpHeaders = HttpHeadersUtils.createJsonHeaders();
        HttpEntity<ProjectEditDto> requestEntity = new HttpEntity<>(projectEditDto, httpHeaders);

        ResponseEntity<Void> exchange = restTemplate.exchange(projectServiceProperties.getAddress() + "/{projectId}",
                HttpMethod.PUT,
                requestEntity,
                Void.class,
                projectEditDto.getProjectId());

        if (exchange.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("HTTP Status: " + exchange.getStatusCode());
        }
    }



    // 프로젝트에 멤버 추가하기
    public void addProjectMember(Long projectId, Member member) {
        HttpHeaders httpHeaders = HttpHeadersUtils.createJsonHeaders();
        HttpEntity<Member> requestEntity = new HttpEntity<>(member, httpHeaders);

        ResponseEntity<Void> exchange = restTemplate.exchange(projectServiceProperties.getAddress() + "/{projectId}/members",
                HttpMethod.POST,
                requestEntity,
                Void.class,
                projectId);

        if (exchange.getStatusCode() != HttpStatus.CREATED) {
            throw new RuntimeException("HTTP Status: " + exchange.getStatusCode());
        }
    }

    // 프로젝트에 멤버 삭제하기
    public void deleteProjectMember(Long projectId, Long memberId) {
        HttpHeaders httpHeaders = HttpHeadersUtils.createJsonHeaders();
        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Void> exchange = restTemplate.exchange(projectServiceProperties.getAddress() + "/{projectId}/members/{memberId}",
                HttpMethod.DELETE,
                requestEntity,
                Void.class,
                projectId,
                memberId);

        if (exchange.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("HTTP Status: " + exchange.getStatusCode());
        }
    }


    // task 등록


}
