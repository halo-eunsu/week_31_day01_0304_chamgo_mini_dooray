package com.nhnacademy.springboot.taskapiserver.domain.Member;

import com.nhnacademy.springboot.taskapiserver.entity.Member;

import java.util.List;

public interface MemberService {

    List<Member> getMembers();

    Member getMember(Long id);

    Member createMember(Member member);

    void delete(Member member);
}
