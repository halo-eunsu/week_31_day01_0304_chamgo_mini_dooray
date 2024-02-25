package com.nhnacademy.springboot.openapigateway.domain;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountEditDto {
    private String password;

    private String email;

    private String name;
}
