package com.banking.account.service;

import com.banking.account.DTO.CustomerAccountResponse;
import com.banking.account.model.Account;

import java.util.List;

public interface AccountService {
    Account createAccount(Account account,Long customerId);
    Account getAccountById(Long accountId);
    CustomerAccountResponse getAccountsByCustomerId(Long customerId);
    Account debitAmount(Long accountId, Double amount);
    Account creditAmount(Long accountId, Double amount);
    Double getBalance(Long accountId);
    List<Account> getAllAccounts();
    String deleteAccount(Long accountId);
}
