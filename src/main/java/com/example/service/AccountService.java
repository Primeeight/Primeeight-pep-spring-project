package com.example.service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Account;
import com.example.repository.AccountRepository;


@Transactional
@Service
public class AccountService {
    //Create the respective repository.
    AccountRepository accountRepository;
    //Constructor
    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }
    public AccountService() {
    }
    public Optional<Account> loginAccount(Account account) {
        Optional<Account> target = accountRepository.findByUsername(account.getUsername());
        if (!target.isEmpty()) {
            if (target.get().getPassword().equals(account.getPassword())) {
                return target;
                }
                
            }
            return Optional.empty();
    }
    public Optional<Account> postAccount(Account account) throws Exception {
        Optional<Account> existing = accountRepository.findByUsername(account.getUsername());
        if (account.getUsername()!= "" && account.getPassword().length() > 3) {
                if(existing.isEmpty()){
                    return Optional.of(accountRepository.save(account));
                }
                throw new Exception("Duplicate Username");
            }
            return Optional.empty();
    }
    public Optional<Account> findById(Integer postedBy) {
        return accountRepository.findById(postedBy);
    }
}
