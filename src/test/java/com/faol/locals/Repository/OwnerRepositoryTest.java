package com.faol.locals.Repository;

import com.faol.locals.Entities.Owner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class OwnerRepositoryTest {

    @Autowired
    private OwnerRepository ownerRepository;

    @Test
    public void findAllOwners(){
        List<Owner> owners = ownerRepository.findAll();
        owners.forEach(owner -> System.out.println(
                    "Owner Id: " + owner.getOwner_id() + " / " +
                    "Name: " + owner.getOwner_name() + " / " +
                    "Lastname: " + owner.getOwner_lastname() + " / " +
                    "Local Id: " + owner.getLocal().getLocal_id() + " / " +
                    "Local Name: " + owner.getLocal().getName() + " / "
                )

        );
    }

}