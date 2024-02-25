package com.nhnacademy.springboot.openapigateway.domain;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Task {
    @NotEmpty
    private Long taskId;

    @NotEmpty
    private Long projectId;

    private Long milestoneId;

    @NotBlank
    private String memberId;

    @NotEmpty
    private String title;

    private String content;
}
