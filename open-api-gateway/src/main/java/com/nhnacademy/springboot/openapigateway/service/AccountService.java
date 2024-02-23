package com.nhnacademy.springboot.openapigateway.service;

import com.nhnacademy.springboot.openapigateway.config.properties.AccountServiceProperties;
import com.nhnacademy.springboot.openapigateway.domain.Account;
import com.nhnacademy.springboot.openapigateway.utils.HttpHeadersUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AccountService {

    private final RestTemplate restTemplate;
    private final AccountServiceProperties accountServiceProperties;

    public AccountService(RestTemplate restTemplate, AccountServiceProperties accountServiceProperties) {
        this.restTemplate = restTemplate;
        this.accountServiceProperties = accountServiceProperties;
    }

    public List<Account> getAccounts() {
        HttpHeaders httpHeaders = HttpHeadersUtils.createJsonHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<List<Account>> exchange = restTemplate.exchange(accountServiceProperties.getAddress(),
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });

        if (exchange.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("HTTP Status: " + exchange.getStatusCode());
        }

        return exchange.getBody();
    }

    public Account getAccount(Long id) {
        HttpHeaders httpHeaders = HttpHeadersUtils.createJsonHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Account> exchange = restTemplate.exchange(accountServiceProperties.getAddress() + "/{id}",
                HttpMethod.GET,
                requestEntity,
                Account.class,
                id);

        if (exchange.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("HTTP Status: " + exchange.getStatusCode());
        }

        return exchange.getBody();
    }

    public void createAccount(Account account) {
        HttpHeaders httpHeaders = HttpHeadersUtils.createJsonHeaders();
        HttpEntity<Account> requestEntity = new HttpEntity<>(account, httpHeaders);

        ResponseEntity<Void> exchange = restTemplate.exchange(accountServiceProperties.getAddress(),
                HttpMethod.POST,
                requestEntity,
                Void.class);
        if (exchange.getStatusCode() != HttpStatus.CREATED) {
            throw new RuntimeException("HTTP Status: " + exchange.getStatusCode());
        }
    }

    public void deleteAccount(Long id) {
        HttpHeaders httpHeaders = HttpHeadersUtils.createJsonHeaders();
        HttpEntity<Account> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Void> exchange = restTemplate.exchange(accountServiceProperties.getAddress() + "/{id}",
                HttpMethod.DELETE,
                requestEntity,
                Void.class,
                id);
        if (exchange.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("HTTP Status: " + exchange.getStatusCode());
        }
    }
}
