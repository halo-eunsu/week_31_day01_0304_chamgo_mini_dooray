package com.nhnacademy.mini.dooray.controller;

import com.nhnacademy.mini.dooray.domain.Account;
import com.nhnacademy.mini.dooray.domain.AccountRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.implementation.bytecode.ShiftRight;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final AccountRepository accountRepository;

    @PostMapping("/api/login")
    public ResponseEntity<?> doLogin(@RequestBody LoginRequest loginRequest) {
        Optional<Account> accountOptional = accountRepository.findById(loginRequest.getId());
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();

            if (account.getPassword() != null && account.getPassword().equals(loginRequest.getPassword())) {
                HashMap<String, String> resp = new HashMap<>();
                resp.put("id", loginRequest.getId());
                resp.put("email", account.getEmail());
                resp.put("name", account.getName());
                return ResponseEntity.ok(resp);
            }

        }
        HashMap<String, String> errorResp = new HashMap<>();
        errorResp.put("error", "Invalid ID or PASSWORD");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResp);
    }

    @Getter
    static class LoginRequest {
        private String id;
        private String password;

        public LoginRequest(String validId, String validPassword) {
        }
    }

    @Getter
    static class LoginReponse {
        private String id;
        private String email;
        private String password;
    }
}
