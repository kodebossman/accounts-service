package com.bank.accountsservice.model;

import com.bank.accountsservice.common.BaseEntity;
import com.bank.accountsservice.common.Gender;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "customers", indexes = {@Index(name = "indx_customer", columnList = "id", unique = true)})
@Getter
@Setter
@ToString
public class Customer extends BaseEntity {

  @Column(name = "customername", nullable = false, length = 45)
  private String customerName;
  @Column(name = "customerIdnumber", unique = true, nullable = false, length = 45)
  private String customerIdNumber;
  @Column(name = "surname", unique = true, nullable = false, length = 45)
  private String surname;

  @Column(name = "symbol", length = 100, nullable = false)
  private String address;
  @Column(name = "phoneNo",  unique = true, nullable = false)
  private String phoneNumber;
  @Column(name = "gender",  unique = true, nullable = false)
  private Gender gender;
  @Column(name = "email",  unique = true, nullable = false)
  private String email;
  @Column(name = "dob", length = 2, unique = false, nullable = false)
  private LocalDate dateOfBirth;

}
