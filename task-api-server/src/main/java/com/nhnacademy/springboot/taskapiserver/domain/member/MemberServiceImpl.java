package com.nhnacademy.springboot.taskapiserver.domain.member;

import com.nhnacademy.springboot.taskapiserver.domain.project.Project;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public List<Member> getMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Member getMember(String memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found: " + memberId));
    }

    @Override
    public Member createMember(Member member) {
        if (memberRepository.existsByMemberId(member.getMemberId())) {
            throw new IllegalStateException("Already exist ID: " + member.getMemberId());
        }
        return memberRepository.save(member);
    }

    @Override
    public void delete(String memberId) {
        Member member = this.getMember(memberId);
        memberRepository.delete(member);
    }

    @Override
    public List<Project> getProjects(String memberId) {
        Member member = this.getMember(memberId);
        return new ArrayList<>(member.getProjects());
    }
}
