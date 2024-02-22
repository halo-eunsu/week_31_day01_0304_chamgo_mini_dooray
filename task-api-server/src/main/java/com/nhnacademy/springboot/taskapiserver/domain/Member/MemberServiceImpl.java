package com.nhnacademy.springboot.taskapiserver.domain.Member;

import com.nhnacademy.springboot.taskapiserver.entity.Member;

import java.util.List;

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
    public Member getMember(Long id) {
        return memberRepository.findById(id)
                .orElse(null);
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
}
