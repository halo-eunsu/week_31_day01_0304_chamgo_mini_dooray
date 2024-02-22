package com.nhnacademy.springboot.taskapiserver.domain.task;

import com.nhnacademy.springboot.taskapiserver.domain.comment.Comment;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void registerTask(Task task) {
        taskRepository.save(task);
    }

    @Override
    public void modifyTaskComment(Task task) {
        taskRepository.findById(task.getTaskId()).ifPresent(
                targetTask -> {
                    targetTask.setComments(task.getComments());
                    taskRepository.save(targetTask);
                });
    }

    @Override
    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }

    @Override
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    @Override
    public List<Comment> getTaskComment(Long id) {
        return taskRepository.findById(id)
                .map(Task::getComments)
                .orElse(Collections.emptyList());
    }
}
