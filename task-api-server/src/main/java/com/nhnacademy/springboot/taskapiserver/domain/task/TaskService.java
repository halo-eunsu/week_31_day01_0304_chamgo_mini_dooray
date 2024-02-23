package com.nhnacademy.springboot.taskapiserver.domain.task;

import com.nhnacademy.springboot.taskapiserver.domain.comment.Comment;
import java.util.Optional;
import java.util.List;

public interface TaskService {


    //todo 01: Task 를 등록/수정/삭제 (Project Member only)
    Task registerTask(Long projectId, Task task);
    Task modifyTask(Long projectId, Long taskId, Task updatedTask);
    void deleteTask(Long projectId, Long taskId);
    void modifyTaskComment(Task task);


    //todo 02: Project 멤버는 Task 의 목록 및 내용을 확인 할 수 있습니다.
    List<Task> getTasks();
    List<Comment> getTaskComment(Long id);

    //todo 03 : getter setter
    List<Task> getTasksByProjectId(Long projectId);


    Optional<Task> getTaskByProjectIdAndTaskId(Long projectId, Long taskId);
}
