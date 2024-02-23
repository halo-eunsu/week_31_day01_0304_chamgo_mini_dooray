package com.nhnacademy.springboot.taskapiserver.domain.project;

public interface ProjectService {

    Project createProject(Project project);

    Project getProjectNameById(Long id);



}
