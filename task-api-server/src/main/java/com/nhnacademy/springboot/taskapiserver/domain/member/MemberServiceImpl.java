package com.nhnacademy.springboot.taskapiserver.domain.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nhnacademy.springboot.taskapiserver.domain.project.Project;
import com.nhnacademy.springboot.taskapiserver.domain.project.ProjectRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository, ProjectRepository projectRepository) {
        this.memberRepository = memberRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Member> getMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Member getMember(Long id) {
        Optional<Member> memberOptional = memberRepository.findById(id);
        if (memberOptional.isPresent()) {
            return memberOptional.get();
        }
        return null;
    }

    @Override
    public Member createMember(Member member) {
        if (memberRepository.existsByMemberId(member.getMemberId())) {
            throw new IllegalStateException("Already exist ID: " + member.getMemberId());
        }
        return memberRepository.save(member);
    }

    @Override
    public void delete(Member member) {
        memberRepository.delete(member);
    }

    @Override
    public void addMemberToProject(Long projectId, Long memberId) {
        Optional<Project> projectOptional = projectRepository.findById(projectId);
        if (!projectOptional.isPresent()) {
            throw new IllegalArgumentException("Project not found with ID: " + projectId);
        }
        Project project = projectOptional.get();

        Optional<Member> memberOptional = memberRepository.findById(memberId);
        if (!memberOptional.isPresent()) {
            throw new IllegalArgumentException("Member not found with ID: " + memberId);
        }
        Member member = memberOptional.get();

        member.setProject(project);
        memberRepository.save(member);
    }

    @Override
    public void removeMemberFromProject(Long projectId, Long memberId) {
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        if (!memberOptional.isPresent()) {
            throw new IllegalArgumentException("Member not found with ID: " + memberId);
        }
        Member member = memberOptional.get();

        if (member.getProject() != null && member.getProject().getProjectId().equals(projectId)) {
            member.setProject(null);
            memberRepository.save(member);
        }
    }
}
