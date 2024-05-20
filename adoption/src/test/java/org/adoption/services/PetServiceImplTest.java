package org.adoption.services;

import jakarta.transaction.Transactional;
import org.adoption.domain.Pet;
import org.adoption.repository.PetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
class PetServiceImplTest {
    @Autowired
    private PetRepository petRepository;

    @Test
    void getAllPets() {
        List<Pet> result = petRepository.findAll();
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    void addPet() {
        Pet newPet = new Pet();
        newPet.setName("Patita II");
        newPet.setDateAdoption(LocalDate.of(2024,5,20));
        petRepository.save(newPet);

        Optional<Pet> result = petRepository.findById(newPet.getId());

        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals(newPet.getName(), result.get().getName());
    }

    @Test
    void getPetById() {
        Optional<Pet> pet = petRepository.findById(2000);
        assertNotNull(pet);
        assertTrue(pet.isPresent());
        assertEquals("Warren T.", pet.get().getName());
    }

    @Test
    void getPetByName() {
        List<Pet> pets = petRepository.findByNameIgnoreCaseContaining("te");
        assertNotNull(pets);
        assertFalse(pets.isEmpty());
        assertEquals(2, pets.size());
    }

    @Test
    void getPetsWithAdopter() {
        List<Pet> pets = petRepository.findByAdopterIsNotNull();
        assertNotNull(pets);
        assertFalse(pets.isEmpty());
    }

    @Test
    void getPetsWithoutAdopter() {
        List<Pet> pets = petRepository.findByAdopterIsNull();
        assertNotNull(pets);
        assertFalse(pets.isEmpty());
    }

    @Test
    void updatePet() {
        Pet pet = new Pet();
        pet.setId(2000);
        pet.setName("Chow");
        pet.setDateAdoption(LocalDate.of(2024,5,20));
        petRepository.save(pet);

        Optional<Pet> result = petRepository.findById(pet.getId());

        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals(pet.getName(), result.get().getName());
    }

    @Test
    void deletePet() {
        petRepository.deleteById(2005);
        Optional<Pet> result = petRepository.findById(2005);
        assertFalse(result.isPresent());
    }
}