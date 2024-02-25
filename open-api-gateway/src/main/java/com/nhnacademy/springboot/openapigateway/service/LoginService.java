package com.nhnacademy.springboot.openapigateway.service;

import com.nhnacademy.springboot.openapigateway.config.properties.LoginServiceProperties;
import com.nhnacademy.springboot.openapigateway.domain.LoginRequest;
import com.nhnacademy.springboot.openapigateway.domain.LoginResponse;
import com.nhnacademy.springboot.openapigateway.utils.HttpHeadersUtils;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class LoginService {
    private final RestTemplate restTemplate;

    private final LoginServiceProperties loginServiceProperties;

    public LoginService(RestTemplate restTemplate, LoginServiceProperties loginServiceProperties) {
        this.restTemplate = restTemplate;
        this.loginServiceProperties = loginServiceProperties;
    }

    public Optional<LoginResponse> login(LoginRequest loginRequest) {
        HttpHeaders httpHeaders = HttpHeadersUtils.createJsonHeaders();
        HttpEntity<LoginRequest> requestEntity = new HttpEntity<>(loginRequest, httpHeaders);

        ResponseEntity<LoginResponse> exchange = restTemplate.exchange(loginServiceProperties.getAddress(),
                HttpMethod.POST,
                requestEntity,
                LoginResponse.class);

        if (exchange.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("HTTP Status: " + exchange.getStatusCode());
        }
        return Optional.ofNullable(exchange.getBody());
    }
}