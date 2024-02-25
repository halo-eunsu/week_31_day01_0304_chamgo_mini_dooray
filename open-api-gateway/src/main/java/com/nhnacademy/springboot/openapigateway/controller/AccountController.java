package com.nhnacademy.springboot.openapigateway.controller;

import com.nhnacademy.springboot.openapigateway.domain.Account;
import com.nhnacademy.springboot.openapigateway.domain.AccountEditDto;
import com.nhnacademy.springboot.openapigateway.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public String getAccount(@PathVariable String id, Model model) {
        model.addAttribute("account", accountService.getAccount(id));
        return "accountDetail";
    }

    @PostMapping
    public String createAccount(@Valid Account account) {
        accountService.createAccount(account);
        return "redirect:/";
    }

    @PutMapping("/{id}")
    public String editAccount(@PathVariable String id, @Valid AccountEditDto accountEditDto) {
        accountService.editAccount(id, accountEditDto);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String deleteAccount(@PathVariable String id) {
        accountService.deleteAccount(id);
        return "redirect:/";
    }
}
