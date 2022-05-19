package com.genspark.Accounts.Controller;

import com.genspark.Accounts.Entity.Account;
import com.genspark.Accounts.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/")
    public String home() {
        return "Hi there.";
    }

    @GetMapping("/accounts")
    public List<Account> getCourses()
    {
        return this.accountService.getAllAccount();
    }

    @GetMapping("/accounts/{accountID}")
    public Account getAccount(@PathVariable String accountID)
    {
        return this.accountService.getAccountById(Long.parseLong(accountID));
    }

    @PostMapping("/accounts")
    public Account addAccount(@RequestBody Account account)
    {
        return this.accountService.addAccount(account);
    }

    @PutMapping("/accounts/{accountID}")
    public Account updateAccount(@RequestBody Account account, @PathVariable Long accountID)
    {
        return this.accountService.updateAccount(account, accountID);
    }

    @DeleteMapping("/accounts/{accountID}")
    public String deleteAccount(@PathVariable String accountID)
    {
        return this.accountService.deleteAccountById(Long.parseLong(accountID));
    }

    @GetMapping("/accounts/auth")
    public Account getAccount(@RequestBody Account account)
    {
        return this.accountService.authenticate(account);
    }
}