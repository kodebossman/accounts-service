package com.bank.accountsservice.customer;

import com.bank.accountsservice.account.dto.CreateAccountDto;
import com.bank.accountsservice.account.dto.ViewAccountDto;
import com.bank.accountsservice.account.service.AccountService;
import com.bank.accountsservice.customer.dto.CreateCustomerDto;
import com.bank.accountsservice.customer.service.CustomerService;
import com.bank.accountsservice.exceptions.BadRequestDataException;
import com.bank.accountsservice.model.Account;
import com.bank.accountsservice.model.Customer;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Objects;

@Slf4j
@RestController
public class CustomerController {

  private final CustomerService customerService;
  public CustomerController( CustomerService customerService) {
    this.customerService = customerService;
  }

  @SneakyThrows
  @PostMapping(value = "/customers/create")

  public ResponseEntity<Customer> createCustomer(@RequestBody @Validated CreateCustomerDto createCustomerDto) throws IOException {

    log.info("New Registration : {} ", createCustomerDto);

    Customer customer = customerService.getCustomerByIdNumber(createCustomerDto.getCustomerIdNumber());
    if (Objects.isNull(customer)) {
      final Customer cust = customerService.createCustomer(createCustomerDto);
      return new ResponseEntity<>( cust, HttpStatus.CREATED);
    }else{
      throw new BadRequestDataException("There is a customer with ID Number "+ createCustomerDto.getCustomerIdNumber());
    }

  }
}
