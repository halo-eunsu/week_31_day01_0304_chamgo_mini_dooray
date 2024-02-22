package com.nhnacademy.springboot.openapigateway.interceptor;

import com.nhnacademy.springboot.openapigateway.domain.Auth;
import com.nhnacademy.springboot.openapigateway.domain.Member;
import com.nhnacademy.springboot.openapigateway.service.MemberService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MemberAuthInterceptor implements HandlerInterceptor {

    private final MemberService memberService;

    public MemberAuthInterceptor(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        Member member = memberService.getMember((String) request.getSession().getAttribute("loginId"));
        if (Auth.ADMIN == member.getAuth()) {
            return true;
        }
        response.sendRedirect("/");
        return false;
    }
}
