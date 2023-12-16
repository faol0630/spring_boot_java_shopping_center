package com.faol.locals.Controller;

import com.faol.locals.Controller.dto.LocalDTO;
import com.faol.locals.Controller.dto.LocalDTOToLocal;
import com.faol.locals.Controller.dto.LocaltoLocalDTO;
import com.faol.locals.Entities.Address;
import com.faol.locals.Entities.Local;
import com.faol.locals.Exceptions.Exception.BadRequestException;
import com.faol.locals.Service.LocalServiceInt;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class LocalController {

    @Autowired
    LocalServiceInt service;

    @GetMapping("/")
    public ResponseEntity<List<?>> findAll() {

        List<Local> verify = service.findAll();
        if (!(verify.size() == 0)) {

            List<LocalDTO> localDTOList = verify.stream().map( local -> {

                Address address = new Address();
                address.setStreet(local.getAddress().getStreet());
                address.setNumber(local.getAddress().getNumber());
                address.setCity(local.getAddress().getCity());

                return LocalDTO.builder()
                        .floor(local.getFloor())
                        .code(local.getCode())
                        .local_id(local.getLocal_id())
                        .name(local.getName())
                        .transactionList(local.getTransactionList())
                        .customerList(local.getCustomerList())
                        .address(address)
                        .build();
            }).toList();

            return new ResponseEntity<>(localDTOList, HttpStatus.OK);
        } else {

            String errorMessage = "Empty list (faol)";

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/id/{local_id}")
    public ResponseEntity<?> findById(@PathVariable Long local_id) {

        Map<String, Object> response = new HashMap<>();

        Optional<Local> verify = service.findById(local_id);
        if (verify.isPresent()){

            Local local = verify.get();
            LocalDTO localDTO = LocaltoLocalDTO.localToLocalDTO(local);

            return new ResponseEntity<>(localDTO, HttpStatus.OK);

        } else{

            response.put("Message faol", "Local with id ".concat(local_id.toString()).concat(" not founded"));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    }


    @GetMapping("/name/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name) {

        Map<String, Object> response = new HashMap<>();

        Optional<Local> verify = service.findByName(name);

        if (verify.isPresent()) {

            Local local = verify.get();
            LocalDTO localDTO = LocaltoLocalDTO.localToLocalDTO(local);
            return new ResponseEntity<>(localDTO, HttpStatus.OK);

        } else {
            response.put("Message", "Local with name ".concat(name).concat(" not founded"));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/name_ignore_case/{name}")
    public ResponseEntity<?> findByNameIgnoreCase(@PathVariable String name) {

        Map<String, Object> response = new HashMap<>();

        Optional<Local> verify = service.findByNameIgnoreCase(name);
        if (verify.isPresent()) {

            Local local = verify.get();
            LocalDTO localDTO = LocaltoLocalDTO.localToLocalDTO(local);
            return new ResponseEntity<>(localDTO, HttpStatus.OK);
        } else {
            response.put("Message", "Local with name ".concat(name).concat(" not founded"));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add/")
    public ResponseEntity<?> addNewLocal(@Valid @RequestBody LocalDTO localDTO) {

        if (localDTO.getName().isEmpty() || localDTO.getName() == null){
            throw new BadRequestException("Invalid name");

        }
        if (localDTO.getFloor().isEmpty() || localDTO.getFloor() == null){
            throw new BadRequestException("Invalid floor");
        }
        if (localDTO.getCode().isEmpty() || localDTO.getCode() == null){
            throw new BadRequestException("Invalid code");

        }
        Local local = LocalDTOToLocal.localDTOToLocal(localDTO);
        service.saveNewLocal(local);
        return new ResponseEntity<>("Local created", HttpStatus.CREATED);

    }

    @PutMapping("/{local_id}")
    public ResponseEntity<?> editLocal(@Valid @PathVariable Long local_id, @RequestBody Local local) {

        Map<String, Object> response = new HashMap<>();

        Optional<Local> verify = service.findById(local_id);

        if (verify.isPresent()) {
            Local update = service.editLocal(local_id, local);
            LocalDTO localDTO = LocaltoLocalDTO.localToLocalDTO(update);
            return new ResponseEntity<>(localDTO, HttpStatus.OK);
        } else {
            response.put("Message faol", "Local with id ".concat(local_id.toString()).concat(" not founded"));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{local_id}")
    public ResponseEntity<?> deleteById(@PathVariable Long local_id) {

        Map<String, Object> response = new HashMap<>();

        Optional<Local> verify = service.findById(local_id);

        if (verify.isPresent()) {
            service.deleteById(local_id);
            response.put("Message ", "Local with id ".concat(local_id.toString()).concat(" deleted correctly."));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

        } else {
            response.put("Message ", "Error deleting local with id: ".concat(local_id.toString()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);

        }

    }

    @DeleteMapping()
    public ResponseEntity<String> deleteAll() {
        List<Local> verify = service.findAll();
        if (!verify.isEmpty()) {
            service.deleteAll();
            return new ResponseEntity<String>("All locals deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Empty list", HttpStatus.NO_CONTENT);
        }
    }

}
