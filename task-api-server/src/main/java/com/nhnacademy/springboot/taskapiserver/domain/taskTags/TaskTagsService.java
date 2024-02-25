package com.nhnacademy.springboot.taskapiserver.domain.taskTags;

public interface TaskTagsService {
    TaskTags assignTagToTask(Long taskId, Long tagId);
    void unAssignTagFromTask(Long taskTagId);
}