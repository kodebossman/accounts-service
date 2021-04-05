package com.bank.accountsservice.customer.dto;

import com.bank.accountsservice.common.BaseDto;
import com.bank.accountsservice.common.Gender;
import com.bank.accountsservice.model.Account;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
public class ViewCustomerDetailsDto extends BaseDto implements Serializable {

  private String customerName;
  private String customerIdNumber;
  private String surname;
  private String address;
  private String phoneNumber;
  private Gender gender;
  private String email;
  private LocalDate dateOfBirth;
  private List<Account> accountList;
}
