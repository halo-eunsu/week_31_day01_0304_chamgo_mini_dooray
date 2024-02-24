package com.nhnacademy.springboot.taskapiserver.domain.tag;

import com.nhnacademy.springboot.taskapiserver.domain.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {

    boolean existsByProjectAndTagName(Project project, String tagName);

    @Query("SELECT t FROM Tag t WHERE t.tagId = :tagId AND t.project.projectId = :projectId")
    Optional<Tag> findByIdAndProjectId(@Param("tagId") Long tagId, @Param("projectId") Long projectId);
}
