package com.nhnacademy.springboot.taskapiserver.domain.tag;

import com.nhnacademy.springboot.taskapiserver.domain.project.Project;

import java.util.List;

public interface TagService {

    List<Tag> getTags();

    Tag getTag(Long id);

    void registerTag(Tag tag);
    void modifyTagName(Tag tag);
    void deleteTag(Tag tag);

    boolean isTagNameExistsInProject(Project project, String tagName);
}
