package com.bank.accountsservice.customer.service;

import com.bank.accountsservice.customer.dto.CreateCustomerDto;
import com.bank.accountsservice.model.Customer;

import java.util.Optional;

public interface CustomerService {
  Customer getCustomerByIdNumber(String customerIdNumber);
  Customer createCustomer(CreateCustomerDto customerDto);
  Customer getCustomerById(Long Id);

}
