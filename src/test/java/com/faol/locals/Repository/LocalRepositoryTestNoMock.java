package com.faol.locals.Repository;

import com.faol.locals.Entities.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@SpringBootTest
public class LocalRepositoryTestNoMock {

    @Autowired
    private LocalRepository repoNoMock;


    @Test
    void saveSuccessfullWithEmbbededNoMock() {


        try{
            Owner owner1 = Owner.builder()
                    .owner_name("Ana")
                    .owner_lastname("Ann")
                    .build();

            //Embeddable
            Address address1 = Address.builder()
                    .city("Jaen")
                    .street("Principe")
                    .number("1")
                    .build();

            //Entity
            Local local = Local.builder()
                    .name("Tvs and cells")
                    .floor("3")
                    .code("78659")
                    .address(address1)
                    .owner(owner1)
                    .build();


            Local result = repoNoMock.save(local);
            System.out.println("Result: " + result);

        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Local or owner already exist");
        }


    }

    @Test
    void findLocalByName() {
        String name = "Market";
        Optional<Local> result = repoNoMock.findByName(name);
        if (result.isPresent()){
            System.out.println("Result: " + result);

        }else{
            System.out.println("There are not matches with name " + name);
        }
    }

    @Test
    void updateAddressToLocal() {

        List<Local> list = repoNoMock.findAll();
        String name = "Computers";

        if (list.contains(name)) {

            Local result = repoNoMock.findByName(name).get();

            Owner owner1 = Owner.builder()
                    .owner_name("Pedro")
                    .owner_lastname("Perez")
                    .build();

            result.setOwner(owner1);

            Address addressNew = Address.builder()
                    .city("Sevilla")
                    .street("Vereda")
                    .number("4")
                    .build();

            result.setAddress(addressNew);

            repoNoMock.save(result);

        } else {
            System.out.println("Nothing to update");
        }

    }



    @Test
    void findAllLocalsByNameContaining() {

        List<Local> localList = repoNoMock.findByNameContaining("es");

        if ((long) localList.size() == 0) {
            System.out.println("There are not matches");
        } else {
            localList.forEach(local -> System.out.println(
                    "Id : " + local.getLocal_id() + " / " +
                    "Name : " + local.getName() + " / " +
                    "Code : " + local.getCode() + " / " +
                    "Floor : " + local.getFloor() + " / " +
                    "City : " + local.getAddress().getCity() + " / " +
                    "Street : " + local.getAddress().getStreet() + " / " +
                    "Number : " + local.getAddress().getNumber() + " / " +
                    "Owner name : " + local.getOwner().getOwner_name() + " / " +
                    "Owner lastname : " + local.getOwner().getOwner_lastname() + " / "
            ));
            System.out.println();
        }

    }

    @Test
    void findAllLocalsByCodeContaining() {
        List<Local> localList = repoNoMock.findByCodeContaining("78");
        if (localList.isEmpty()) {
            System.out.println("There are not matches");
        } else {
            localList.forEach(local -> System.out.println(
                    "Id : " + local.getLocal_id() + " / " +
                            "Name : " + local.getName() + " / " +
                            "Code : " + local.getCode() + " / " +
                            "Floor : " + local.getFloor() + " / " +
                            "City : " + local.getAddress().getCity() + " / " +
                            "Street : " + local.getAddress().getStreet() + " / " +
                            "Number : " + local.getAddress().getNumber() + " / " +
                            "Owner name : " + local.getOwner().getOwner_name() + " / " +
                            "Owner lastname : " + local.getOwner().getOwner_lastname() + " / "
            ));
            System.out.println();
        }
    }

    @Test
    void findAllLocalsByFloor() {
        List<Local> localList = repoNoMock.findByFloor("4");
        if (localList.isEmpty()) {
            System.out.println("There are not matches");
        } else {
            localList.forEach(x -> System.out.println("Local : " + x));

        }
    }

    @Test
    @Transactional
    void deleteByName() {

        List<Local> list = repoNoMock.findAll();
        String name = "Cell Phones 3";

        if (list.contains(name)) {
            repoNoMock.deleteByName(name);
            System.out.println("Local with name <" + name + "> deleted");

        } else {
            System.out.println("Nothing to delete");
        }
    }

