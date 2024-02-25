package com.nhnacademy.springboot.openapigateway.domain;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Member {
    @NotBlank
    private String memberId;

    @NotEmpty
    private Long projectId;

    @NotBlank
    private Auth auth;

    public enum Auth {
        ADMIN,
        MEMBER
    }
}

