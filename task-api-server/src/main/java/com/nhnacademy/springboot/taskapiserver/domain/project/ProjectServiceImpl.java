package com.nhnacademy.springboot.taskapiserver.domain.project;

import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project createProject(Project project) {
        if (projectRepository.existsById(project.getProjectId())) {
            throw new IllegalArgumentException("이미 존재: " + project.getProjectId());
        }
        return projectRepository.save(project);
    }


    @Override
    public Project getProjectNameById(Long id) {
        return projectRepository.findById(id)
                .orElse(null);
    }

}
