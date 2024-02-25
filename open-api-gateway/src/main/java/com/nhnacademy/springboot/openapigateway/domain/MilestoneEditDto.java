package com.nhnacademy.springboot.openapigateway.domain;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MilestoneEditDto {
    @NotBlank
    private String milestoneName;
}
