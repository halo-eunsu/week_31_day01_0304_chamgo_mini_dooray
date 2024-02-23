package com.nhnacademy.springboot.taskapiserver.domain.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project updateProject(Long projectId, Project projectDetails) {
        Optional<Project> existingProject = projectRepository.findById(projectId);
        if (existingProject.isPresent()) {
            Project project = existingProject.get();
            return projectRepository.save(project);
        } else {
            throw new IllegalArgumentException("Project not found with ID: " + projectId);
        }
    }

    @Override
    public void deleteProject(Long projectId) {
        projectRepository.deleteById(projectId);
    }

    @Override
    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }
}
