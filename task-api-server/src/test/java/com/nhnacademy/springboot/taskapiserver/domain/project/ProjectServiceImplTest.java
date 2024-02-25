package com.nhnacademy.springboot.taskapiserver.domain.project;

import com.nhnacademy.springboot.taskapiserver.domain.member.Member;
import com.nhnacademy.springboot.taskapiserver.domain.member.MemberRepository;
import com.nhnacademy.springboot.taskapiserver.dto.ProjectDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProjectServiceImplTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private ProjectServiceImpl projectService;

    private Project project;
    private ProjectDto projectDto;
    private Member member;

    @BeforeEach
    void setUp() {
        project = new Project();
        project.setProjectId(1L);
        project.setProjectName("Test Project");
        project.setContent("테스트");
        project.setStatus(Project.StatusType.ACTIVE);

        projectDto = new ProjectDto();
        projectDto.setProjectId(1L);
        projectDto.setProjectName("Update test");
        projectDto.setContent("Updated content");
        projectDto.setStatus("ACTIVE");

        member = new Member();
        member.setMemberId("member1");
    }

    @Test
    void createProject() {
        when(projectRepository.save(any(Project.class))).thenReturn(project);
        Project createdProject = projectService.createProject(project);
        assertNotNull(createdProject);
        assertEquals("Test Project", createdProject.getProjectName());
    }

    @Test
    void getProjectById() {
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        Project foundProject = projectService.getProjectById(1L);
        assertNotNull(foundProject);
        assertEquals("Test Project", foundProject.getProjectName());
    }

    @Test
    void modifyProject() {
        when(projectRepository.findById(anyLong())).thenReturn(Optional.of(project));
        projectService.modifyProject(projectDto);
        verify(projectRepository).save(project);
    }

    @Test
    void deleteProject() {
        doNothing().when(projectRepository).deleteById(anyLong());
        projectService.deleteProject(1L);
        verify(projectRepository).deleteById(1L);
    }

    @Test
    void addProjectMember() {
        when(projectRepository.findById(anyLong())).thenReturn(Optional.of(project));
        when(memberRepository.findById(anyString())).thenReturn(Optional.of(member));
        projectService.addProjectMember(1L, member);
        assertTrue(project.getMembers().contains(member));
    }

    @Test
    void deleteProjectMember() {
        project.addMember(member);
        when(projectRepository.findById(anyLong())).thenReturn(Optional.of(project));
        when(memberRepository.findById(anyString())).thenReturn(Optional.of(member));
        projectService.deleteProjectMember(1L, "member1");
        assertFalse(project.getMembers().contains(member));
    }
}
