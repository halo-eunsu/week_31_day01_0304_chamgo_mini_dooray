package com.nhnacademy.springboot.taskapiserver.domain.tag;

import com.nhnacademy.springboot.taskapiserver.domain.project.Project;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public List<Tag> getTags() {
        return tagRepository.findAll();
    }

    @Override
    public Tag getTag(Long id) {
        return tagRepository.findById(id)
                .orElse(null);
    }


    @Override
    public void registerTag(Tag tag) {
        if (tagRepository.existsByProjectAndTagName(tag.getProject(), tag.getTagName())) {
            throw new IllegalStateException("Already exist tagName: " + tag.getTagName());
        }
        tagRepository.save(tag);
    }

    @Override
    public void modifyTagName(Tag tag) {
        tagRepository.findById(tag.getTagId()).ifPresent(targetTage -> {
            targetTage.setTagName(tag.getTagName());
            tagRepository.save(targetTage);
        });
    }

    @Override
    public void deleteTag(Tag tag) {
        tagRepository.delete(tag);
    }

    @Override
    public boolean isTagNameExistsInProject(Project project, String tagName) {
        return tagRepository.existsByProjectAndTagName(project, tagName);
    }
}
