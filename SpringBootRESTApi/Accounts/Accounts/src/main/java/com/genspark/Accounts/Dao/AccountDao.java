package com.genspark.Accounts.Dao;

import com.genspark.Accounts.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDao extends JpaRepository<Account, Long> {

    @Query("SELECT a FROM Account a WHERE a.username = ?1")
    Account findAccountByUsername(String username);
}
