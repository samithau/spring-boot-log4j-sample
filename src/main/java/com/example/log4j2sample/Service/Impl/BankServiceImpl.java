package com.example.log4j2sample.Service.Impl;

import com.example.log4j2sample.Entity.AccountDetails;
import com.example.log4j2sample.Repo.AccountRepo;
import com.example.log4j2sample.Service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableJpaRepositories("com.example.log4j2sample.Repo")
public class BankServiceImpl implements BankService {

    @Autowired
    AccountRepo accountRepo;

    @Override
    public List<AccountDetails> getAllAccountDetails() {
        try {
            List<AccountDetails> district = accountRepo.findAll();
            return district;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<AccountDetails> getAllAccountDetailsbyAccountNumber(String accountNo) {
        try {
            List<AccountDetails> accountDetails = accountRepo.findByAccountNumber(accountNo);
            return accountDetails;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
