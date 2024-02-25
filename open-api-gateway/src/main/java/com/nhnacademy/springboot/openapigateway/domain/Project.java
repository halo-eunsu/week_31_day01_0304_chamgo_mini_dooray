package com.nhnacademy.springboot.openapigateway.domain;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Project {
    @NotEmpty
    private Long projectId;

    @NotBlank
    @Length(min = 3, max = 30)
    private String projectName;

    @NotBlank
    private StatusType status;

    private String content;

    public enum StatusType {
        ACTIVE,
        SLEEP,
        TERMINATION
    }
}
