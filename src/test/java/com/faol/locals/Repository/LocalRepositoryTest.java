package com.faol.locals.Repository;

import com.faol.locals.Entities.Local;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
class LocalRepositoryTest {

    @MockBean
    private LocalRepository repo;

    Local local;
    Local local1;
    Local local2;

    List<Local> locals;

    @BeforeEach
    void setUp() {

        local = Local.builder()
                        .local_id(5L)
                        .name("Supermarket")
                        .floor("3")
                        .code("56453")
                        .build();

        local1 = Local.builder()
                        .local_id(4L)
                        .name("Market")
                        .floor("4")
                        .code("57841")
                        .build();

        local2 = Local.builder()
                .local_id(3L)
                .name("Muebles")
                .floor("1")
                .code("56476")
                .build();

        locals = new ArrayList<>();

        locals.add(local);
        locals.add(local1);
        locals.add(local2);

        Mockito.when(repo.findAll()).thenReturn(locals);
        Mockito.when(repo.findById(3L)).thenReturn(Optional.of(local2));
        Mockito.when(repo.findByName("Supermarket")).thenReturn(Optional.of(local));
        Mockito.when(repo.findByName("Electronics")).thenReturn(Optional.empty());
        Mockito.when(repo.findByNameIgnoreCase("Muebles")).thenReturn(Optional.of(local2));
        Mockito.when(repo.findByNameIgnoreCase("Cinema")).thenReturn(Optional.empty());

    }

    @Test
    void findAllSuccess(){
        List<Local> localList = repo.findAll();
        assertEquals(localList, locals);
    }

    @Test
    void findByIdFound(){
        Optional<Local> local = repo.findById(3L);
        assertEquals(local.get().getLocal_id(), local2.getLocal_id());
    }

    @Test
    void findByNameFound() {
        Optional<Local> local = repo.findByName("Supermarket");
        assertEquals(local.get().getName(), "Supermarket");
    }

    @Test
    void findByNameNotFound() {
        Optional<Local> local = repo.findByName("Electronics");
        assertEquals(local, Optional.empty());
    }

    @Test
    void findByNameIgnoreCaseFound() {
        Optional<Local> local = repo.findByNameIgnoreCase("Muebles");
        assertEquals(local.get().getName(), "Muebles");
    }

    @Test
    void findByNameIgnoreCaseNotFound() {
        Optional<Local> local = repo.findByNameIgnoreCase("Cinema");
        assertEquals(local, Optional.empty());
    }

    @Test
    void saveSuccessful(){
        Local local123 = Local.builder()
                .name("Market")
                .floor("4")
                .code("57841")
                .build();

        Mockito.when(repo.save(local123)).thenReturn(local1);
        System.out.println(local123);
    }

    @Test
    void saveSuccessfulVerify(){
        Local local123 = Local.builder()
                .name("Market")
                .floor("4")
                .code("57841")
                .build();

        repo = Mockito.spy(LocalRepository.class);
        repo.save(local123);
        Mockito.verify(repo).save(local123);
    }



    @Test
    void deleteByIdSuccessful(){

        repo = Mockito.spy(LocalRepository.class);
        repo.deleteById(3L);
        Mockito.verify(repo).deleteById(3L);

    }

    @Test
    void deleteAllSuccessful(){
        repo = Mockito.spy(LocalRepository.class);
        repo.deleteAll();
        Mockito.verify(repo).deleteAll();
    }

    @AfterEach
    void tearDown() {

    }

}