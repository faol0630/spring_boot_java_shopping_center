package com.faol.locals.Controller;

import com.faol.locals.Entities.Local;
import com.faol.locals.Service.LocalServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(LocalController.class)
class LocalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LocalServiceImpl localServiceImpl;

    private Local local;

    @BeforeEach
    void setUp() {

        local = Local.builder()
                .local_id(5L)
                .name("Recording")
                .floor("8")
                .code("91827")
                .build();


    }

    @Test
    void findAll() {
    }

    @Test
    void findById() throws Exception {

        Mockito.when(localServiceImpl.findById(5L)).thenReturn(Optional.of(local));
        mockMvc.perform(get("/id/5L").contentType(MediaType.APPLICATION_JSON));
        System.out.println("findById()");
    }

    @Test
    void findByName() {
    }

    @Test
    void findByNameIgnoreCase() {
    }

    @Test
    void addNewLocal() throws Exception {

        Local postLocal = Local.builder()
                .name("Recording")
                .floor("8")
                .code("91827")
                .build();

        Mockito.when(localServiceImpl.saveNewLocal(postLocal)).thenReturn(local);
        mockMvc.perform(post("/add/").contentType(MediaType.APPLICATION_JSON));
        System.out.println("addNewLocal()");

    }

    @Test
    void editLocal() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void deleteAll() {
    }

    @AfterEach
    void tearDown() {
    }
}