package com.nhnacademy.springboot.taskapiserver.domain.member;

import com.nhnacademy.springboot.taskapiserver.domain.project.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

class MemberServiceImplTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberServiceImpl memberService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getMembers() {

        when(memberRepository.findAll()).thenReturn(Arrays.asList(new Member(), new Member()));

        // when
        List<Member> members = memberService.getMembers();

        // then
        assertNotNull(members);
        assertEquals(2, members.size());
    }

    @Test
    void getMember() {
        // given
        String memberId = "member1";
        Member mockMember = new Member();
        when(memberRepository.findById(memberId)).thenReturn(Optional.of(mockMember));

        // when
        Member member = memberService.getMember(memberId);

        // then
        assertNotNull(member);
        assertEquals(mockMember, member);
    }

    @Test
    void createMember() {
        // given
        Member newMember = new Member();
        newMember.setMemberId("member2");
        when(memberRepository.existsByMemberId(anyString())).thenReturn(false);
        when(memberRepository.save(any(Member.class))).thenReturn(newMember);

        // when
        Member savedMember = memberService.createMember(newMember);

        // then
        assertNotNull(savedMember);
        assertEquals("member2", savedMember.getMemberId());
    }

    @Test
    void delete() {
        // given
        String memberId = "member3";
        Member existingMember = new Member();
        existingMember.setMemberId(memberId);
        when(memberRepository.findById(memberId)).thenReturn(Optional.of(existingMember));

        // when
        memberService.delete(memberId);

        // then
        verify(memberRepository).delete(existingMember);
    }

    @Test
    void getProjects() {
        // given
        String memberId = "member4";
        Member memberWithProjects = new Member();
        Set<Project> mockProjects = new HashSet<>(Arrays.asList(new Project(), new Project()));
        when(memberRepository.findById(memberId)).thenReturn(Optional.of(memberWithProjects));
        when(memberWithProjects.getProjects()).thenReturn(mockProjects);

        // when
        List<Project> projects = new ArrayList<>(memberService.getProjects(memberId));

        // then
        assertNotNull(projects);
        assertEquals(2, projects.size());
    }

}
