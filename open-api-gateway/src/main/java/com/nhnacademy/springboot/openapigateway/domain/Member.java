package com.nhnacademy.springboot.openapigateway.domain;


import com.nhnacademy.springboot.openapigateway.domain.type.Auth;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member {
    @NotBlank
    private String memberId;
    @NotBlank
    private Long projectId;
    @NotBlank
    private Auth auth;
}

