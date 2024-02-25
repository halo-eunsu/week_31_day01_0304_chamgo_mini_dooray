package com.nhnacademy.springboot.openapigateway.domain;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProjectEditDto {
    private Long statusId;

    private String projectName;

    private String content;
}
