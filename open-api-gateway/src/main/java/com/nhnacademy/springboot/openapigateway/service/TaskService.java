package com.nhnacademy.springboot.openapigateway.service;

import com.nhnacademy.springboot.openapigateway.config.properties.TaskServiceProperties;
import com.nhnacademy.springboot.openapigateway.domain.MilestoneSetDto;
import com.nhnacademy.springboot.openapigateway.domain.TagSetDto;
import com.nhnacademy.springboot.openapigateway.domain.Task;
import com.nhnacademy.springboot.openapigateway.domain.TaskEditDto;
import com.nhnacademy.springboot.openapigateway.utils.HttpHeadersUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class TaskService {
    private final RestTemplate restTemplate;

    private final TaskServiceProperties taskServiceProperties;

    public TaskService(RestTemplate restTemplate, TaskServiceProperties taskServiceProperties) {
        this.restTemplate = restTemplate;
        this.taskServiceProperties = taskServiceProperties;
    }

    // task 등록
    public void createTask(Long projectId, Task task) {
        HttpHeaders httpHeaders = HttpHeadersUtils.createJsonHeaders();
        HttpEntity<Task> requestEntity = new HttpEntity<>(task, httpHeaders);

        ResponseEntity<Void> exchange = restTemplate.exchange(taskServiceProperties.getAddress(),
                HttpMethod.POST,
                requestEntity,
                Void.class,
                projectId);

        if (exchange.getStatusCode() != HttpStatus.CREATED) {
            throw new RuntimeException("HTTP Status: " + exchange.getStatusCode());
        }
    }

    // task 수정
    public void editTask(Long projectId, Long taskId, TaskEditDto taskEditDto) {
        HttpHeaders httpHeaders = HttpHeadersUtils.createJsonHeaders();
        HttpEntity<TaskEditDto> requestEntity = new HttpEntity<>(taskEditDto, httpHeaders);

        ResponseEntity<Void> exchange = restTemplate.exchange(taskServiceProperties.getAddress() + "/{taskId}",
                HttpMethod.PUT,
                requestEntity,
                Void.class,
                projectId,
                taskId);

        if (exchange.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("HTTP Status: " + exchange.getStatusCode());
        }
    }

    // Task 삭제
    public void deleteTask(Long projectId, Long taskId) {
        HttpHeaders httpHeaders = HttpHeadersUtils.createJsonHeaders();
        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Void> exchange = restTemplate.exchange(taskServiceProperties.getAddress() + "/{taskId}",
                HttpMethod.DELETE,
                requestEntity,
                Void.class,
                projectId,
                taskId);

        if (exchange.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("HTTP Status: " + exchange.getStatusCode());
        }
    }

    // 프로젝트의 Task 목록 조회
    public List<Task> getTasks(Long projectId) {
        HttpHeaders httpHeaders = HttpHeadersUtils.createJsonHeaders();
        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<List<Task>> exchange = restTemplate.exchange(taskServiceProperties.getAddress(),
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {},
                projectId);

        if (exchange.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("HTTP Status: " + exchange.getStatusCode());
        }

        return exchange.getBody();
    }

    // Task 내용 조회
    public Task getTask(Long projectId, Long taskId) {
        HttpHeaders httpHeaders = HttpHeadersUtils.createJsonHeaders();
        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Task> exchange = restTemplate.exchange(taskServiceProperties.getAddress() + "/{taskId}",
                HttpMethod.GET,
                requestEntity,
                Task.class,
                projectId,
                taskId);

        if (exchange.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("HTTP Status: " + exchange.getStatusCode());
        }

        return exchange.getBody();
    }

    // Task의 Tag 설정
    public void setTags(Long projectId, Long taskId, TagSetDto tagSetDto) {
        HttpHeaders httpHeaders = HttpHeadersUtils.createJsonHeaders();
        HttpEntity<TagSetDto> requestEntity = new HttpEntity<>(tagSetDto, httpHeaders);

        ResponseEntity<Void> exchange = restTemplate.exchange(taskServiceProperties.getAddress() + "/{taskId}/tags",
                HttpMethod.POST,
                requestEntity,
                Void.class,
                projectId,
                taskId);

        if (exchange.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("HTTP Status: " + exchange.getStatusCode());
        }
    }

    // Task의 Milestone 설정
    public void setMilestone(Long projectId, Long taskId, MilestoneSetDto milestoneSetDto) {
        HttpHeaders httpHeaders = HttpHeadersUtils.createJsonHeaders();
        HttpEntity<MilestoneSetDto> requestEntity = new HttpEntity<>(milestoneSetDto, httpHeaders);

        ResponseEntity<Void> exchange = restTemplate.exchange(taskServiceProperties.getAddress() + "/{taskId}/milestone",
                HttpMethod.POST,
                requestEntity,
                Void.class,
                projectId,
                taskId);

        if (exchange.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("HTTP Status: " + exchange.getStatusCode());
        }
    }
}
