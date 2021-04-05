package com.bank.accountsservice.transaction.dto;


import com.bank.accountsservice.model.Account;

import com.bank.accountsservice.model.Customer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ViewTransactionDto extends CreateTransactionDto implements Serializable {

  private Account account;
  private Account destinationAccount;
  private Customer customer;
  private LocalDateTime transactionDateTime;
  private Long transactionId;

}
