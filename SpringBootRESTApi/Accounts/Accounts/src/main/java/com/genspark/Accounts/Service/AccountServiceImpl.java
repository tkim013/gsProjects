package com.genspark.Accounts.Service;

import com.genspark.Accounts.Dao.AccountDao;
import com.genspark.Accounts.Entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Account> getAllAccount() {
        return this.accountDao.findAll();
    }

    @Override
    public Account getAccountById(Long id) {
        Optional<Account> a = this.accountDao.findById(id);
        Account account = null;
        if (a.isPresent())
        {
            account = a.get();
        } else {
            throw new RuntimeException("Account not found for id : " + id);
        }
        return account;
    }

    @Override
    public Account addAccount(Account account) {

        Account a = this.accountDao.findAccountByUsername(account.getUsername());
        if (a != null) {
            throw new DataIntegrityViolationException("username exists");
        }
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return this.accountDao.save(account);
    }

    @Override
    public Account updateAccount(Account account, Long accountID) {

        Account a;

        Optional<Account> o = accountDao.findById(accountID);

        if (o.isPresent()) {
            a = o.get();

            a.setPassword(passwordEncoder.encode(account.getPassword()));
            a.setFirstname(account.getFirstname());
            a.setLastname(account.getLastname());
            a.setPhone(account.getPhone());
            a.setEmail(account.getEmail());
            a.setDob(account.getDob());
            a.setAddress(account.getAddress());
            a.setCity(account.getCity());
            a.setState(account.getState());
            a.setZip(account.getZip());

        } else {
            a = account;
            a.setPassword(passwordEncoder.encode(a.getPassword()));
        }
        return this.accountDao.save(a);
    }

    @Override
    public String deleteAccountById(Long id) {
        this.accountDao.deleteById(id);

        return "Deleted Successfully";
    }

    @Override
    public Account authenticate(Account account) {
        Account a = null;

        Account q = this.accountDao.findAccountByUsername(account.getUsername());

        if (q != null) {
            if (BCrypt.checkpw(account.getPassword(), q.getPassword())) {
                a = q;
            }
        }
        return a;
    }
}
