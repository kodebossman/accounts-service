package com.bank.accountsservice.account.service;

import com.bank.accountsservice.account.dto.CreateAccountDto;
import com.bank.accountsservice.account.dto.ViewAccountDto;
import com.bank.accountsservice.model.Account;
import com.bank.accountsservice.model.Customer;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface AccountService {

  ViewAccountDto openAccount(CreateAccountDto createAccountDto);
  List<Account> checkAccount(Customer customer);
  ViewAccountDto findAccountById(Long id);
  ViewAccountDto findByAccountNumber(String accountNumber);
  List<ViewAccountDto> findAllAccountsByNationalId(String nationalId);
  List<ViewAccountDto> findAllAccounts();
}
