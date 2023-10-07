package com.faol.locals.Service;

import com.faol.locals.Entities.Local;
import com.faol.locals.Repository.LocalRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LocalServiceImplTest {

    @Autowired
    private LocalServiceInt localService;

    @MockBean
    private LocalRepository localRepository;

    List<Local> locals;

    Local local;

    @BeforeEach
    void setUp() {

        local = Local.builder()
                .local_id(2L)
                .name("Petshop")
                .floor("2")
                .code("67563")
                .build();

        locals = new ArrayList<>();
        locals.add(local);

        Mockito.when(localRepository.findByNameIgnoreCase("Petshop")).thenReturn(Optional.of(local));
        Mockito.when(localRepository.findByName("Petshop")).thenReturn(Optional.of(local));
        Mockito.when(localRepository.findAll()).thenReturn(locals);
        Mockito.when(localRepository.findById(2L)).thenReturn(Optional.of(local));
    }

    @Test
    void findAll() {
        List<Local> localList = localService.findAll();
        assertEquals(localList, locals);
        System.out.println(locals);
    }

    @Test
    void findById() {
        Local local1 = localService.findById(2L).get();
        assertEquals(local.getLocal_id(), local1.getLocal_id());
        System.out.println(local);
    }

    @Test
    void saveNewLocal() {

        Local postLocal = Local.builder()
                .name("Petshop")
                .floor("2")
                .code("67563")
                .build();

        Mockito.when(localRepository.save(postLocal)).thenReturn(local);
        System.out.println(local);

    }

    @Test
    void editLocal() {
        Local postLocal = Local.builder()
                .name("Petshop")
                .floor("2")
                .code("34562")
                .build();

        Mockito.when(localRepository.save(postLocal)).thenReturn(local);
        System.out.println(postLocal);
    }

    @Test
    void deleteById() {

        localService = Mockito.spy(LocalServiceInt.class);
        localService.deleteById(2L);
        Mockito.verify(localService).deleteById(2L);
        System.out.println("Local deleted");

    }

    @Test
    void deleteAll() {
        localService = Mockito.spy(LocalServiceInt.class);
        localService.deleteAll();
        Mockito.verify(localService).deleteAll();
        System.out.println("All locals entities deleted");
    }

    @Test
    void findByName() {
        String localName = "Petshop";
        Local local = localService.findByName(localName).get();
        assertEquals(localName, local.getName());
        System.out.println(local);
    }

    @Test
    @DisplayName("Obteniendo un Entity Local a traves del nombre")
    void findByNameIgnoreCaseFound() {
        String localName = "Petshop";
        Local local = localService.findByNameIgnoreCase(localName).get();
        assertEquals(localName, local.getName());
        System.out.println(local);
    }

    @AfterEach
    void tearDown() {
    }
}