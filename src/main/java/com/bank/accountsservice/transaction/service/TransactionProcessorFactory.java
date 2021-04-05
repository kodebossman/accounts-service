package com.bank.accountsservice.transaction.service;

import com.bank.accountsservice.common.TransactionType;
import com.bank.accountsservice.transaction.dto.CreateTransactionDto;
import com.bank.accountsservice.transaction.processors.DepositProcessor;
import com.bank.accountsservice.transaction.processors.SendMoneyProcessor;
import com.bank.accountsservice.transaction.processors.WithdrawalProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional(propagation = Propagation.REQUIRED)
public class TransactionProcessorFactory {

  public static TransactionStrategy getTransactionType ( int transactionTypeId) {

    if (transactionTypeId == TransactionType.SEND_MONEY.getTransactionTypeId()) {
      return new SendMoneyProcessor();
    } else if (transactionTypeId == TransactionType.WITHDRAW.getTransactionTypeId()) {
      return new WithdrawalProcessor();
    } else if (transactionTypeId == TransactionType.DEPOSIT.getTransactionTypeId()) {
      return new DepositProcessor();
  }
    else {
      throw new IllegalArgumentException("Unknown TransactionType with ID: " + transactionTypeId);
    }

  }
}
