package org.adoption.services;

import org.adoption.domain.Adopter;
import org.adoption.domain.Pet;
import org.adoption.repository.PetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetServiceImplTest {
    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private PetServiceImpl petService;

    @Test
    void findAllPets() {
        List<Pet> pets = List.of(
                new Pet(),
                new Pet(),
                new Pet()
        );

        when(petRepository.findAll()).thenReturn(pets);

        List<Pet> result = petService.findAllPets();

        assertNotNull(result);
        assertEquals(pets.size(),result.size());

        verify(petRepository).findAll();
    }

    @Test
    void addPet() {
        Pet pet = new Pet();
        pet.setId(1500);

        when(petRepository.save(any(Pet.class))).thenReturn(pet);

        Pet result = petService.addPet(pet);

        assertEquals(pet.getId(), result.getId());
        assertEquals(pet.getName(), result.getName());

        verify(petRepository).save(any(Pet.class));
    }

    @Test
    void getPetById() {
        Pet adopter = new Pet();
        adopter.setId(1);

        when(petRepository.findById(1)).thenReturn(Optional.of(adopter));

        Optional<Pet> result = petService.getPetById(adopter.getId());

        assertTrue(result.isPresent());
        assertEquals(adopter.getName(), result.get().getName());
        verify(petRepository).findById(adopter.getId());
    }

    @Test
    void findPetsByName() {
        String name = "ex";

        List<Pet> adopters = List.of(
                new Pet(),
                new Pet(),
                new Pet()
        );

        when(petRepository.findByNameIgnoreCaseContaining(name)).thenReturn(adopters);

        List<Pet> result = petService.findPetsByName(name);

        assertNotNull(result);
        assertEquals(adopters.size(),result.size());

        verify(petRepository).findByNameIgnoreCaseContaining(name);
    }

    @Test
    void findPetsWithAdopter() {

        List<Pet> pets = new ArrayList<>();

        for(int i = 0; i < 5; i++) {
            Pet pet = new Pet();

            pet.setAdopter(new Adopter());

            pets.add(pet);
        }

        when(petRepository.findByAdopterIsNotNull()).thenReturn(pets);

        List<Pet> result = petService.findPetsWithAdopter();

        assertNotNull(result);
        assertEquals(pets.size(),result.size());

        verify(petRepository).findByAdopterIsNotNull();
    }

    @Test
    void findPetsWithoutAdopter() {
        List<Pet> adopters = List.of(
                new Pet(),
                new Pet(),
                new Pet()
        );

        when(petRepository.findByAdopterIsNull()).thenReturn(adopters);

        List<Pet> result = petService.findPetsWithoutAdopter();

        assertNotNull(result);
        assertEquals(adopters.size(),result.size());
        assertNull(result.get(0).getAdopter());
        assertNull(result.get(1).getAdopter());
        assertNull(result.get(2).getAdopter());

        verify(petRepository).findByAdopterIsNull();
    }

    @Test
    void updatePet() {
        Pet pet = new Pet();
        pet.setId(570);
        pet.setName("Rock");

        when(petRepository.save(any(Pet.class))).thenReturn(pet);

        Pet result = petService.updatePet(pet);

        assertEquals(pet.getName(), result.getName());
        assertEquals(pet.getType(), result.getType());

        verify(petRepository).save(any(Pet.class));
    }

    @Test
    void deletePet() {
        int adopterId = 13;

        doNothing().when(petRepository).deleteById(adopterId);

        boolean result = petService.deletePet(adopterId);

        assertTrue(result);

        verify(petRepository).deleteById(adopterId);
    }
}