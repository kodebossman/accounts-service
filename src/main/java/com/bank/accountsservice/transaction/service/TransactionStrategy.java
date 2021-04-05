package com.bank.accountsservice.transaction.service;

import com.bank.accountsservice.transaction.dto.CreateTransactionDto;
import com.bank.accountsservice.transaction.dto.ViewTransactionDto;

public interface TransactionStrategy {

  ViewTransactionDto createTransaction(CreateTransactionDto transactionDto);
}
