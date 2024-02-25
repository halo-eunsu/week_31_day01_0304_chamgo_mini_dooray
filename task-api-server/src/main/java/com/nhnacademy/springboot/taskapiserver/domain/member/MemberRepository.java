package com.nhnacademy.springboot.taskapiserver.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {

    boolean existsByMemberId(String memberId);
}
