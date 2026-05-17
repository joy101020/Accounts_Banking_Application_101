package com.banking.account.service;

import com.banking.account.DTO.CustomerAccountResponse;
import com.banking.account.client.CustomerClient;
import com.banking.account.exception.AccountDoesNotExistException;
import com.banking.account.model.Account;
import com.banking.account.model.Customer;
import com.banking.account.repository.AccountRepository;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class AccountServiceImps implements AccountService {

    private final CustomerClient customerClient;
    private final AccountRepository accountRepository;
    public AccountServiceImps(CustomerClient customerClient, AccountRepository accountRepository) {
        this.customerClient = customerClient;
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createAccount(Account account,Long customerId) {
        if (account == null ||  customerClient.getCustomer(customerId)==null) {
            throw new AccountDoesNotExistException("Please enter valid data!");
        }
        account.setCustomerId(customerId);
        return accountRepository.save(account);
    }

    @Override
    public Account getAccountById(Long accountId) {
        return  accountRepository.findById(accountId).orElseThrow(() -> new AccountDoesNotExistException("Account does not exist!"));
    }

    @Override
    public CustomerAccountResponse getAccountsByCustomerId(Long customerId) {
        if(customerClient.getCustomer(customerId) == null){
            throw new AccountDoesNotExistException("Customer does not exist!");
        }
        Customer customer = customerClient.getCustomer(customerId);
        List<Account> accounts = accountRepository.findByCustomerId(customerId) ;
        CustomerAccountResponse customerAccountResponse = new CustomerAccountResponse();
        customerAccountResponse.setCustomerId(customerId);
        customerAccountResponse.setFirstName(customer.getFirstName());
        customerAccountResponse.setLastName(customer.getLastName());
        customerAccountResponse.setEmail(customer.getEmail());
        customerAccountResponse.setPhoneNumber(customer.getPhoneNumber());
        customerAccountResponse.setAddress(customer.getAddress());
        customerAccountResponse.setGender(customer.getGender());
        customerAccountResponse.setDateOfBirth(customer.getDateOfBirth());
        customerAccountResponse.setCitizenId(customer.getCitizenId());
        customerAccountResponse.setNominee(customer.getNominee());
        customerAccountResponse.setAccounts(accounts);

        return customerAccountResponse;
    }

    @Override
    public Account debitAmount(Long accountId, Double amount) {
        Account account = getAccountById(accountId);
        if(account.getBalance() < amount){
            throw new RuntimeException("Insufficient balance!");
        }
        account.setBalance(account.getBalance() - amount);
        return accountRepository.save(account);
    }

    @Override
    public Account creditAmount(Long accountId, Double amount) {
        Account account = getAccountById(accountId);
        account.setBalance(account.getBalance() + amount);
        return accountRepository.save(account);
    }

    @Override
    public Double getBalance(Long accountId) {
        Account account = getAccountById(accountId);
        return account.getBalance();
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public String deleteAccount(Long accountId) {
        Account account = getAccountById(accountId);
        accountRepository.delete(account);
        return "Account has been deleted successfully!";
    }
}
