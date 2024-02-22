package com.nhnacademy.springboot.taskapiserver.domain.member;

import org.springframework.stereotype.Service;

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
