package com.nhnacademy.springboot.taskapiserver.domain.taskTags;

import com.nhnacademy.springboot.taskapiserver.domain.tag.TagRepository;
import com.nhnacademy.springboot.taskapiserver.domain.task.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskTagsServiceImpl implements TaskTagsService {

    private final TaskTagsRepository taskTagsRepository;
    private final TaskRepository taskRepository;
    private final TagRepository tagRepository;

    @Autowired
    public TaskTagsServiceImpl(TaskTagsRepository taskTagsRepository,
                               TaskRepository taskRepository,
                               TagRepository tagRepository) {
        this.taskTagsRepository = taskTagsRepository;
        this.taskRepository = taskRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public TaskTags assignTagToTask(Long taskId, Long tagId) {
        return taskRepository.findById(taskId).flatMap(task ->
                tagRepository.findById(tagId).map(tag -> {
                    TaskTags taskTags = new TaskTags();
                    taskTags.setTask(task);
                    taskTags.setTag(tag);
                    return taskTagsRepository.save(taskTags);
                })
        ).orElseThrow(() -> new RuntimeException("Task or Tag 없음"));
    }

    @Override
    public void unAssignTagFromTask(Long taskTagId) {
        taskTagsRepository.deleteById(taskTagId);
    }
}