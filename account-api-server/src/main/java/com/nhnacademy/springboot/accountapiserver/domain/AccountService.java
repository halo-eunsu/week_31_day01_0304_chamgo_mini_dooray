package com.nhnacademy.springboot.accountapiserver.domain;

import java.util.List;

public interface AccountService {
    List<Account> getAccounts();

    Account getAccount(String id);

    Account createAccount(Account account);

    void deleteAccount(String id);
}
