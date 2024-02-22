package com.nhnacademy.springboot.openapigateway.controller;

import com.nhnacademy.springboot.openapigateway.domain.LoginRequest;
import com.nhnacademy.springboot.openapigateway.domain.LoginResponse;
import com.nhnacademy.springboot.openapigateway.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public String login(@RequestBody LoginRequest loginRequest, HttpSession session, Model model) {
        Optional<LoginResponse> loginResponse = loginService.login(loginRequest);

        if (loginResponse.isPresent() && loginResponse.get().getId() != null) {
            session.setAttribute("loginId", loginResponse.get().getId());
            model.addAttribute("loginId", loginResponse.get().getId());
            return "redirect:home";
        }
//        model.addAttribute("loginError", "Login failed.");
        return "login";
    }
}
