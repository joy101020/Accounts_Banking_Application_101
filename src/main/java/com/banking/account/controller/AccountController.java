package com.banking.account.controller;
import com.banking.account.DTO.AmountRequest;
import com.banking.account.DTO.CustomerAccountResponse;
import com.banking.account.model.Account;
import com.banking.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/all")
    public ResponseEntity<List<Account>> getAllAccounts(){
        return new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.OK);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long accountId){
        return new ResponseEntity<>(accountService.getAccountById(accountId), HttpStatus.OK);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<CustomerAccountResponse> getAccountByCustomerId(@PathVariable Long customerId){
        return new ResponseEntity<>(accountService.getAccountsByCustomerId(customerId), HttpStatus.OK);
    }

    @PostMapping("/add/{customerId}")
    public ResponseEntity<Account> createAccount(@RequestBody Account account, @PathVariable Long customerId){
        return new ResponseEntity<>(accountService.createAccount(account, customerId), HttpStatus.CREATED);
    }

    @PutMapping("/debit/{accountId}")
    public ResponseEntity<Account> debitAmount(@PathVariable Long accountId, @RequestBody AmountRequest amount){
        return new ResponseEntity<>(accountService.debitAmount(accountId,amount.getBalance()), HttpStatus.OK);
    }

    @PutMapping("/credit/{accountId}")
    public ResponseEntity<Account> creditAmount(@PathVariable Long accountId, @RequestBody AmountRequest amount){
        return new ResponseEntity<>(accountService.creditAmount(accountId,amount.getBalance()), HttpStatus.OK);
    }

    @GetMapping("/balance/{accountId}")
    public ResponseEntity<Double> getBalance(@PathVariable Long accountId){
        return new ResponseEntity<>(accountService.getBalance(accountId), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{accountId}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long accountId){
        return new ResponseEntity<>(accountService.deleteAccount(accountId),HttpStatus.OK);
    }
}
