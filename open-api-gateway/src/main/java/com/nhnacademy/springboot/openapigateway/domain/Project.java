package com.nhnacademy.springboot.openapigateway.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @NotBlank
    private Long id;
    @NotBlank
    private Long statusId;
    @NotEmpty
    private String projectName;
    private String content;
}
