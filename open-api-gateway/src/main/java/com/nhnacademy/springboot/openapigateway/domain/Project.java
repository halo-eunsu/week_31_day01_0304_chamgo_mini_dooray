package com.nhnacademy.springboot.openapigateway.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Project {
    @NotBlank
    private Long projectId;
    @NotBlank
    private Long statusId;
    @NotEmpty
    private String projectName;
    private String content;
}
