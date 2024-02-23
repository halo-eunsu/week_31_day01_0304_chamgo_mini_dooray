package com.nhnacademy.springboot.taskapiserver.domain.task;

import com.nhnacademy.springboot.taskapiserver.domain.comment.Comment;

import java.util.List;

public interface TaskService {


    //todo 01: Task 를 등록/수정/삭제 (Project Member only)
    Task registerTask(Task task);
    void modifyTaskComment(Task task);
    void deleteTask(Long taskId);


    //todo 02: Project 멤버는 Task 의 목록 및 내용을 확인 할 수 있습니다.
    List<Task> getTasks();
    List<Comment> getTaskComment(Long id);


    Task modifyTask(Long taskId, Task task);
}
