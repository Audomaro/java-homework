package org.adoption.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.adoption.domain.Adopter;
import org.adoption.domain.Pet;
import org.adoption.services.AdopterServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AdopterController.class)
class AdopterControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdopterServiceImpl adopterService;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void getAdopters()  throws Exception {

        List<Adopter> adopters = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            Adopter adopter = new Adopter();
            adopter.setId(i+1);

            for (int j = 0; j < 2; j++) {
                Pet pet = new Pet();
                pet.setId(j+1);
                pet.setAdopter(adopter);
                adopter.getPets().add(pet);
            }

            adopters.add(adopter);
        }

        when(adopterService.findAllAdopters()).thenReturn(adopters);

        mockMvc.perform(
                        get("/adopter")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print());

        verify(adopterService).findAllAdopters();
    }

    @Test
    void getAdopterById() throws Exception {
        Adopter adopter = new Adopter();
        adopter.setId(1);

        for (int j = 0; j < 2; j++) {
            Pet pet = new Pet();
            pet.setId(j+1);
            pet.setAdopter(adopter);
            adopter.getPets().add(pet);
        }

        when(adopterService.findAdopterById(1)).thenReturn(Optional.of(adopter));

        mockMvc.perform(
                        get("/adopter/1")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print());

        verify(adopterService).findAdopterById(1);
    }

    @Test
    void getByName()  throws Exception {
        List<Adopter> adopters = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            Adopter adopter = new Adopter();
            adopter.setId(i+1);

            for (int j = 0; j < 2; j++) {
                Pet pet = new Pet();
                pet.setId(j+1);
                pet.setAdopter(adopter);
                adopter.getPets().add(pet);
            }

            adopters.add(adopter);
        }

        when(adopterService.findAdopterByName("Rub")).thenReturn(adopters);

        mockMvc.perform(
                        get("/adopter/by-name/Rub")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print());

        verify(adopterService).findAdopterByName("Rub");
    }

    @Test
    void getAdopterWithPets()  throws Exception {
        List<Adopter> adopters = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            Adopter adopter = new Adopter();
            adopter.setId(i+1);

            for (int j = 0; j < 2; j++) {
                Pet pet = new Pet();
                pet.setId(j+1);
                pet.setAdopter(adopter);
                adopter.getPets().add(pet);
            }

            adopters.add(adopter);
        }

        when(adopterService.findAdopterWithPets()).thenReturn(adopters);

        mockMvc.perform(
                        get("/adopter/with-pets")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print());

        verify(adopterService).findAdopterWithPets();
    }

    @Test
    void getAdopterWithoutPets()  throws Exception {

        List<Adopter> adopters = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            Adopter adopter = new Adopter();
            adopter.setId(i+1);
            adopters.add(adopter);
        }

        when(adopterService.findAdopterWithoutPets()).thenReturn(adopters);

        mockMvc.perform(
                        get("/adopter/without-pets")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print());

        verify(adopterService).findAdopterWithoutPets();
    }

    @Test
    void addAdopter() throws Exception {
        Adopter adopter = new Adopter();
        adopter.setId(1);

        for (int j = 0; j < 2; j++) {
            Pet pet = new Pet();
            pet.setId(j + 1);
            pet.setAdopter(adopter);
            adopter.getPets().add(pet);
        }

        String jsonAdopter = mapper.writeValueAsString(adopter);

        when(adopterService.addAdopterWithPets(any(Adopter.class))).thenReturn(adopter);

        mockMvc.perform(
                        post("/adopter")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonAdopter))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/adopter/1"))
                .andDo(print());

        verify(adopterService).addAdopterWithPets(any(Adopter.class));
    }

    @Test
    void updateAdopter() throws Exception {
        Adopter adopter = new Adopter();
        adopter.setId(1);
        adopter.setName("JOE");

        String jsonAdopter = mapper.writeValueAsString(adopter);

        when(adopterService.updateAdopter(any(Adopter.class))).thenReturn(adopter);

        mockMvc.perform(
                        put("/adopter")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonAdopter)
                )
                .andExpect(status().isOk() )
                .andDo(print());

        verify(adopterService).updateAdopter(any(Adopter.class));
    }

    @Test
    void deleteAdopter() throws Exception {
        when(adopterService.deleteAdopter(1)).thenReturn(true);

        mockMvc.perform(
                        delete("/adopter/1")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent())
                .andDo(print());

        verify(adopterService).deleteAdopter(1);
    }
}