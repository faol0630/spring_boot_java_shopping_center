package com.faol.locals.Controller.dto;

import com.faol.locals.Entities.Address;
import com.faol.locals.Entities.Local;

public class LocalDTOToLocal {

    public static Local localDTOToLocal(LocalDTO localDTO){

        Address address = new Address();
        address.setCity(localDTO.getAddress().getCity());
        address.setNumber(localDTO.getAddress().getNumber());
        address.setStreet(localDTO.getAddress().getStreet());

        Local local = Local.builder()
                .local_id(localDTO.getLocal_id())
                .code(localDTO.getCode())
                .name(localDTO.getName())
                .floor(localDTO.getFloor())
                .transactionList(localDTO.getTransactionList())
                .customerList(localDTO.getCustomerList())
                .address(address)
                .build();

        return local;

    }
}
