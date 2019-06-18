package com.dao;

import com.domain.Account;
import com.domain.AccountUser;

import java.util.List;

public interface AccountDao {
    List<Account> findAll();
    List<AccountUser> findAllAccount();

}
