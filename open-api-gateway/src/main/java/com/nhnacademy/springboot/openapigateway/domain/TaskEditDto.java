package com.nhnacademy.springboot.openapigateway.domain;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TaskEditDto {
    private Long milestoneId;

    private String title;

    private String content;
}
