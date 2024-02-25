package com.nhnacademy.springboot.openapigateway.domain;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Comment {

    @NotEmpty
    private Long commentId;

    @NotEmpty
    private Long taskId;

    @NotEmpty
    private String content;

    @NotBlank
    private String memberId;

}
