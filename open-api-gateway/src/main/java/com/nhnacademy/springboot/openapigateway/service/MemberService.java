package com.nhnacademy.springboot.openapigateway.service;

import com.nhnacademy.springboot.openapigateway.config.properties.MemberServiceProperties;
import com.nhnacademy.springboot.openapigateway.domain.Member;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class MemberService {
    private final RestTemplate restTemplate;

    private final MemberServiceProperties memberServiceProperties;

    public MemberService(RestTemplate restTemplate, MemberServiceProperties memberServiceProperties) {
        this.restTemplate = restTemplate;
        this.memberServiceProperties = memberServiceProperties;
    }

    public Member getMember(String memberId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<Member> exchange = restTemplate.exchange(memberServiceProperties.getAddress() + "/{memberId}",
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

