package com.nhnacademy.springboot.taskapiserver.domain.tag;

import com.nhnacademy.springboot.taskapiserver.domain.project.Project;

import java.util.List;

public interface TagService {

//    List<Tag> getTags();

    Tag getTag(Long id);

    Tag registerTag(Tag tag, Long id);
    void modifyTagName(Tag tag);
    void deleteTag(Long tagId, Long projectId);

    boolean isTagNameExistsInProject(Project project, String tagName);

    void assignTagToTask(Long projectId, Long taskId, Long tagId);
}
