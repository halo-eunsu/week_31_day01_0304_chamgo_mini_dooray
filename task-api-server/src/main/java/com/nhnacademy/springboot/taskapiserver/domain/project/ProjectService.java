package com.nhnacademy.springboot.taskapiserver.domain.project;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    Project createProject(Project project);
    Project updateProject(Long projectId, Project projectDetails);
    void deleteProject(Long projectId);
    Optional<Project> getProjectById(Long id);
    List<Project> getAllProjects();
}