    @Test
    public void saveLocalWithTransactions(){

        try {

            Transaction transaction1 = Transaction.builder()
                    .description("Pasta")
                    .total_amount(56.30)
                    .build();

            Transaction transaction2 = Transaction.builder()
                    .description("Bolognesa")
                    .total_amount(21.15)
                    .build();

            Transaction transaction3 = Transaction.builder()
                    .description("Fetucchini")
                    .total_amount(25.50)
                    .build();

            Address address1 = Address.builder()
                    .street("Cortijo")
                    .number("4")
                    .city("Estepona")
                    .build();

            Owner owner1 = Owner.builder()
                    .owner_name("Ramiro")
                    .owner_lastname("Roa")
                    .build();

            Local local1 = Local.builder()
                    .name("Italian_restaurant")
                    .floor("3")
                    .code("18342")
                    .address(address1)
                    .owner(owner1)
                    //.transactionList(List.of(transaction1, transaction2, transaction3))
                    .build();

            List<Local> localList = repoNoMock.findAll();

            if (!localList.contains(local1)){
                repoNoMock.save(local1);

            }

        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Nothing to add");

        }

    }

    @Test
    public void addTransactionsToLocal(){

        Local local1 = repoNoMock.findById(38L).get();

        Transaction transaction1 = Transaction.builder()
                .total_amount(34.50)
                .description("Several items")
                .build();

        Transaction transaction2 = Transaction.builder()
                .total_amount(23.30)
                .description("Several items 2")
                .build();

        local1.setTransactionList(List.of(transaction1, transaction2));

        if (local1.getTransactionList().isEmpty()){
            repoNoMock.save(local1);
        }else{
            System.out.println("Transactions already added");
        }

    }


    @Test
    public void findAllLocalsWithTransactionsSize(){
        List<Local> localList = repoNoMock.findAll();
        localList.forEach(local -> System.out.println(
                "Id : " + local.getLocal_id() + " / " +
                        "Name : " + local.getName() + " / " +
                        "Code : " + local.getCode() + " / " +
                        "Floor : " + local.getFloor() + " / " +
                        "City : " + local.getAddress().getCity() + " / " +
                        "Street : " + local.getAddress().getStreet() + " / " +
                        "Number : " + local.getAddress().getNumber() + " / " +
                        "Owner name : " + local.getOwner().getOwner_name() + " / " +
                        "Owner lastname : " + local.getOwner().getOwner_lastname() + " / " +
                        "Transactions size : " + local.getTransactionList().size() + " / "

        ));
    }

    @Test
    public void saveLocalWithCustomer(){

        try {

            Customer customer = Customer.builder()
                    .customer_name("Pedro")
                    .customer_lastname("Last")
                    .build();

            Customer customer1 = Customer.builder()
                    .customer_name("Anita")
                    .customer_lastname("Prado")
                    .build();

            List<Customer> customerList = new ArrayList<>();
            customerList.add(customer);
            customerList.add(customer1);

            Owner owner1 = Owner.builder()
                    .owner_name("Bill")
                    .owner_lastname("Doe")
                    .build();

            Address address1 = Address.builder()
                    .city("Estepona")
                    .street("Malanga")
                    .number("12")
                    .build();

            Local local1 = Local.builder()
                    .name("Air conditioner")
                    .floor("5")
                    .code("86759")
                    .address(address1)
                    .owner(owner1)
                    .customerList(customerList)
                    .build();

            List<Local> localList = repoNoMock.findAll();

            if (!localList.contains(local1)){
                repoNoMock.save(local1);

            }

        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Nothing to add");
        }
    }

    @Test
    public void findAllLocalsWithCustomers(){//esto no seria lo mas indicado
        List<Local> localList = repoNoMock.findAll();
        localList.forEach( local -> System.out.println(
                "Id : " + local.getLocal_id() + " / " +
                        "Name : " + local.getName() + " / " +
                        "Code : " + local.getCode() + " / " +
                        "Floor : " + local.getFloor() + " / " +
                        "City : " + local.getAddress().getCity() + " / " +
                        "Street : " + local.getAddress().getStreet() + " / " +
                        "Number : " + local.getAddress().getNumber() + " / " +
                        "Owner name : " + local.getOwner().getOwner_name() + " / " +
                        "Owner lastname : " + local.getOwner().getOwner_lastname() + " / " +
                        "Customer list : " + local.getCustomerList() + " / "

        ));
    }

    @Test
    public void findCustomersByLocal(){
        Local local = repoNoMock.findById(92L).get();
        List<Customer> customerList = local.getCustomerList();
        customerList.forEach(System.out::println);

    }

}