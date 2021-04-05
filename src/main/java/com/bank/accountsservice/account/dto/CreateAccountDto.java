package com.bank.accountsservice.account.dto;

import com.bank.accountsservice.common.AccountType;
import com.bank.accountsservice.common.BaseDto;
import com.bank.accountsservice.model.Customer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
@Data
public class CreateAccountDto extends BaseDto implements Serializable {

  private AccountType accountType;
  private String accountNumber;
  private String customerIdNumber;
  private String currency;
  private double  accountBalance;
  private LocalDate creationDate;
}
