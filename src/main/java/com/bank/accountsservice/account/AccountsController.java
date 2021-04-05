package com.bank.accountsservice.account;

import com.bank.accountsservice.account.dto.CreateAccountDto;
import com.bank.accountsservice.account.dto.ViewAccountDto;
import com.bank.accountsservice.account.service.AccountService;
import com.bank.accountsservice.customer.service.CustomerService;
import com.bank.accountsservice.exceptions.BadRequestDataException;
import com.bank.accountsservice.model.Customer;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import java.util.Objects;

@Slf4j
@RestController
public class AccountsController {

  private final AccountService accountService;
  private final CustomerService customerService;
  public AccountsController(AccountService accountService, CustomerService customerService) {
    this.accountService = accountService;
    this.customerService = customerService;
  }

  @SneakyThrows
  @PostMapping(value = "/accounts/create")

  public ResponseEntity<ViewAccountDto> openAccount(@RequestBody CreateAccountDto accountDto) throws IOException {

    log.info("New Registration : {} ", accountDto);

    Customer  customer = customerService.getCustomerByIdNumber(accountDto.getCustomerIdNumber());
    if (!Objects.isNull(customer)) {
      final ViewAccountDto createAccountDto = accountService.openAccount(accountDto);
      return new ResponseEntity<>( createAccountDto, HttpStatus.CREATED);
    }else{
      throw new BadRequestDataException("There is no customer with ID Number "+ accountDto.getCustomerIdNumber());
    }

  }

}
