package com.faol.locals.Controller.dto;

import com.faol.locals.Entities.Address;
import com.faol.locals.Entities.Customer;
import com.faol.locals.Entities.Transaction;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;
@Data
@Builder
public class LocalDTO {


    private Long local_id;
    private String name;
    private String floor;
    private String code;

    @Embedded
    private Address address;
    private List<Transaction> transactionList;
    private List<Customer> customerList;
}
