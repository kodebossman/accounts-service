package com.bank.accountsservice.account.repository;

import com.bank.accountsservice.model.Account;
import com.bank.accountsservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> {

  Account findByAccountNumber(String accountId);
  Optional<Account> findById(Long Id);
  List<Account> findByCustomer(Customer customer);
}
