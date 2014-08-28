package com.example.service;


import com.example.model.Account;

public interface AccountService {
    
    public void addAccount(Account account);
    public Account findAccountByName(String accountName);
    public String findAccountName(String accountId);
}
