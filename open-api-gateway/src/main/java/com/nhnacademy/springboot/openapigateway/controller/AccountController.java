package com.nhnacademy.springboot.openapigateway.controller;

import com.nhnacademy.springboot.openapigateway.domain.Account;
import com.nhnacademy.springboot.openapigateway.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public String getAccounts(Model model) {
        model.addAttribute("accounts", accountService.getAccounts());
        return "accounts";
    }

    @GetMapping("/{id}")
    public String getAccount(@PathVariable Long id) {
        accountService.getAccount(id);
        return "accounts/" + id;
    }

    @PostMapping
    public String createAccount(@RequestBody Account account) {
        accountService.createAccount(account);
        return "OK";
    }

    @DeleteMapping("/{id}")
    public String deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return "OK";
    }
}
