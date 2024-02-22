package com.nhnacademy.springboot.openapigateway.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @NotBlank
    private String memberId;
    @NotBlank
    private Long projectId;
    @NotBlank
    private Auth auth;
}

