package com.bank.accountsservice.transaction;

import com.bank.accountsservice.account.dto.CreateAccountDto;
import com.bank.accountsservice.account.dto.ViewAccountDto;
import com.bank.accountsservice.account.service.AccountService;
import com.bank.accountsservice.common.TransactionType;
import com.bank.accountsservice.customer.service.CustomerService;
import com.bank.accountsservice.exceptions.BadRequestDataException;
import com.bank.accountsservice.model.Customer;
import com.bank.accountsservice.transaction.dto.CreateTransactionDto;
import com.bank.accountsservice.transaction.dto.ViewTransactionDto;
import com.bank.accountsservice.transaction.service.TransactionProcessorFactory;
import com.bank.accountsservice.transaction.service.TransactionService;
import com.bank.accountsservice.util.CheckBankDetailsUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
public class TransactionController {

  private final TransactionService transactionService;
  private final CustomerService customerService;


  public TransactionController(TransactionService transactionService, CustomerService customerService) {
    this.transactionService = transactionService;
    this.customerService = customerService;
  }

  @SneakyThrows
  @PostMapping(value = "/transactions/{transactiontypeid}")

  public ResponseEntity<ViewTransactionDto> createTransaction(@RequestBody CreateTransactionDto createTransactionDto,@PathVariable("transactiontypeid") int transactionTypeId ) throws IOException {

    log.info("New Transaction : {} ", createTransactionDto);

     //TODO check using security token, userid, or login key

    Customer  customer = customerService.getCustomerById(createTransactionDto.getCustomerId());
    if(!Objects.isNull(customer)) {
      ViewTransactionDto viewTransactionDto = TransactionProcessorFactory.getTransactionType(transactionTypeId).createTransaction(createTransactionDto);
      return new ResponseEntity<>(viewTransactionDto, HttpStatus.CREATED);
    }else {
      throw new BadRequestDataException(" Customer does not exist");
    }

  }

  @GetMapping(value = "/transactions/{transactiontypeid}/{customerid}")
  public ResponseEntity<List<ViewTransactionDto>> getTransactionHistoryByType(@PathVariable("customerid")Long customerId, @PathVariable("transactiontypeid") int transactionTypeId ) throws IOException {

    log.info("Getting transaction of type :"+transactionTypeId + "{} for this customerId"+ customerId);

    //TODO check using security token, userid, or login key

    CheckBankDetailsUtil.checkCustomer(customerId);
    List<ViewTransactionDto> viewTransactionDtoList = transactionService.getTransactionHistoryByType(TransactionType.getTransactionType(transactionTypeId),customerId);
    return new ResponseEntity<>( viewTransactionDtoList, HttpStatus.CREATED);

  }

  @GetMapping(value = "/transactions/customers/{customerid}")
  public ResponseEntity<List<ViewTransactionDto>> getAllTransactionHistory(@PathVariable("customerid")Long customerId ) throws IOException {

    log.info("Getting transaction history {} for this customerId"+ customerId);

    //TODO check using security token, userid, or login key

    CheckBankDetailsUtil.checkCustomer(customerId);
    List<ViewTransactionDto> viewTransactionDtoList = transactionService.getAllTransactions(customerId);
    return new ResponseEntity<>( viewTransactionDtoList, HttpStatus.CREATED);

  }



}
