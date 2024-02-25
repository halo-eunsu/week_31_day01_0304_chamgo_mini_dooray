package com.nhnacademy.springboot.openapigateway.controller;

import com.nhnacademy.springboot.openapigateway.domain.LoginRequest;
import com.nhnacademy.springboot.openapigateway.domain.LoginResponse;
import com.nhnacademy.springboot.openapigateway.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping
    public String login() {
        return "login";
    }

    @PostMapping
    public String login(@Valid LoginRequest loginRequest, HttpSession session, Model model) {
        Optional<LoginResponse> loginResponseOptional = loginService.login(loginRequest);

        if (!loginResponseOptional.isPresent()) {
            // Laravel 서비스에서 로그인 응답이 없을 경우 처리
            model.addAttribute("loginError", "Login failed. Please check your ID or password.");
            return "login";
        }

        LoginResponse loginResponse = loginResponseOptional.get();

        if (loginResponse.getId() != null) {
            session.setAttribute("loginId", loginResponse.getId());
            model.addAttribute("loginId", loginResponse.getId());
            return "redirect:/home";
        }

        // Laravel 서비스에서는 응답이 있지만 ID가 없는 경우 처리
        model.addAttribute("loginError", "Login failed. Please check your ID or password.");
        return "login";
    }
}
