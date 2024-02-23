package com.nhnacademy.springboot.taskapiserver.domain.task;

import com.nhnacademy.springboot.taskapiserver.domain.comment.Comment;
import com.nhnacademy.springboot.taskapiserver.domain.project.Project;
import com.nhnacademy.springboot.taskapiserver.domain.project.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public Task registerTask(Long projectId, Task task) {
        return projectRepository.findById(projectId).map(project -> {
            task.setProject(project);
            return taskRepository.save(task);
        }).orElseThrow(() -> new IllegalArgumentException("Invalid project ID: " + projectId));
    }

    @Override
    public Task modifyTask(Long projectId, Long taskId, Task updatedTask) {
        if (!projectRepository.existsById(projectId)) {
            throw new IllegalArgumentException("Invalid project ID: " + projectId);
        }
        return taskRepository.findById(taskId).map(task -> {
            task.setTitle(updatedTask.getTitle());
            task.setContent(updatedTask.getContent());

            return taskRepository.save(task);
        }).orElseThrow(() -> new IllegalArgumentException("Invalid task ID: " + taskId));
    }

    @Override
    public void deleteTask(Long projectId, Long taskId) {
        if (!projectRepository.existsById(projectId)) {
            throw new IllegalArgumentException("Invalid project ID: " + projectId);
        }
        taskRepository.deleteById(taskId);
    }

    @Override
    public void modifyTaskComment(Task task) {
    }

    @Override
    public List<Task> getTasks() {
        return null;
    }

    @Override
    public List<Comment> getTaskComment(Long id) {
        return null;
    }

    @Override
    public List<Task> getTasksByProjectId(Long projectId) {
        return taskRepository.findAllByProjectId(projectId);
    }

    @Override
    public Optional<Task> getTaskByProjectIdAndTaskId(Long projectId, Long taskId) {
        return taskRepository.findByIdAndProjectId(taskId, projectId);
    }
}
