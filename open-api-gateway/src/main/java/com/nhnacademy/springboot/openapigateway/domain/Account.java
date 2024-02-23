package com.nhnacademy.springboot.openapigateway.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {
    @NotBlank
    private String id;
    @NotBlank
    private String password;
    @NotBlank
    private String email;
    @NotBlank
    private String name;
}