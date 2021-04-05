package com.bank.accountsservice.transaction.repository;

import com.bank.accountsservice.common.TransactionType;
import com.bank.accountsservice.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {

  List<Transaction> findByTransactionTypeAndCustomerId(TransactionType transactionType, Long customerId);
  List<Transaction> findByCustomerId(Long customerId);

}
