package com.nhnacademy.springboot.taskapiserver.domain.project;

import com.nhnacademy.springboot.taskapiserver.domain.member.Member;
import com.nhnacademy.springboot.taskapiserver.domain.member.MemberRepository;
import com.nhnacademy.springboot.taskapiserver.dto.ProjectDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final MemberRepository memberRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository, MemberRepository memberRepository) {
        this.projectRepository = projectRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public Project createProject(Project project) {
        if (projectRepository.existsById(project.getProjectId())) {
            throw new IllegalArgumentException("이미 존재: " + project.getProjectId());
        }
        return projectRepository.save(project);
    }


    @Override
    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElse(null);
    }

    @Override
    public void modifyProject(ProjectDto projectDto) {
        Project project = projectRepository.findById(projectDto.getProjectId())
                .orElse(null);
        project.setProjectName(projectDto.getProjectName());
        project.setContent(projectDto.getContent());
        project.setStatus(Project.StatusType.valueOf(projectDto.getStatus()));
        projectRepository.save(project);
    }

    @Override
    public void deleteProject(Long projectId) {
        projectRepository.deleteById(projectId);
    }

    @Override
    @Transactional
    public void addProjectMember(Long projectId, Member member) {
        Project project = projectRepository.findById(projectId)
                .orElse(null);

        project.addMember(member); // addMember 메서드는 Project 엔티티 내부에 구현되어야 함
        projectRepository.save(project);
    }

    @Override
    @Transactional
    public void deleteProjectMember(Long projectId, String memberId) {
        Project project = projectRepository.findById(projectId)
                .orElse(null);
        Member member = memberRepository.findById(memberId)
                .orElse(null);

        project.removeMember(member); // removeMember 메서드는 Project 엔티티 내부에 구현되어야 함
        projectRepository.save(project);
    }

}
