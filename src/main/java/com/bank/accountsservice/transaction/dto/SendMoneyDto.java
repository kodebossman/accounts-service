package com.bank.accountsservice.transaction.dto;

import com.bank.accountsservice.common.BaseDto;
import com.bank.accountsservice.common.TransactionType;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
public class SendMoneyDto extends CreateTransactionDto implements Serializable {
  private String destinationAccount;
}
