package com.nhnacademy.springboot.taskapiserver.controller;

import lombok.Data;

@Data
public class ResultResponse {
    private String result;
    public ResultResponse(String result) {
        this.result = result;
    }
}
