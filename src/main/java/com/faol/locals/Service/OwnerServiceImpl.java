package com.faol.locals.Service;

import com.faol.locals.Entities.Owner;
import com.faol.locals.Repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerServiceImpl implements OwnerServiceInt{

    @Autowired
    OwnerRepository ownerRepo;

    @Override
    public List<Owner> findAll() {
        return ownerRepo.findAll();
    }

    @Override
    public Optional<Owner> findById(Long owner_id) {

         Optional<Owner> response = ownerRepo.findById(owner_id) ;
         return response;
    }

    @Override
    public void saveNewOwner(Owner owner) {
        ownerRepo.save(owner);
    }

    @Override
    public Owner editOwner(Long owner_id, Owner owner) {

        Owner newOwner = new Owner();

        Optional<Owner> response = ownerRepo.findById(owner_id);

        if (response.isPresent()){
            newOwner.setOwner_name(owner.getOwner_name());
            newOwner.setOwner_lastname(owner.getOwner_lastname());
        }
        return ownerRepo.save(newOwner);
    }

    @Override
    public void deleteById(Long owner_id) {
        ownerRepo.deleteById(owner_id);
    }

    @Override
    public void deleteAll() {
        ownerRepo.deleteAll();
    }
}
