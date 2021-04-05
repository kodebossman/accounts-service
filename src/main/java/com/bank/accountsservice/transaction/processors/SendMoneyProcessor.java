package com.bank.accountsservice.transaction.processors;

import com.bank.accountsservice.account.repository.AccountRepository;
import com.bank.accountsservice.model.Account;
import com.bank.accountsservice.model.Transaction;
import com.bank.accountsservice.transaction.dto.CreateTransactionDto;
import com.bank.accountsservice.transaction.dto.ViewSendMoneyDto;
import com.bank.accountsservice.transaction.dto.ViewTransactionDto;

import com.bank.accountsservice.transaction.repository.TransactionRepository;
import com.bank.accountsservice.transaction.service.TransactionStrategy;
import com.bank.accountsservice.util.CheckBankDetailsUtil;

public class SendMoneyProcessor implements TransactionStrategy {

 private   AccountRepository accountRepository;
 private  TransactionRepository transactionRepository;

  public SendMoneyProcessor(){

  }
  public SendMoneyProcessor(AccountRepository accountRepository,TransactionRepository transactionRepository) {
    this.accountRepository = accountRepository;
    this.transactionRepository = transactionRepository;

  }

  @Override
  public ViewTransactionDto createTransaction(CreateTransactionDto transactionDto) {
    //check if account exists else throw exception
    Account sourceAccount = CheckBankDetailsUtil.checkAccountInfo(transactionDto.getAccountNumber());
    Account destinationAccount = CheckBankDetailsUtil.checkAccountInfo(transactionDto.getDestinationAccountNumber());

    Transaction transaction = CheckBankDetailsUtil.getTransaction(transactionDto, sourceAccount);

    //debit in source account if the requirements are met else throw exception
    CheckBankDetailsUtil.checkWithdrawalLimit(sourceAccount.getAccountNumber(), transactionDto.getAmount());
    double sourceAccountBalance = sourceAccount.getAccountBalance();
    double debitedAccountBalance = sourceAccountBalance - transactionDto.getAmount();

    //we make it here we are ready to commit debit in the source account
    sourceAccount.setAccountBalance(debitedAccountBalance);
    CheckBankDetailsUtil.updateAccount(sourceAccount);

    //credit account being sent to
    destinationAccount.setAccountBalance(destinationAccount.getAccountBalance() + transactionDto.getAmount());
    CheckBankDetailsUtil.updateAccount(destinationAccount);
    //save transaction
    CheckBankDetailsUtil.saveTransaction(transaction);

    return getViewTransactionDto(sourceAccount, destinationAccount, transaction);
  }

  private ViewTransactionDto getViewTransactionDto(Account sourceAccount, Account destinationAccount, Transaction transaction) {
    ViewSendMoneyDto viewSendMoneyDto = new ViewSendMoneyDto();
    viewSendMoneyDto.setDestinationAccount(destinationAccount);
    viewSendMoneyDto.setFromAccount(sourceAccount);
    viewSendMoneyDto.setCustomerId(transaction.getCustomerId());
    viewSendMoneyDto.setTransactionType(transaction.getTransactionType());
    viewSendMoneyDto.setTransactionDateTime(transaction.getTransactionDate());
    viewSendMoneyDto.setAccountNumber(transaction.getAccountNumber());
    viewSendMoneyDto.setAmount(transaction.getAmount());
    viewSendMoneyDto.setDestinationAccountNumber(destinationAccount.getAccountNumber());

    return viewSendMoneyDto;
  }


}
