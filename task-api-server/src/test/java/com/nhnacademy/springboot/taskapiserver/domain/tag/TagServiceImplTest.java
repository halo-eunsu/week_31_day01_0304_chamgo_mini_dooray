package com.nhnacademy.springboot.taskapiserver.domain.tag;

import com.nhnacademy.springboot.taskapiserver.domain.project.Project;
import com.nhnacademy.springboot.taskapiserver.domain.task.Task;
import com.nhnacademy.springboot.taskapiserver.domain.task.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class TagServiceImplTest {

    @Mock
    private TagRepository tagRepository;

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TagServiceImpl tagService;

    private Tag tag;
    private Project project;

    @BeforeEach
    void setUp() {
        project = new Project();
        project.setProjectId(1L);

        tag = new Tag();
        tag.setTagId(1L);
        tag.setTagName("Tag");
        tag.setProject(project);
    }

    @Test
    void getTag() {
        when(tagRepository.findById(any(Long.class))).thenReturn(Optional.of(tag));

        Tag found = tagService.getTag(1L);

        assertNotNull(found);
        assertEquals("Tag", found.getTagName());
    }

    @Test
    void registerTag() {
        when(tagRepository.existsByProjectAndTagName(any(Project.class), any(String.class))).thenReturn(false);
        when(tagRepository.save(any(Tag.class))).thenReturn(tag);

        Tag savedTag = tagService.registerTag(tag, project.getProjectId());

        assertNotNull(savedTag);
        assertEquals("Tag", savedTag.getTagName());
    }


    @Test
    void modifyTagName() {
        Tag existingTag = new Tag();
        existingTag.setTagId(1L);
        existingTag.setTagName("Tag");
        existingTag.setProject(project);

        Tag updatedTag = new Tag();
        updatedTag.setTagId(1L);
        updatedTag.setTagName("Critical Tag");
        updatedTag.setProject(project);

        when(tagRepository.findById(any(Long.class))).thenReturn(Optional.of(existingTag));
        when(tagRepository.save(any(Tag.class))).thenReturn(updatedTag); // 수정된 객체를 반환하도록 스텁 설정

        tagService.modifyTagName(updatedTag);

        verify(tagRepository).save(any(Tag.class));
    }

    @Test
    void deleteTag() {
        when(tagRepository.findByIdAndProjectId(eq(tag.getTagId()), eq(project.getProjectId()))).thenReturn(Optional.of(tag));

        tagService.deleteTag(tag.getTagId(), project.getProjectId());

        verify(tagRepository).delete(tag);
    }

    @Test
    void isTagNameExistsInProject() {
        when(tagRepository.existsByProjectAndTagName(any(Project.class), any(String.class))).thenReturn(true);

        boolean exists = tagService.isTagNameExistsInProject(project, "Tag");

        assertTrue(exists);
    }

    @Test
    void assignTagToTask() {
        Task task = new Task();
        task.setTaskId(1L);

        when(taskRepository.findById(any(Long.class))).thenReturn(Optional.of(task));
        when(tagRepository.findById(any(Long.class))).thenReturn(Optional.of(tag));

        tagService.assignTagToTask(project.getProjectId(), task.getTaskId(), tag.getTagId());

        assertTrue(task.getTags().contains(tag));
    }
}
