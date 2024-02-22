package com.nhnacademy.springboot.taskapiserver.domain.Project;

import com.nhnacademy.springboot.taskapiserver.entity.Project;

import java.util.Optional;

public interface ProjectService {

    Project createService(Project project);

    Project getProjectNameById(Long id);



}
