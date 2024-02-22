package com.nhnacademy.springboot.openapigateway.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ConfigurationProperties(prefix = "login.adaptor")
public class LoginServiceProperties {
    @NotNull
    private String address;
}
