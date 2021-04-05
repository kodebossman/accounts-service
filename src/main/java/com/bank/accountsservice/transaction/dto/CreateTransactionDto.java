package com.bank.accountsservice.transaction.dto;

import com.bank.accountsservice.common.BaseDto;
import com.bank.accountsservice.common.TransactionType;
import lombok.Data;

import java.io.Serializable;

@Data
public class CreateTransactionDto extends BaseDto implements Serializable {

  private TransactionType transactionType;
  private double amount;
  private String accountNumber;
  private Long customerId;
  private String destinationAccountNumber;

}
