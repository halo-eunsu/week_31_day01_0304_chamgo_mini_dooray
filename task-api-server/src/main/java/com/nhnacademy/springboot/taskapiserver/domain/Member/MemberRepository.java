package com.nhnacademy.springboot.taskapiserver.domain.Member;

import com.nhnacademy.springboot.taskapiserver.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByMemberId(String memberId);
}
