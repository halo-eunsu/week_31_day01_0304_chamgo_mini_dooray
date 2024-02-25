package com.nhnacademy.springboot.openapigateway.domain;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentEditDto {

    @NotEmpty
    private String content;

}
