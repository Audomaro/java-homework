package org.adoption.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.adoption.domain.Pet;
import org.adoption.services.PetServiceImpl;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PetController.class)
class PetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PetServiceImpl petService;

    @Autowired
    private ObjectMapper mapper;


    @Test
    void getPets() throws Exception {
        List<Pet> pets = new ArrayList<>();

        for (int j = 0; j < 10; j++) {
            Pet pet = new Pet();
            pet.setId(j+1);
            pets.add(pet);
        }

        when(petService.findAllPets()).thenReturn(pets);

        mockMvc.perform(
                        get("/pet")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print());

        verify(petService).findAllPets();
    }

    @Test
    void getPetById() throws Exception {
        Pet pet = new Pet();
        pet.setId(1);

        when(petService.getPetById(1)).thenReturn(Optional.of(pet));

        mockMvc.perform(
                        get("/pet/1")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getPetsByName() throws Exception {
        List<Pet> pets = new ArrayList<>();

        for (int j = 0; j < 10; j++) {
            Pet pet = new Pet();
            pet.setId(j+1);
            pets.add(pet);
        }

        when(petService.findPetsByName("ces")).thenReturn(pets);

        mockMvc.perform(
                        get("/pet/by-name/ces")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print());

        verify(petService).findPetsByName("ces");
    }

    @Test
    void getPetsWithAdopter() throws Exception {
        List<Pet> pets = new ArrayList<>();

        for (int j = 0; j < 10; j++) {
            Pet pet = new Pet();
            pet.setId(j+1);
            pets.add(pet);
        }

        when(petService.findPetsWithAdopter()).thenReturn(pets);

        mockMvc.perform(
                        get("/pet/with-adopter")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print());

        verify(petService).findPetsWithAdopter();
    }

    @Test
    void getPetsWithoutAdopter() throws Exception {
        List<Pet> pets = new ArrayList<>();

        for (int j = 0; j < 10; j++) {
            Pet pet = new Pet();
            pet.setId(j+1);
            pets.add(pet);
        }

        when(petService.findPetsWithoutAdopter()).thenReturn(pets);

        mockMvc.perform(
                        get("/pet/without-adopter")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print());

        verify(petService).findPetsWithoutAdopter();
    }

    @Test
    void addPet() throws Exception {
        Pet pet = new Pet();
        pet.setId(100);

        String jsonPet = mapper.writeValueAsString(pet);

        when(petService.addPet(any(Pet.class))).thenReturn(pet);

        mockMvc.perform(
                        post("/pet")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonPet)
                )
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/pet/100"))
                .andDo(print());

        verify(petService).addPet(any(Pet.class));
    }

    @Test
    void updatePet() throws Exception {
        Pet pet = new Pet();
        pet.setId(100);
        pet.setName("Rex");

        String jsonPet = mapper.writeValueAsString(pet);

        when(petService.updatePet(any(Pet.class))).thenReturn(pet);

        mockMvc.perform(
                        put("/pet")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonPet)
                )
                .andExpect(status().isOk())
                .andDo(print());

        verify(petService).updatePet(any(Pet.class));
    }

    @Test
    void deletePet() throws Exception {
        when(petService.deletePet(1)).thenReturn(true);

        mockMvc.perform(
                        delete("/pet/1")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent())
                .andDo(print());

        verify(petService).deletePet(1);
    }

    @Test
    void handleEntityNotFoundException() {
    }
}