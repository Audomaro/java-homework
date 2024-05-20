package org.adoption.services;

import jakarta.transaction.Transactional;
import org.adoption.domain.Adopter;
import org.adoption.domain.Pet;
import org.adoption.repository.AdopterRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
class AdopterServiceImplTest {
    @Autowired
    private AdopterRepository adopterRepository;

    @Test
    void findAllAdopters() {
        List<Adopter> result = adopterRepository.findAll();
        assertNotNull(result);
    }

    @Test
    void findAdopterById() {
        Optional<Adopter> result = adopterRepository.findById(860);
        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals("Isiah Deckow", result.get().getName());
    }

    @Test
    void findAdopterByName() {
        List<Adopter> adopters = adopterRepository.findAdopterByName("ck");
        assertNotNull(adopters);
        assertFalse(adopters.isEmpty());
    }

    @Test
    void findAdopterWithoutPets() {
        List<Adopter> result = adopterRepository.findAdopterWithoutPets();
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    void findAdopterWithPets() {
        List<Adopter> result = adopterRepository.findAdopterWithPets();
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    void addAdopterWithPets() {
        Adopter newAdopter = new Adopter();

        List<Pet> pets = List.of(
                new Pet(),
                new Pet()
        );

        newAdopter.getPets().addAll(pets);
        adopterRepository.save(newAdopter);
    }

    @Test
    void updateAdopter() {
        Adopter adopter = new Adopter();
        adopter.setId(877);
        adopter.setName("Isiah Deckow");
        Adopter result = adopterRepository.save(adopter);
        assertNotNull(result);
        assertEquals("Isiah Deckow", result.getName());
    }

    @Test
    void deleteAdopter() {
        adopterRepository.deleteById(858);
        Optional<Adopter> result = adopterRepository.findById(858);
        assertTrue(result.isEmpty());
    }
}