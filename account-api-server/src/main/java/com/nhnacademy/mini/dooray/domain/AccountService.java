package com.nhnacademy.mini.dooray.domain;

import java.util.List;

public interface AccountService {
    List<Account> getAccounts();

    Account getAccount(String id);

    Account createAccount(Account account);

    void deleteAccount(String id);
}