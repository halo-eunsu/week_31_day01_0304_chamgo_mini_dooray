package com.nhnacademy.springboot.taskapiserver.domain.Project;

import com.nhnacademy.springboot.taskapiserver.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
