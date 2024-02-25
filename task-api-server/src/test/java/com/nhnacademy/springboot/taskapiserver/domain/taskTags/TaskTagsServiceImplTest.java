package com.nhnacademy.springboot.taskapiserver.domain.taskTags;

import com.nhnacademy.springboot.taskapiserver.domain.tag.Tag;
import com.nhnacademy.springboot.taskapiserver.domain.tag.TagRepository;
import com.nhnacademy.springboot.taskapiserver.domain.task.Task;
import com.nhnacademy.springboot.taskapiserver.domain.task.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskTagsServiceImplTest {

    @Mock
    private TaskTagsRepository taskTagsRepository;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TagRepository tagRepository;

    @InjectMocks
    private TaskTagsServiceImpl taskTagsService;

    private Task task;
    private Tag tag;
    private TaskTags taskTags;

    @BeforeEach
    void setUp() {
        task = new Task();
        task.setTaskId(1L);

        tag = new Tag();
        tag.setTagId(1L);

        taskTags = new TaskTags();
        taskTags.setTask(task);
        taskTags.setTag(tag);
    }

    @Test
    void assignTagToTask() {
        when(taskRepository.findById(anyLong())).thenReturn(Optional.of(task));
        when(tagRepository.findById(anyLong())).thenReturn(Optional.of(tag));
        when(taskTagsRepository.save(any(TaskTags.class))).thenReturn(taskTags);

        TaskTags result = taskTagsService.assignTagToTask(1L, 1L);

        assertNotNull(result);
        assertEquals(task, result.getTask());
        assertEquals(tag, result.getTag());
        verify(taskTagsRepository).save(any(TaskTags.class));
    }

    @Test
    void unAssignTagFromTask() {
        doNothing().when(taskTagsRepository).deleteById(anyLong());
        taskTagsService.unAssignTagFromTask(1L);
        verify(taskTagsRepository).deleteById(anyLong());
    }
}
