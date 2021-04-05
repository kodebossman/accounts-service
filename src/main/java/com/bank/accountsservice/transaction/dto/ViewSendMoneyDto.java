package com.bank.accountsservice.transaction.dto;

import com.bank.accountsservice.model.Account;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
public class ViewSendMoneyDto extends ViewTransactionDto implements Serializable {
  private Account fromAccount;
  private Account destinationAccount;
  private LocalDateTime transactionDateTime;

}
