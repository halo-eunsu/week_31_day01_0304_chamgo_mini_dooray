package com.nhnacademy.springboot.taskapiserver.domain.Project;

import com.nhnacademy.springboot.taskapiserver.entity.Project;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project createService(Project project) {
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
