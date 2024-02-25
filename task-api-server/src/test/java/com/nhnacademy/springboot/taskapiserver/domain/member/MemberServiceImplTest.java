package com.nhnacademy.springboot.taskapiserver.domain.member;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.never;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.nhnacademy.springboot.taskapiserver.domain.AuthType;
import com.nhnacademy.springboot.taskapiserver.domain.project.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class MemberServiceImplTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberServiceImpl memberService;

    private Member member;

    @BeforeEach
    void setUp() {
        member = new Member();
        member.setMemberId("testMember");
        member.setAuth(AuthType.MEMBER);
        member.setProjects(new HashSet<>(Arrays.asList(new Project(1L))));
    }

    @Test
    void getMembers() {
        when(memberRepository.findAll()).thenReturn(Arrays.asList(member));
        List<Member> members = memberService.getMembers();
        assertEquals(1, members.size());
        verify(memberRepository).findAll();
    }

    @Test
    void getMember() {
        when(memberRepository.findById(any())).thenReturn(Optional.of(member));
        Member result = memberService.getMember("testMember");
        assertEquals("testMember", result.getMemberId());
        verify(memberRepository).findById(any());
    }

    @Test
    void createMember() {
        when(memberRepository.existsByMemberId(any())).thenReturn(false);
        when(memberRepository.save(any(Member.class))).thenReturn(member);
        Member createdMember = memberService.createMember(member);
        assertEquals("testMember", createdMember.getMemberId());
        verify(memberRepository).save(any(Member.class));
    }

    @Test
    void delete() {
        when(memberRepository.findById(any())).thenReturn(Optional.of(member));
        memberService.delete("testMember");
        verify(memberRepository).delete(any(Member.class));
    }

    @Test
    void getProjects() {
        when(memberRepository.findById(any())).thenReturn(Optional.of(member));
        List<Project> projects = memberService.getProjects("testMember");
        assertEquals(1, projects.size());
        verify(memberRepository).findById(any());
    }

    @Test
    void MemberExceptions() {
        when(memberRepository.existsByMemberId(any())).thenReturn(true);
        assertThrows(IllegalStateException.class, () -> memberService.createMember(member));
        verify(memberRepository, never()).save(any(Member.class));
    }
}
