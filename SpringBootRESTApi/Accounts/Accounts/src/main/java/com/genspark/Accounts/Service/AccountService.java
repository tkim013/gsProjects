package com.genspark.Accounts.Service;

import com.genspark.Accounts.Entity.Account;

import java.util.List;

public interface AccountService {

    List<Account> getAllAccount();
    Account getAccountById(Long id);
    Account addAccount(Account account);
    Account updateAccount(Account account, Long accountID);
    String deleteAccountById(Long id);
    Account authenticate(Account account);
}
