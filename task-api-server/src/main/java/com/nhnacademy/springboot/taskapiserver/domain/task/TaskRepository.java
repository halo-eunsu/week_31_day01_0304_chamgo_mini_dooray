package com.nhnacademy.springboot.taskapiserver.domain.task;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    //    List<Task> findByProjectId(Long projectId);
    List<Task> findByProject_ProjectId(Long projectId);

}
