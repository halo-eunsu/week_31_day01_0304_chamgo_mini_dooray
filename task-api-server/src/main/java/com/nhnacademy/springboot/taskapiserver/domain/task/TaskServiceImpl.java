package com.nhnacademy.springboot.taskapiserver.domain.task;

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
        // 프로젝트 ID를 사용하여 프로젝트 검색
        return projectRepository.findById(projectId).map(project -> {
            task.setProject(project); // Task에 프로젝트 설정
            return taskRepository.save(task); // Task 저장
        }).orElseThrow(() -> new IllegalArgumentException("Invalid project ID: " + projectId));
    }

    @Override
    public Task modifyTask(Long projectId, Long taskId, Task updatedTask) {
        if (!projectRepository.existsById(projectId)) {
            throw new IllegalArgumentException("Invalid project ID: " + projectId);
        }
        return taskRepository.findById(taskId).map(task -> {
            // Task 정보 업데이트 로직 (예시)
            task.setTitle(updatedTask.getTitle());
            task.setContent(updatedTask.getContent());
            // 기타 필요한 정보 업데이트
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
    public List<Task> getTasksByProjectId(Long projectId) {
        // 특정 프로젝트에 속한 모든 Task 조회
        return taskRepository.findAllByProjectId(projectId);
    }

    @Override
    public Optional<Task> getTaskByProjectIdAndTaskId(Long projectId, Long taskId) {
        // 특정 프로젝트의 특정 Task 조회
        return taskRepository.findByIdAndProjectId(taskId, projectId);
    }
}
