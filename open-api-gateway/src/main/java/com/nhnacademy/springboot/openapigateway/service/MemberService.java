package com.nhnacademy.springboot.openapigateway.service;

import com.nhnacademy.springboot.openapigateway.config.properties.MemberServiceProperties;
import com.nhnacademy.springboot.openapigateway.domain.Member;
import com.nhnacademy.springboot.openapigateway.domain.Project;
import com.nhnacademy.springboot.openapigateway.utils.HttpHeadersUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private final RestTemplate restTemplate;

    private final MemberServiceProperties memberServiceProperties;

    public MemberService(RestTemplate restTemplate, MemberServiceProperties memberServiceProperties) {
        this.restTemplate = restTemplate;
        this.memberServiceProperties = memberServiceProperties;
    }

    // 멤버 아이디로 멤버 보기
    public Optional<Member> getMember(String memberId) {
        HttpHeaders httpHeaders = HttpHeadersUtils.createJsonHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Member> exchange;
        try {
            exchange = restTemplate.exchange(memberServiceProperties.getAddress() + "/{memberId}",
                    HttpMethod.GET,
                    requestEntity,
                    Member.class,
                    memberId);
        } catch (HttpServerErrorException e) {
            return Optional.empty();
        }

        if (exchange.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("HTTP Status: " + exchange.getStatusCode());
        }
        System.out.println(exchange.getBody());
        return Optional.ofNullable(exchange.getBody());
    }

    // 내가 속한 프로젝트 목록 보기
    public List<Project> getProjects(String memberId) {
        HttpHeaders httpHeaders = HttpHeadersUtils.createJsonHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<List<Project>> exchange = restTemplate.exchange(memberServiceProperties.getAddress() + "/{memberId}/projects",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                },
                memberId);

        if (exchange.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("HTTP Status: " + exchange.getStatusCode());
        }
        return exchange.getBody();
    }

}

