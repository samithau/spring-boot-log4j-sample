package com.example.log4j2sample.Service;

import com.example.log4j2sample.Entity.AccountDetails;

import java.util.List;

public interface BankService {

    List<AccountDetails> getAllAccountDetails();
    public List<AccountDetails> getAllAccountDetailsbyAccountNumber(String  accountNo);
}
