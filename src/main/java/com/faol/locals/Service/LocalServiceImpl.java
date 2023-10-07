package com.faol.locals.Service;

import com.faol.locals.Entities.Local;
import com.faol.locals.Repository.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LocalServiceImpl implements LocalServiceInt{

    @Autowired
    LocalRepository repo;

    @Override
    public List<Local> findAll() {
        return repo.findAll();

    }

    @Override
    public Optional<Local> findById(Long local_id) {
        return repo.findById(local_id);
    }


    @Override
    public Local saveNewLocal(Local local) {

       return repo.save(local);

    }

    @Override
    public Local editLocal(Long local_id, Local local) {

        Local newLocal = repo.findById(local_id).get();

        if (Objects.nonNull(local.getName()) && !"".equalsIgnoreCase(local.getName())){
            newLocal.setName(local.getName());

        }
        if (Objects.nonNull(local.getFloor()) && !"".equalsIgnoreCase(local.getFloor())){
            newLocal.setFloor(local.getFloor());

        }
        if (Objects.nonNull(local.getCode()) && !"".equalsIgnoreCase(local.getCode())){
            newLocal.setCode(local.getCode());

        }

        return repo.save(newLocal);
    }

    @Override
    public void deleteById(Long local_id) {
        repo.deleteById(local_id);
    }

    @Override
    public void deleteAll() {
        repo.deleteAll();
    }

    @Override
    public Optional<Local> findByName(String name) {
        return repo.findByName(name);
    }

    @Override
    public Optional<Local> findByNameIgnoreCase(String name) {
        return repo.findByNameIgnoreCase(name);
    }
}
