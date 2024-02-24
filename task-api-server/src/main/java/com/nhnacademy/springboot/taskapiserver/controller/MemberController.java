package com.nhnacademy.springboot.taskapiserver.controller;

import com.nhnacademy.springboot.taskapiserver.domain.member.Member;
import com.nhnacademy.springboot.taskapiserver.domain.member.MemberService;
import com.nhnacademy.springboot.taskapiserver.domain.project.Project;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 멤버 아이디로 멤버 보기
    @GetMapping("/{memberId}")
    public ResponseEntity<Member> getMember(@PathVariable String memberId) {
        Optional<Member> member = Optional.ofNullable(memberService.getMember(memberId));
        return member
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    // 내가 속한 프로젝트 목록 보기
    @GetMapping("/{memberId}/projects")
    public ResponseEntity<List<Project>> getProjects(@PathVariable String memberId) {
        try {

            List<Project> projects = memberService.getProjects(memberId);
            return new ResponseEntity<>(projects, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
