package com.faol.locals.Controller;

import com.faol.locals.Entities.Owner;
import com.faol.locals.Service.OwnerServiceInt;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class OwnerController {

    @Autowired
    OwnerServiceInt ownerService;


    @PostMapping("/add_new_owner/")
    public ResponseEntity<?> addNewOwner(@Valid @RequestBody Owner owner) {

        ownerService.saveNewOwner(owner);
        return new ResponseEntity<>("Owner created", HttpStatus.CREATED);
    }
}
