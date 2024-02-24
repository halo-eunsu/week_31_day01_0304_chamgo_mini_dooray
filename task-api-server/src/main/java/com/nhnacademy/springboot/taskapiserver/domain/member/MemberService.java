package com.nhnacademy.springboot.taskapiserver.domain.member;

import com.nhnacademy.springboot.taskapiserver.domain.project.Project;

import java.util.List;

public interface MemberService {

    List<Member> getMembers();

    Member getMember(String memberId);

    Member createMember(Member member);

    void delete(String memberId);
    List<Project> getProjects(String memberId);
}
