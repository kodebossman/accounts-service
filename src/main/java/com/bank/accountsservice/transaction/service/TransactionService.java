package com.bank.accountsservice.transaction.service;

import com.bank.accountsservice.common.TransactionType;
import com.bank.accountsservice.transaction.dto.ViewTransactionDto;

import java.util.List;

public interface TransactionService {

  List<ViewTransactionDto> getTransactionHistoryByType(TransactionType transactionType, Long customerId);
  List<ViewTransactionDto> getAllTransactions(Long customerId );

}
