package com.nhnacademy.springboot.openapigateway.domain;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginRequest {
    @NotBlank
    private String id;

    @NotBlank
    private String password;
}
