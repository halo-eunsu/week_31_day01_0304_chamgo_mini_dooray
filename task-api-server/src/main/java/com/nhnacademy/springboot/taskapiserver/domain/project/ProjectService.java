package com.nhnacademy.springboot.taskapiserver.domain.project;

public interface ProjectService {

    Project createService(Project project);

    Project getProjectNameById(Long id);



}
