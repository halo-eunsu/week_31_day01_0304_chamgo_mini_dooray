package com.nhnacademy.springboot.taskapiserver.domain.member;

import java.util.List;

public interface MemberService {

    List<Member> getMembers();

    Member getMember(Long id);

    Member createMember(Member member);

    void delete(Member member);
}
