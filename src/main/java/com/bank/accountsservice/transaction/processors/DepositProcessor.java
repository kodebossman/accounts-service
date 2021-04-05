package com.bank.accountsservice.transaction.processors;

import com.bank.accountsservice.account.repository.AccountRepository;
import com.bank.accountsservice.account.service.AccountService;
import com.bank.accountsservice.exceptions.BadRequestDataException;
import com.bank.accountsservice.model.Account;
import com.bank.accountsservice.model.Transaction;
import com.bank.accountsservice.transaction.dto.CreateTransactionDto;
import com.bank.accountsservice.transaction.dto.ViewTransactionDto;
import com.bank.accountsservice.transaction.repository.TransactionRepository;
import com.bank.accountsservice.transaction.service.TransactionServiceImpl;
import com.bank.accountsservice.transaction.service.TransactionStrategy;
import com.bank.accountsservice.util.CheckBankDetailsUtil;


import java.util.Objects;

public class DepositProcessor implements TransactionStrategy {

  private static TransactionRepository transactionRepository;
  private static AccountRepository accountRepository;


  public DepositProcessor(){

  }
   public DepositProcessor(TransactionRepository transactionRepository, AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
    this.transactionRepository = transactionRepository;
  }

  @Override
  public ViewTransactionDto createTransaction(CreateTransactionDto transactionDto) {
    Account account = CheckBankDetailsUtil.checkAccountInfo(transactionDto.getAccountNumber());
    Transaction transaction = null;
    if (!Objects.isNull(account)) {
      transaction = CheckBankDetailsUtil.getTransaction(transactionDto, account);
      transaction= CheckBankDetailsUtil.saveTransaction(transaction);

    } else {
      throw new BadRequestDataException("The Account provided is not valid:" + transactionDto.getAccountNumber() + " we can not complete the transaction");
    }
    //update/credit deposit account
    account.setAccountBalance(transactionDto.getAmount()+ account.getAccountBalance());
    CheckBankDetailsUtil.updateAccount(account);
    return CheckBankDetailsUtil.getViewTransactionDto(transaction, account);

  }

}