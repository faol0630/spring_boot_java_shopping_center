package com.faol.locals.Service;

import com.faol.locals.Entities.Owner;

import java.util.List;
import java.util.Optional;

public interface OwnerServiceInt {

    List<Owner> findAll();

    Optional<Owner> findById(Long owner_id);

    void  saveNewOwner(Owner owner);

    Owner editOwner(Long owner_id, Owner owner);

    void deleteById(Long owner_id);

    void deleteAll();


}
