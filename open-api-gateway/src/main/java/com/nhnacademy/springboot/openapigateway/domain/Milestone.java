package com.nhnacademy.springboot.openapigateway.domain;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Milestone {
    @NotEmpty
    private Long milestoneId;

    @NotEmpty
    private Long projectId;

    @NotBlank
    private String milestoneName;
}
