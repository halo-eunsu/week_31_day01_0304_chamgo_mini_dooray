package com.nhnacademy.springboot.taskapiserver.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectDto {
    private Long projectId;
    private String projectName;
    private String content;
    private String status;
}
