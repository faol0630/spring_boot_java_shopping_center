package com.faol.locals.Repository;

import com.faol.locals.Entities.Address;
import com.faol.locals.Entities.Local;
import com.faol.locals.Entities.Owner;
import com.faol.locals.Entities.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Objects;


@SpringBootTest
class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    public void saveOrder(){

        try {

            Address address1 = Address.builder()
                    .street("Figueira")
                    .number("7")
                    .city("Malaga")
                    .build();

            Owner owner1 = Owner.builder()
                    .owner_name("Luisa")
                    .owner_lastname("Lane")
                    .build();

            Local local1 = Local.builder()
                    .name("Ropa")
                    .floor("7")
                    .code("67567")
                    .owner(owner1)
                    .address(address1)
                    .build();

            Transaction transaction1 = Transaction.builder()
                    .local(local1)
                    .description("Camisa")
                    .total_amount(10.00)
                    .build();

            List<Transaction> transactionList = transactionRepository.findAll();

            if (!transactionList.contains(transaction1)){
                transactionRepository.save(transaction1);

            }

        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Nothing to add");
        }
    }

    @Test
    public void findAllTransactions(){

        List<Transaction> transactionList = transactionRepository.findAll();

        transactionList.forEach(transaction -> System.out.println(
                "Id: " + transaction.getTransactionId() +
                " / Description: " + transaction.getDescription() +
                        " / Amount: " + transaction.getTotal_amount() +
                        " / Local Id: " + transaction.getLocal().getLocal_id() +
                        " / Local Name: " + transaction.getLocal().getName()
        ));

    }

    @Test
    public void findAllTransactionsByLocalId(){

        List<Transaction> transactionList = transactionRepository.findAll();
        Long local_id = 71L;

        transactionList.forEach( transaction -> {
            if (Objects.equals(transaction.getLocal().getLocal_id(), local_id)){
                System.out.println("Transactions list with Id " + local_id + ":");
                System.out.println(
                        "Id: " + transaction.getTransactionId() +
                                " / Description: " + transaction.getDescription() +
                                " / Amount: " + transaction.getTotal_amount() +
                                " / Local Id: " + transaction.getLocal().getLocal_id() +
                                " / Local Name: " + transaction.getLocal().getName()
                );
            }
        });

    }

}