package com.nhnacademy.springboot.taskapiserver.domain.project;

import com.nhnacademy.springboot.taskapiserver.domain.member.Member;
import com.nhnacademy.springboot.taskapiserver.dto.ProjectDto;

public interface ProjectService {

    Project createProject(Project project);

    Project getProjectById(Long id);

    void modifyProject(ProjectDto projectDto); // 프로젝트 수정 기능 추가
    void deleteProject(Long projectId); // 프로젝트 삭제 기능 추가
    void addProjectMember(Long projectId, Member member); // 프로젝트에 멤버 추가 기능 추가
    void deleteProjectMember(Long projectId, Long memberId); // 프로젝트에서 멤버 삭제 기능 추가
}