package com.faol.locals.Service;

import com.faol.locals.Entities.Local;

import java.util.List;
import java.util.Optional;

public interface LocalServiceInt {

    List<Local> findAll();
    Optional<Local> findById(Long local_id);
    Local saveNewLocal(Local local);
    Local editLocal(Long local_id, Local local);
    void deleteById(Long local_id);
    void deleteAll();
    Optional<Local>findByName(String name);
    Optional<Local>findByNameIgnoreCase(String name);
}
