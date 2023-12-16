package com.faol.locals.Controller.dto;

import com.faol.locals.Entities.Address;
import com.faol.locals.Entities.Local;

public class LocaltoLocalDTO {

    public static LocalDTO localToLocalDTO(Local local){

        Address address = new Address();
        address.setStreet(local.getAddress().getStreet());
        address.setNumber(local.getAddress().getNumber());
        address.setCity(local.getAddress().getCity());

        LocalDTO localDTO = LocalDTO.builder()
                .local_id(local.getLocal_id())
                .code(local.getCode())
                .floor(local.getFloor())
                .name(local.getName())
                .transactionList(local.getTransactionList())
                .customerList(local.getCustomerList())
                .address(address)
                .build();

        return localDTO;
    }
}
