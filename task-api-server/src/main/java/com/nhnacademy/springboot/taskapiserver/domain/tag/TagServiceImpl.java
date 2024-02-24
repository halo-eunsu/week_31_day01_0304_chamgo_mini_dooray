package com.nhnacademy.springboot.taskapiserver.domain.tag;

import com.nhnacademy.springboot.taskapiserver.domain.project.Project;
import com.nhnacademy.springboot.taskapiserver.domain.task.Task;
import com.nhnacademy.springboot.taskapiserver.domain.task.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final TaskRepository taskRepository;


    public TagServiceImpl(TagRepository tagRepository, TaskRepository taskRepository) {
        this.tagRepository = tagRepository;
        this.taskRepository = taskRepository;
    }

//    @Override
//    public List<Tag> getTags() {
//        return tagRepository.findAll();
//    }

    @Override
    public Tag getTag(Long id) {
        return tagRepository.findById(id)
                .orElse(null);
    }


    @Override
    public Tag registerTag(Tag tag, Long id) {
        if (tagRepository.existsByProjectAndTagName(tag.getProject(), tag.getTagName())) {
            throw new IllegalStateException("이미 존재하는 태그: " + tag.getTagName());
        }
        return tagRepository.save(tag);
    }

    @Override
    public void modifyTagName(Tag tag) {
        tagRepository.findById(tag.getTagId()).ifPresent(targetTage -> {
            targetTage.setTagName(tag.getTagName());
            tagRepository.save(targetTage);
        });
    }

    @Override
    public void deleteTag(Long tagId, Long projectId) {
        Tag tag = tagRepository.findByIdAndProjectId(tagId, projectId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는: " + tagId));
        tagRepository.delete(tag);
    }

    @Override
    public boolean isTagNameExistsInProject(Project project, String tagName) {
        return tagRepository.existsByProjectAndTagName(project, tagName);
    }

    @Override
    public void assignTagToTask(Long projectId, Long taskId, Long tagId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 태스크 ID: " + taskId));

        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 태그 ID: " + tagId));

        task.addTag(tag); // 태스크에 태그 추가
        taskRepository.save(task); // 변경사항 저장
    }
}
