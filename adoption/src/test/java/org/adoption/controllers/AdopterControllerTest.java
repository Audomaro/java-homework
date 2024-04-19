package org.adoption.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.adoption.domain.Adopter;
import org.adoption.domain.Pet;
import org.adoption.services.AdopterService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AdopterControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private AdopterService adopterService;

    @Test
    void getAdopters()  throws Exception {

        List<Adopter> adopters = List.of(
                new Adopter(new Pet(Pet.PetType.CAT)),
                new Adopter(new Pet(Pet.PetType.TURTLE)),
                new Adopter(new Pet(Pet.PetType.DOG))
        );

        Mockito.when(adopterService.findAll()).thenReturn(adopters);

        this.mockMvc.perform(
                get("/adopter")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getAdopter() throws Exception {
        Adopter adopter = new Adopter(new Pet(Pet.PetType.CAT));
        adopter.setId(1);

        Mockito.when(adopterService.findByID(1)).thenReturn(adopter);

        this.mockMvc.perform(
                get("/adopter/1")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void addAdopter() throws Exception {
        Adopter adopter = new Adopter(new Pet(Pet.PetType.CAT));
        adopter.setId(1);
        String jsonAdopter = mapper.writeValueAsString(adopter);

        Mockito.when(adopterService.addAdopter(adopter)).thenReturn(adopter);

        this.mockMvc.perform(
                post("/adopter")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonAdopter)
                )
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost:8080/adopter/1}"));
    }

    @Test
    void updateStudent() throws Exception {
        Adopter adopter = new Adopter(new Pet(Pet.PetType.CAT));

        String jsonAdopter = mapper.writeValueAsString(adopter);

        Mockito.when(adopterService.updateAdopter(adopter)).thenReturn(true);

        this.mockMvc.perform(
                        put("/adopter")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonAdopter)
                )
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    void deleteStudent() throws Exception {

        Mockito.when(adopterService.removeAdopter(1)).thenReturn(true);

        this.mockMvc.perform(
                        delete("/adopter/1")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent())
                .andDo(print());
    }
}